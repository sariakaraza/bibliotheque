package com.springjpa.controller;

import com.springjpa.entity.Bibliothecaire;
import com.springjpa.entity.Livre;
import com.springjpa.entity.Adherant;
import com.springjpa.service.BibliothecaireService;
import com.springjpa.service.LivreService;
import com.springjpa.service.AdherantService;
import jakarta.servlet.http.HttpSession;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private BibliothecaireService bibliothecaireService;

    @Autowired
    private AdherantService adherantService;

    @Autowired
    private LivreService livreService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/catalogue")
    public String afficherCatalogue(Model model) {
        List<Livre> livres = livreService.listerLivresAvecExemplairesEtStatut();
        System.out.println("Livres récupérés : " + livres.size());
        model.addAttribute("livres", livres);
        return "catalogue";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

        Bibliothecaire bibliothecaire = bibliothecaireService.findByEmailAndPassword(email, password);
        if (bibliothecaire != null) {
            session.setAttribute("userType", "bibliothecaire");
            session.setAttribute("userId", bibliothecaire.getIdAdmin());
            return "pret";
        }

        Adherant adherant = adherantService.findByEmailAndPassword(email, password);
        if (adherant != null) {
            session.setAttribute("userType", "adherant");
            session.setAttribute("userId", adherant.getIdAdherant());
            return "redirect:/catalogue";
        }

        model.addAttribute("error", "Identifiants incorrects");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "login";
    }
}
