package com.springjpa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "duree_pret")
public class DureePret {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_duree_pret")
    private Integer idDureePret;

    @Column(name = "duree", nullable = false)
    private Integer duree;

    @ManyToOne
    @JoinColumn(name = "id_profil", nullable = false)
    private Profil profil;

    // Getters & setters

    public Integer getIdDureePret() {
        return idDureePret;
    }

    public void setIdDureePret(Integer idDureePret) {
        this.idDureePret = idDureePret;
    }

    public Integer getDuree() {
        return duree;
    }

    public void setDuree(Integer duree) {
        this.duree = duree;
    }

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }
}
