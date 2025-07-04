package com.springjpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springjpa.service.PretService;

@Controller
@RequestMapping("/pret")
public class PretController {

    @Autowired
    private PretService pretService;

    @GetMapping
    public String afficherFormulairePret() {
        return "pret";  // retourne la page JSP du formulaire prêt
    }

    @PostMapping("/save")
    public String enregistrerPret(
            @RequestParam Integer idAdherant,
            @RequestParam Integer idExemplaire,
            @RequestParam Integer idTypePret,
            Model model) {
        try {
            pretService.effectuerPret(idAdherant, idExemplaire, idTypePret);
            model.addAttribute("successMessage", "Prêt enregistré avec succès.");
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "pret";  // retourne la même page avec message
    }
}
