package com.example.hackaton1.application;


import com.example.hackaton1.Entities.Usuario.Usuario;
import com.example.hackaton1.Entities.Usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PatchMapping("/{usuario_id}")
    ResponseEntity<Usuario> patchUsuario(@RequestBody Usuario usuario, @PathVariable Long usuario_id) {
        return ResponseEntity.ok(usuarioService.patchUsuario(usuario, usuario_id));
    }
}
