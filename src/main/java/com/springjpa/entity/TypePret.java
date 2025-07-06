package com.springjpa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "type_pret")
public class TypePret {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type_pret")
    private Integer idTypePret;

    @Column(nullable = false)
    private String type;

    public TypePret() {}

    public TypePret(String type) {
        this.type = type;
    }

    public Integer getIdTypePret() {
        return idTypePret;
    }

    public void setIdTypePret(Integer idTypePret) {
        this.idTypePret = idTypePret;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

