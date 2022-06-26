package com.example.demo.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CredencialesDTO {

    private Long idCredencial;
    private String cuenta;
    private String password;
    private String url;
    private String plataforma;
    private String usuarioCreador;
    private String usuarioModificador;
    private Date fechaCreacion;
    private Date fechaModificacion;
}
