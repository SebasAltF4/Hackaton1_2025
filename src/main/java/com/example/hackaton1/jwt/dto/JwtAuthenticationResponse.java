package com.example.hackaton1.jwt.dto;

import lombok.*;

@Setter
@Getter
public class JwtAuthenticationResponse {
    private String token;

    public JwtAuthenticationResponse() {
    }

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }
}
