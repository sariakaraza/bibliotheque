package com.springjpa.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springjpa.entity.TypePret;

@Repository
public interface TypePretRepository extends JpaRepository<TypePret, Integer> {
    Optional<TypePret> findByType(String type);

}
