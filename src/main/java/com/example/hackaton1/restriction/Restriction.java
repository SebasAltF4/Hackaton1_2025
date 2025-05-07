package com.example.hackaton1.restriction;

import com.example.hackaton1.company.Company;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Restriction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Company company;

    @Enumerated(EnumType.STRING)
    private ModelType modelType;

    private Integer maxRequests;
    private Integer maxTokens;
    private Integer windowSeconds;
}

