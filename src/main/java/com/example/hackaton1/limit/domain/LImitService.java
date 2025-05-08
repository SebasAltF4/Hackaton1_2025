package com.example.hackaton1.limit.domain;

import com.example.hackaton1.exceptions.ResourceNotFoundException;
import com.example.hackaton1.limit.dto.CreateLimitRequest;
import com.example.hackaton1.limit.dto.LimitDto;
import com.example.hackaton1.limit.infrastructure.LimitRepository;
import com.example.hackaton1.user.domain.Usuario;
import com.example.hackaton1.user.infrastructure.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LImitService {
    private final LimitRepository limitRepository;
    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;

    public LimitDto createLimitForUser(Long userId, CreateLimitRequest createLimitRequest) {
        Usuario usuario = usuarioRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + userId));
        Limit limit = modelMapper.map(createLimitRequest, Limit.class);
        limit.setUser(usuario);
        Limit saved = limitRepository.save(limit);
        LimitDto limitDto = modelMapper.map(saved, LimitDto.class);
        limitDto.setUserId(userId);
        return limitDto;
    }

    public List<LimitDto> listRestrictionForUser(Long userId) {
        return limitRepository.findAllByUserId(userId)
                .stream()
                .map(l -> {
                    LimitDto dto = modelMapper.map(l, LimitDto.class);
                    dto.setUserId(userId);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public LimitDto updateLimitForUser(Long userId, Long limitId, CreateLimitRequest request) {
        Limit limit = limitRepository.findById(limitId)
                .orElseThrow(() -> new ResourceNotFoundException("Límite no encontrado con ID: " + limitId));
        if (!limit.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("No puede modificar este límite");
        }
        limit.setModelName(request.getModelName());
        limit.setMaxRequests(request.getMaxRequests());
        limit.setMaxTokens(request.getMaxTokens());
        limit.setTimeWindow(request.getTimeWindow());
        Limit limitToUpdate = limitRepository.save(limit);
        LimitDto limitDto = modelMapper.map(limitToUpdate, LimitDto.class);
        limitDto.setUserId(userId);
        return limitDto;
    }

    public void deleteLimitForUser(Long userId, Long limitId) {
        Limit entity = limitRepository.findById(limitId)
                .orElseThrow(() -> new ResourceNotFoundException("Límite no encontrado con ID: " + limitId));
        if (!entity.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("No puede eliminar este límite");
        }
        limitRepository.delete(entity);
    }
}
