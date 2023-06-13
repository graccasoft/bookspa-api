package com.graccasoft.redkokia.model.dto;

public record RegisterUserDto(
        String firstName,
        String lastName,
        String username,
        String password,
        TenantDto tenant
) {
}
