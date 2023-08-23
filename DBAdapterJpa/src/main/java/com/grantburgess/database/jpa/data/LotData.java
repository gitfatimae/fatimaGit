package com.grantburgess.database.jpa.data;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "lot")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LotData {
    @Id
    private UUID id;
    private String nom;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String description;
    private UUID idProjet;

}
