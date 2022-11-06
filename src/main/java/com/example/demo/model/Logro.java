package com.example.demo.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "logro")
public class Logro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_logro", nullable = false)
    private Long idLogro;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "imagen", nullable = false)
    private String imagen;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "categoria", nullable = false)
    private String categoria;

    @Column(name = "usuario_creador", nullable = false, length = 50)
    private String usuarioCreador;

    @Column(name = "usuario_modificador", length = 50)
    private String usuarioModificador;

    @Column(name = "fecha_creacion", nullable = false)
    private Date fechaCreacion;

    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "logro")
    List<LogroEstudiante> logroEstudiantes = new ArrayList<>();
}
