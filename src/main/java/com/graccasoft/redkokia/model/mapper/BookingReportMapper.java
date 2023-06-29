package com.graccasoft.redkokia.model.mapper;

import com.graccasoft.redkokia.model.dto.BookingReportDto;
import com.graccasoft.redkokia.model.entity.Booking;
import com.graccasoft.redkokia.model.entity.Treatment;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class BookingReportMapper implements Function<Booking, BookingReportDto> {
    @Override
    public BookingReportDto apply(Booking booking) {
        BigDecimal total = BigDecimal.valueOf(booking.getTreatments().stream().mapToDouble(treatment->treatment.getPrice().doubleValue()).sum());
        String treatments = booking.getTreatments().stream().map(Treatment::getName).collect(Collectors.joining());

        return BookingReportDto.builder()
                .id(booking.getId())
                .bookingDate(booking.getBookingDate())
                .reference(booking.getReference())
                .status(booking.getStatus())
                .treatments(treatments)
                .duration(booking.getDuration())
                .employee( booking.getEmployee()  !=null ? booking.getEmployee().getFirstName() : "")
                .createdAt(booking.getCreatedAt())
                .totalAmount(total)
                .paymentMethod(booking.getPaymentMethod())
                .client(booking.getClient().getFirstName() + booking.getClient().getSurname())
                .build();
    }
}
