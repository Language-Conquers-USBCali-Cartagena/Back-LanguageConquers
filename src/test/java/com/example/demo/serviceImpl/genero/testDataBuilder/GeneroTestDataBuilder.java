package com.example.demo.serviceImpl.genero.testDataBuilder;

import com.example.demo.model.dto.GeneroDTO;

import java.util.Date;

public class GeneroTestDataBuilder {

    private Long idGenero;
    private String genero;
    private String usuarioCreador;
    private String usuarioModificador;
    private Date fechaCreacion;
    private Date fechaModificacion;

    public GeneroTestDataBuilder(){
        this.idGenero = 4343858L;
        this.genero = "femenino";
        this.usuarioCreador = "angela";
        this.fechaCreacion = new Date();
        this.usuarioModificador = "angela";
        this.fechaModificacion = new Date();
    }

    public GeneroTestDataBuilder conIdGenero(Long idGenero){
        this.idGenero = idGenero;
        return this;
    }

    public GeneroTestDataBuilder conGenero(String genero){
        this.genero = genero;
        return this;
    }

    public GeneroTestDataBuilder conUsuarioCreador(String usuarioCreador){
        this.usuarioCreador = usuarioCreador;
        return this;
    }

    public GeneroTestDataBuilder conFechaCreacion(Date fechaCreacion){
        this.fechaCreacion = fechaCreacion;
        return this;
    }
    public GeneroTestDataBuilder conUsuarioModificador(String usuarioModificador){
        this.usuarioModificador = usuarioModificador;
        return this;
    }

    public GeneroTestDataBuilder conFechaModificacion(Date fechaModificacion){
        this.fechaModificacion = fechaModificacion;
        return this;
    }

    public GeneroDTO build(){
        return new GeneroDTO(idGenero, genero, usuarioCreador, usuarioModificador,  fechaCreacion, fechaModificacion);
    }

}
