package com.grantburgess.ports.usescases.Lot.updateLot;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateLotRequest {
    private UUID id;
    private String nom;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;


    public void setId(UUID id) {
        this.id = id;
    }
}

