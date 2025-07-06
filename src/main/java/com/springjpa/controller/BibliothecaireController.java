package com.springjpa.controller;

import com.springjpa.entity.Bibliothecaire;
import com.springjpa.service.BibliothecaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bibliothecaires")
public class BibliothecaireController {

    @Autowired
    private BibliothecaireService bibliothecaireService;

    @GetMapping
    public List<Bibliothecaire> getAll() {
        return bibliothecaireService.getAllBibliothecaires();
    }

    @GetMapping("/{id}")
    public Bibliothecaire getById(@PathVariable int id) {
        return bibliothecaireService.getBibliothecaireById(id);
    }

    @PostMapping
    public Bibliothecaire create(@RequestBody Bibliothecaire bibliothecaire) {
        return bibliothecaireService.saveBibliothecaire(bibliothecaire);
    }

    @PutMapping("/{id}")
    public Bibliothecaire update(@PathVariable int id, @RequestBody Bibliothecaire updated) {
        updated.setIdAdmin(id);
        return bibliothecaireService.updateBibliothecaire(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        bibliothecaireService.deleteBibliothecaire(id);
    }
}
