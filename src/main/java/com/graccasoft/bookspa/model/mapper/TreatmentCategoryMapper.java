package com.graccasoft.bookspa.model.mapper;

import com.graccasoft.bookspa.model.dto.TreatmentCategoryDto;
import com.graccasoft.bookspa.model.entity.TreatmentCategory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TreatmentCategoryMapper extends EntityMapper<TreatmentCategory, TreatmentCategoryDto> {
}
