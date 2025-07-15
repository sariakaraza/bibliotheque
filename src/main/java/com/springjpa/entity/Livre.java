package com.springjpa.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
@Table(name = "livre")
public class Livre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLivre;

    private String titre;

    private Integer anneePublication;

    private Integer nbPage;

    private Integer ageRestriction;

    private String auteur;

    @OneToMany(mappedBy = "livre", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Exemplaire> exemplaires;

    public Livre() {}

    public Livre(String titre, Integer anneePublication, Integer nbPage, Integer ageRestriction, String auteur) {
        this.titre = titre;
        this.anneePublication = anneePublication;
        this.nbPage = nbPage;
        this.ageRestriction = ageRestriction;
        this.auteur = auteur;
    }

    public Integer getIdLivre() {
        return idLivre;
    }

    public void setIdLivre(Integer idLivre) {
        this.idLivre = idLivre;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Integer getAnneePublication() {
        return anneePublication;
    }

    public void setAnneePublication(Integer anneePublication) {
        this.anneePublication = anneePublication;
    }

    public Integer getNbPage() {
        return nbPage;
    }

    public void setNbPage(Integer nbPage) {
        this.nbPage = nbPage;
    }

    public Integer getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(Integer ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public List<Exemplaire> getExemplaires() {
        return exemplaires;
    }

    public void setExemplaires(List<Exemplaire> exemplaires) {
        this.exemplaires = exemplaires;
    }

    
}
