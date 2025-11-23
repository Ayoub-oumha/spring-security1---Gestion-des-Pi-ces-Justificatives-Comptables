package com.gestionapprovisionnements.spring_security1.dto.request;

import com.gestionapprovisionnements.spring_security1.entity.enums.TypePiece;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentRequest {
    @NotBlank(message = "Numéro de pièce est obligatoire")
    private String numeroPiece;

    @NotNull(message = "Type de pièce est obligatoire")
    private TypePiece typePiece;

    @NotNull(message = "Montant est obligatoire")
    @PositiveOrZero(message = "Montant doit être positif")
    private Double montant;

    @NotBlank(message = "Fournisseur est obligatoire")
    private String fournisseur;

    private String commentaire;

    @NotNull(message = "Société est obligatoire")
    private Long societeId;
}
