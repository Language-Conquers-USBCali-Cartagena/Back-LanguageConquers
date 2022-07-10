package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="credenciales")
public class Credenciales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_credencial", nullable = false)
    private Long idCredencial;

    @Column(name = "cuenta", length = 80)
    private String cuenta;

    @Column(name = "password", length = 80)
    private String password;

    @Column(name = "url", length = 150)
    private String url;

    @Column(name = "plataforma", length = 80)
    private String plataforma;

    @Column(name = "usuario_creador", nullable = false, length = 50)
    private String usuarioCreador;

    @Column(name = "usuario_modificador", length = 50)
    private String usuarioModificador;

    @Column(name = "fecha_creacion", nullable = false)
    private Date fechaCreacion;

    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;




}
