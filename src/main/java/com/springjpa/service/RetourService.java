package com.springjpa.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springjpa.entity.Adherant;
import com.springjpa.entity.ExemplaireStatut;
import com.springjpa.entity.Penalite;
import com.springjpa.entity.Pret;
import com.springjpa.entity.Retour;
import com.springjpa.entity.StatutExemplaire;
import com.springjpa.repository.ExemplaireStatutRepository;
import com.springjpa.repository.PenaliteRepository;
import com.springjpa.repository.PretRepository;
import com.springjpa.repository.RetourRepository;
import com.springjpa.repository.StatutExemplaireRepository;

import jakarta.transaction.Transactional;

@Service
public class RetourService {
    @Autowired
    private PretRepository pretRepository;

    @Autowired
    private RetourRepository retourRepository;

    @Autowired
    private StatutExemplaireRepository statutExemplaireRepository;

    @Autowired
    private ExemplaireStatutRepository exemplaireStatutRepository;

    @Autowired
    private PenaliteRepository penaliteRepository;

    public List<Pret> listerPretsAvecExemplaireIndisponible() {
        List<Pret> prets = pretRepository.findAllPretsNonRetournes();
        List<Pret> resultat = new ArrayList<>();
    
        for (Pret pret : prets) {
            Optional<ExemplaireStatut> dernier = exemplaireStatutRepository.findDernierStatut(
                pret.getExemplaire().getIdExemplaire()
            );
            if (dernier.isPresent() && 
                "indisponible".equalsIgnoreCase(dernier.get().getStatutExemplaire().getNomStatut())) {
                resultat.add(pret);
            }
        }
    
        return resultat;
    }
    

    @Transactional
    public void enregistrerRetour(Integer idPret, LocalDateTime dateRetour) {
        Pret pret = pretRepository.findById(idPret)
            .orElseThrow(() -> new IllegalArgumentException("Prêt introuvable"));

        penaliser(pret, dateRetour);
        // Enregistrer dans la table retour
        Retour retour = new Retour();
        retour.setDateRetour(dateRetour);
        retour.setPret(pret);
        retourRepository.save(retour);

        // Changer le statut de l'exemplaire à "disponible"
        StatutExemplaire dispo = statutExemplaireRepository.findByNomStatut("disponible")
            .orElseThrow(() -> new IllegalStateException("Statut 'disponible' introuvable"));

        ExemplaireStatut es = new ExemplaireStatut();
        es.setExemplaire(pret.getExemplaire());
        es.setStatutExemplaire(dispo);
        es.setDateStatut(LocalDateTime.now());

        exemplaireStatutRepository.save(es);
    }

    private void penaliser(Pret pret, LocalDateTime dateRetour) {
        boolean dejaRetourne = retourRepository.existsByPret_IdPret(pret.getIdPret());
        if (dejaRetourne) {
            throw new IllegalStateException("Ce prêt a déjà été retourné.");
        }

        if (dateRetour.isBefore(pret.getDateDebut())) {
            throw new IllegalStateException("La date de retour est antérieure à la date de début.");
        }

        if (dateRetour.isAfter(pret.getDateFin())) {
            Adherant adherant = pret.getAdherant();
            int dureePenalite = adherant.getProfil().getDureePenalite();

            Penalite penalite = new Penalite();
            penalite.setAdherant(adherant);
            penalite.setDateDebut(dateRetour.toLocalDate());
            penalite.setDateFin(dateRetour.toLocalDate().plusDays(dureePenalite));

            penaliteRepository.save(penalite);
        }
    }

}
