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
public class CursoDTO implements Serializable {


    private static final long serialVersionUID = -4784770527274137330L;

    private Long idCurso;
    private String nombre;
    private String password;
    private int cantidadEstudiantes;
    private Date inicioCurso;
    private Date finCurso;
    private int progreso;
    private String usuarioCreador;
    private String usuarioModificador;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Long idEstado;
    private Long idProfesor;
}
