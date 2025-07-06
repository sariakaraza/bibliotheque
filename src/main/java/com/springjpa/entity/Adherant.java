package com.springjpa.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "adherant")
public class Adherant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAdherant;

    private String nomAdherant;

    private String prenomAdherant;

    private LocalDate dateNaissance;

    private String email;

    private String password;

    @ManyToOne
    @JoinColumn(name = "id_profil", nullable = false)
    private Profil profil;

    public Adherant() {}

    

    public Adherant(String nomAdherant, String prenomAdherant, LocalDate dateNaissance, String email, String password,
            Profil profil) {
        this.nomAdherant = nomAdherant;
        this.prenomAdherant = prenomAdherant;
        this.dateNaissance = dateNaissance;
        this.email = email;
        this.password = password;
        this.profil = profil;
    }


    public Integer getIdAdherant() {
        return idAdherant;
    }

    public void setIdAdherant(Integer idAdherant) {
        this.idAdherant = idAdherant;
    }

    public String getNomAdherant() {
        return nomAdherant;
    }

    public void setNomAdherant(String nomAdherant) {
        this.nomAdherant = nomAdherant;
    }

    public String getPrenomAdherant() {
        return prenomAdherant;
    }

    public void setPrenomAdherant(String prenomAdherant) {
        this.prenomAdherant = prenomAdherant;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }



    public Profil getProfil() {
        return profil;
    }



    public void setProfil(Profil profil) {
        this.profil = profil;
    }

   


}
