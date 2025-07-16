package com.graccasoft.bookspa.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Employee extends BaseEntity {

    private String firstName;
    private String surname;
    private String phoneNumber;
    private Boolean isAvailable;

    @ManyToOne
    private Tenant tenant;
    private Boolean isDeleted = false;
}
