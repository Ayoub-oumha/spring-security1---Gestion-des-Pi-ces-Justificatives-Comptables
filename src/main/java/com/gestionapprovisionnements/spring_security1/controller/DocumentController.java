package com.gestionapprovisionnements.spring_security1.controller;

import com.gestionapprovisionnements.spring_security1.dto.request.DocumentRequest;
import com.gestionapprovisionnements.spring_security1.dto.response.DocumentResponse;
import com.gestionapprovisionnements.spring_security1.service.DocumentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/document")
@RequiredArgsConstructor

public class  DocumentController {
    private final DocumentService documentService;

    @GetMapping
    public String index() {
        return "index";
    }

    @PostMapping("/create")
    public ResponseEntity<DocumentResponse> createDocument(
            @RequestParam("numeroPiece") String numeroPiece,
            @RequestParam("typePiece") String typePiece,
            @RequestParam("montant") Double montant,
            @RequestParam("fournisseur") String fournisseur,
            @RequestParam(value = "commentaire", required = false) String commentaire,
            @RequestParam("societeId") Long societeId,
            @RequestParam("file") MultipartFile file) {
        try {
            DocumentRequest request = new DocumentRequest();
            request.setNumeroPiece(numeroPiece);
            request.setTypePiece(com.gestionapprovisionnements.spring_security1.entity.enums.TypePiece.valueOf(typePiece));
            request.setMontant(montant);
            request.setFournisseur(fournisseur);
            request.setCommentaire(commentaire);
            request.setSocieteId(societeId);
            
            DocumentResponse response = documentService.createDocument(request, file);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
