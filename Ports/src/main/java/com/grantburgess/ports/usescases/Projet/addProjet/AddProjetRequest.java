package com.grantburgess.ports.usescases.Projet.addProjet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddProjetRequest {
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
}
