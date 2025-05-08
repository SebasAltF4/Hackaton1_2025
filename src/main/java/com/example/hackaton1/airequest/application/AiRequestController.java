package com.example.hackaton1.airequest.application;

import com.example.hackaton1.airequest.domain.AirRequestService;
import com.example.hackaton1.airequest.dto.AIRequestDto;
import com.example.hackaton1.airequest.dto.CreateAIRequestDto;
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
@RequestMapping("/api/ai")
public class AiRequestController {
    private final AirRequestService aiRequestService;

    @PostMapping("/chat")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<AIRequestDto> chat(
            @Valid @RequestBody CreateAIRequestDto createAIRequestDto,
            @AuthenticationPrincipal Usuario usuario) {
        AIRequestDto dto = aiRequestService.createChat(usuario.getId(), createAIRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping("/history")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<AIRequestDto>> history(@AuthenticationPrincipal Usuario usuario) {
        return ResponseEntity.ok(aiRequestService.historyByUser(usuario.getId()));
    }
}
