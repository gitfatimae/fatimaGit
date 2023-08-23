package com.grantburgess.ports.usescases.Projet.addProjet;

import lombok.Getter;

import java.util.UUID;

@Getter
public class NewProjetResponse {
    private UUID id;

    public NewProjetResponse(UUID id) {
        this.id = id;
    }
}
