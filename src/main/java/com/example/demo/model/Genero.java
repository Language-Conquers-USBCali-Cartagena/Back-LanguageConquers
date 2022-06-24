package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.loader.entity.NaturalIdEntityJoinWalker;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "genero")
public class Genero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_genero", nullable = false)
    private Long idGenero;

    @Column(name = "genero", nullable = false, length = 50)
    private String genero;

    @Column(name = "usuario_creador", nullable = false, length = 50)
    private String usuarioCreador;

    @Column(name = "usuario_modificador", length = 50)
    private String usuarioModificador;

    @Column(name = "fecha_creacion", nullable = false)
    private Date fechaCreacion;

    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "genero")
    List<Estudiante> estudiantes = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "genero")
    List<Profesor> profesor = new ArrayList<>();


}
