package com.example.demo.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class PalabrasReservadasDTO implements Serializable {


    private static final long serialVersionUID = -4607504373710525858L;

    private Long idPalabraReservada;
    private String nombre;
    private Integer orden;
    private Long padre;
    private Integer lista;
    private String categoria;
    private String usuarioCreador;
    private String usuarioModificador;
    private Date fechaCreacion;
    private Date fechaModificacion;
}
