package com.grantburgess.ports.usescases.Projet.get.Projetbyid;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class GetProjetRequest {
    private UUID id;
}
