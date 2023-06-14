package com.graccasoft.redkokia.model.mapper;

import com.graccasoft.redkokia.model.dto.TreatmentCategoryDto;
import com.graccasoft.redkokia.model.entity.TreatmentCategory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TreatmentCategoryMapper extends EntityMapper<TreatmentCategory, TreatmentCategoryDto> {
}
