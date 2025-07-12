package com.springjpa.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Retour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRetour;

    private LocalDateTime dateRetour;

    @OneToOne
    @JoinColumn(name = "id_pret", nullable = false)
    private Pret pret;

    public Integer getIdRetour() {
        return idRetour;
    }

    public void setIdRetour(Integer idRetour) {
        this.idRetour = idRetour;
    }

    public LocalDateTime getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(LocalDateTime dateRetour) {
        this.dateRetour = dateRetour;
    }

    public Pret getPret() {
        return pret;
    }

    public void setPret(Pret pret) {
        this.pret = pret;
    }

    // Getters & Setters
}

