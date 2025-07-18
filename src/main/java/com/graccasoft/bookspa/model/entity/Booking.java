package com.graccasoft.bookspa.model.entity;

import com.graccasoft.bookspa.model.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private Date createdAt;

    private Date bookingDate;
    private Integer duration;
    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @ManyToOne
    private Client client;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "booking_treatment",
            joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "treatment_id")
    )
    private List<Treatment> treatments;

    private String clientNotes;
    private String reference;

    @ManyToOne
    private Employee employee;

    private String paymentMethod;
}
