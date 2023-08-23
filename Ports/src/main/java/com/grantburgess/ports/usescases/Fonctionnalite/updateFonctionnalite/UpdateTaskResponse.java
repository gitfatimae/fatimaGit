package com.grantburgess.ports.usescases.Fonctionnalite.updateFonctionnalite;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class UpdateTaskResponse {
    private UUID id;

    public UpdateTaskResponse(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

}
