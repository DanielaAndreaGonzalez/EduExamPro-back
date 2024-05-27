package com.eduexampro.EduExamProback.entidades;

import java.util.Date;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Examenes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Examen {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "examen_generator")
    @SequenceGenerator(name = "examen_generator", sequenceName = "examen_seq", allocationSize = 1)
    private Long idExamen;

    private String nombre;
    private String descripcion;
    private String categoria;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDisponible;

    private Integer tiempoLimite;

    @ManyToMany
    @JoinTable(name = "examen_pregunta", joinColumns = @JoinColumn(name = "examen_id"), inverseJoinColumns = @JoinColumn(name = "pregunta_id"))
    private List<Pregunta> preguntas;

    @OneToMany(mappedBy = "examen", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ExamenPregunta> examenPreguntas;

    @ManyToOne
    @JoinColumn(name = "ID_Curso", referencedColumnName = "idCurso")
    private Curso curso;

}
