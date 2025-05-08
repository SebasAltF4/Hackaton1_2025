package com.example.hackaton1.restriction.dto;

import lombok.Data;

import java.time.Duration;

@Data
public class RestrictionDto {
    private Long id;
    private String modelName;
    private int maxTokens;
    private int maxRequests;
    private Duration timeWindow;
}
