package com.example.hackaton1.airequest.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AIRequestDto {
    private Long id;
    private String modelName;
    private String prompt;
    private String response;
    private int tokensUsed;
    private LocalDateTime timestamp;
    private Long userId;
    private Long companyId;
}
