package com.springjpa.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springjpa.entity.Penalite;

@Repository
public interface PenaliteRepository extends JpaRepository<Penalite, Integer>{
    @Query("""
        SELECT COUNT(p) > 0
        FROM Penalite p
        WHERE p.adherant.idAdherant = :idAdherant
        AND :datePret BETWEEN p.dateDebut AND p.dateFin
    """)
    boolean isAdherantPenalise(@Param("idAdherant") Integer idAdherant,
                            @Param("datePret") LocalDate datePret);

}
