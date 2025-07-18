package com.graccasoft.bookspa.model.dto;

import java.math.BigDecimal;

public record TreatmentDto(
        Long id,
        String name,
        String description,
        BigDecimal price,
        Integer minimumDuration,
        Integer maximumDuration,
        TenantDto tenant,
        TreatmentCategoryDto category,
        Boolean isPromotion

) {
}
