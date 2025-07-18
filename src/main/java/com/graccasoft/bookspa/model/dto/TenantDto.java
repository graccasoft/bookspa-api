package com.graccasoft.bookspa.model.dto;

public record TenantDto(
        Long id,
        String companyName,
        String companyPhone,
        String companyEmail,
        String companyAddress,
        String contactName,
        String contactPhone,
        String reference,
        String openingTime,
        String closingTime,
        Boolean isActive,
        IndustryDto industry
) {
}
