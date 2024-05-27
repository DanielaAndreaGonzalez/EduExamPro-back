package com.eduexampro.EduExamProback.dto;

import java.util.List;

public record PreguntaDto(
        Long id,
        String texto,
        String tipo,
        Boolean esPublica,
        List<OpcionPreguntaDto> opciones) {
}
