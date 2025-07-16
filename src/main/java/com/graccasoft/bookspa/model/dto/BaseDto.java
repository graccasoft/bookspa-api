package com.graccasoft.bookspa.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class BaseDto {
    private Long id;
    private Instant createdOn;
    private Instant updatedOn;
    private Instant deletedOn;
}
