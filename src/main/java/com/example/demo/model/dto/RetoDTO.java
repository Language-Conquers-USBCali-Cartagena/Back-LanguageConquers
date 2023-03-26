package com.example.demo.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class RetoDTO implements Serializable {


    private static final long serialVersionUID = -706319021765545474L;

    private Long idReto;
    private String nombreReto;
    private String descripcion;
    private String urlVideo1;
    private String urlVideo2;
    private String imagenTema1;
    private String imagenTema2;
    private String descripcionTeoria;
    private int moneda;
    private String solucion;
    private int maximoIntentos;
    private Date fechaLimite;
    private Date fechaInicio;
    private boolean esGrupal;
    private int nrEstudiatesGrupo;
    private String usuarioCreador;
    private String usuarioModificador;
    private Date fechaCreacion;
    private Date fechaModificacion;

    private Long idMision;
    private Long idEstado;
    private Long idCurso;
}
