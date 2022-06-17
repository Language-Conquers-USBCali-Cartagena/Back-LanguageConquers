package com.example.demo.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class MonedasDTO implements Serializable {


    private static final long serialVersionUID = -4508431943181877794L;

    private Long idMoneda;
    private int cantidad;
    private String imgMonedas;
    private String usuarioCreador;
    private String usuarioModificador;
    private Date fechaCreacion;
    private Date fechaModificacion;
}
