package com.springjpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springjpa.entity.ExemplaireStatut;
import com.springjpa.entity.Livre;

@Repository
public interface LivreRepository extends JpaRepository<Livre, Integer> {

}

