package com.example.hackaton1.dto;

import com.example.hackaton1.restriction.ModelType;
import lombok.Data;

@Data

public class RestrictionRequestDto {
    private Long id;
    private Long companyId;
    private ModelType modelType;
    private Integer maxRequests;
    private Integer maxTokens;
    private Integer windowSeconds;
}