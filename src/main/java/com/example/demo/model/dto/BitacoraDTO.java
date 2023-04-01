package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BitacoraDTO implements Serializable {


    private static final long serialVersionUID = 6239479249290711244L;

    private Long idBitacora;
    private Date fechaIngreso;
    private Date fechaSalida;
    private Long idUsuario;
    private String usuarioCreador;
    private String usuarioModificador;
    private Date fechaCreacion;
    private Date fechaModificacion;
}
