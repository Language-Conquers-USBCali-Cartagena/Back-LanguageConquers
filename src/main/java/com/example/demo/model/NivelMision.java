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
@Table(name = "Nivel_mision")
public class NivelMision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nivel_mision", nullable = false)
    private Long idNivelMision;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "img_nivel_mision", nullable = false, length = 80)
    private String imgNivelMision;

    @Column(name = "puntaje_minimo", nullable = false)
    private int puntajeMinimo;

    @Column(name = "usuario_creador", nullable = false, length = 50)
    private String usuarioCreador;

    @Column(name = "usuario_modificador", length = 50)
    private String usuarioModificador;

    @Column(name = "fecha_creacion", nullable = false)
    private Date fechaCreacion;

    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "nivelMision")
    List<Mision> misiones = new ArrayList<>();

}
