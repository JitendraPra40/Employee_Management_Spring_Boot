package com.antsskill.employee_management.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponseDto {

    private String accessToken;
    private String refreshToken;
    private String tokenType = "Bearer";

    public JwtResponseDto(String accessToken, String token) {
        this.accessToken = accessToken;
        this.refreshToken = token;
    }

    // constructor + getters
}