package com.agh.communicationplatform.email;

import org.springframework.stereotype.Component;

@Component
public class EmailContentGenerator {

    private static final String CREATED_ACCOUNT_TITLE = "[COMMUNICATION PLATFORM] - Created account";

    private EmailContentGenerator() {
    }

    public static EmailMessage generateCreateAccountEmailMessage(String firstName, String email, String activationLink) {
        return new EmailMessage(email, CREATED_ACCOUNT_TITLE, String.format(
                "Hi %s,\n\n" +
                        "Your account has been created. Please activate your account by clicking on the link below.\n\n" +
                        "Activation link: %s\n\n" +
                        "Your sincerely,\n" +
                        "Support Team",
                firstName, activationLink));
    }
}
