package com.example.hackaton1.aiRequest;

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

    private String modelName;
    private String requestType; // chat, completion, multimodal
    private int tokensUsed;

    @Column(length = 10000)
    private String prompt;

    @Column(length = 20000)
    private String response;

    private String fileName; // si es multimodal

    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
