package com.gestionapprovisionnements.spring_security1.controller;

import com.gestionapprovisionnements.spring_security1.dto.request.SocieteRequest;
import com.gestionapprovisionnements.spring_security1.dto.response.SocieteResponse;
import com.gestionapprovisionnements.spring_security1.service.SocieteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/societe")
@RequiredArgsConstructor
public class SocieteController {
    private final SocieteService societeService;
    @PostMapping
    public ResponseEntity<SocieteResponse> createSociete(@Valid @RequestBody SocieteRequest request) {
        SocieteResponse response = societeService.createSociete(request);
        return ResponseEntity.status(201).body(response);

    }
    @GetMapping
    public ResponseEntity<List<SocieteResponse>> getAllSocietes(){
        List<SocieteResponse> societes = societeService.getAllSocietes();
        return ResponseEntity.ok(societes);
    }
}
