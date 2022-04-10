package com.agh.communicationplatform.email;

public class SmtpEmailService implements EmailService {

    @Override
    public void sendEmail() {
        System.out.println("Email sent");
    }
}
