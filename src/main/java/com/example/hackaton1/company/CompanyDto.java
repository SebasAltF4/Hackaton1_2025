package com.example.hackaton1.company;

import lombok.Data;

@Data
public class CompanyDto {
    private Long id;
    private String name;
    private String ruc;
    private boolean active;
}
