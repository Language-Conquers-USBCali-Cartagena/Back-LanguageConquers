package com.example.demo.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
public class MisionEstudianteDTO implements Serializable {


    private static final long serialVersionUID = 4508804876871435860L;

    private Long idMisionEstudiante;
    private int puntajeObtenido;
    private String usuarioCreador;
    private String usuarioModificador;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Long idEstudiante;
    private Long idEstado;
    private Long idMision;

}
