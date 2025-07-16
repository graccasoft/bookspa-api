package com.graccasoft.bookspa.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
public class Treatment extends BaseEntity {

    @ManyToOne
    private TreatmentCategory category;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer minimumDuration;//in minutes
    private Integer maximumDuration;

    @ManyToOne
    private Tenant tenant;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Booking> bookingList;

    private Boolean isPromotion = false;
}
