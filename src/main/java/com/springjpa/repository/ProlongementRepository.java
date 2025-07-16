package com.springjpa.repository;

import com.springjpa.entity.Prolongement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProlongementRepository extends JpaRepository<Prolongement, Integer> {
    List<Prolongement> findByStatut(String statut);

    @Query("SELECT COUNT(p) FROM Prolongement p WHERE p.pret.adherant.idAdherant = :idAdherant AND p.statut = 'en attente'")
    long countProlongementsEnAttenteByAdherantId(@Param("idAdherant") Integer idAdherant);
   
    @Query("""
        SELECT p FROM Prolongement p
        WHERE p.pret.adherant.idAdherant = :idAdherant
        AND p.statut = 'en attente'
    """)
    List<Prolongement> findEnAttenteByAdherant(@Param("idAdherant") Integer idAdherant);

}
