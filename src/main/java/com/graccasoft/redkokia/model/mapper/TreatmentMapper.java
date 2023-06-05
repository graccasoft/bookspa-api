package com.graccasoft.redkokia.model.mapper;

import com.graccasoft.redkokia.model.dto.TreatmentDto;
import com.graccasoft.redkokia.model.entity.Treatment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TreatmentMapper extends EntityMapper<Treatment, TreatmentDto> {
}
