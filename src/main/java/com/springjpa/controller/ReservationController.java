package com.springjpa.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springjpa.entity.Reservation;
import com.springjpa.repository.ReservationRepository;
import com.springjpa.service.*;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.TransactionScoped;
import jakarta.transaction.Transactional;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationRepository reservationRepository;

    @PostMapping("/new")
    public String reserver(@RequestParam("idExemplaire") Integer idExemplaire,
                        @RequestParam("dateDebut") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateDebut,
                        HttpSession session,
                        RedirectAttributes redirectAttributes) {

        Integer idAdherant = (Integer) session.getAttribute("userId");

        try {
            reservationService.reserverExemplaire(idExemplaire, idAdherant, dateDebut);
            redirectAttributes.addFlashAttribute("successMessage", "Réservation enregistrée avec succès !");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }

        return "redirect:/catalogue";
    }


    @GetMapping
    public String afficherReservations(Model model) {
        List<Reservation> reservations = reservationRepository.findAll();
        model.addAttribute("reservations", reservations);
        return "reservation";
    }

    @PostMapping("/valider")
    public String validerReservation(@RequestParam("idReservation") Integer idReservation, RedirectAttributes redirectAttributes) {
        try {
            reservationService.validerReservation(idReservation);
            redirectAttributes.addFlashAttribute("successMessage", "Réservation validée !");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erreur : " + e.getMessage());
        }
    
        return "redirect:/reservation";
    }

    

}
