package com.springjpa.dto;

import java.util.List;

public class EtatAdherantDTO {
    private Integer id;
    private String nom;
    private String prenom;
    private List<PeriodeDTO> abonnements;
    private List<PeriodeDTO> penalites;
    private Integer quotaPret;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public List<PeriodeDTO> getAbonnements() {
        return abonnements;
    }
    public void setAbonnements(List<PeriodeDTO> abonnements) {
        this.abonnements = abonnements;
    }
    public List<PeriodeDTO> getPenalites() {
        return penalites;
    }
    public void setPenalites(List<PeriodeDTO> penalites) {
        this.penalites = penalites;
    }
    public Integer getQuotaPret() {
        return quotaPret;
    }
    public void setQuotaPret(Integer quotaPret) {
        this.quotaPret = quotaPret;
    }
  

    
}
