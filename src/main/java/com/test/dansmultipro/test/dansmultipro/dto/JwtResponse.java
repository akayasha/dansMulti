package com.test.dansmultipro.test.dansmultipro.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class JwtResponse implements Serializable {
    private String token;
    private String refreshToken;
    private String type = "Bearer";
    private String username;

    public JwtResponse(
            String accessToken,
            String refreshToken,
            String username) {
        this.username = username;
        this.token = accessToken;
        this.refreshToken = refreshToken;
    }
}
