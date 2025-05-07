package com.example.hackaton1.company;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateCompanyRequest {
    @NotNull
    private String name;
    @NotNull
    private String ruc;
}
