package com.springjpa.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springjpa.entity.*;
import com.springjpa.repository.*;

import jakarta.transaction.Transactional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private AdherantRepository adherantRepository;

    @Autowired
    private ExemplaireRepository exemplaireRepository;
 
    @Transactional
    public void reserverExemplaire(Integer idExemplaire, Integer idAdherant, LocalDateTime dateDebut) {
        Adherant adherant = adherantRepository.findById(idAdherant)
            .orElseThrow(() -> new IllegalArgumentException("Adhérent introuvable"));

        Exemplaire exemplaire = exemplaireRepository.findById(idExemplaire)
            .orElseThrow(() -> new IllegalArgumentException("Exemplaire introuvable"));

        // long nbReservationsActives = reservationRepository.countActiveReservationsByAdherantId(idAdherant);
        // if (nbReservationsActives >= adherant.getProfil().getQuotaReservation()) {
        //     throw new IllegalStateException("Quota de réservation dépassé.");
        // }

        // Création de la réservation
        Reservation reservation = new Reservation();
        reservation.setAdherant(adherant);
        reservation.setExemplaire(exemplaire);
        reservation.setDateDeReservation(dateDebut);
        reservationRepository.save(reservation);
    }

    @Transactional
    public void validerReservation(Integer idReservation) {
        Reservation reservation = reservationRepository.findById(idReservation)
            .orElseThrow(() -> new IllegalArgumentException("Réservation introuvable"));

        reservation.setStatut("valide");
        reservationRepository.save(reservation);
    }

}
