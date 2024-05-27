package com.eduexampro.EduExamProback.dto;

import java.util.List;

public record InfoQuizPreguntasDto(
    Long id,
    List<PreguntaDto> preguntas
) {
} 