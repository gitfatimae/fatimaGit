package com.grantburgess.ports.usescases.Fonctionnalite.addFonctionnalite;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddTaskRequest {
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
}
