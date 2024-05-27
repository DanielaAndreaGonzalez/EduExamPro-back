package com.eduexampro.EduExamProback.repositorio;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.eduexampro.EduExamProback.entidades.Pregunta;

@Repository
public interface PreguntaRepository extends JpaRepository<Pregunta, Long> {

}
