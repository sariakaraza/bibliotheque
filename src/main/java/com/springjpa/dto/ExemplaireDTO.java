// dto/ExemplaireDTO.java
package com.springjpa.dto;

public class ExemplaireDTO {
    private Integer idExemplaire;
    private String statut;

    public ExemplaireDTO(Integer idExemplaire, String statut) {
        this.idExemplaire = idExemplaire;
        this.statut = statut;
    }

    // Getters
    public Integer getIdExemplaire() { return idExemplaire; }
    public String getStatut() { return statut; }
}
