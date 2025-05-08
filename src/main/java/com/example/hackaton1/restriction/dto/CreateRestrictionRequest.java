package com.example.hackaton1.restriction.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Duration;

@Data
public class CreateRestrictionRequest {
    @NotNull
    private String modelName;

    @Min(0)
    private int maxTokens;

    @Min(0)
    private int maxRequests;

    @NotNull
    private Duration timeWindow;
}
