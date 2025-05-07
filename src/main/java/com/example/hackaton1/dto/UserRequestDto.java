package com.example.hackaton1.dto;

import com.example.hackaton1.user.Role;
import lombok.Data;

@Data

public class UserRequestDto {
    private Long id;
    private String name;
    private String email;
    private Role role;
    private Long companyId;
}
