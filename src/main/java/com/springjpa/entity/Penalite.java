package com.springjpa.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "penalite")
public class Penalite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPenalite;

    @ManyToOne
    @JoinColumn(name = "id_adherant", nullable = false)
    private Adherant adherant;

    private LocalDate dateDebut;
    private LocalDate dateFin;
    
    public Integer getIdPenalite() {
        return idPenalite;
    }
    public void setIdPenalite(Integer idPenalite) {
        this.idPenalite = idPenalite;
    }
    public Adherant getAdherant() {
        return adherant;
    }
    public void setAdherant(Adherant adherant) {
        this.adherant = adherant;
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

    
}
