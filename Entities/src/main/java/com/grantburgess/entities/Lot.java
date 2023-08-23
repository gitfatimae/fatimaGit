package com.grantburgess.entities;

import java.time.LocalDate;
import java.util.UUID;

public class Lot {
    private UUID id;
    private String nom;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String description;
  private UUID idProjet;

    public UUID getId() {
        return id;
    }
    public Lot(String name, String description, LocalDate dateDebut, LocalDate dateFin) {
        this(UUID.randomUUID(), name, dateDebut, dateFin,description);
    }
    public Lot(UUID id, String nom, LocalDate dateDebut, LocalDate dateFin, String description ) {
        this.id = id;
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.description = description;
      //this.idProjet = idProjet;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

  public UUID getIdProjet() {
        return idProjet;
    }

    public void setIdProjet(UUID idProjet) {
        this.idProjet = idProjet;
    }
}
