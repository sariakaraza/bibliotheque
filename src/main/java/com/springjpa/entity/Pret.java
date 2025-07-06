package com.springjpa.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pret")
public class Pret {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pret")
    private Integer idPret;

    @Column(name = "date_debut")
    private LocalDateTime dateDebut;

    @ManyToOne
    @JoinColumn(name = "id_admin", nullable = false)
    private Bibliothecaire bibliothecaire;

    @ManyToOne
    @JoinColumn(name = "id_type_pret", nullable = false)
    private TypePret typePret;

    @ManyToOne
    @JoinColumn(name = "id_exemplaire", nullable = false)
    private Exemplaire exemplaire;

    @ManyToOne
    @JoinColumn(name = "id_adherant", nullable = false)
    private Adherant adherant;

    @Column(name = "date_fin")
    private LocalDateTime dateFin;

    // Constructeurs
    public Pret() {}

    public Pret(LocalDateTime dateDebut, Bibliothecaire bibliothecaire, TypePret typePret, Exemplaire exemplaire, Adherant adherant) {
        this.dateDebut = dateDebut;
        this.bibliothecaire = bibliothecaire;
        this.typePret = typePret;
        this.exemplaire = exemplaire;
        this.adherant = adherant;
    }

    // Getters et Setters

    public Integer getIdPret() {
        return idPret;
    }

    public void setIdPret(Integer idPret) {
        this.idPret = idPret;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Bibliothecaire getBibliothecaire() {
        return bibliothecaire;
    }

    public void setBibliothecaire(Bibliothecaire bibliothecaire) {
        this.bibliothecaire = bibliothecaire;
    }

    public TypePret getTypePret() {
        return typePret;
    }

    public void setTypePret(TypePret typePret) {
        this.typePret = typePret;
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

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }

    
}
