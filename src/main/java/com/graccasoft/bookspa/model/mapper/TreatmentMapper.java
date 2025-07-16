package com.graccasoft.bookspa.model.mapper;

import com.graccasoft.bookspa.model.dto.TreatmentDto;
import com.graccasoft.bookspa.model.entity.Treatment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TreatmentMapper extends EntityMapper<Treatment, TreatmentDto> {

}
