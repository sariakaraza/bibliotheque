package com.springjpa.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "prolongement")
public class Prolongement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProlongement;

    @ManyToOne
    @JoinColumn(name = "id_pret", nullable = false)
    private Pret pret;

    private LocalDateTime dateProlongement;

    private String statut = "en attente";

    public Integer getIdProlongement() { return idProlongement; }
    public void setIdProlongement(Integer idProlongement) { this.idProlongement = idProlongement; }

    public Pret getPret() { return pret; }
    public void setPret(Pret pret) { this.pret = pret; }

    public LocalDateTime getDateProlongement() { return dateProlongement; }
    public void setDateProlongement(LocalDateTime dateProlongement) { this.dateProlongement = dateProlongement; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }
}
