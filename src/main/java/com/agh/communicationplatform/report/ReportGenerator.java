package com.agh.communicationplatform.report;

import com.agh.communicationplatform.account.Account;
import com.agh.communicationplatform.account.AccountService;
import com.agh.communicationplatform.audit.AuditEventService;
import com.agh.communicationplatform.audit.AuditEventType;
import com.agh.communicationplatform.device.Device;
import com.agh.communicationplatform.device.DeviceService;
import org.springframework.stereotype.Component;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@Component
public class ReportGenerator {

    private final AuditEventService auditEventService;
    private final DeviceService deviceService;
    private final AccountService accountService;

    public ReportGenerator(AuditEventService auditEventService, DeviceService deviceService, AccountService accountService) {
        this.auditEventService = auditEventService;
        this.deviceService = deviceService;
        this.accountService = accountService;
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);

        Paragraph paragraph = new Paragraph("======COMMUNICATION PLATFORM REPORT=====\n\n", fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(12);

        List<Device> devices = deviceService.getAllDevices();
        List<Account> accounts = accountService.getAccounts();
        Map<String, List<Device>> devicesPerState = devices.stream()
                .collect(groupingBy(Device::getState));

        StringBuilder builder = new StringBuilder();
        builder.append("Registered accounts: ").append(accounts.size()).append("\n\n");
        builder.append("Devices states:\n\n");
        devicesPerState.forEach((state, devicePerState) ->
                builder.append(state).append(": ").append(devicePerState.size()).append("\n"));
        builder.append("\n");
        builder.append("Registered devices:\n\n");
        devices.forEach(d -> {
            builder.append("Name: ").append(d.getName()).append("\n");
            builder.append("Model: ").append(d.getModel()).append("\n");
            builder.append("State: ").append(d.getState()).append("\n");
            builder.append("Measurement frequency: ").append(d.getMeasurementFrequency()).append("\n\n");
        });

        Paragraph paragraph2 = new Paragraph(builder.toString(), fontParagraph);
        paragraph2.setAlignment(Paragraph.ALIGN_LEFT);

        document.add(paragraph);
        document.add(paragraph2);
        document.close();

        auditEventService.logAuditEvent(AuditEventType.REPORT_GENERATED, "Report on registered devices has been generated");
    }
}
