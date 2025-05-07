package com.example.hackaton1.company.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CompanyDto {
    private String name;
    private String ruc;
    private LocalDateTime affiliationDate;
    private boolean active;
}
