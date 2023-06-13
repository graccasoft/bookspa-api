package com.graccasoft.redkokia.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtDto {
    private Long id;
    private String firstName;
    private String lastName;
    private TenantDto tenant;
    private String token;
}
