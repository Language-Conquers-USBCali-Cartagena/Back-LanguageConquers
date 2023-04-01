package com.example.demo.serviceImpl.estado.testDataBuilder;

import com.example.demo.model.dto.EstadoDTO;

import java.util.Date;

public class EstadoTestDataBuilder {

    private Long idEstado;
    private String estado;
    private String usuarioCreador;
    private String usuarioModificador;
    private Date fechaCreacion;
    private Date fechaModificacion;

    public EstadoTestDataBuilder(){
        this.idEstado = 2787L;
        this.estado = "Pausado";
        this.usuarioCreador = "Angela";
        this.fechaCreacion =  new Date();
        this.usuarioModificador = "Angela Acosta";
        this.fechaModificacion = new Date();
    }

    public EstadoTestDataBuilder conIdEstado(Long idEstado){
        this.idEstado = idEstado;
        return this;
    }
    public EstadoTestDataBuilder conEstado(String estado){
        this.estado = estado;
        return this;
    }

    public EstadoTestDataBuilder conUsuarioCreador(String usuarioCreador){
        this.usuarioCreador = usuarioCreador;
        return this;
    }

    public EstadoTestDataBuilder conFechaCreacion(Date fechaCreacion){
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public EstadoTestDataBuilder conUsuarioModificador(String usuarioModificador){
        this.usuarioModificador = usuarioModificador;
        return this;
    }

    public EstadoTestDataBuilder conFechaModificacion(Date fechaModificacion){
        this.fechaModificacion = fechaModificacion;
        return this;
    }

    public EstadoDTO build(){
        return new EstadoDTO(idEstado, estado, usuarioCreador, usuarioModificador, fechaCreacion, fechaModificacion);
    }
}
