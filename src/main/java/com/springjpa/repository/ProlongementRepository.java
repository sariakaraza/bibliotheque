package com.springjpa.repository;

import com.springjpa.entity.Prolongement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProlongementRepository extends JpaRepository<Prolongement, Integer> {
    List<Prolongement> findByStatut(String statut);
}
