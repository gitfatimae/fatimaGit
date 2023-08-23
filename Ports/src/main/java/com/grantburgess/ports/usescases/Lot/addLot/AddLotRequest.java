package com.grantburgess.ports.usescases.Lot.addLot;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddLotRequest {
    private String nom;
    private String description;
    private LocalDate dateDebut;
    private LocalDate dateFin;
}
