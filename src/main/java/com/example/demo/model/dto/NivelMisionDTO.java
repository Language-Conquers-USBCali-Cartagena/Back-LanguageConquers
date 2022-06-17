package com.example.demo.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
public class NivelMisionDTO implements Serializable {


    private static final long serialVersionUID = -4515295978903744202L;

    private Long idNivelMision;
    private String nombre;
    private String imgNivelMision;
    private int puntajeMinimo;
    private String usuarioCreador;
    private String usuarioModificador;
    private Date fechaCreacion;
    private Date fechaModificacion;

}
