package com.graccasoft.redkokia.model.dto;

public record ClientDto(
        Long id,
        String firstName,
        String surname,
        String phoneNumber,
        String email,
        String address,
        String city,
        String country,
        TenantDto tenant

) {
}
