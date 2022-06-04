package com.agh.communicationplatform.account;

import com.agh.communicationplatform.audit.AuditEventService;
import com.agh.communicationplatform.audit.AuditEventType;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final AuditEventService auditEventService;

    public AccountService(AccountRepository accountRepository, AuditEventService auditEventService) {
        this.accountRepository = accountRepository;
        this.auditEventService = auditEventService;
    }

    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    @Transactional
    public void activateAccount(UUID activationToken) {
        final boolean isAccountExists = accountRepository.existsAccountByActivationToken(activationToken);

        if (isAccountExists) {
            accountRepository.activateAccount(activationToken);
        }

        auditEventService.logAuditEvent(AuditEventType.ACCOUNT_ACTIVATED, "Account successfully activated");
    }
}
