package com.springjpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springjpa.entity.Livre;
import com.springjpa.service.LivreService;

@Controller
public class CatalogueController {

    @Autowired
    private LivreService livreService;

    @GetMapping("/catalogue")
    public String afficherCatalogue(Model model) {
        List<Livre> livres = livreService.listerLivresAvecExemplairesEtStatut();
        System.out.println("Livres récupérés : " + livres.size());
        model.addAttribute("livres", livres);
        return "catalogue";
    }
    
}
