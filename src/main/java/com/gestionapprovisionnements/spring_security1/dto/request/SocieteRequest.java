package com.gestionapprovisionnements.spring_security1.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SocieteRequest {
    @NotBlank(message = "Raison sociale est obligatoire")
    private String raisonSociale;

    @NotBlank(message = "ICE obligatoire")
    private String ice;

    private String adresse;

    @NotBlank(message = "Téléphone est obligatoire")
    private String telephone;

    @NotBlank(message = "Email est obligatoire")
    private String email;
}
