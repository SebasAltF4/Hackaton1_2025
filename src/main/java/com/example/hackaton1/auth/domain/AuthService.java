package com.example.hackaton1.auth.domain;

import com.example.hackaton1.jwt.domain.JwtService;
import com.example.hackaton1.jwt.dto.JwtAuthenticationResponse;
import com.example.hackaton1.jwt.dto.SigninRequest;
import com.example.hackaton1.user.domain.Usuario;
import com.example.hackaton1.user.infrastructure.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationResponse signup(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        usuarioRepository.save(usuario);
        var jwt = jwtService.generateToken(usuario);

        JwtAuthenticationResponse response = new JwtAuthenticationResponse();
        response.setToken(jwt);

        return response;
    }

    public JwtAuthenticationResponse signing(SigninRequest request) throws IllegalArgumentException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getNombreDeUsuario(), request.getPassword()));
        var user = usuarioRepository.findByNombreDeUsuario(request.getNombreDeUsuario());
        var jwt = jwtService.generateToken(user);

        JwtAuthenticationResponse response = new JwtAuthenticationResponse();
        response.setToken(jwt);

        return response;
    }
}
