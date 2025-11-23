package com.gestionapprovisionnements.spring_security1.service;

import com.gestionapprovisionnements.spring_security1.dto.request.SocieteRequest;
import com.gestionapprovisionnements.spring_security1.dto.response.SocieteResponse;
import com.gestionapprovisionnements.spring_security1.entity.Societe;
import com.gestionapprovisionnements.spring_security1.exception.DuplicateResourceException;
import com.gestionapprovisionnements.spring_security1.exception.BadRequestException;
import com.gestionapprovisionnements.spring_security1.repository.SocieteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SocieteService {
    private final SocieteRepository societeRepository;

    public SocieteResponse createSociete(SocieteRequest request) {
        // Vérifier si l'ICE existe déjà
        if (societeRepository.existsByIce(request.getIce())) {
            throw new DuplicateResourceException("Une société avec cet ICE existe déjà: " + request.getIce());
        }

        // Validation supplémentaire
        if (request.getIce() == null || request.getIce().trim().isEmpty()) {
            throw new BadRequestException("L'ICE ne peut pas être vide");
        }

        Societe societe = new Societe();
        societe.setAdresse(request.getAdresse());
        societe.setEmailContact(request.getEmail());
        societe.setTelephone(request.getTelephone());
        societe.setIce(request.getIce());
        societe.setRaisonSociale(request.getRaisonSociale());

        Societe saved = societeRepository.save(societe);

        return new SocieteResponse(
                saved.getId(),
                saved.getRaisonSociale(),
                saved.getIce(),
                saved.getAdresse(),
                saved.getEmailContact(),
                saved.getTelephone()
        );
    }

    public List<SocieteResponse> getAllSocietes() {
        return societeRepository.findAll().stream()
                .map(societe -> new SocieteResponse(
                        societe.getId(),
                        societe.getRaisonSociale(),
                        societe.getIce(),
                        societe.getAdresse(),
                        societe.getEmailContact(),
                        societe.getTelephone()
                ))
                .collect(Collectors.toList());
    }
}
