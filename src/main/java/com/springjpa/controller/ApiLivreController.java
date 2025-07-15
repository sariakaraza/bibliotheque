// controller/ApiLivreController.java
package com.springjpa.controller;

import com.springjpa.dto.*;
import com.springjpa.entity.*;
import com.springjpa.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/livres")
public class ApiLivreController {

    @Autowired
    private LivreRepository livreRepository;

    @Autowired
    private ExemplaireStatutRepository exemplaireStatutRepository;

    @GetMapping("/{id}")
    public ResponseEntity<?> getLivreAvecExemplaires(@PathVariable Integer id) {
        Optional<Livre> optLivre = livreRepository.findByIdWithExemplaires(id);
        
        if (optLivre.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livre introuvable");
        }

        Livre livre = optLivre.get();

        // Pour chaque exemplaire : on récupère le dernier statut
        for (Exemplaire ex : livre.getExemplaires()) {
            exemplaireStatutRepository.findDernierStatut(ex.getIdExemplaire())
                .ifPresentOrElse(
                    es -> ex.setDernierStatutTemporaire(es.getStatutExemplaire().getNomStatut()),
                    () -> ex.setDernierStatutTemporaire("Inconnu")
                );
        }

        return ResponseEntity.ok(livre);
    }
}

