package com.springjpa.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springjpa.entity.ExemplaireStatut;
import com.springjpa.entity.Pret;
import com.springjpa.entity.Retour;
import com.springjpa.entity.StatutExemplaire;
import com.springjpa.repository.ExemplaireStatutRepository;
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

}
