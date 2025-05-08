package com.example.hackaton1.user.domain;

import com.example.hackaton1.company.domain.Company;
import com.example.hackaton1.company.domain.CompanyService;
import com.example.hackaton1.company.infrastructure.CompanyRepository;
import com.example.hackaton1.exceptions.ResourceNotFoundException;
import com.example.hackaton1.user.dto.RegisterUsuarioRequest;
import com.example.hackaton1.user.dto.UsuarioDto;
import com.example.hackaton1.user.infrastructure.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public Usuario patchUsuario(Usuario usuario, Long usuario_id) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(usuario_id);

        if (optionalUsuario.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado con ID: " + usuario.getId());
        }

        Usuario existente = optionalUsuario.get();

        if (usuario.getNombreDeUsuario() != null) {
            existente.setNombreDeUsuario(usuario.getNombreDeUsuario());
        }

        if (usuario.getCorreo() != null) {
            existente.setCorreo(usuario.getCorreo());
        }

        if (usuario.getPassword() != null) {
            existente.setPassword(usuario.getPassword());
        }

        return usuarioRepository.save(existente);
    }


    public UsuarioDto createUsuarioForCompany(Long companyId, RegisterUsuarioRequest registerUsuarioRequest) {
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new ResourceNotFoundException("Empresa no encontrada con ID: " + companyId));
        if (usuarioRepository.findByCorreo(registerUsuarioRequest.getCorreo()).isPresent()) {
            throw new IllegalArgumentException("El correo ya existe");
        }
        Usuario newUsuario = modelMapper.map(registerUsuarioRequest, Usuario.class);
        newUsuario.setCompany(company);
        newUsuario.setCompany(company);
        newUsuario.setPassword(passwordEncoder.encode(registerUsuarioRequest.getPassword()));
        newUsuario.setEnable(true);
        newUsuario.setExpired(false);
        newUsuario.setLocked(false);
        newUsuario.setCredentialsExpired(false);

        Usuario usuarioToSaved = usuarioRepository.save(newUsuario);
        UsuarioDto usuarioDto = modelMapper.map(usuarioToSaved, UsuarioDto.class);
        usuarioDto.setCompanyId(company.getId());
        return usuarioDto;
    }

    public List<UsuarioDto> listAllUsuariosByCompany(Long companyId) {
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new ResourceNotFoundException("Empresa no encontrada con ID: " + companyId));
        return usuarioRepository.findAllByCompanyId(companyId).stream()
                .map(usuario -> {UsuarioDto usuarioDto = modelMapper.map(usuario, UsuarioDto.class);
                    usuarioDto.setCompanyId(company.getId());
                    return usuarioDto;})
                .collect(Collectors.toList());
    }

    public UsuarioDto findUsuarioById(Long companyId, Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));
        if (!usuario.getCompany().getId().equals(companyId)) {
            throw new ResourceNotFoundException("Usuario con ID: " + id  + " no pertenece a esta empresa");
        }
        UsuarioDto usuarioDto = modelMapper.map(usuario, UsuarioDto.class);
        usuarioDto.setCompanyId(companyId);
        return usuarioDto;
    }

    public Object updateUsuarioById(Long companyId, Long id, RegisterUsuarioRequest registerUsuarioRequest) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));
        if (!usuario.getCompany().getId().equals(companyId)) {
            throw new ResourceNotFoundException("Usuario con ID: " + id  + " no pertenece a esta empresa");
        }
        usuario.setNombreDeUsuario(registerUsuarioRequest.getNombreDeUsuario());
        usuario.setCorreo(registerUsuarioRequest.getCorreo());
        if (registerUsuarioRequest.getPassword() != null) {
            usuario.setPassword(passwordEncoder.encode(registerUsuarioRequest.getPassword()));
        }
        Usuario usuarioToSaved = usuarioRepository.save(usuario);
        UsuarioDto usuarioDto = modelMapper.map(usuarioToSaved, UsuarioDto.class);
        usuarioDto.setCompanyId(companyId);
        return usuarioDto;
    }

    public void deleteUsuarioById(Long companyId, Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));
        if (!usuario.getCompany().getId().equals(companyId)) {
            throw new ResourceNotFoundException("Usuario con ID: " + id  + " no pertenece a esta empresa");
        }
        usuarioRepository.delete(usuario);
    }
}
