package com.graccasoft.redkokia.controller;

import com.graccasoft.redkokia.helper.CSVHelper;
import com.graccasoft.redkokia.model.dto.BookingDto;
import com.graccasoft.redkokia.model.dto.BookingReportDto;
import com.graccasoft.redkokia.model.dto.GenericResponse;
import com.graccasoft.redkokia.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("bookings")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public BookingDto saveBooking(@RequestBody BookingDto bookingDto){
        return bookingService.saveBooking(bookingDto);
    }

    @GetMapping
    public List<BookingDto> getBookings(@RequestParam Long tenantId){
        return bookingService.getBookings(tenantId);
    }

    @DeleteMapping("{bookingId}")
    public void cancelBooking(@PathVariable Long bookingId){
        bookingService.cancelBooking(bookingId);
    }

    @PatchMapping
    public GenericResponse updatePaymentMethod(@RequestBody BookingDto bookingDto){
        bookingService.updatePaymentMethod (bookingDto);
        return new GenericResponse(true,"Booking payment method successfully updated");
    }

    @GetMapping("report")
    public List<BookingReportDto> getBookingsReport(
            @RequestParam Long tenantId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate
    ){
        return bookingService.bookingsReport(tenantId, startDate, endDate);
    }
    @GetMapping("report-csv")
    public ResponseEntity<Resource> getFile(
            @RequestParam Long tenantId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate
    ) {
        String filename = "bookings.csv";
        List<BookingReportDto> bookings= bookingService.bookingsReport(tenantId, startDate, endDate);
        InputStreamResource file = new InputStreamResource(CSVHelper.bookingsToCsv(bookings));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }

    @GetMapping("report-pdf")
    public ResponseEntity<Resource> getPdfReport(
            @RequestParam Long tenantId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate
    ) {
        Resource pdfReport =  bookingService.bookingsReportPdf(tenantId, startDate, endDate);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=bookings-report.pdf")
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(pdfReport);
    }
}
