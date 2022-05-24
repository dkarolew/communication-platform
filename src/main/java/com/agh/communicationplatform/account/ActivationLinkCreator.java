package com.agh.communicationplatform.account;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ActivationLinkCreator {

    private static final String HTTP = "http://";
    private static final String ACCOUNT_ACTIVATION_ENDPOINT = "/api/v1/account/activate?token=";

    @Value("${server.address}")
    private String host;

    @Value("${server.port}")
    private String port;

    public String create(UUID token) {
        return HTTP + host + ":" + port + ACCOUNT_ACTIVATION_ENDPOINT + token;
    }
}
