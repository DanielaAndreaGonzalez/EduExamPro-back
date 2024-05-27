package com.eduexampro.EduExamProback.repositorio;

import org.springframework.stereotype.Repository;

import com.eduexampro.EduExamProback.entidades.ExamenPregunta;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface ExamenPreguntaRepository extends JpaRepository<ExamenPregunta, Long> {
    @Modifying
    @Transactional
    @Query("DELETE FROM ExamenPregunta ep WHERE ep.examen.idExamen = :examenId AND ep.pregunta.idPregunta = :preguntaId")
    void deleteByExamenIdAndPreguntaId(Long examenId, Long preguntaId);
}
