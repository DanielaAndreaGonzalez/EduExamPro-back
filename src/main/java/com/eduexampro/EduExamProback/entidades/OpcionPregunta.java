package com.eduexampro.EduExamProback.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "opcion_Pregunta")
@Getter
@Setter
public class OpcionPregunta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "opcionPregunta_generator")
    @SequenceGenerator(name = "opcionPregunta_generator", sequenceName = "opcion_pregunta_seq", allocationSize = 1)
    private Long idOpcion;

    private String textoOpcion;
    private Boolean esCorrecta;

    @ManyToOne
    @JoinColumn(name = "ID_PREGUNTA", referencedColumnName = "idPregunta")
    private Pregunta pregunta;
}
