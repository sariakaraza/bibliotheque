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

import com.springjpa.entity.Adherant;
import com.springjpa.entity.Exemplaire;
import com.springjpa.repository.*;
import com.springjpa.service.PretService;

@Controller
@RequestMapping("/pret")
public class PretController {

    @Autowired
    private PretService pretService;

    @Autowired
    private AdherantRepository adherantRepository;

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    @GetMapping
    public String afficherFormulairePret(Model model) {
        List<Adherant> adherants = adherantRepository.findAll();
        List<Exemplaire> exemplaires = exemplaireRepository.findAll();

        model.addAttribute("adherants", adherants);
        model.addAttribute("exemplaires", exemplaires);
        return "pret";  // retourne la page JSP du formulaire prêt
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
        return "pret";  // retourne la même page avec message
    }




}
