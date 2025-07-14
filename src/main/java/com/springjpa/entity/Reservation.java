package com.springjpa.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReservation;

    private LocalDateTime dateDeReservation;

    @ManyToOne
    @JoinColumn(name = "id_exemplaire")
    private Exemplaire exemplaire;

    @ManyToOne
    @JoinColumn(name = "id_adherant")
    private Adherant adherant;

    @Column(name = "statut", nullable = false)
    private String statut = "en attente";

    // === Getters & Setters ===

    public Integer getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(Integer idReservation) {
        this.idReservation = idReservation;
    }

    public LocalDateTime getDateDeReservation() {
        return dateDeReservation;
    }

    public void setDateDeReservation(LocalDateTime dateDeReservation) {
        this.dateDeReservation = dateDeReservation;
    }

    public Exemplaire getExemplaire() {
        return exemplaire;
    }

    public void setExemplaire(Exemplaire exemplaire) {
        this.exemplaire = exemplaire;
    }

    public Adherant getAdherant() {
        return adherant;
    }

    public void setAdherant(Adherant adherant) {
        this.adherant = adherant;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}
