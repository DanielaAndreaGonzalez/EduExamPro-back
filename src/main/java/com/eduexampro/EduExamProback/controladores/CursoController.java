package com.eduexampro.EduExamProback.controladores;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduexampro.EduExamProback.dto.CursoDto;
import com.eduexampro.EduExamProback.servicios.CursoService;
import java.util.List;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/cursos")
@CrossOrigin(origins = "*")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public ResponseEntity<List<CursoDto>> getAllCursos() {
        return ResponseEntity.ok(cursoService.findAllCursos());
    }

}
