package com.example.hackaton1.limit;

import com.example.hackaton1.restriction.Restriction;
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
@NoArgsConstructor
@AllArgsConstructor
public class Limit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String modelName;
    private Integer maxTokens;
    private Integer maxRequests;
    private Duration timeWindow;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
