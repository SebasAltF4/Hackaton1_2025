package com.example.hackaton1.limit.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Duration;

@Data
public class CreateLimitRequest {
    @NotNull
    private String modelName;

    @Min(0)
    private int maxRequests;

    @Min(0)
    private int maxTokens;

    @NotNull
    private Duration timeWindow;
}
