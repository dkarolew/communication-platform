package com.agh.communicationplatform.account;

import lombok.Getter;
import javax.persistence.*;
import java.util.UUID;

@Getter
@Entity
@Table(name = "ACCOUNT")
public class Account {
    @Id
    @Column(name = "ACCOUNT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "STATE")
    private String state;

    @Column(name = "ACTIVATION_TOKEN")
    private UUID activationToken;

    public Account() {}

    public Account(Long userId, String state, UUID activationToken) {
        this.userId = userId;
        this.state = state;
        this.activationToken = activationToken;
    }
}
