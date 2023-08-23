package com.grantburgess.ports.usescases.Lot.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LotResponse {
    private UUID id;
    private String nom;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;

}
