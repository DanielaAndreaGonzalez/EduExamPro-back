package com.eduexampro.EduExamProback.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eduexampro.EduExamProback.dto.ExamenDto;
import com.eduexampro.EduExamProback.dto.InfoQuizPreguntasDto;
import com.eduexampro.EduExamProback.dto.OpcionPreguntaDto;
import com.eduexampro.EduExamProback.dto.PreguntaDto;
import com.eduexampro.EduExamProback.dto.PresentacionExamenDto;
import com.eduexampro.EduExamProback.dto.QuizPreguntaDto;
import com.eduexampro.EduExamProback.entidades.Curso;
import com.eduexampro.EduExamProback.entidades.Examen;
import com.eduexampro.EduExamProback.entidades.ExamenPregunta;
import com.eduexampro.EduExamProback.entidades.OpcionPregunta;
import com.eduexampro.EduExamProback.entidades.Pregunta;
import com.eduexampro.EduExamProback.entidades.PresentacionExamen;
import com.eduexampro.EduExamProback.entidades.Usuario;
import com.eduexampro.EduExamProback.repositorio.ExamenRepository;

import jakarta.transaction.Transactional;

@Service
public class ExamenService {

    private final ExamenRepository examenRepository;
    private final CursoService cursoService;
    private final PreguntaService preguntaService;
    private final ExamenPreguntaService examenPreguntaService;
    private final UsuarioService usuarioService;

    public ExamenService(ExamenRepository examenRepository, CursoService cursoService, PreguntaService preguntaService,
            ExamenPreguntaService examenPreguntaService, UsuarioService usuarioService) {
        this.examenRepository = examenRepository;
        this.cursoService = cursoService;
        this.preguntaService = preguntaService;
        this.examenPreguntaService = examenPreguntaService;
        this.usuarioService = usuarioService;
    }

    public List<ExamenDto> findAllExamenes() {
        List<Examen> examenes = examenRepository.findAll();
        return getExamenDtoFromExamenes(examenes);
    }

    private List<ExamenDto> getExamenDtoFromExamenes(List<Examen> examenes) {
        return examenes.stream().map(examen -> getExamenDtoFromExamen(examen)).toList();
    }

    public ExamenDto findExamenById(Long id) {
        return getExamenDtoFromExamen(examenRepository.findById(id).orElseThrow());
    }

    public ExamenDto saveExamen(ExamenDto usuarioDto) {
        return getExamenDtoFromExamen(examenRepository.save(getUsuarioFromUsuarioDto(usuarioDto)));
    }

    public InfoQuizPreguntasDto findExamenPreguntasById(Long id) {
        Examen examen = examenRepository.findById(id).orElseThrow();
        List<Pregunta> preguntas = examen.getPreguntas();
        return getInfoQuizPreguntasDtoFromPreguntas(id, preguntas);
    }

    private InfoQuizPreguntasDto getInfoQuizPreguntasDtoFromPreguntas(Long id,
            List<Pregunta> preguntas) {
        return new InfoQuizPreguntasDto(id,
                getPreguntaDtoFromPreguntas(preguntas));
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

    private ExamenDto getExamenDtoFromExamen(Examen examen) {
        return new ExamenDto(examen.getIdExamen(), examen.getNombre(), examen.getDescripcion(), examen.getCategoria(),
                examen.getFechaDisponible(), examen.getTiempoLimite(), examen.getCurso().getIdCurso());
    }

    private Examen getUsuarioFromUsuarioDto(ExamenDto usuarioDto) {
        Examen examen = new Examen();
        examen.setNombre(usuarioDto.nombre());
        examen.setDescripcion(usuarioDto.descripcion());
        examen.setCategoria(usuarioDto.categoria());
        examen.setFechaDisponible(usuarioDto.fechaDisponible());
        examen.setTiempoLimite(usuarioDto.tiempoLimite());
        examen.setCurso(getCursoById(usuarioDto.idCurso()));
        return examen;
    }

    private Curso getCursoById(Long idCurso) {
        return cursoService.findCursoById(idCurso);
    }

    @Transactional
    public boolean addPreguntaToQuiz(QuizPreguntaDto quizPreguntaDto) {
        ExamenPregunta examenPregunta = new ExamenPregunta();

        Pregunta pregunta = preguntaService.findPreguntaById(quizPreguntaDto.idPregunta());
        Examen examen = examenRepository.findById(quizPreguntaDto.idQuiz()).orElseThrow();

        if (pregunta == null || examen == null) {
            return false;
        }

        examenPregunta.setPregunta(pregunta);
        examenPregunta.setExamen(examen);

        examenPreguntaService.saveExamenPregunta(examenPregunta);
        return true;
    }

    public boolean removePreguntaFromQuiz(Long quizId, Long preguntaId) {
        try {
            examenPreguntaService.removePreguntaFromQuiz(quizId, preguntaId);
            return true;
        } catch (Exception e) {
            // Log the exception
            return false;
        }
    }

    public boolean guardarPresentacionExamen(PresentacionExamenDto presentacionExamenDto) {
        PresentacionExamen presentacionExamen = new PresentacionExamen();
        Examen examen = examenRepository.findById(presentacionExamenDto.examenDto().idExamen()).orElseThrow();
        presentacionExamen.setExamen(examen);
        Usuario usuario = usuarioService.findUsuarioById(presentacionExamenDto.usuarioDto().id());
        presentacionExamen.setUsuario(usuario);
        presentacionExamen.setFechaHoraInicio(presentacionExamenDto.fechaHoraInicio());
        presentacionExamen.setFechaHoraFin(presentacionExamenDto.fechaHoraFin());
        examenRepository.save(examen);
        return true;
    }

}
