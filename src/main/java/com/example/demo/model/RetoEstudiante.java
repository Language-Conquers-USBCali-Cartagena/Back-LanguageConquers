package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "reto_estudiante")
public class RetoEstudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reto_estudiante", nullable = false)
    private Long idRetoEstudiante;

    @Column(name = "puntaje", nullable = false)
    private int puntaje;

    @Column(name = "fecha_entrega")
    private Date fechaEntrega;

    @Column(name = "usuario_creador", nullable = false, length = 50)
    private String usuarioCreador;

    @Column(name = "usuario_modificador", length = 50)
    private String usuarioModificador;

    @Column(name = "fecha_creacion", nullable = false)
    private Date fechaCreacion;

    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    @Column(name = "intentos")
    private Integer intentos;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reto", nullable = false)
    private Reto reto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado", nullable = false)
    private Estado estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estudiante", nullable = false)
    private Estudiante estudiante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rol", nullable = false)
    private Rol rol;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_grupo", nullable = false)
    private Grupo grupo;





}
