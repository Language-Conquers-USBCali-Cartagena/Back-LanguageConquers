package com.example.demo.serviceImpl.bitacora.testDataBuilder;

import com.example.demo.model.dto.BitacoraDTO;

import java.util.Date;

public class BitacoraTestDataBuilder {

    private Long idBitacora;
    private Date fechaIngreso;
    private Date fechaSalida;
    private Long idUsuario;
    private String usuarioCreador;
    private String usuarioModificador;
    private Date fechaCreacion;
    private Date fechaModificacion;

    public BitacoraTestDataBuilder(){
        this.idBitacora = 376483L;
        this.fechaIngreso = new Date();
        this.fechaSalida = new Date();
        this.idUsuario = 27376734L;
        this.usuarioCreador = "Angela";
        this.usuarioModificador = "Angela";
        this.fechaCreacion = new Date();
        this.fechaModificacion = new Date();
    }

    public BitacoraTestDataBuilder conIdBitacora(Long idBitacora) {
        this.idBitacora = idBitacora;
        return this;
    }

    public BitacoraTestDataBuilder conFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
        return this;
    }

    public BitacoraTestDataBuilder conFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
        return this;
    }

    public BitacoraTestDataBuilder conIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
        return this;
    }

    public BitacoraTestDataBuilder conUsuarioCreador(String usuarioCreador) {
        this.usuarioCreador = usuarioCreador;
        return this;
    }

    public BitacoraTestDataBuilder conUsuarioModificador(String usuarioModificador) {
        this.usuarioModificador = usuarioModificador;
        return this;
    }

    public BitacoraTestDataBuilder conFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public BitacoraTestDataBuilder conFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
        return this;
    }

    public BitacoraDTO build(){
        return new BitacoraDTO(idBitacora, fechaIngreso,fechaSalida,idUsuario,usuarioCreador,usuarioModificador,fechaCreacion,fechaModificacion);
    }
}
