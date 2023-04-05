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
@Table(name = "reto")
public class Reto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reto", nullable = false)
    private Long idReto;

    @Column(name = "nombre_reto", nullable = false, length = 50)
    private String nombreReto;

    @Column(name = "descripcion", nullable = false, length = 1000)
    private String descripcion;

    @Column(name = "urlVideo1", length = 300)
    private String urlVideo1;

    @Column(name = "urlVideo2", length = 300)
    private String urlVideo2;

    @Column(name = "imagenTema1", length = 300)
    private String imagenTema1;

    @Column(name = "imagenTema2", length = 300)
    private String imagenTema2;

    @Column(name = "descripcionTeoria", length = 1000)
    private String descripcionTeoria;

    @Column(name = "moneda", nullable = false)
    private Integer moneda;

    @Column(name = "solucion", nullable = false, length = 800)
    private String solucion;

    @Column(name = "maximo_intentos", nullable = false)
    private Integer maximoIntentos;

    @Column(name = "fecha_limite", nullable = false)
    private Date fechaLimite;

    @Column(name = "fecha_inicio", nullable = false)
    private Date fechaInicio;

    @Column(name = "es_grupal", nullable = false)
    private Boolean esGrupal;


    @Column(name = "usuario_creador", nullable = false, length = 50)
    private String usuarioCreador;

    @Column(name = "usuario_modificador", length = 50)
    private String usuarioModificador;

    @Column(name = "fecha_creacion", nullable = false)
    private Date fechaCreacion;

    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mision", nullable = false)
    private Mision mision;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado", nullable = false)
    private Estado estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_curso", nullable = false)
    private Curso curso;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reto")
    List<RetoEstudiante> retoEstudiantes = new ArrayList<>();


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reto")
    List<Rol> rols = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reto")
    List<PalabraReto> palabraRetoList = new ArrayList<>();
}
