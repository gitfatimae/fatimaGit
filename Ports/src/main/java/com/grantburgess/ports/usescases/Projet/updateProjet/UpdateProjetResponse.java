package com.grantburgess.ports.usescases.Projet.updateProjet;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class UpdateProjetResponse {
    private UUID id;

    public UpdateProjetResponse(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

}
