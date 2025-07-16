package com.springjpa.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "profil")
public class Profil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProfil;

    private String nomProfil;
    private Integer quotaPret;
    private Integer quotaReservation;
    private Double cotisation;
    private Integer dureePenalite;
    
    // Nouvelle colonne prolongement
    private Integer prolongement;

    @OneToMany(mappedBy = "profil")
    private List<Adherant> adherants;

    @OneToMany(mappedBy = "profil", cascade = CascadeType.ALL)
    private List<DureePret> dureesPret;

    public Integer getIdProfil() {
        return idProfil;
    }

    public void setIdProfil(Integer idProfil) {
        this.idProfil = idProfil;
    }

    public String getNomProfil() {
        return nomProfil;
    }

    public void setNomProfil(String nomProfil) {
        this.nomProfil = nomProfil;
    }

    public Integer getQuotaPret() {
        return quotaPret;
    }

    public void setQuotaPret(Integer quotaPret) {
        this.quotaPret = quotaPret;
    }

    public Integer getQuotaReservation() {
        return quotaReservation;
    }

    public void setQuotaReservation(Integer quotaReservation) {
        this.quotaReservation = quotaReservation;
    }

    public Double getCotisation() {
        return cotisation;
    }

    public void setCotisation(Double cotisation) {
        this.cotisation = cotisation;
    }

    public Integer getDureePenalite() {
        return dureePenalite;
    }

    public void setDureePenalite(Integer dureePenalite) {
        this.dureePenalite = dureePenalite;
    }

    public Integer getProlongement() {
        return prolongement;
    }

    public void setProlongement(Integer prolongement) {
        this.prolongement = prolongement;
    }

    public List<Adherant> getAdherants() {
        return adherants;
    }

    public void setAdherants(List<Adherant> adherants) {
        this.adherants = adherants;
    }

    public List<DureePret> getDureesPret() {
        return dureesPret;
    }

    public void setDureesPret(List<DureePret> dureesPret) {
        this.dureesPret = dureesPret;
    }
}
