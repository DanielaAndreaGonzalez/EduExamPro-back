package com.eduexampro.EduExamProback.dto;

public record OpcionPreguntaDto(
        Long idOpcion,
        String texto,
        Boolean esCorrecta) {
}
