package com.graccasoft.redkokia.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String surname;
    private String phoneNumber;
    private Boolean isAvailable;

    @ManyToOne
    private Tenant tenant;
}
