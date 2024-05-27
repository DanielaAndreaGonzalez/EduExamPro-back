package com.eduexampro.EduExamProback.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduexampro.EduExamProback.dto.OpcionPreguntaDto;
import com.eduexampro.EduExamProback.dto.PreguntaDto;
import com.eduexampro.EduExamProback.entidades.OpcionPregunta;
import com.eduexampro.EduExamProback.entidades.Pregunta;
import com.eduexampro.EduExamProback.repositorio.OpcionPreguntaRepository;
import com.eduexampro.EduExamProback.repositorio.PreguntaRepository;
import java.util.List;

@Service
public class PreguntaService {

    @Autowired
    private PreguntaRepository preguntaRepository;

    @Autowired
    private OpcionPreguntaRepository opcionPreguntaRepository;

    public List<PreguntaDto> findAllPreguntas() {
        List<Pregunta> preguntas = preguntaRepository.findAll();
        return getPreguntaDtoFromPreguntas(preguntas);
    }

    private List<PreguntaDto> getPreguntaDtoFromPreguntas(List<Pregunta> preguntas) {
        return preguntas.stream().map(pregunta -> new PreguntaDto(pregunta.getIdPregunta(), pregunta.getTexto(),
                pregunta.getTipo(), pregunta.getEsPublica(),
                getOpcionPreguntaDtoFromOpcion(pregunta.getOpciones()))).toList();
    }

    private List<OpcionPreguntaDto> getOpcionPreguntaDtoFromOpcion(List<OpcionPregunta> opciones) {
        return opciones.stream().map(opcion -> new OpcionPreguntaDto(opcion.getIdOpcion(), opcion.getTextoOpcion(),
                opcion.getEsCorrecta())).toList();
    }

    public Pregunta findPreguntaById(Long idPregunta) {
        return preguntaRepository.findById(idPregunta)
                .orElseThrow(() -> new RuntimeException("Pregunta no encontrada"));
    }

    public void guardarPreguntaFronDto(PreguntaDto preguntaDto) {
        Pregunta pregunta = new Pregunta();

        pregunta.setTexto(preguntaDto.texto());
        pregunta.setTipo(preguntaDto.tipo());
        pregunta.setEsPublica(preguntaDto.esPublica());
        preguntaRepository.save(pregunta);

        List<OpcionPregunta> opciones = opcionesPregustasFromPreguntaDto(preguntaDto.opciones(), pregunta);
        opciones = opcionPreguntaRepository.saveAll(opciones);
        pregunta.setOpciones(opciones);

    }

    public void savePregunta(Pregunta pregunta) {
        preguntaRepository.save(pregunta);
    }

    public List<OpcionPregunta> opcionesPregustasFromPreguntaDto(List<OpcionPreguntaDto> opcionPreguntas,
            Pregunta pregunta) {
        return opcionPreguntas.stream().map(preguntaDto -> getPreguntaFromPreguntaDto(preguntaDto, pregunta)).toList();
    }

    public OpcionPregunta getPreguntaFromPreguntaDto(OpcionPreguntaDto preguntaDto, Pregunta pregunta) {
        OpcionPregunta opcionPregunta = new OpcionPregunta();
        opcionPregunta.setTextoOpcion(preguntaDto.texto());
        opcionPregunta.setEsCorrecta(preguntaDto.esCorrecta());
        opcionPregunta.setPregunta(pregunta);
        return opcionPregunta;
    }
}
