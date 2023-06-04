package com.graccasoft.redkokia.model.entity;

import com.graccasoft.redkokia.model.enums.BookingStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private Date createdAt;

    private Date bookingDate;
    private Integer duration;
    private BookingStatus status;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Treatment treatment;
}
