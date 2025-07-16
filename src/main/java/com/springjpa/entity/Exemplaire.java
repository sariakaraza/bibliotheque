package com.springjpa.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "exemplaire")
public class Exemplaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idExemplaire;

    @ManyToOne
    @JoinColumn(name = "id_livre", nullable = false)
    @JsonBackReference
    private Livre livre;

    @Column(name = "libelle", nullable = false)
    private String libelle;

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Integer getIdExemplaire() {
        return idExemplaire;
    }

    public void setIdExemplaire(Integer idExemplaire) {
        this.idExemplaire = idExemplaire;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    @Transient
    private String dernierStatutTemporaire;

    public String getDernierStatutTemporaire() {
        return dernierStatutTemporaire;
    }

    public void setDernierStatutTemporaire(String statut) {
        this.dernierStatutTemporaire = statut;
    }

    
}
