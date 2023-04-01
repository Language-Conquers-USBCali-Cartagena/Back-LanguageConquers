package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
