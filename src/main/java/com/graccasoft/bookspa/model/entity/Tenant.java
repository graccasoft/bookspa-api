package com.graccasoft.bookspa.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Tenant extends BaseEntity {

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

    @ManyToOne
    private Industry industry;
}
