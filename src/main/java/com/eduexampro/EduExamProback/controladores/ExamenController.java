package com.eduexampro.EduExamProback.controladores;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduexampro.EduExamProback.dto.ExamenDto;
import com.eduexampro.EduExamProback.entidades.Examen;
import com.eduexampro.EduExamProback.servicios.ExamenService;
import java.util.List;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/examenes")
@CrossOrigin(origins = "*")
public class ExamenController {
    private final ExamenService examenService;

    public ExamenController(ExamenService examenService) {
        this.examenService = examenService;
    }

    @GetMapping
    public ResponseEntity<List<Examen>> findAllExamenes() {
        return ResponseEntity.ok(examenService.findAllExamenes());
    }

    @PostMapping
    public ResponseEntity<ExamenDto> saveExamen(@RequestBody ExamenDto examen) {
        return ResponseEntity.ok(examenService.saveExamen(examen));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Examen> findExamenById(@PathVariable Long id) {
        return ResponseEntity.ok(examenService.findExamenById(id));
    }
}
