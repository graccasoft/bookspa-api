package com.graccasoft.redkokia.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;
    private String companyPhone;
    private String companyEmail;
    private String companyAddress;
    private String contactName;
    private String contactPhone;
    private String reference;
    private String openingTime;
    private String closingTime;
    private Boolean isActive = true;
    private Boolean isDeleted = false;

}
