package com.agh.communicationplatform.audit;

import lombok.Getter;
import javax.persistence.*;
import java.util.Date;

@Getter
@Entity
@Table(name = "AUDIT_EVENT")
public class AuditEvent {
    @Id
    @Column(name = "AUDIT_EVENT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auditEventId;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "MESSAGE")
    private String message;

    @Column(name = "EVENT_TIME")
    private Date eventTime;

    public AuditEvent() {}

    public AuditEvent(String type, String message, Date eventTime) {
        this.type = type;
        this.message = message;
        this.eventTime = eventTime;
    }
}
