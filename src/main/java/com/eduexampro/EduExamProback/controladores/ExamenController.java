package com.eduexampro.EduExamProback.controladores;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduexampro.EduExamProback.dto.ExamenDto;
import com.eduexampro.EduExamProback.dto.InfoQuizPreguntasDto;
import com.eduexampro.EduExamProback.dto.PresentacionExamenDto;
import com.eduexampro.EduExamProback.dto.QuizPreguntaDto;
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
    public ResponseEntity<List<ExamenDto>> findAllExamenes() {
        return ResponseEntity.ok(examenService.findAllExamenes());
    }

    @PostMapping
    public ResponseEntity<ExamenDto> saveExamen(@RequestBody ExamenDto examen) {
        return ResponseEntity.ok(examenService.saveExamen(examen));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamenDto> findExamenById(@PathVariable Long id) {
        return ResponseEntity.ok(examenService.findExamenById(id));
    }

    @GetMapping("/find-examen-preguntas/{id}")
    public ResponseEntity<InfoQuizPreguntasDto> findExamenPreguntasById(@PathVariable Long id) {
        return ResponseEntity.ok(examenService.findExamenPreguntasById(id));
    }

    @PostMapping("/add-pregunta-to-quiz")
    public ResponseEntity<?> addPreguntaToQuiz(@RequestBody QuizPreguntaDto quizPreguntaDto) {
        return ResponseEntity.ok(examenService.addPreguntaToQuiz(quizPreguntaDto));
    }

    @DeleteMapping("/quizzes/{quizId}/preguntas/{preguntaId}")
    public ResponseEntity<?> removePreguntaFromQuiz(@PathVariable Long quizId, @PathVariable Long preguntaId) {
        boolean result = examenService.removePreguntaFromQuiz(quizId, preguntaId);
        if (result) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("Error al eliminar la pregunta del quiz");
        }
    }

    @PostMapping("/guardar-presentacion-examen")
    public ResponseEntity<?> guardarPresentacionExamen(@RequestBody PresentacionExamenDto presentacionExamen) {
        examenService.guardarPresentacionExamen(presentacionExamen);
        return ResponseEntity.ok().build();
    }
}
