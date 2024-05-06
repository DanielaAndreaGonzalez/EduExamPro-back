package com.eduexampro.EduExamProback.dto;

import java.util.Date;

public record ExamenDto(
        Long idExamen,
        String nombre,
        String descripcion,
        String categoria,
        Date fechaDisponible,
        Integer tiempoLimite,
        Long idCurso) {

}
