package com.graccasoft.redkokia.model.dto;

import com.graccasoft.redkokia.model.enums.BookingStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
public class BookingReportDto{
    private Long id;
    private Date createdAt;
    private Date bookingDate;
    private Integer duration;
    private BookingStatus status;
    private String client;
    private String treatments;
    private String reference;
    private String employee;
    private String paymentMethod;
    private BigDecimal totalAmount;
}
