package com.agh.communicationplatform.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticationController {

    private final static Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody SignUpDto signUpDto) {
        LOGGER.info("User with {} email is registering", signUpDto.getEmail());
        return authenticationService.register(signUpDto);
    }

    @PostMapping("/login")
    public JwtDto login(@RequestBody SignInDto signInDto) {
        LOGGER.info("User with {} email is logging", signInDto.getEmail());
        return authenticationService.login(signInDto);
    }
}
