package com.example.hackaton1.limit.domain;

import com.example.hackaton1.user.domain.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;

@Entity
@Table(name = "limits")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Limit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "model_name", nullable = false)
    private String modelName;

    @Column(name = "max_requests", nullable = false)
    private int maxRequests;

    @Column(name = "max_tokens", nullable = false)
    private int maxTokens;

    @Column(name = "time_window", nullable = false)
    private Duration timeWindow;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Usuario user;
}
