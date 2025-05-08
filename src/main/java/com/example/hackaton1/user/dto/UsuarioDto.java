package com.example.hackaton1.user.dto;

import com.example.hackaton1.user.domain.Role;
import lombok.Data;

@Data
public class UsuarioDto {
    private Long id;
    private String nombreDeUsuario;
    private String correo;
    private Role role;
    private Long companyId;
}
