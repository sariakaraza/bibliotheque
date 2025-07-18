package com.springjpa.service;

import java.time.LocalDateTime;
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
    @Autowired private TypePretRepository typePretRepository;
    @Autowired private PretRepository pretRepository;

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

        LocalDateTime now = LocalDateTime.now();
        // Vérifier s’il est abonné aujourd’hui
        boolean estAbonneAjd = abonnementRepository.existsByAdherant_IdAdherantAndDateDebutBeforeAndDateFinAfter(
            idAdherant, now, now);

        // Vérifier s’il est pénalisé aujourd’hui
        boolean estPenaliseAjd = penaliteRepository.isAdherantPenalise(idAdherant, now.toLocalDate());
        // boolean estPenaliseAjd = penaliteRepository.isAdherantPenalise(idAdherant, now.toLocalDate())
        // || pretRepository.countPretsEnRetardNonRendus(idAdherant, now) > 0;
    
        int quotaMax = adherant.getProfil().getQuotaPret();
        TypePret typeSurPlace = typePretRepository.findByType("Sur place")
            .orElseThrow(() -> new IllegalStateException("Type 'Sur place' introuvable"));

        long nombrePretsActifs = pretRepository.countPretsActifsHorsSurPlace(
            adherant.getIdAdherant(), LocalDateTime.now(), typeSurPlace
        );
        int quotaRestantAjd = (int) Math.max(0, quotaMax - nombrePretsActifs);

        dto.setAbonnements(abonnements);
        dto.setPenalites(penalites);
        dto.setQuotaPret(adherant.getProfil().getQuotaPret());
        dto.setEstAbonneAjd(estAbonneAjd);
        dto.setEstPenaliseAjd(estPenaliseAjd);
        dto.setQuotaRestantAjd(quotaRestantAjd);
        return dto;
    }
}
