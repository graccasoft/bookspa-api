package com.graccasoft.redkokia.model.dto;

public record EmployeeDto(
        Long id,
        String firstName,
        String surname,
        String phoneNumber,
        Boolean isAvailable,
        TenantDto tenant

) {
}
