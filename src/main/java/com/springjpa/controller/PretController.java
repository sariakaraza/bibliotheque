package com.springjpa.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springjpa.entity.Adherant;
import com.springjpa.entity.Exemplaire;
import com.springjpa.entity.Pret;
import com.springjpa.entity.Reservation;
import com.springjpa.repository.*;
import com.springjpa.service.PretService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/pret")
public class PretController {

    @Autowired
    private PretService pretService;

    @Autowired
    private AdherantRepository adherantRepository;
    
    @Autowired
    private ExemplaireRepository exemplaireRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @GetMapping("/mes-prets")
    public String afficherPretsAdherant(Model model, HttpSession session) {
        Integer idAdherant = (Integer) session.getAttribute("userId");

        List<Pret> prets = pretService.listerPretsParAdherant(idAdherant);
        model.addAttribute("mesPrets", prets);

        return "mes-prets"; // 👉 page JSP à créer
    }


    @GetMapping
    public String afficherFormulairePret(Model model) {
        List<Adherant> adherants = adherantRepository.findAll();
        List<Exemplaire> exemplaires = exemplaireRepository.findAll();

        List<Reservation> reservationsValidees = reservationRepository.findByStatut("valide");

        model.addAttribute("adherants", adherants);
        model.addAttribute("exemplaires", exemplaires);
        model.addAttribute("reservationsValidees", reservationsValidees);

        return "pret";
    }


    @PostMapping("/save")
    public String enregistrerPret(
            @RequestParam Integer idAdherant,
            @RequestParam Integer idExemplaire,
            @RequestParam Integer idTypePret,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateDebut,
            Model model) {
        try {
            pretService.effectuerPret(idAdherant, idExemplaire, idTypePret, dateDebut);
            // pretService.effectuerPret(idAdherant, idExemplaire, idTypePret);
            model.addAttribute("successMessage", "Prêt enregistré avec succès.");
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "pret";  
    }

    @PostMapping("/fromReservation")
    public String effectuerPretDepuisReservation(
            @RequestParam Integer idAdherant,
            @RequestParam Integer idExemplaire,
            @RequestParam Integer idTypePret,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateDebut,
            @RequestParam Integer idReservation,
            Model model) {
        try {
            pretService.effectuerPret(idAdherant, idExemplaire, idTypePret, dateDebut);
            
            // Optionnel : changer le statut de la réservation en "utilisée" ou "traitée"
            reservationRepository.findById(idReservation).ifPresent(r -> {
                r.setStatut("traitée");
                reservationRepository.save(r);
            });

            model.addAttribute("successMessage", "Prêt effectué à partir de la réservation.");
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }

        return afficherFormulairePret(model); // Recharge la même page avec les données à jour
    }


    @PostMapping("/prolonger")
    public String prolongerPret(@RequestParam Integer idPret, RedirectAttributes redirectAttributes) {
        try {
            pretService.demanderProlongement(idPret);
            redirectAttributes.addFlashAttribute("successMessage", "Demande de prolongement envoyée !");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erreur : " + e.getMessage());
        }
        return "redirect:/pret/mes-prets";
    }

}
