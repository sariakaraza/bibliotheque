package com.springjpa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "exemplaire")
public class Exemplaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idExemplaire;

    @ManyToOne
    @JoinColumn(name = "id_livre", nullable = false)
    private Livre livre;

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
