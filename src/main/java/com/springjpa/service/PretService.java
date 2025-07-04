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

    @Autowired
    private DureePretRepository dureePretRepository;    

    /**
     * Effectue un prêt si les conditions sont valides.
     */
    // public void effectuerPret(Integer idAdherant, Integer idExemplaire, Integer idTypePret) {
    //     // 1. Verifie que l'adherent existe
    //     Adherant adherant = adherantRepository.findById(idAdherant)
    //         .orElseThrow(() -> new IllegalArgumentException("Adherent introuvable"));

    //     // 2. Verifie que l'exemplaire existe
    //     Exemplaire exemplaire = exemplaireRepository.findById(idExemplaire)
    //         .orElseThrow(() -> new IllegalArgumentException("Exemplaire introuvable"));

    //     // 3. Verifie que l’adherent est actif (a un abonnement valide aujourd’hui)
    //     LocalDateTime maintenant = LocalDateTime.now();
    //     boolean actif = abonnementRepository.existsByAdherant_IdAdherantAndDateDebutBeforeAndDateFinAfter(
    //         idAdherant, maintenant, maintenant
    //     );

    //     if (!actif) {
    //         throw new IllegalStateException("Adherent inactif (abonnement expire ou non commence)");
    //     }

    //     // Recuperation du livre via l'exemplaire
    //     Livre livre = exemplaire.getLivre();  // Si tu as bien fait @ManyToOne dans Exemplaire

    //     if (livre.getAgeRestriction() != null) {
    //         // Calcul de l'âge de l'adherent
    //         int age = Period.between(adherant.getDateNaissance(), LocalDate.now()).getYears();

    //         if (age < livre.getAgeRestriction()) {
    //             throw new IllegalStateException("Adherent trop jeune pour emprunter ce livre (restriction : " 
    //                 + livre.getAgeRestriction() + " ans)");
    //         }
    //     }


    //     // 4. Recuperation type de prêt (ex. à domicile, id = 1)
    //     TypePret typePret = typePretRepository.findById(idTypePret)
    //     .orElseThrow(() -> new IllegalStateException("Type de prêt introuvable"));
    
    //     // 5. Recuperation bibliothecaire par defaut (id = 1 ici pour l’exemple)
    //     Bibliothecaire bibliothecaire = bibliothecaireRepository.findById(1)
    //         .orElseThrow(() -> new IllegalStateException("Bibliothecaire par defaut non trouve"));

    //     // 6. Creation et enregistrement du prêt
    //     Pret pret = new Pret();
    //     pret.setDateDebut(LocalDateTime.now());
    //     pret.setAdherant(adherant);
    //     pret.setExemplaire(exemplaire);
    //     pret.setTypePret(typePret);
    //     pret.setBibliothecaire(bibliothecaire);

    //     pretRepository.save(pret);
    // }

    // public void effectuerPret(Integer idAdherant, Integer idExemplaire, Integer idTypePret, LocalDateTime inputDateDebut) {
    //     Adherant adherant = getAdherantOrThrow(idAdherant);
    //     Exemplaire exemplaire = getExemplaireOrThrow(idExemplaire);
    
    //     LocalDateTime datePret = inputDateDebut != null ? inputDateDebut : LocalDateTime.now();
    
    //     verifierAbonnementActif(adherant, datePret);
    //     verifierRestrictionAge(adherant, exemplaire);
    //     verifierQuotaPret(adherant, datePret);
    
    //     TypePret typePret = getTypePretOrThrow(idTypePret);
    //     Bibliothecaire bibliothecaire = getBibliothecaireParDefaut();
    
    //     Pret pret = new Pret();
    //     pret.setDateDebut(datePret);
    //     pret.setAdherant(adherant);
    //     pret.setExemplaire(exemplaire);
    //     pret.setTypePret(typePret);
    //     pret.setBibliothecaire(bibliothecaire);
    
    //     int duree = getDureePretPourProfil(adherant.getProfil());
    //     pret.setDateFin(datePret.plusDays(duree));
    
    //     pretRepository.save(pret);
    // }
    
    public void effectuerPret(Integer idAdherant, Integer idExemplaire, Integer idTypePret, LocalDateTime inputDateDebut) {
        Adherant adherant = getAdherantOrThrow(idAdherant);
        Exemplaire exemplaire = getExemplaireOrThrow(idExemplaire);
    
        LocalDateTime datePret = inputDateDebut != null ? inputDateDebut : LocalDateTime.now();
    
        verifierAbonnementActif(adherant, datePret);
        verifierRestrictionAge(adherant, exemplaire);
    
        TypePret typePret = getTypePretOrThrow(idTypePret);
    
        if (!"Sur place".equalsIgnoreCase(typePret.getType())) {
            verifierQuotaPret(adherant, datePret);
        }
    
        Bibliothecaire bibliothecaire = getBibliothecaireParDefaut();
    
        Pret pret = new Pret();
        pret.setDateDebut(datePret);
        pret.setAdherant(adherant);
        pret.setExemplaire(exemplaire);
        pret.setTypePret(typePret);
        pret.setBibliothecaire(bibliothecaire);
    
        if ("Sur place".equalsIgnoreCase(typePret.getType())) {
            pret.setDateFin(datePret);
        } else {
            int duree = getDureePretPourProfil(adherant.getProfil());
            pret.setDateFin(datePret.plusDays(duree));
        }
    
        pretRepository.save(pret);
    }
    
    
    private Adherant getAdherantOrThrow(Integer idAdherant) {
        return adherantRepository.findById(idAdherant)
            .orElseThrow(() -> new IllegalArgumentException("Adhérent introuvable"));
    }
    
    private Exemplaire getExemplaireOrThrow(Integer idExemplaire) {
        return exemplaireRepository.findById(idExemplaire)
            .orElseThrow(() -> new IllegalArgumentException("Exemplaire introuvable"));
    }
    
    private void verifierAbonnementActif(Adherant adherant, LocalDateTime dateCheck) {
        boolean actif = abonnementRepository.existsByAdherant_IdAdherantAndDateDebutBeforeAndDateFinAfter(
            adherant.getIdAdherant(), dateCheck, dateCheck
        );
        if (!actif) {
            throw new IllegalStateException("Adhérent inactif à la date de prêt");
        }
    }
    
    
    private void verifierRestrictionAge(Adherant adherant, Exemplaire exemplaire) {
        Livre livre = exemplaire.getLivre();
        if (livre.getAgeRestriction() != null) {
            int age = Period.between(adherant.getDateNaissance(), LocalDate.now()).getYears();
            if (age < livre.getAgeRestriction()) {
                throw new IllegalStateException("Adhérent trop jeune pour emprunter ce livre (restriction : " 
                    + livre.getAgeRestriction() + " ans)");
            }
        }
    }
    
    private TypePret getTypePretOrThrow(Integer idTypePret) {
        return typePretRepository.findById(idTypePret)
            .orElseThrow(() -> new IllegalStateException("Type de prêt introuvable"));
    }
    
    private Bibliothecaire getBibliothecaireParDefaut() {
        return bibliothecaireRepository.findById(1)
            .orElseThrow(() -> new IllegalStateException("Bibliothécaire par défaut non trouvé"));
    }
    
    private int getDureePretPourProfil(Profil profil) {
        DureePret dureePret = dureePretRepository.findByProfil(profil)
            .orElseThrow(() -> new IllegalStateException("Durée de prêt non définie pour le profil"));
        return dureePret.getDuree();
    }

    private void verifierQuotaPret(Adherant adherant, LocalDateTime datePret) {
        int quotaMax = adherant.getProfil().getQuotaPret();
    
        TypePret surPlace = typePretRepository.findByType("Sur place")
            .orElseThrow(() -> new IllegalStateException("Type de prêt 'Sur place' introuvable"));
    
        long nombrePretsActifs = pretRepository.countPretsActifsHorsSurPlace(
            adherant.getIdAdherant(), datePret, surPlace
        );
    
        if (nombrePretsActifs >= quotaMax) {
            throw new IllegalStateException("Quota de prêt atteint. Vous avez déjà " + nombrePretsActifs + " prêts actifs.");
        }
    }
    
    
    
}
