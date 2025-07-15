package com.springjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springjpa.entity.Weekend;

public interface WeekendRepository extends JpaRepository<Weekend, Integer> {

    @Query("SELECT w FROM Weekend w ORDER BY w.id ASC LIMIT 1")
    Weekend findPremier();
}
