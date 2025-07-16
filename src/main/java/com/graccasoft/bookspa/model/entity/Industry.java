package com.graccasoft.bookspa.model.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Industry extends BaseEntity {
    private String name;
}
