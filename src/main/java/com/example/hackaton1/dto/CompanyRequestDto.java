package com.example.hackaton1.dto;

import lombok.Data;

import java.time.LocalDate;

@Data

public class CompanyRequestDto {
    private Long id;
    private String name;
    private String ruc;
    private LocalDate subscriptionDate;
    private boolean active;
    private Long adminId;
}
