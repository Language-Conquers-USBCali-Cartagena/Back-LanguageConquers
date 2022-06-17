package com.example.demo.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class TipoMisionDTO implements Serializable {


    private static final long serialVersionUID = -6458684416017629939L;
    private Long idTipoMision;
    private String descripcion;
    private String usuarioCreador;
    private String usuarioModificador;
    private Date fechaCreacion;
    private Date fechaModificacion;
}
