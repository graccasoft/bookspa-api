package com.graccasoft.redkokia.model.dto;

public record UserDto(
        String firstName,
        String lastName,
        String username,
        String password,
        TenantDto tenant
) {
}
