package com.example.hackaton1.dto;

import com.example.hackaton1.restriction.ModelType;
import lombok.Data;

import java.time.LocalDateTime;

@Data

public class AiRequestDto {
    private Long id;
    private Long userId;
    private Long companyId;
    private ModelType modelType;
    private String input;
    private String output;
    private Integer tokenCount;
    private LocalDateTime timestamp;
    private String fileName;
}
