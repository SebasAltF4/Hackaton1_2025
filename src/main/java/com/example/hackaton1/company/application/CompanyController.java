package com.example.hackaton1.company.application;

import com.example.hackaton1.company.domain.CompanyService;
import com.example.hackaton1.company.dto.CompanyDto;
import com.example.hackaton1.company.dto.CreateCompanyRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/companies")
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    @PreAuthorize( "hasRole('ROLE_SPARKY_ADMIN')")
    public ResponseEntity<CompanyDto> createCompany(@Valid @RequestBody CreateCompanyRequest createCompanyRequest) {
        return ResponseEntity.ok(companyService.createCompany(createCompanyRequest));
    }

    @GetMapping
    @PreAuthorize( "hasRole('ROLE_SPARKY_ADMIN')")
    public ResponseEntity<List<CompanyDto>> getAllCompanies() {
        return ResponseEntity.ok(companyService.findAllCompanies());
    }

    @GetMapping("/{id}")
    @PreAuthorize( "hasAnyRole('ROLE_SPARKY_ADMIN', 'ROLE_COMPANY_ADMIN')")
    public ResponseEntity<CompanyDto> getCompanyById(@PathVariable Long id) {
        return ResponseEntity.ok(companyService.findCompanyById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize( "hasRole('ROLE_SPARKY_ADMIN')")
    public ResponseEntity<CompanyDto> updateCompany(@PathVariable Long id, @Valid @RequestBody CreateCompanyRequest request) {
        return ResponseEntity.ok(companyService.updateCompany(id, request));
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize( "hasRole('ROLE_SPARKY_ADMIN')")
    public ResponseEntity<Void> toggleCompanyStatus(@PathVariable Long id) {
        companyService.toggleCompanyStatus(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/consumption")
    @PreAuthorize( "hasAnyRole('ROLE_SPARKY_ADMIN', 'ROLE_COMPANY_ADMIN')")
    public ResponseEntity<Void> getConsumption(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }
}
