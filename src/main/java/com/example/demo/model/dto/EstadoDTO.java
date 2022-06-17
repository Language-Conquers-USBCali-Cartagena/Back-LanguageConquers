package com.example.demo.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class EstadoDTO implements Serializable {


    private static final long serialVersionUID = -4018552177194566381L;

    private Long idEstado;
    private String estado;
    private String usuarioCreador;
    private String usuarioModificador;
    private Date fechaCreacion;
    private Date fechaModificacion;
}
