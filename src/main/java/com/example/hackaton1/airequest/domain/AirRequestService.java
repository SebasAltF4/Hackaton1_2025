package com.example.hackaton1.airequest.domain;

import com.example.hackaton1.airequest.dto.AIRequestDto;
import com.example.hackaton1.airequest.dto.CreateAIRequestDto;
import com.example.hackaton1.airequest.infrastructure.AiRequestRepository;
import com.example.hackaton1.company.infrastructure.CompanyRepository;
import com.example.hackaton1.limit.domain.LImitService;
import com.example.hackaton1.limit.infrastructure.LimitRepository;
import com.example.hackaton1.user.infrastructure.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AirRequestService {
    private final AiRequestRepository aiRequestRepository;
    private final UsuarioRepository usuarioRepository;
    private final CompanyRepository companyRepository;
    private final LImitService limitService;
    private final ModelMapper modelMapper;

    // Placeholder para el SDK de GitHub Models o similar
    //private final GitHubModelService modelService;

    public AIRequestDto createChat(Long userId, CreateAIRequestDto createAIRequestDto) {
        return modelMapper.map(createAIRequestDto, AIRequestDto.class);
    }

    public List<AIRequestDto> historyByUser(Long userId) {
        return aiRequestRepository.findByUserId(userId)
                .stream()
                .map(r -> {
                    AIRequestDto dto = modelMapper.map(r, AIRequestDto.class);
                    dto.setUserId(userId);
                    dto.setCompanyId(r.getCompany().getId());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
