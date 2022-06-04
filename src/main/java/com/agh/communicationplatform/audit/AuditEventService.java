package com.agh.communicationplatform.audit;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AuditEventService {

    private final AuditEventRepository auditEventRepository;

    public AuditEventService(AuditEventRepository auditEventRepository) {
        this.auditEventRepository = auditEventRepository;
    }

    public List<AuditEvent> getAuditEvents() {
        return auditEventRepository.findAll();
    }

   public void logAuditEvent(AuditEventType type, String message) {
        AuditEvent auditEvent = new AuditEvent(String.valueOf(type), message, new Date());
        auditEventRepository.save(auditEvent);
    }
}
