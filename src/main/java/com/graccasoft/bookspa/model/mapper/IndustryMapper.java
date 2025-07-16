package com.graccasoft.bookspa.model.mapper;

import com.graccasoft.bookspa.model.dto.IndustryDto;
import com.graccasoft.bookspa.model.entity.Industry;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IndustryMapper extends EntityMapper<Industry, IndustryDto> {
}
