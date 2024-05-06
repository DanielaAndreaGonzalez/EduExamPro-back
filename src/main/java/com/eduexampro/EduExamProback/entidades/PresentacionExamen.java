package com.eduexampro.EduExamProback.entidades;

import java.math.BigDecimal;
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
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Presentacion_Examen")
@Getter
@Setter
public class PresentacionExamen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPresentacion;

    @ManyToOne
    @JoinColumn(name = "ID_Examen", referencedColumnName = "idExamen")
    private Examen examen;

    @ManyToOne
    @JoinColumn(name = "ID_Usuario", referencedColumnName = "idUsuario")
    private Usuario usuario;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraInicio;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraFin;

    private BigDecimal calificacion;
}
