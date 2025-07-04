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

    @ManyToOne
    @JoinColumn(name = "id_statut_exemplaire", nullable = false)
    private StatutExemplaire statutExemplaire;

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

    public StatutExemplaire getStatutExemplaire() {
        return statutExemplaire;
    }

    public void setStatutExemplaire(StatutExemplaire statutExemplaire) {
        this.statutExemplaire = statutExemplaire;
    }

    
}
