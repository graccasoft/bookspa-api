package com.graccasoft.redkokia.controller;

import com.graccasoft.redkokia.model.dto.JwtDto;
import com.graccasoft.redkokia.model.entity.User;
import com.graccasoft.redkokia.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
@Slf4j
public class Token {
    private final JwtEncoder encoder;
    private final UserService userService;

    @PostMapping
    public JwtDto token(Authentication authentication) {
        Instant now = Instant.now();
        long expiry = 36000L;
        // @formatter:off
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();
        // @formatter:on


        String tokenValue = this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return userService.getUserJwt(authentication.getName(),  tokenValue);
    }
}
