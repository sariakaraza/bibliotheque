package com.springjpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springjpa.entity.Prolongement;
import com.springjpa.repository.ProlongementRepository;

@Controller
@RequestMapping("/admin/prolongements")
public class ProlongementController {

    @Autowired
    private ProlongementRepository prolongementRepository;

    @GetMapping
    public String afficherTousLesProlongements(Model model) {
        List<Prolongement> prolongements = prolongementRepository.findAll();
        model.addAttribute("prolongements", prolongements);
        return "admin-prolongements"; // nom du fichier JSP
    }

    @PostMapping("/valider")
    public String validerProlongement(@RequestParam("idProlongement") Integer id, RedirectAttributes redirectAttributes) {
        try {
            Prolongement p = prolongementRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Prolongement introuvable"));
            p.setStatut("valide");
            prolongementRepository.save(p);
            redirectAttributes.addFlashAttribute("successMessage", "Prolongement validé !");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }

        return "redirect:/admin/prolongements";
    }
}

