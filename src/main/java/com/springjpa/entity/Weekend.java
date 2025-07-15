package com.springjpa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "weekend")
public class Weekend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_weekend")
    private Integer id;

    @Column(name = "decalage", nullable = false)
    private Integer decalage;

    // Getters & Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDecalage() {
        return decalage;
    }

    public void setDecalage(Integer decalage) {
        this.decalage = decalage;
    }
}
