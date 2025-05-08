package com.example.hackaton1.restriction.application;

import com.example.hackaton1.restriction.domain.RestrictionService;
import com.example.hackaton1.restriction.dto.CreateRestrictionRequest;
import com.example.hackaton1.restriction.dto.RestrictionDto;
import com.example.hackaton1.user.domain.Usuario;
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
@RequestMapping("/api/company/restrictions")
@PreAuthorize( "hasAnyRole('ROLE_COMPANY_ADMIN', 'ROLE_SPARKY_ADMIN')")
public class RestrictionController {
    private final RestrictionService restrictionService;

    @PostMapping
    public ResponseEntity<RestrictionDto> createRestrictionForCompany(@Valid @RequestBody CreateRestrictionRequest createRestrictionRequest,
                                                     @AuthenticationPrincipal Usuario usuario) {
        Long companyId = usuario.getCompany().getId();
        return ResponseEntity.status(HttpStatus.CREATED).body(restrictionService.createRestrictionForCompany(companyId, createRestrictionRequest));
    }

    @GetMapping
    public ResponseEntity<List<RestrictionDto>> getAllRestrictionsForCompany(@AuthenticationPrincipal Usuario usuario) {
        Long companyId = usuario.getCompany().getId();
        return ResponseEntity.ok(restrictionService.getAllRestrictionsForCompany(companyId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestrictionDto> updateRestrictionForCompany(@PathVariable Long id,
                                                            @Valid @RequestBody CreateRestrictionRequest createRestrictionRequest,
                                                            @AuthenticationPrincipal Usuario usuario) {
        Long companyId = usuario.getCompany().getId();
        return ResponseEntity.ok(restrictionService.updateRestrictionForCompany(companyId, id, createRestrictionRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestrictionForCompany(@PathVariable Long id, @AuthenticationPrincipal Usuario usuario){
        Long companyId = usuario.getCompany().getId();
        restrictionService.deleteRestrictionForCompany(companyId, id);
        return  ResponseEntity.noContent().build();
    }

}
