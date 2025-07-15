package com.springjpa.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PeriodeDTO {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateDebut;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateFin;
    public LocalDate getDateDebut() {
        return dateDebut;
    }
    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }
    public LocalDate getDateFin() {
        return dateFin;
    }
    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }


    
}