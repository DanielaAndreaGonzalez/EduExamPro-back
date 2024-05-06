package com.eduexampro.EduExamProback.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eduexampro.EduExamProback.entidades.Examen;

@Repository
public interface ExamenRepository extends JpaRepository<Examen, Long> {

}
