package com.springjpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springjpa.entity.Penalite;
import com.springjpa.repository.PenaliteRepository;

@Service
public class PenaliteService {

    @Autowired
    private PenaliteRepository penaliteRepository;

    public List<Penalite> listerPenalites() {
        return penaliteRepository.findAllWithAdherant();
    }
}

