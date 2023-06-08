package com.graccasoft.redkokia.controller;

import com.graccasoft.redkokia.model.dto.BookingDto;
import com.graccasoft.redkokia.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
}
