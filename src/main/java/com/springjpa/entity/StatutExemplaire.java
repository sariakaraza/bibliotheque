package com.springjpa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "statut_exemplaire")
public class StatutExemplaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idStatutExemplaire;

    private String nomStatut;

    public Integer getIdStatutExemplaire() {
        return idStatutExemplaire;
    }

    public void setIdStatutExemplaire(Integer idStatutExemplaire) {
        this.idStatutExemplaire = idStatutExemplaire;
    }

    public String getNomStatut() {
        return nomStatut;
    }

    public void setNomStatut(String nomStatut) {
        this.nomStatut = nomStatut;
    }

    // getters/setters

    
}
