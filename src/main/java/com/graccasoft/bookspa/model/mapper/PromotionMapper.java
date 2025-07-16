package com.graccasoft.bookspa.model.mapper;

import com.graccasoft.bookspa.model.dto.PromotionDto;
import com.graccasoft.bookspa.model.entity.Promotion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PromotionMapper extends EntityMapper<Promotion, PromotionDto> {
}
