package com.agh.communicationplatform.report;

import com.agh.communicationplatform.audit.AuditEventService;
import com.agh.communicationplatform.audit.AuditEventType;
import org.springframework.stereotype.Component;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ReportGenerator {

    private final AuditEventService auditEventService;

    public ReportGenerator(AuditEventService auditEventService) {
        this.auditEventService = auditEventService;
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

        StringBuilder builder = new StringBuilder();

        builder.append("Registered devices:");

        Paragraph paragraph2 = new Paragraph(builder.toString(), fontParagraph);
        paragraph2.setAlignment(Paragraph.ALIGN_LEFT);

        document.add(paragraph);
        document.add(paragraph2);
        document.close();

        auditEventService.logAuditEvent(AuditEventType.REPORT_GENERATED, "Report on registered devices has been generated");
    }
}
