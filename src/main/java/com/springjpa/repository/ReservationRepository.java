package com.springjpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springjpa.entity.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findByStatut(String statut);
    
    @Query("SELECT COUNT(r) FROM Reservation r WHERE r.adherant.idAdherant = :idAdherant AND r.statut = 'valide'")
    long countActiveReservationsByAdherantId(@Param("idAdherant") Integer idAdherant);

}

