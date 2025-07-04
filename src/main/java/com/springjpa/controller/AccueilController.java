package com.springjpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccueilController {

    @GetMapping("/accueil")
    public String accueilPage(){
        return "accueil";
    }

    @GetMapping("/")
    public String accueilPageRoot() {
        return "login";
    }
}
