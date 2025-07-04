package com.springjpa.controller;

import com.springjpa.entity.Bibliothecaire;
import com.springjpa.entity.Adherant;
import com.springjpa.service.BibliothecaireService;
import com.springjpa.service.AdherantService;
import jakarta.servlet.http.HttpSession;
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

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
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
            return "catalogue";
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
