package com.graccasoft.redkokia.controller;

import com.graccasoft.redkokia.model.dto.BookingDto;
import com.graccasoft.redkokia.model.dto.TimeSlot;
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

    @GetMapping("/available-slots")
    public List<TimeSlot> getAvailableTimeSlots(
            @RequestParam Long tenantId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
            @RequestParam Integer duration
    ){
        return bookingService.getAvailableTimeSlots(date,tenantId, duration);
    }
}
