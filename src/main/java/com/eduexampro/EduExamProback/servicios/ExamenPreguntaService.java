package com.eduexampro.EduExamProback.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduexampro.EduExamProback.entidades.ExamenPregunta;
import com.eduexampro.EduExamProback.repositorio.ExamenPreguntaRepository;

@Service
public class ExamenPreguntaService {

    @Autowired
    private ExamenPreguntaRepository examenPreguntaRepository;

    public ExamenPregunta saveExamenPregunta(ExamenPregunta examenPregunta) {
        return examenPreguntaRepository.save(examenPregunta);
    }

    public boolean removePreguntaFromQuiz(Long examenId, Long preguntaId) {
        try {
            examenPreguntaRepository.deleteByExamenIdAndPreguntaId(examenId, preguntaId);
            return true;
        } catch (Exception e) {
            // Log the exception
            return false;
        }
    }
}
