package com.example.hackaton1.dto;

import com.example.hackaton1.restriction.ModelType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data

public class LimitRequestDto {
    private Long id;
    private Long userId;
    private ModelType modelType;
    private Integer maxRequests;
    private Integer maxTokens;
    private Integer windowSeconds;
}
