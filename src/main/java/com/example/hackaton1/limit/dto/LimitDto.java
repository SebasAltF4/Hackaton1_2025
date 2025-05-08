package com.example.hackaton1.limit.dto;

import lombok.Data;
import java.time.Duration;

@Data
public class LimitDto {
    private Long id;
    private String modelName;
    private int maxRequests;
    private int maxTokens;
    private Duration timeWindow;
    private Long userId;
}
