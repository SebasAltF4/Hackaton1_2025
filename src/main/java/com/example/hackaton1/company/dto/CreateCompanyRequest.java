package com.example.hackaton1.company.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateCompanyRequest {

    @NotNull
    private String name;
    @NotNull
    private String ruc;
}
