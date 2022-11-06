package com.example.demo.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class LogroDTO implements Serializable {


    private static final long serialVersionUID = -4974437160909794547L;

    private Long idLogro;
    private String nombre;
    private String imagen;
    private String descripcion;
    private String categoria;
    private String usuarioCreador;
    private String usuarioModificador;
    private Date fechaCreacion;
    private Date fechaModificacion;
}
