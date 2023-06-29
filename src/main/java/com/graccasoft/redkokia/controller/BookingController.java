package com.graccasoft.redkokia.controller;

import com.graccasoft.redkokia.model.dto.BookingDto;
import com.graccasoft.redkokia.model.dto.BookingReportDto;
import com.graccasoft.redkokia.model.dto.GenericResponse;
import com.graccasoft.redkokia.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
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
}
