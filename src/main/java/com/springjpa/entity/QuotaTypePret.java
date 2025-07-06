package com.springjpa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "quota_type_pret")
public class QuotaTypePret {

    @EmbeddedId
    private QuotaTypePretId id;

    @ManyToOne
    @MapsId("idProfil")
    @JoinColumn(name = "id_profil")
    private Profil profil;

    @ManyToOne
    @MapsId("idTypePret")
    @JoinColumn(name = "id_type_pret")
    private TypePret typePret;

    @Column(nullable = false)
    private Integer quota;

    public QuotaTypePret() {}

    public QuotaTypePret(Profil profil, TypePret typePret, Integer quota) {
        this.profil = profil;
        this.typePret = typePret;
        this.quota = quota;
        this.id = new QuotaTypePretId(profil.getIdProfil(), typePret.getIdTypePret());
    }

    public QuotaTypePretId getId() {
        return id;
    }

    public void setId(QuotaTypePretId id) {
        this.id = id;
    }

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }

    public TypePret getTypePret() {
        return typePret;
    }

    public void setTypePret(TypePret typePret) {
        this.typePret = typePret;
    }

    public Integer getQuota() {
        return quota;
    }

    public void setQuota(Integer quota) {
        this.quota = quota;
    }
}
