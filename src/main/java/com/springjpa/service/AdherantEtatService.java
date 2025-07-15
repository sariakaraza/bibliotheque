package com.springjpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springjpa.dto.EtatAdherantDTO;
import com.springjpa.dto.PeriodeDTO;
import com.springjpa.entity.*;
import com.springjpa.repository.*;

@Service
public class AdherantEtatService {

    @Autowired private AdherantRepository adherantRepository;
    @Autowired private AbonnementRepository abonnementRepository;
    @Autowired private PenaliteRepository penaliteRepository;

    public EtatAdherantDTO getInfosAdherant(Integer idAdherant) {
        Adherant adherant = adherantRepository.findById(idAdherant)
            .orElseThrow(() -> new IllegalArgumentException("Adhérent introuvable"));

        EtatAdherantDTO dto = new EtatAdherantDTO();
        dto.setId(adherant.getIdAdherant());
        dto.setNom(adherant.getNomAdherant());
        dto.setPrenom(adherant.getPrenomAdherant());

        // Abonnements
        List<PeriodeDTO> abonnements = abonnementRepository
            .findByAdherant_IdAdherant(idAdherant)
            .stream()
            .map(a -> {
                PeriodeDTO p = new PeriodeDTO();
                p.setDateDebut(a.getDateDebut().toLocalDate());
                p.setDateFin(a.getDateFin().toLocalDate());
                return p;
            }).toList();

        // Pénalités
        List<PeriodeDTO> penalites = penaliteRepository
            .findByAdherant_IdAdherant(idAdherant)
            .stream()
            .map(pen -> {
                PeriodeDTO p = new PeriodeDTO();
                p.setDateDebut(pen.getDateDebut());
                p.setDateFin(pen.getDateFin());
                return p;
            }).toList();

        dto.setAbonnements(abonnements);
        dto.setPenalites(penalites);
        dto.setQuotaPret(adherant.getProfil().getQuotaPret());
        return dto;
    }
}
