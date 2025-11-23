package com.gestionapprovisionnements.spring_security1.service;

import com.gestionapprovisionnements.spring_security1.dto.request.DocumentRequest;
import com.gestionapprovisionnements.spring_security1.dto.response.DocumentResponse;
import com.gestionapprovisionnements.spring_security1.entity.Document;
import com.gestionapprovisionnements.spring_security1.entity.Societe;
import com.gestionapprovisionnements.spring_security1.exception.ResourceNotFoundException;
import com.gestionapprovisionnements.spring_security1.exception.BadRequestException;
import com.gestionapprovisionnements.spring_security1.repository.DocumentRepository;
import com.gestionapprovisionnements.spring_security1.repository.SocieteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DocumentService {
    private final DocumentRepository documentRepository;
    private final SocieteRepository societeRepository;

    public DocumentResponse createDocument(DocumentRequest request, MultipartFile file) throws IOException {
        String uploadDir = "uploads";
        String fileName = file.getOriginalFilename();

        if (fileName == null || fileName.isEmpty()) {
            throw new BadRequestException("Le nom du fichier ne peut pas être vide");
        }

        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        
        String uniqueFileName = System.currentTimeMillis() + "_" + fileName;
        Path filePath = uploadPath.resolve(uniqueFileName);
        Files.copy(file.getInputStream(), filePath);

        // Récupérer l'objet Societe
        Societe societe = societeRepository.findById(request.getSocieteId())
                .orElseThrow(() -> new ResourceNotFoundException("Société non trouvée avec l'ID: " + request.getSocieteId()));

        //document
        Document document = new Document();
        document.setNumeroPiece(request.getNumeroPiece());
        document.setType(request.getTypePiece());
        document.setDatePiece(LocalDateTime.now());
        document.setMontant(request.getMontant());
        document.setFournisseur(request.getFournisseur());
        document.setCommentaire(request.getCommentaire());
        document.setSociete_id(societe);
        document.setFichierPath(filePath.toString());
        document.setDateCreation(LocalDateTime.now());
        document.setDateModification(LocalDateTime.now());

        Document saved = documentRepository.save(document);

        return new DocumentResponse(
                saved.getId(),
                saved.getNumeroPiece(),
                saved.getType().name(),
                saved.getStatut().name(),
                saved.getMontant(),
                saved.getFournisseur(),
                saved.getFichierPath(),
                saved.getCommentaire(),
                saved.getSociete_id().getId(),
                saved.getDateCreation(),
                saved.getDateModification()
        );
    }
}
