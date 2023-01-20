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
@Table(name = "articulo")
public class Articulos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_articulo", nullable = false)
    private Long idArticulo;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "precio", nullable = false, precision=19, scale=2)
    private double precio;

    @Column(name = "nivel_valido", nullable = false)
    private int nivelValido;

    @Column(name = "descripcion", nullable = false, length = 200)
    private String descripcion;

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
    @JoinColumn(name = "id_estado", nullable = false)
    private Estado estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "articulos")
    List<ArticulosAdquiridos> articulosAdquiridosList = new ArrayList<>();


}
