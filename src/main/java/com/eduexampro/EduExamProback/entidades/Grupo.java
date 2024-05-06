package com.eduexampro.EduExamProback.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Grupos")
@Getter
@Setter
public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGrupo;

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "ID_Curso", referencedColumnName = "idCurso")
    private Curso curso;
}
