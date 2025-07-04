package com.springjpa.controller;

import com.springjpa.entity.Adherant;
import com.springjpa.service.AdherantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adherants")
public class AdherantController {

    @Autowired
    private AdherantService adherantService;

    @GetMapping
    public List<Adherant> getAll() {
        return adherantService.getAllAdherants();
    }

    @GetMapping("/{id}")
    public Adherant getById(@PathVariable int id) {
        return adherantService.getAdherantById(id);
    }

    @PostMapping
    public Adherant create(@RequestBody Adherant adherant) {
        return adherantService.saveAdherant(adherant);
    }

    @PutMapping("/{id}")
    public Adherant update(@PathVariable int id, @RequestBody Adherant updated) {
        updated.setIdAdherant(id);
        return adherantService.updateAdherant(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        adherantService.deleteAdherant(id);
    }
}
