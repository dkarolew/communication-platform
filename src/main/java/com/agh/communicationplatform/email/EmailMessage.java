package com.agh.communicationplatform.email;

public class EmailMessage {
    private final String recipient;
    private final String title;
    private final String content;

    public EmailMessage(String recipient, String title, String content) {
        this.recipient = recipient;
        this.title = title;
        this.content = content;
    }

    public static EmailMessage create(String recipient, String title, String content) {
        return new EmailMessage(recipient, title, content);
    }

    public String getRecipient() {
        return recipient;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
