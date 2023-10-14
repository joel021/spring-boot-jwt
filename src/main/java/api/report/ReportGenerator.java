package api.report;


import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.*;

public class ReportGenerator {

    private JasperReport compiledReport;


    public ReportGenerator() throws JRException {

        compiledReport = (JasperReport) JRLoader.loadObjectFromFile(ReportGenerator.class.getResource("/payments_report.jasper").getFile());

    }


    public JasperPrint fillReport() throws JRException {
/*
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("period", period);
        parameters.put("generationDate", generationDate);
        parameters.put("valueTotal", String.format("%.2f", valueTotal));
        parameters.put("netValueTotal", String.format("%.2f", netValueTotal));
        parameters.put("inssTotal", String.format("%.2f", inssTotal));
        parameters.put("irrfTotal",  String.format("%.2f", irrfTotal));
        parameters.put("otherPaymentFontTotal", String.format("%.2f", otherPaymentFontTotal));
        parameters.put("paymentsQuantity", String.valueOf(paymentsReports.size()));
        parameters.put("paymentsProfessionalsQuantity", String.valueOf(paymentsProfessionals.size()));
        parameters.put("collectionPaymentParam", new JRBeanCollectionDataSource(paymentsReports));

        return JasperFillManager.fillReport(compiledReport, parameters, new JREmptyDataSource());

 */
        return null;
    }

    public ByteArrayInputStream getPdfReport() throws JRException {

        return new ByteArrayInputStream(JasperExportManager.exportReportToPdf(fillReport()));
    }

    public String saveInResources(JasperPrint print, String outputFileName) throws JRException, IOException {

        Path outputFile = new File(outputFileName).toPath();
        OutputStream outputStream = Files.newOutputStream(outputFile);
        JasperExportManager.exportReportToPdfStream(print, outputStream);
        return outputFile.toString();
    }
}
