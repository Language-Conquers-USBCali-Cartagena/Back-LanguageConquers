package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "curso")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_curso", nullable = false)
    private Long idCurso;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "password", nullable = false, length = 50)
    private String password;

    @Column(name = "cantidad_estudiantes", nullable = false)
    private int cantidadEstudiantes;

    @Column(name = "inicio_curso", nullable = false)
    private Date inicioCurso;

    @Column(name = "fin_curso", nullable = false)
    private Date finCurso;

    @Column(name = "progreso", nullable = false)
    private int progreso;

    @Column(name = "usuario_creador", nullable = false, length = 50)
    private String usuarioCreador;

    @Column(name = "usuario_modificador", length = 50)
    private String usuarioModificador;

    @Column(name = "fecha_creacion", nullable = false)
    private Date fechaCreacion;

    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado", nullable = false)
    private Estado estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profesor", nullable = false)
    private Profesor profesor;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "curso")
    List<CursoEstudiante> cursoEstudiantes = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "curso")
    List<Reto> retos = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "curso")
    List<Mision> misiones = new ArrayList<>();






}
