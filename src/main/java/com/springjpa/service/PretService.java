package com.springjpa.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springjpa.entity.*;
import com.springjpa.repository.*;

@Service
public class PretService {

    @Autowired
    private AdherantRepository adherantRepository;

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    @Autowired
    private AbonnementRepository abonnementRepository;

    @Autowired
    private PretRepository pretRepository;

    @Autowired
    private TypePretRepository typePretRepository;

    @Autowired
    private BibliothecaireRepository bibliothecaireRepository;

    /**
     * Effectue un prêt si les conditions sont valides.
     */
    public void effectuerPret(Integer idAdherant, Integer idExemplaire, Integer idTypePret) {
        // 1. Verifie que l'adherent existe
        Adherant adherant = adherantRepository.findById(idAdherant)
            .orElseThrow(() -> new IllegalArgumentException("Adherent introuvable"));

        // 2. Verifie que l'exemplaire existe
        Exemplaire exemplaire = exemplaireRepository.findById(idExemplaire)
            .orElseThrow(() -> new IllegalArgumentException("Exemplaire introuvable"));

        // 3. Verifie que l’adherent est actif (a un abonnement valide aujourd’hui)
        LocalDateTime maintenant = LocalDateTime.now();
        boolean actif = abonnementRepository.existsByAdherant_IdAdherantAndDateDebutBeforeAndDateFinAfter(
            idAdherant, maintenant, maintenant
        );

        if (!actif) {
            throw new IllegalStateException("Adherent inactif (abonnement expire ou non commence)");
        }

        // Recuperation du livre via l'exemplaire
        Livre livre = exemplaire.getLivre();  // Si tu as bien fait @ManyToOne dans Exemplaire

        if (livre.getAgeRestriction() != null) {
            // Calcul de l'âge de l'adherent
            int age = Period.between(adherant.getDateNaissance(), LocalDate.now()).getYears();

            if (age < livre.getAgeRestriction()) {
                throw new IllegalStateException("Adherent trop jeune pour emprunter ce livre (restriction : " 
                    + livre.getAgeRestriction() + " ans)");
            }
        }


        // 4. Recuperation type de prêt (ex. à domicile, id = 1)
        TypePret typePret = typePretRepository.findById(idTypePret)
        .orElseThrow(() -> new IllegalStateException("Type de prêt introuvable"));
    
        // 5. Recuperation bibliothecaire par defaut (id = 1 ici pour l’exemple)
        Bibliothecaire bibliothecaire = bibliothecaireRepository.findById(1)
            .orElseThrow(() -> new IllegalStateException("Bibliothecaire par defaut non trouve"));

        // 6. Creation et enregistrement du prêt
        Pret pret = new Pret();
        pret.setDateDebut(LocalDateTime.now());
        pret.setAdherant(adherant);
        pret.setExemplaire(exemplaire);
        pret.setTypePret(typePret);
        pret.setBibliothecaire(bibliothecaire);

        pretRepository.save(pret);
    }
}
