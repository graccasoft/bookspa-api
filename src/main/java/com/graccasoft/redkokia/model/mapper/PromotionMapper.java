package com.graccasoft.redkokia.model.mapper;

import com.graccasoft.redkokia.model.dto.PromotionDto;
import com.graccasoft.redkokia.model.entity.Promotion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PromotionMapper extends EntityMapper<Promotion, PromotionDto> {
}
