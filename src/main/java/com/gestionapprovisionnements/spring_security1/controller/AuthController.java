package com.gestionapprovisionnements.spring_security1.controller;

import com.gestionapprovisionnements.spring_security1.dto.response.AuthResponse;
import com.gestionapprovisionnements.spring_security1.dto.request.LoginRequest;
import com.gestionapprovisionnements.spring_security1.dto.request.RegisterRequest;
import com.gestionapprovisionnements.spring_security1.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
