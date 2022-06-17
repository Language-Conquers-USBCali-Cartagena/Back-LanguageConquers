package com.example.demo.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
public class ArticulosDTO implements Serializable {


    private static final long serialVersionUID = -4622195713905232557L;

    private Long idArticulo;
    private String nombre;
    private double precio;
    private int nivelValido;
    private String descripcion;
    private String imagen;
    private String usuarioCreador;
    private String usuarioModificador;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Long idEstado;
    private Long idCategoria;
}
