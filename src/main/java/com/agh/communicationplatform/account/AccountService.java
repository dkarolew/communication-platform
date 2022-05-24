package com.agh.communicationplatform.account;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
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
    }
}
