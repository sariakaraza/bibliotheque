// dto/LivreAvecExemplairesDTO.java
package com.springjpa.dto;

import java.util.List;

public class LivreAvecExemplairesDTO {
    private String titre;
    private String auteur;
    private List<ExemplaireDTO> exemplaires;

    public LivreAvecExemplairesDTO(String titre, String auteur, List<ExemplaireDTO> exemplaires) {
        this.titre = titre;
        this.auteur = auteur;
        this.exemplaires = exemplaires;
    }

    // Getters
    public String getTitre() { return titre; }
    public String getAuteur() { return auteur; }
    public List<ExemplaireDTO> getExemplaires() { return exemplaires; }
}
