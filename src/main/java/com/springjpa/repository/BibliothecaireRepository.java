package com.springjpa.repository;


import com.springjpa.entity.Bibliothecaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BibliothecaireRepository extends JpaRepository<Bibliothecaire, Integer> {
    Bibliothecaire findByEmailAndPassword(String email, String password);
}
