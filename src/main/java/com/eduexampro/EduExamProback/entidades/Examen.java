package com.eduexampro.EduExamProback.entidades;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Examenes")
@Getter
@Setter
@AllArgsConstructor
public class Examen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idExamen;

    private String nombre;
    private String descripcion;
    private String categoria;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDisponible;

    private Integer tiempoLimite;

    @ManyToOne
    @JoinColumn(name = "ID_Curso", referencedColumnName = "idCurso")
    private Curso curso;

}
