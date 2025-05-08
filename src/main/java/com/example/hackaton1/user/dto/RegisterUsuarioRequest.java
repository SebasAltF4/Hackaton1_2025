package com.example.hackaton1.user.dto;

import com.example.hackaton1.user.domain.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterUsuarioRequest {
    @NotNull
    @Size(min = 4, max = 50)
    private String nombreDeUsuario;

    @NotNull
    @Email
    private String correo;

    @NotNull
    @Size(min = 6)
    private String password;

    @NotNull
    private Role role;
}
