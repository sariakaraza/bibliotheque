package com.springjpa.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "jour_ferie")
public class JourFerie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_jour_ferie")
    private Integer id;

    @Column(name = "date_jour_ferie", nullable = false, unique = true)
    private LocalDate date;

    @Column(name = "nom", nullable = false, length = 100)
    private String nom;

    @Column(name = "decalage")
    private Integer decalage = 1; // Valeur par défaut

    // Getters & Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getDecalage() {
        return decalage;
    }

    public void setDecalage(Integer decalage) {
        this.decalage = decalage;
    }
}
