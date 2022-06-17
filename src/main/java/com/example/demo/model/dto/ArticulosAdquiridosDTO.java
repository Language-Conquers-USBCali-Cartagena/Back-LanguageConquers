package com.example.demo.model.dto;



import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class ArticulosAdquiridosDTO implements Serializable {


    private static final long serialVersionUID = -1878528017562034375L;
    private Long idArticuloAdquirido;
    private String usuarioCreador;
    private String usuarioModificador;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Long idEstudiante;
    private Long idArticulos;
}
