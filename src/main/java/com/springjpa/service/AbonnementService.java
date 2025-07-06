package com.springjpa.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springjpa.entity.Abonnement;
import com.springjpa.entity.Adherant;
import com.springjpa.repository.AbonnementRepository;

@Service
public class AbonnementService {

    @Autowired
    private AbonnementRepository abonnementRepository;


    public boolean isActif(Adherant adherant) {
        LocalDateTime now = LocalDateTime.now();

        List<Abonnement> abonnements = abonnementRepository.findByAdherant_IdAdherant(adherant.getIdAdherant());

        return abonnements.stream().anyMatch(a ->
            a.getDateDebut() != null &&
            a.getDateFin() != null &&
            !now.isBefore(a.getDateDebut()) &&
            !now.isAfter(a.getDateFin())
        );
    }
}
