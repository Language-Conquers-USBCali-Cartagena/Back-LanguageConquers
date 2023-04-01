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
public class CursoEstudianteDTO implements Serializable {


    private static final long serialVersionUID = 7069681575384154652L;
    private Long idCursoEstudiante;
    private int puntaje;
    private int nivel;
    private String usuarioCreador;
    private String usuarioModificador;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Long idCurso;
    private Long idEstudiante;
}
