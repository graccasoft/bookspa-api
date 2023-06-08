package com.graccasoft.redkokia.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Treatment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private BigDecimal price;
    private Integer minimumDuration;//in minutes
    private Integer maximumDuration;

    @ManyToOne
    private Tenant tenant;
}
