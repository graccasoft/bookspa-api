package com.graccasoft.bookspa.model.dto;

import com.graccasoft.bookspa.model.enums.BookingStatus;

import java.util.Date;
import java.util.List;

public record BookingDto(
        Long id,
        Date createdAt,
        Date bookingDate,
        Integer duration,
        BookingStatus status,
        ClientDto client,
        List<TreatmentDto> treatments,
        String clientNotes,
        String reference,
        EmployeeDto employee,
        String paymentMethod
) {
}
