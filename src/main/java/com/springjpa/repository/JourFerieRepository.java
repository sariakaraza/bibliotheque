package com.springjpa.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springjpa.entity.JourFerie;

public interface JourFerieRepository extends JpaRepository<JourFerie, Integer> {
    boolean existsByDate(LocalDate date);
    Optional<JourFerie> findByDate(LocalDate date);
}
