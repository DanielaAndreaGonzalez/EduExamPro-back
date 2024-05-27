package com.eduexampro.EduExamProback.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eduexampro.EduExamProback.entidades.OpcionPregunta;

@Repository
public interface OpcionPreguntaRepository extends JpaRepository<OpcionPregunta, Long> {

}
