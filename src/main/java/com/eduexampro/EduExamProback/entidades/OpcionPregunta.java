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
@Table(name = "Opcion_Pregunta")
@Getter
@Setter
public class OpcionPregunta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOpcion;

    private String textoOpcion;
    private Boolean esCorrecta;

    @ManyToOne
    @JoinColumn(name = "ID_Pregunta", referencedColumnName = "idPregunta")
    private Pregunta pregunta;
}
