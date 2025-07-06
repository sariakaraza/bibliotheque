package com.springjpa.service;

import com.springjpa.entity.Adherant;
import com.springjpa.entity.Profil;
import com.springjpa.repository.AdherantRepository;
import com.springjpa.repository.ProfilRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdherantService {

    @Autowired
    private AdherantRepository adherantRepository;

    @Autowired
    private ProfilRepository profilRepository;

    public List<Adherant> getAllAdherants() {
        return adherantRepository.findAll();
    }

    public Adherant saveAdherant(Adherant adherant) {
        return adherantRepository.save(adherant);
    }

    public Adherant getAdherantById(int id) {
        return adherantRepository.findById(id).orElse(null);
    }

    public void deleteAdherant(int id) {
        adherantRepository.deleteById(id);
    }

    public Adherant updateAdherant(Adherant adherant) {
        return adherantRepository.save(adherant);
    }

    public Adherant findByEmailAndPassword(String email, String password) {
        return adherantRepository.findByEmailAndPassword(email, password);
    }

    // public Adherant inscrireAdherant(Adherant adherant) {
    //     Profil profilDefaut = profilRepository.findByNomProfil("Adhérent");
    //     adherant.setProfil(profilDefaut);
    //     return adherantRepository.save(adherant);
    // }
}
