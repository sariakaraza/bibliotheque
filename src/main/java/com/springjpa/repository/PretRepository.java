package com.springjpa.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.springjpa.entity.Pret;
import com.springjpa.entity.Prolongement;
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

    @Query("SELECT p FROM Pret p WHERE p.idPret NOT IN (SELECT r.pret.idPret FROM Retour r)")
    List<Pret> findAllPretsNonRetournes();
                                      
  
    List<Pret> findByAdherant_IdAdherant(Integer idAdherant); 
    
    // @Query("SELECT COUNT(p) FROM Pret p WHERE p.adherant.idAdherant = :idAdherant " +
    //     "AND p.dateFin < :dateNow AND p.dateRetour IS NULL")
    // long countPretsEnRetardNonRendus(@Param("idAdherant") Integer idAdherant,
    //                                 @Param("dateNow") LocalDateTime dateNow);
    // @Query("SELECT COUNT(p) FROM Pret p " +
    //     "WHERE p.adherant.idAdherant = :idAdherant " +
    //     "AND p.dateFin < :dateNow " +
    //     "AND p.dateRetour IS NULL")
    // long countPretsEnRetardNonRendus(@Param("idAdherant") Integer idAdherant,
    //                             @Param("dateNow") LocalDateTime dateNow);

}
