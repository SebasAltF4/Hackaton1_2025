package com.example.hackaton1.auth.domain;

import com.example.hackaton1.user.infrastructure.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByNombreDeUsuario(username);
    }

    public UserDetailsService userDetailsService(){
        return this;
    }

}
