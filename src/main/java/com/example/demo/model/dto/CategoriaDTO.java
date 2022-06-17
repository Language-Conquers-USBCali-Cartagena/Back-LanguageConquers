package com.example.demo.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class CategoriaDTO implements Serializable {


    private static final long serialVersionUID = 648811747149284084L;

    private Long idCategoria;
    private String nombre;
    private String descripcion;
    private String usuarioCreador;
    private String usuarioModificador;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Long idEstado;


}
