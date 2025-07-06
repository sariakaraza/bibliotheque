package com.springjpa.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.springjpa.entity.Abonnement;

public interface AbonnementRepository extends JpaRepository<Abonnement, Integer> {
    List<Abonnement> findByAdherant_IdAdherant(Integer idAdherant);

    boolean existsByAdherant_IdAdherantAndDateDebutBeforeAndDateFinAfter(
        Integer idAdherant, LocalDateTime start, LocalDateTime end);
}

