package com.agh.communicationplatform.report;

import com.lowagie.text.DocumentException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


@RestController
@RequestMapping(path = "api/v1/report")
@CrossOrigin(origins = "http://localhost:3000")
public class ReportController {

    private final ReportGenerator reportGenerator;

    public ReportController(ReportGenerator reportGenerator) {
        this.reportGenerator = reportGenerator;
    }

    @GetMapping("/export")
    public void generatePDF(HttpServletResponse response) throws IOException, DocumentException {
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String startDate = dateFormatter.format(new Date());
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DAY_OF_MONTH, 7);
        String finishDate = dateFormatter.format(c.getTime());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=report_" + startDate + "_" + finishDate + ".pdf";
        response.setContentType("application/pdf");
        response.setHeader(headerKey, headerValue);

        reportGenerator.export(response);
    }
}
