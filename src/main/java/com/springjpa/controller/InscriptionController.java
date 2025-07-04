package com.springjpa.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springjpa.entity.Adherant;
import com.springjpa.entity.Profil;
import com.springjpa.repository.AdherantRepository;
import com.springjpa.repository.ProfilRepository;
import com.springjpa.service.AdherantService;

import org.springframework.ui.Model;

@Controller
public class InscriptionController {

    @Autowired
    private AdherantService adherantService;

    @Autowired
    private ProfilRepository profilRepository;

    @Autowired
    private AdherantRepository adherantRepository;      

    @GetMapping("/inscription")
    public String showForm(Model model) {
        model.addAttribute("adherant", new Adherant());
        return "inscription";
    }

    // @PostMapping("/inscription")
    // public String inscrire(@ModelAttribute Adherant adherant) {
    //     adherantService.inscrireAdherant(adherant);
    //     return "login";
    // }

    @PostMapping("/inscription")
    public String inscrireAdherant(@RequestParam String nom,
                               @RequestParam String prenom,
                               @RequestParam String email,
                               @RequestParam String password,
                               @RequestParam String dateNaissance,
                               @RequestParam String nomProfil,
                               Model model) {
    Profil profilChoisi = profilRepository.findByNomProfil(nomProfil);
    if (profilChoisi == null) {
        model.addAttribute("error", "Profil invalide.");
        return "inscription"; // ou la page d'inscription
    }

    Adherant adherant = new Adherant();
    adherant.setNomAdherant(nom);
    adherant.setPrenomAdherant(prenom);
    adherant.setEmail(email);
    adherant.setPassword(password);
    adherant.setDateNaissance(LocalDate.parse(dateNaissance)); // adapte si besoin
    adherant.setProfil(profilChoisi);

    adherantRepository.save(adherant);
    return "redirect:/login";
}

}
