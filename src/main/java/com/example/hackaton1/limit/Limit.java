package com.example.hackaton1.limit;

import com.example.hackaton1.restriction.ModelType;
import com.example.hackaton1.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Limit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private ModelType modelType;

    private Integer maxRequests;
    private Integer maxTokens;
    private Integer windowSeconds;
}

