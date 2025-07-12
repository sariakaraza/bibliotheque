package com.springjpa.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Optional;

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
    
    @Autowired
    private ExemplaireStatutRepository exemplaireStatutRepository;

    @Autowired
    private StatutExemplaireRepository statutExemplaireRepository;
    
    @Autowired
    private PenaliteRepository penaliteRepository;

    public void effectuerPret(Integer idAdherant, Integer idExemplaire, Integer idTypePret, LocalDateTime inputDateDebut) {
        Adherant adherant = getAdherantOrThrow(idAdherant);
        Exemplaire exemplaire = getExemplaireOrThrow(idExemplaire);
    
        LocalDateTime datePret = inputDateDebut != null ? inputDateDebut : LocalDateTime.now();
    
        verifierPenaliteAdherant(adherant, datePret.toLocalDate());
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

        // Vérifier si l’exemplaire est actuellement indisponible
        Optional<ExemplaireStatut> dernierStatut = exemplaireStatutRepository.findDernierStatut(exemplaire.getIdExemplaire());

        if (dernierStatut.isPresent() && "indisponible".equalsIgnoreCase(dernierStatut.get().getStatutExemplaire().getNomStatut())) {
            throw new IllegalStateException("Cet exemplaire est actuellement indisponible.");
        }

    
        pretRepository.save(pret);

        StatutExemplaire statutIndispo = statutExemplaireRepository.findByNomStatut("indisponible")
            .orElseThrow(() -> new IllegalStateException("Statut 'indisponible' introuvable"));

        ExemplaireStatut nouveauStatut = new ExemplaireStatut();
        nouveauStatut.setExemplaire(exemplaire);
        nouveauStatut.setStatutExemplaire(statutIndispo);
        nouveauStatut.setDateStatut(LocalDateTime.now());

        exemplaireStatutRepository.save(nouveauStatut);

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

    private void verifierPenaliteAdherant(Adherant adherant, LocalDate datePret) {
        boolean penalise = penaliteRepository.isAdherantPenalise(adherant.getIdAdherant(), datePret);
        if (penalise) {
            throw new IllegalStateException("L'adhérent est pénalisé à cette date et ne peut pas emprunter.");
        }
    }
    
    
}
