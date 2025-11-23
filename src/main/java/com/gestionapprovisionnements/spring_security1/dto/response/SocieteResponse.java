package com.gestionapprovisionnements.spring_security1.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SocieteResponse {
    private Long id;
    private String raisonSociale;
    private String ICE;
    private String adresse;
    private String email;
    private String telephone;
}
