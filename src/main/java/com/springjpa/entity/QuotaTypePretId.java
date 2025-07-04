package com.springjpa.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class QuotaTypePretId implements Serializable {

    @Column(name = "id_profil")
    private Integer idProfil;

    @Column(name = "id_type_pret")
    private Integer idTypePret;

    public QuotaTypePretId() {}

    public QuotaTypePretId(Integer idProfil, Integer idTypePret) {
        this.idProfil = idProfil;
        this.idTypePret = idTypePret;
    }

    public Integer getIdProfil() {
        return idProfil;
    }

    public void setIdProfil(Integer idProfil) {
        this.idProfil = idProfil;
    }

    public Integer getIdTypePret() {
        return idTypePret;
    }

    public void setIdTypePret(Integer idTypePret) {
        this.idTypePret = idTypePret;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuotaTypePretId)) return false;
        QuotaTypePretId that = (QuotaTypePretId) o;
        return Objects.equals(idProfil, that.idProfil) &&
               Objects.equals(idTypePret, that.idTypePret);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProfil, idTypePret);
    }
}
