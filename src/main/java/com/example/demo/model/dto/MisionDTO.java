package com.example.demo.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class MisionDTO implements Serializable {


    private static final long serialVersionUID = -3269524906068751757L;

    private Long idMision;
    private String nombre;
    private String imagen;
    private String usuarioCreador;
    private String usuarioModificador;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Long idCurso;

}
