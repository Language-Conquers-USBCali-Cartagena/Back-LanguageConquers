package com.example.demo.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class RetoEstudianteDTO implements Serializable {


    private static final long serialVersionUID = -7414311881060338124L;

    private Long idRetoEstudiante;
    private int puntaje;
    private Date fechaEntrega;
    private String usuarioCreador;
    private String usuarioModificador;
    private Integer intentos;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Long idReto;
    private Long idEstado;
    private Long idEstudiante;
    private Long idRol;
    private Long idGrupo;
}
