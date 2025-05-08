package com.example.hackaton1.user.application;

import com.example.hackaton1.company.domain.Company;
import com.example.hackaton1.user.domain.Usuario;
import com.example.hackaton1.user.domain.UsuarioService;
import com.example.hackaton1.user.dto.RegisterUsuarioRequest;
import com.example.hackaton1.user.dto.UsuarioDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize( "hasRole('ROLE_COMPANY_ADMIN')")
@RequestMapping("/api/company/users")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDto> createUsuarioForCompany(@Valid @RequestBody RegisterUsuarioRequest registerUsuarioRequest,
                                                              @AuthenticationPrincipal Usuario usuario) {
        Long companyId = usuario.getCompany().getId();
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.createUsuarioForCompany(companyId, registerUsuarioRequest));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> getAllUsuariosByCompany(@AuthenticationPrincipal Usuario usuario) {
        Long companyId = usuario.getCompany().getId();
        return ResponseEntity.ok(usuarioService.listAllUsuariosByCompany(companyId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> getUsuarioByCompany(@PathVariable Long id, @AuthenticationPrincipal Usuario usuario) {
        Long companyId = usuario.getCompany().getId();
        return ResponseEntity.ok(usuarioService.findUsuarioById(companyId, id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDto> updateUsuarioByCompany(@PathVariable Long id,
                                                             @Valid @RequestBody RegisterUsuarioRequest registerUsuarioRequest,
                                                             @AuthenticationPrincipal Usuario usuario){
        Long companyId = usuario.getCompany().getId();
        return ResponseEntity.ok(usuarioService.updateUsuarioById(companyId, id, registerUsuarioRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuarioByCompany(@PathVariable Long id, @AuthenticationPrincipal Usuario usuario){
        Long companyId = usuario.getCompany().getId();
        usuarioService.deleteUsuarioById(companyId, id);
        return  ResponseEntity.noContent().build();
    }

}
