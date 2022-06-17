package com.example.demo.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
public class GeneroDTO implements Serializable {


    private static final long serialVersionUID = -6918325734894375999L;
    private Long idGenero;
    private String genero;
    private String usuarioCreador;
    private String usuarioModificador;
    private Date fechaCreacion;
    private Date fechaModificacion;
}
