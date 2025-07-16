package com.graccasoft.bookspa.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TreatmentCategoryDto extends BaseDto{
    private String name;
    private IndustryDto industry;
}
