package com.example.hackaton1.user;

import com.example.hackaton1.aiRequest.AiRequest;
import com.example.hackaton1.company.Company;
import com.example.hackaton1.limit.Limit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne
    private Company company;

    @OneToMany(mappedBy = "user")
    private List<Limit> limits;

    @OneToMany(mappedBy = "user")
    private List<AiRequest> requests;
}

