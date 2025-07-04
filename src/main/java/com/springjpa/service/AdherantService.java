package com.springjpa.service;

import com.springjpa.entity.Adherant;
import com.springjpa.repository.AdherantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdherantService {

    @Autowired
    private AdherantRepository adherantRepository;

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
}
