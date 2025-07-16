package com.graccasoft.bookspa.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TreatmentCategory extends BaseEntity {

    private String name;
    @ManyToOne
    private Industry industry;
}
