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
@Table(name = "Respuesta_Estudiante")
@Getter
@Setter
public class RespuestaEstudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRespuesta;

    @ManyToOne
    @JoinColumn(name = "ID_Presentacion", referencedColumnName = "idPresentacion")
    private PresentacionExamen presentacionExamen;

    @ManyToOne
    @JoinColumn(name = "ID_Pregunta", referencedColumnName = "idPregunta")
    private Pregunta pregunta;

    @ManyToOne
    @JoinColumn(name = "ID_Opcion_Elegida", referencedColumnName = "idOpcion")
    private OpcionPregunta opcionElegida;

}
