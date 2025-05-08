package com.example.hackaton1.restriction.domain;

import com.example.hackaton1.company.domain.Company;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "restrictions")
public class Restriction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "model_name", nullable = false)
    private String modelName;

    @Column(name = "max_tokens", nullable = false)
    private int maxTokens;

    @Column(name = "max_requests", nullable = false)
    private int maxRequests;

    @Column(name = "time_window", nullable = false)
    private Duration timeWindow;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;
}
