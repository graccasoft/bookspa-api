package com.graccasoft.redkokia.model.dto;

import com.graccasoft.redkokia.model.enums.BookingStatus;

import java.util.Date;

public record BookingDto(
        Long id,
        Date createdAt,
        Date bookingDate,
        Integer duration,
        BookingStatus status,
        ClientDto client,
        TreatmentDto treatment
) {
}
