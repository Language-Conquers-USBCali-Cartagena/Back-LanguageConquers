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
@Table(name = "avatar")
public class Avatar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_avatar", nullable = false)
    private Long idAvatar;

    @Column(name = "nombre_avatar", nullable = false, length = 50)
    private String nombreAvatar;

    @Column(name = "img_avatar", nullable = false, length = 250)
    private String imgAvatar;

    @Column(name = "usuario_creador", nullable = false, length = 50)
    private String usuarioCreador;

    @Column(name = "usuario_modificador", length = 50)
    private String usuarioModificador;

    @Column(name = "fecha_creacion", nullable = false)
    private Date fechaCreacion;

    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "avatar")
    List<Estudiante> estudianteList = new ArrayList<>();
}
