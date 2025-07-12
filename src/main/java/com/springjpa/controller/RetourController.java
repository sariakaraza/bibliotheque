package com.springjpa.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springjpa.entity.Pret;
import com.springjpa.service.RetourService;

@Controller
public class RetourController {
    @Autowired
    private RetourService retourService;

    @PostMapping("/retour")
    public String enregistrerRetour(@RequestParam("pretId") Integer idPret,
                                    @RequestParam("dateRetour") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateRetour,
                                    Model model) {
        boolean penalise = retourService.enregistrerRetour(idPret, dateRetour);
        model.addAttribute("penalise", penalise);
        return "retour";
    }

    @GetMapping("/retour")
    public String afficherListePretsIndisponibles(Model model) {
        List<Pret> prets = retourService.listerPretsAvecExemplaireIndisponible();
        model.addAttribute("prets", prets);
        return "retour"; 
    }


}
