package com.springjpa.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "exemplaire_statut")
public class ExemplaireStatut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_exemplaire", nullable = false)
    private Exemplaire exemplaire;

    @ManyToOne
    @JoinColumn(name = "id_statut_exemplaire", nullable = false)
    private StatutExemplaire statutExemplaire;

    @Column(name = "date_statut", nullable = false)
    private LocalDateTime dateStatut = LocalDateTime.now();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Exemplaire getExemplaire() {
        return exemplaire;
    }

    public void setExemplaire(Exemplaire exemplaire) {
        this.exemplaire = exemplaire;
    }

    public StatutExemplaire getStatutExemplaire() {
        return statutExemplaire;
    }

    public void setStatutExemplaire(StatutExemplaire statutExemplaire) {
        this.statutExemplaire = statutExemplaire;
    }

    public LocalDateTime getDateStatut() {
        return dateStatut;
    }

    public void setDateStatut(LocalDateTime dateStatut) {
        this.dateStatut = dateStatut;
    }
}
