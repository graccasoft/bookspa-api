package com.graccasoft.redkokia.service;

import com.graccasoft.redkokia.model.dto.BookingReportDto;
import com.graccasoft.redkokia.model.entity.Tenant;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PdfGeneratorService {

    private final SpringTemplateEngine springTemplateEngine;

    @SneakyThrows
    public Resource getBookingsReportPdf(List<BookingReportDto> bookings, Tenant tenant){

        String pdfFile = "booking-report-" + UUID.randomUUID() + ".pdf";
        String reportHtml = parseClientDetailsTemplate(bookings , tenant );

        String tmpdir = Files.createTempDirectory("tmpDirPrefix").toFile().getAbsolutePath();

        String outputFile = tmpdir + "/" + pdfFile;
        OutputStream outputStream = new FileOutputStream(outputFile);
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(reportHtml);
        renderer.layout();
        renderer.createPDF(outputStream);
        outputStream.close();

        Path file = Paths.get(outputFile);
        Resource resource = new UrlResource(file.toUri());

        if (resource.exists() || resource.isReadable()) {
            return resource;
        }else{
            throw new RuntimeException("Reports pdf file not found");
        }

    }
    private String parseClientDetailsTemplate(List<BookingReportDto> bookings, Tenant tenant){
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();
        context.setVariable("bookings", bookings);
        context.setVariable("tenant", tenant);

        BigDecimal totalBank = bookings.stream().filter(booking -> booking.getPaymentMethod() != null && booking.getPaymentMethod().equals("BANK"))
                .map(BookingReportDto::getTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        context.setVariable("totalBank", totalBank);

        BigDecimal totalCash= bookings.stream().filter(booking -> booking.getPaymentMethod() != null && booking.getPaymentMethod().equals("CASH"))
                .map(BookingReportDto::getTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        context.setVariable("totalCash", totalCash);

        return templateEngine.process("bookings-report", context);
    }


}
