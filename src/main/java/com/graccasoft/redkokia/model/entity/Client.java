package com.graccasoft.redkokia.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String surname;
    private String phoneNumber;
    private String email;
    private String address;
    private String city;
    private String country;

    @ManyToOne
    private Tenant tenant;
}
