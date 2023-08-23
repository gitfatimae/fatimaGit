package com.grantburgess.ports.usescases.Projet.delete;


import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class DeleteProjetRequest {
    private UUID id;
}