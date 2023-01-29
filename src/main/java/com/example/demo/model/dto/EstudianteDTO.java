package com.example.demo.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class EstudianteDTO implements Serializable {


    private static final long serialVersionUID = -6757757241787501562L;

    private Long idEstudiante;
    private String nombre;
    private String apellido;
    private String nickName;
    private int puntaje;
    private int monedasObtenidas;
    private Date fechaNacimiento;
    private String correo;
    private String usuarioCreador;
    private String usuarioModificador;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Long idPrograma;
    private Long idEstado;
    private Long idSemestre;
    private Long idAvatar;
    private Long idGenero;
}
