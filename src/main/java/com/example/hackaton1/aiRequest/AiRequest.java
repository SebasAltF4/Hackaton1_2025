package com.example.hackaton1.aiRequest;

import com.example.hackaton1.company.Company;
import com.example.hackaton1.restriction.ModelType;
import com.example.hackaton1.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class AiRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Company company;

    @Enumerated(EnumType.STRING)
    private ModelType modelType;

    private String input;
    private String output;
    private Integer tokenCount;
    private LocalDateTime timestamp;
    private String fileName;
}
