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
@Table(name = "estado")
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado", nullable = false)
    private Long idEstado;

    @Column(name = "estado", nullable = false, length = 50)
    private String estado;

    @Column(name = "usuario_creador", nullable = false, length = 50)
    private String usuarioCreador;

    @Column(name = "usuario_modificador", length = 50)
    private String usuarioModificador;

    @Column(name = "fecha_creacion", nullable = false)
    private Date fechaCreacion;

    @Column(name = "fecha_modificación")
    private Date fechaModificacion;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "estado")
    List<Reto> retos = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "estado")
    List<Curso> curso = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "estado")
    List<Articulos> articulos = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "estado")
    List<MisionEstudiante> misionEstudiantes = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "estado")
    List<Categoria> categorias = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "estado")
    List<Estudiante> estudiantes = new ArrayList<>();

}
