package com.gestionapprovisionnements.spring_security1.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class DocumentResponse {
    private Long id;
    private String numeroPiece ;
    private String typePiece ;
    private String statut;
    private Double montant ;
    private String fournisseur ;
    private String fichierPath ;
    private String commentaire ;
    private Long societe_id;
    private LocalDateTime dateCreation ;
    private LocalDateTime dateModification ;

}
