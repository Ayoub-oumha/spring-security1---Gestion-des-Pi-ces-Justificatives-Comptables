package com.gestionapprovisionnements.spring_security1.service;

import com.gestionapprovisionnements.spring_security1.dto.response.AuthResponse;
import com.gestionapprovisionnements.spring_security1.dto.request.LoginRequest;
import com.gestionapprovisionnements.spring_security1.dto.request.RegisterRequest;
import com.gestionapprovisionnements.spring_security1.entity.User;
import com.gestionapprovisionnements.spring_security1.entity.enums.UserStatus;
import com.gestionapprovisionnements.spring_security1.repository.UserRepository;
import com.gestionapprovisionnements.spring_security1.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        user.setStatus(UserStatus.ACTIVE);
        
        userRepository.save(user);
        
        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponse(token, user.getEmail());
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponse(token, user.getEmail());
    }
}
