package com.graccasoft.bookspa.model.dto;

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
    private String role;
    private TenantDto tenant;
    private String token;
}
