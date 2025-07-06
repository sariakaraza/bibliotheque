package com.springjpa.service;

import com.springjpa.entity.Bibliothecaire;
import com.springjpa.repository.BibliothecaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BibliothecaireService {

    @Autowired
    private BibliothecaireRepository bibliothecaireRepository;

    public List<Bibliothecaire> getAllBibliothecaires() {
        return bibliothecaireRepository.findAll();
    }

    public Bibliothecaire saveBibliothecaire(Bibliothecaire bibliothecaire) {
        return bibliothecaireRepository.save(bibliothecaire);
    }

    public Bibliothecaire getBibliothecaireById(int id) {
        return bibliothecaireRepository.findById(id).orElse(null);
    }

    public void deleteBibliothecaire(int id) {
        bibliothecaireRepository.deleteById(id);
    }

    public Bibliothecaire updateBibliothecaire(Bibliothecaire bibliothecaire) {
        return bibliothecaireRepository.save(bibliothecaire);
    }

    public Bibliothecaire findByEmailAndPassword(String email, String password) {
        return bibliothecaireRepository.findByEmailAndPassword(email, password);
    }

}
