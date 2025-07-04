package com.springjpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springjpa.entity.Livre;
import com.springjpa.repository.LivreRepository;

@Service
public class LivreService {
    @Autowired
    private LivreRepository livreRepository;

    public List<Livre> listerLivresAvecExemplairesEtStatut() {
        return livreRepository.findAllWithExemplairesEtStatut();
    }
}
