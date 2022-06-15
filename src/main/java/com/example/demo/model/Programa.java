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
@Table(name = "programa")
public class Programa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_programa", nullable = false)
    private Long idPrograma;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "usuario_creador", nullable = false, length = 50)
    private String usuarioCreador;

    @Column(name = "usuario_modificador", length = 50)
    private String usuarioModificador;

    @Column(name = "fecha_creacion", nullable = false)
    private Date fechaCreacion;

    @Column(name = "fecha_modificación")
    private Date fechaModificacion;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "programa")
    List<Estudiante> estudiantes = new ArrayList<>();
}
