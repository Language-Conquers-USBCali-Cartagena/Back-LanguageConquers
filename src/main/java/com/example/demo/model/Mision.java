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
@Table(name = "mision")
public class Mision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mision", nullable = false)
    private Long idMision;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "imagen", nullable = false, length = 250)
    private String imagen;

    @Column(name = "usuario_creador", nullable = false, length = 50)
    private String usuarioCreador;

    @Column(name = "usuario_modificador", length = 50)
    private String usuarioModificador;

    @Column(name = "fecha_creacion", nullable = false)
    private Date fechaCreacion;

    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nivel_mision", nullable = false)
    private NivelMision nivelMision;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_curso", nullable = false)
    private Curso curso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_mision", nullable = false)
    private TipoMision tipoMision;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_monedas", nullable = false)
    private Monedas monedas;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mision")
    List<Reto> retos = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mision")
    List<MisionEstudiante> misionEstudiantes = new ArrayList<>();
}
