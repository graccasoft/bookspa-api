package com.graccasoft.redkokia.model.dto;

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
        String closingTime
) {
}
