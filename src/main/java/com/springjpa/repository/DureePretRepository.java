package com.springjpa.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springjpa.entity.DureePret;
import com.springjpa.entity.Profil;
@Repository
public interface DureePretRepository extends JpaRepository<DureePret, Integer> {
    Optional<DureePret> findByProfil(Profil profil);
}
