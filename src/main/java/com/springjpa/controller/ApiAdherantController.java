package com.springjpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springjpa.dto.EtatAdherantDTO;
import com.springjpa.service.AdherantEtatService;

@RestController
@RequestMapping("/api/adherants")
public class ApiAdherantController {

    @Autowired
    private AdherantEtatService adherantEtatService;

    @GetMapping("/{id}")
    public ResponseEntity<EtatAdherantDTO> getEtat(@PathVariable Integer id) {
        EtatAdherantDTO dto = adherantEtatService.getInfosAdherant(id);
        return ResponseEntity.ok(dto);
    }
}
