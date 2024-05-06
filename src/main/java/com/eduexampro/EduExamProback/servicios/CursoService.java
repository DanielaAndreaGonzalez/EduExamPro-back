package com.eduexampro.EduExamProback.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eduexampro.EduExamProback.dto.CursoDto;
import com.eduexampro.EduExamProback.entidades.Curso;
import com.eduexampro.EduExamProback.repositorio.CursoRepository;

@Service
public class CursoService {
    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public List<CursoDto> findAllCursos() {
        return getCursoDtoFromCursos(cursoRepository.findAll());
    }

    private List<CursoDto> getCursoDtoFromCursos(List<Curso> cursos) {
        return cursos.stream()
                .map(curso -> new CursoDto(curso.getIdCurso(), curso.getNombre(), curso.getDescripcion(),
                        curso.getDocente().getIdUsuario()))
                .toList();
    }

    public Curso findCursoById(Long id) {
        return cursoRepository.findById(id).orElseThrow(() -> new RuntimeException("Curso no encontrado"));
    }

    public Curso saveCurso(Curso curso) {
        return cursoRepository.save(curso);
    }
}
