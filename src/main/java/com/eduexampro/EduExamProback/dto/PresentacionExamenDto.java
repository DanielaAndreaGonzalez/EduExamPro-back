package com.eduexampro.EduExamProback.dto;

import java.util.Date;

public record PresentacionExamenDto(
        Long idPresentacion,
        ExamenDto examenDto,
        UsuarioDto usuarioDto,
        Date fechaHoraInicio,
        Date fechaHoraFin,
        Double calificacion) {
}
