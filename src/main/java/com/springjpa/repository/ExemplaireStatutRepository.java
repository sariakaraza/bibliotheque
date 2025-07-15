package com.springjpa.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.springjpa.entity.Exemplaire;
import com.springjpa.entity.ExemplaireStatut;

@Repository
public interface ExemplaireStatutRepository extends JpaRepository<ExemplaireStatut, Integer> {

    @Query("SELECT es FROM ExemplaireStatut es WHERE es.exemplaire.idExemplaire = :idExemplaire ORDER BY es.dateStatut DESC")
    List<ExemplaireStatut> findStatutsByExemplaireOrderByDateDesc(@Param("idExemplaire") Integer idExemplaire);

    default Optional<ExemplaireStatut> findDernierStatut(Integer idExemplaire) {
        List<ExemplaireStatut> statuts = findStatutsByExemplaireOrderByDateDesc(idExemplaire);
        return statuts.isEmpty() ? Optional.empty() : Optional.of(statuts.get(0));
    }

    // @Query("""
    //     SELECT se.nomStatut
    //     FROM ExemplaireStatut es
    //     JOIN es.statutExemplaire se
    //     WHERE es.exemplaire.idExemplaire = :idExemplaire
    //     ORDER BY es.dateStatut DESC
    //     LIMIT 1
    // """)
    // String findDernierStatutParExemplaire(@Param("idExemplaire") Integer idExemplaire);

    // @Query("SELECT es FROM ExemplaireStatut es " +
    //        "WHERE es.exemplaire.idExemplaire = :idExemplaire " +
    //        "ORDER BY es.dateStatut DESC LIMIT 1")
    // Optional<ExemplaireStatut> findDernierStatut(@Param("idExemplaire") Integer idExemplaire);
}

