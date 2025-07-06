package com.springjpa.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springjpa.entity.StatutExemplaire;

@Repository
public interface StatutExemplaireRepository extends JpaRepository<StatutExemplaire, Integer> {
    Optional<StatutExemplaire> findByNomStatut(String nomStatut);
}

