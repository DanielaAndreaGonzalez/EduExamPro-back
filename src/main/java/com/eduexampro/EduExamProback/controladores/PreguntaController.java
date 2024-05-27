package com.eduexampro.EduExamProback.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduexampro.EduExamProback.dto.PreguntaDto;
import com.eduexampro.EduExamProback.servicios.PreguntaService;

@RestController
@RequestMapping("/api/pregunta")
@CrossOrigin(origins = "*")
public class PreguntaController {

    @Autowired
    private PreguntaService preguntaService;

    @PostMapping
    public ResponseEntity<String> crearPregunta(@RequestBody PreguntaDto preguntaDto) {
        preguntaService.guardarPreguntaFronDto(preguntaDto);
        return ResponseEntity.ok("");
    }

    @GetMapping
    public ResponseEntity<List<PreguntaDto>> getPreguntas() {
        return ResponseEntity.ok(preguntaService.findAllPreguntas());
    }
}
