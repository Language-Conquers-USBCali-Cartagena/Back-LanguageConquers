package com.example.demo.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class OrdenRetoIDEDTO implements Serializable {


    private static final long serialVersionUID = 998750945994192900L;

    private Long idOrdenRetoIDE;
    private int orden;
    private String usuarioCreador;
    private String usuarioModificador;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Long idReto;
    private Long padre;
    private Long idPalabraReservada;

}
