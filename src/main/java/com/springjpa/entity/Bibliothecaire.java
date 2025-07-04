package com.springjpa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "admin")
public class Bibliothecaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAdmin;

    private String nomAdmin;

    private String prenomAdmin;

    private String email;

    private String password;

    public Integer getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(Integer idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getNomAdmin() {
        return nomAdmin;
    }

    public void setNomAdmin(String nomAdmin) {
        this.nomAdmin = nomAdmin;
    }

    public String getPrenomAdmin() {
        return prenomAdmin;
    }

    public void setPrenomAdmin(String prenomAdmin) {
        this.prenomAdmin = prenomAdmin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Bibliothecaire() {
    }

    public Bibliothecaire(String nomAdmin, String prenomAdmin, String email, String password) {
        this.nomAdmin = nomAdmin;
        this.prenomAdmin = prenomAdmin;
        this.email = email;
        this.password = password;
    }

    
}
