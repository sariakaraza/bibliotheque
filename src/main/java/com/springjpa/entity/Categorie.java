package com.springjpa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "categorie")
public class Categorie {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategorie;
    private String nomCategorie;

    
        public Integer getIdCategorie() {
            return idCategorie;
        }
    
        public void setIdCategorie(Integer idCategorie) {
            this.idCategorie = idCategorie;
        }
    
        public String getNomCategorie() {
            return nomCategorie;
        }
    
        public void setNomCategorie(String nomCategorie) {
            this.nomCategorie = nomCategorie;
        }
    
        public Categorie() {
        }
    
        public Categorie(String nomCategorie) {
            this.nomCategorie = nomCategorie;
        }
}