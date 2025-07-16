package com.graccasoft.bookspa.model.dto;

public record EmployeeDto(
        Long id,
        String firstName,
        String surname,
        String phoneNumber,
        Boolean isAvailable,
        TenantDto tenant

) {
}
