package com.agh.communicationplatform.audit;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/auditEvent")
@CrossOrigin
public class AuditEventController {

    private final AuditEventService auditEventService;

    public AuditEventController(AuditEventService auditEventService) {
        this.auditEventService = auditEventService;
    }

    @GetMapping
    List<AuditEvent> getAuditEvents() {
        return auditEventService.getAuditEvents();
    }
}
