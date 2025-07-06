package com.springjpa.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springjpa.entity.Exemplaire;
import com.springjpa.entity.ExemplaireStatut;
import com.springjpa.entity.Livre;
import com.springjpa.repository.ExemplaireStatutRepository;
import com.springjpa.repository.LivreRepository;


@Service
public class LivreService {
    @Autowired
    private LivreRepository livreRepository;

    @Autowired
    private ExemplaireStatutRepository exemplaireStatutRepository;

    @Transactional(readOnly = true)
    public List<Livre> listerLivresAvecExemplairesEtStatut() {
        List<Livre> livres = livreRepository.findAll(); // pas besoin de fetch ici

        for (Livre livre : livres) {
            Hibernate.initialize(livre.getExemplaires());
            for (Exemplaire ex : livre.getExemplaires()) {
                Optional<ExemplaireStatut> dernier = exemplaireStatutRepository.findDernierStatut(ex.getIdExemplaire());
                dernier.ifPresent(stat -> ex.setDernierStatutTemporaire(stat.getStatutExemplaire().getNomStatut()));
            }
        }        

        return livres;
        }
}
