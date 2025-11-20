package com.gestionapprovisionnements.spring_security1.dto;

import com.gestionapprovisionnements.spring_security1.entity.enums.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String fullName;
    private String email;
    private String password;
    private Role role;
}
