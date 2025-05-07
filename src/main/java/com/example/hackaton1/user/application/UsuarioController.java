package com.example.hackaton1.user.application;

import com.example.hackaton1.user.domain.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;
}
