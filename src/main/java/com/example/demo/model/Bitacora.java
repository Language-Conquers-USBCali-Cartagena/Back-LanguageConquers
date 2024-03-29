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
@Table(name = "bitacora")
public class Bitacora {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bitacora", nullable = false)
    private Long idBitacora;

    @Column(name = "fecha_ingreso", nullable = false)
    private Date fechaIngreso;

    @Column(name = "fecha_salida", nullable = false)
    private Date fechaSalida;

    @Column(name = "id_usuario", nullable = false)
    private Long idUsuario;

    @Column(name = "usuario_creador", nullable = false, length = 50)
    private String usuarioCreador;

    @Column(name = "usuario_modificador", length = 50)
    private String usuarioModificador;

    @Column(name = "fecha_creacion", nullable = false)
    private Date fechaCreacion;

    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;


}
