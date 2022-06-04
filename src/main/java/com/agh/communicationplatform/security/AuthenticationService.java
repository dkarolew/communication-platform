package com.agh.communicationplatform.security;

import com.agh.communicationplatform.account.*;
import com.agh.communicationplatform.audit.AuditEventService;
import com.agh.communicationplatform.audit.AuditEventType;
import com.agh.communicationplatform.email.EmailContentGenerator;
import com.agh.communicationplatform.email.EmailMessage;
import com.agh.communicationplatform.email.EmailService;
import com.agh.communicationplatform.user.User;
import com.agh.communicationplatform.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthenticationService {

    private final static Logger LOGGER = LoggerFactory.getLogger(AuthenticationService.class);

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final EmailService emailService;
    private final ActivationLinkCreator activationLinkCreator;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuditEventService auditEventService;

    public AuthenticationService(AuthenticationManager authenticationManager, UserRepository userRepository, AccountRepository accountRepository, EmailService emailService, ActivationLinkCreator activationLinkCreator, PasswordEncoder passwordEncoder, JwtUtils jwtUtils, AuditEventService auditEventService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.emailService = emailService;
        this.activationLinkCreator = activationLinkCreator;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.auditEventService = auditEventService;
    }

    public ResponseEntity<?> register(SignUpDto signUpDto) {
        if (userRepository.existsByEmail(signUpDto.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("User already exists");
        }

        User user = new User(signUpDto.getFirstName(), signUpDto.getLastName(), String.valueOf(RoleType.USER),
                signUpDto.getEmail(), passwordEncoder.encode(signUpDto.getPassword()));
        userRepository.save(user);
        LOGGER.info("User registered successfully");

        Account account = new Account(user.getUserId(), String.valueOf(AccountState.NOT_CONFIRMED),
                UUID.randomUUID());
        accountRepository.save(account);
        LOGGER.info("Account created successfully");
        
        auditEventService.logAuditEvent(AuditEventType.NEW_USER_REGISTERED, "User with new account registered");

        EmailMessage emailMessage = EmailContentGenerator.generateCreateAccountEmailMessage(signUpDto.getFirstName(),
                signUpDto.getEmail(), activationLinkCreator.create(account.getActivationToken()));
        emailService.sendEmail(emailMessage);

        return ResponseEntity.ok("User registered successfully");
    }

    public JwtDto login(SignInDto signInDto) {
        Account account = accountRepository.getAccountByUserEmail(signInDto.getEmail())
                .orElseThrow(() -> new RuntimeException("Account not exist"));
        User user = userRepository.findByEmail(signInDto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not exist"));

        if (String.valueOf(AccountState.NOT_CONFIRMED).equals(account.getState())) {
            throw new RuntimeException("Account is not confirmed");
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInDto.getEmail(), signInDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String role = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse(String.valueOf(RoleType.USER));

        return new JwtDto(user.getUserId(), user.getFirstName(), user.getLastName(), userDetails.getEmail(), role, jwt);
    }
}
