package com.agh.communicationplatform.email;

import com.agh.communicationplatform.audit.AuditEventService;
import com.agh.communicationplatform.audit.AuditEventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class SmtpEmailService implements EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmtpEmailService.class);
    private static final String SENDER = "communication.platformm@gmail.com";
    private static final String PASSWORD = "";

    private final AuditEventService auditEventService;

    public SmtpEmailService(AuditEventService auditEventService) {
        this.auditEventService = auditEventService;
    }

    @Override
    public void sendEmail(EmailMessage emailMessage) {
        String recipient = emailMessage.getRecipient();

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(SENDER, PASSWORD);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SENDER));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(emailMessage.getTitle());
            message.setText(emailMessage.getContent());

            Transport.send(message);
            LOGGER.info("Email sent successfully");
            auditEventService.logAuditEvent(AuditEventType.SEND_EMAIL_SUCCESS, "Email sent to " + recipient);
        } catch (MessagingException e) {
            LOGGER.error(String.format("Email sent error: %s", e));
            auditEventService.logAuditEvent(AuditEventType.SEND_EMAIL_ERROR, "Error during sending email to " + recipient);
        }
    }
}
