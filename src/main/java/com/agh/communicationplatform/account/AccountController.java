package com.agh.communicationplatform.account;

import com.agh.communicationplatform.security.SignInDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/account")
@CrossOrigin(origins = "http://localhost:3000")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    List<Account> getAccounts() {
        return accountService.getAccounts();
    }

    @GetMapping("/activate")
    void activateAccount(@RequestParam UUID token) {
        accountService.activateAccount(token);
    }
}
