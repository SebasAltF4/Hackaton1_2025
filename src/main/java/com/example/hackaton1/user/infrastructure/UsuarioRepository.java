package com.example.hackaton1.user.infrastructure;

import com.example.hackaton1.user.domain.Usuario;
import com.example.hackaton1.user.dto.UsuarioDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByNombreDeUsuario(String NombreDeUsuario);
    Optional<Usuario> findByCorreo(String correo);
    List<Usuario> findAllByCompanyId(Long companyId);
}
