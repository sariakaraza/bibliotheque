package com.springjpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.springjpa.entity.Penalite;
import com.springjpa.service.PenaliteService;

@Controller
public class PenaliteController {

    @Autowired
    private PenaliteService penaliteService;

    @GetMapping("/penalites")
    public String afficherPenalites(Model model) {
        List<Penalite> penalites = penaliteService.listerPenalites();
        model.addAttribute("penalites", penalites);
        return "penalites"; 
    }
}
