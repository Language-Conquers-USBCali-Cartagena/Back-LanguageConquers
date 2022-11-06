package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;



@Getter
@Setter
@Entity
@Table(name = "logro_estudiante")
public class LogroEstudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_logro_estudiante", nullable = false)
    private Long idLogroEstudiante;

    @Column(name = "usuario_creador", nullable = false, length = 50)
    private String usuarioCreador;

    @Column(name = "usuario_modificador", length = 50)
    private String usuarioModificador;

    @Column(name = "fecha_creacion", nullable = false)
    private Date fechaCreacion;

    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estudiante")
    private Estudiante estudiante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_logro")
    private Logro logro;


}
