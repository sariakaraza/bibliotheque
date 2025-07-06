package com.springjpa.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.springjpa.entity.Pret;
import com.springjpa.entity.TypePret;

@Repository
public interface PretRepository extends JpaRepository<Pret, Integer> {
    @Query("""
        SELECT COUNT(p) FROM Pret p
        WHERE p.adherant.idAdherant = :idAdherant
        AND p.typePret <> :typePretExclu
        AND p.dateDebut <= :datePret
        AND (p.dateFin IS NULL OR p.dateFin > :datePret)
    """)
    long countPretsActifsHorsSurPlace(@Param("idAdherant") Integer idAdherant,
                                      @Param("datePret") LocalDateTime datePret,
                                      @Param("typePretExclu") TypePret typePretExclu);
    
    

}
