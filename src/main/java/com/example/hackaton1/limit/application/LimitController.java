package com.example.hackaton1.limit.application;

import com.example.hackaton1.limit.domain.LImitService;
import com.example.hackaton1.limit.dto.CreateLimitRequest;
import com.example.hackaton1.limit.dto.LimitDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize( "hasRole('ROLE_COMPANY_ADMIN')")
@RequestMapping("/api/company/users/{id}/limits")
public class LimitController {

    private final LImitService limitService;

    @PostMapping
    public ResponseEntity<LimitDto> createLimitForUser(
            @PathVariable Long id,
            @Valid @RequestBody CreateLimitRequest request) {
        LimitDto dto = limitService.createLimitForUser(id, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping
    public ResponseEntity<List<LimitDto>> listRestrictionForUser(@PathVariable Long id) {
        return ResponseEntity.ok(limitService.listRestrictionForUser(id));
    }

    @PutMapping("/{limitId}")
    public ResponseEntity<LimitDto> updateLimitForUser(
            @PathVariable Long id,
            @PathVariable Long limitId,
            @Valid @RequestBody CreateLimitRequest request) {
        return ResponseEntity.ok(limitService.updateLimitForUser(id, limitId, request));
    }

    @DeleteMapping("/{limitId}")
    public ResponseEntity<Void> deleteLimitForUser(
            @PathVariable Long id,
            @PathVariable Long limitId) {
        limitService.deleteLimitForUser(id, limitId);
        return ResponseEntity.noContent().build();
    }
}
