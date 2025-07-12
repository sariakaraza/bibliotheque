package com.springjpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springjpa.entity.Retour;

@Repository
public interface RetourRepository extends JpaRepository<Retour,Integer>{
    @Query("SELECT r.pret.idPret FROM Retour r")
    List<Integer> findAllPretIdsWithRetour();

    boolean existsByPret_IdPret(Integer idPret);

}
