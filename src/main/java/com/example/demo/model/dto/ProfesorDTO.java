package com.example.demo.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class ProfesorDTO implements Serializable {


    private static final long serialVersionUID = 3295015825317206627L;
    private Long idProfesor;
    private String nombre;
    private String apellido;
    private String correo;
    private String foto;
    private String usuarioCreador;
    private String usuarioModificador;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Long idGenero;
}
