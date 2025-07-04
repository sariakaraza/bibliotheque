package com.springjpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springjpa.entity.Livre;

@Repository
public interface LivreRepository extends JpaRepository<Livre, Integer> {
    @Query("SELECT DISTINCT l FROM Livre l LEFT JOIN FETCH l.exemplaires e LEFT JOIN FETCH e.statutExemplaire")
    List<Livre> findAllWithExemplairesEtStatut();
}

