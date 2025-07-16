package com.graccasoft.bookspa.model.dto;

import java.util.List;

public record CategorisedTreatmentsDto(
        TreatmentCategoryDto category,
        List<TreatmentDto> treatments
) {
}
