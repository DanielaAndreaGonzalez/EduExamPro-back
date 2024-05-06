package com.eduexampro.EduExamProback.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eduexampro.EduExamProback.dto.ExamenDto;
import com.eduexampro.EduExamProback.entidades.Curso;
import com.eduexampro.EduExamProback.entidades.Examen;
import com.eduexampro.EduExamProback.repositorio.ExamenRepository;

@Service
public class ExamenService {

    private final ExamenRepository examenRepository;
    private final CursoService cursoService;

    public ExamenService(ExamenRepository examenRepository, CursoService cursoService) {
        this.examenRepository = examenRepository;
        this.cursoService = cursoService;
    }

    public List<Examen> findAllExamenes() {
        return examenRepository.findAll();
    }

    public Examen findExamenById(Long id) {
        return examenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Examen no encontrado"));
    }

    public ExamenDto saveExamen(ExamenDto usuarioDto) {
        return getExamenDtoFromExamen(examenRepository.save(getUsuarioFromUsuarioDto(usuarioDto)));
    }

    private ExamenDto getExamenDtoFromExamen(Examen examen) {
        return new ExamenDto(examen.getIdExamen(), examen.getNombre(), examen.getDescripcion(), examen.getCategoria(),
                examen.getFechaDisponible(), examen.getTiempoLimite(), examen.getCurso().getIdCurso());
    }

    private Examen getUsuarioFromUsuarioDto(ExamenDto usuarioDto) {
        return new Examen(null, usuarioDto.nombre(), usuarioDto.descripcion(), usuarioDto.categoria(),
                usuarioDto.fechaDisponible(), usuarioDto.tiempoLimite(), getCursoById(usuarioDto.idCurso()));
    }

    private Curso getCursoById(Long idCurso) {
        return cursoService.findCursoById(idCurso);
    }

}
