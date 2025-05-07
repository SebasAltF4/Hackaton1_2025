package com.example.hackaton1.user.infrastructure;

import com.example.hackaton1.user.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByNombreDeUsuario(String NombreDeUsuario);
}
