package com.example.hackaton1.airequest.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateAIRequestDto {
    @NotNull
    private String modelName;

    @NotNull
    private String prompt;
}
