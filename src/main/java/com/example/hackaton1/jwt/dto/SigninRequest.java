package com.example.hackaton1.jwt.dto;

import lombok.Data;

@Data
public class SigninRequest {
    private String nombreDeUsuario;
    private String password;
}
