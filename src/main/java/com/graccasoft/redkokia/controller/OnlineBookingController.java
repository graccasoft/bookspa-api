package com.graccasoft.redkokia.controller;

import com.graccasoft.redkokia.model.dto.*;
import com.graccasoft.redkokia.service.BookingService;
import com.graccasoft.redkokia.service.EmployeeService;
import com.graccasoft.redkokia.service.TenantService;
import com.graccasoft.redkokia.service.TreatmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("online-booking")
public class OnlineBookingController {

    private final BookingService bookingService;
    private final TreatmentService treatmentService;
    private final TenantService tenantService;
    private final EmployeeService employeeService;

    @GetMapping("/available-slots")
    public List<TimeSlot> getAvailableTimeSlots(
            @RequestParam Long tenantId,
            @RequestParam Long employeeId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
            @RequestParam Integer duration
    ){
        return bookingService.getAvailableTimeSlots(date,tenantId,employeeId, duration);
    }

    @PostMapping
    public BookingDto saveBooking(@RequestBody BookingDto bookingDto){
        return bookingService.saveBooking(bookingDto);
    }


    @GetMapping("/treatments")
    public List<TreatmentDto> getTreatments(@RequestParam Long tenantId){
        return treatmentService.getTreatments(tenantId);
    }

    @GetMapping("/categorised-treatments")
    public List<CategorisedTreatmentsDto> getCategorisedTreatments(@RequestParam Long tenantId){
        return treatmentService.getCategorisedTreatments(tenantId);
    }

    @GetMapping("/tenants")
    public TenantDto getTenantByReference(@RequestParam String reference){
        return tenantService.findByReference(reference);
    }

    @GetMapping("/employees")
    public List<EmployeeDto> getEmployees(@RequestParam Long tenantId){
        return employeeService.getEmployees(tenantId);
    }

    @GetMapping("/bookings/{reference}")
    public BookingDto findBooking(@PathVariable String reference){
        return bookingService.getBooking(reference);
    }

    @DeleteMapping("/bookings/{reference}")
    public GenericResponse cancelBooking(@PathVariable String reference){
        bookingService.cancelBooking (reference);
        return new GenericResponse(true, "Booking has been successfully cancelled");
    }
}
