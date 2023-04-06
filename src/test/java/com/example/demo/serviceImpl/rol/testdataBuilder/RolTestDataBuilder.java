package com.example.demo.serviceImpl.rol.testdataBuilder;

import com.example.demo.model.dto.RolDTO;

import java.util.Date;

public class RolTestDataBuilder {
    private Long idRol;
    private String nombre;
    private String usuarioCreador;
    private String usuarioModificador;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Long idReto;

    public RolTestDataBuilder() {
        this.idRol = 135651651651L;
        this.nombre = "pepito";
        this.usuarioCreador = "pepito";
        this.usuarioModificador = "pepito";
        this.fechaCreacion = new Date();
        this.fechaModificacion = new Date();
        this.idReto = 131516L;
    }

    public RolTestDataBuilder conIdRol(Long idRol){
        this.idRol = idRol;
        return this;
    }
    public RolTestDataBuilder conNombre(String nombre){
        this.nombre = nombre;
        return this;
    }
    public RolTestDataBuilder conUsuarioModificador(String usuarioModificador){
        this.usuarioModificador = usuarioModificador;
        return this;
    }
    public RolTestDataBuilder conUsuarioCreador(String usuarioCreador){
        this.usuarioCreador = usuarioCreador;
        return this;
    }
    public RolTestDataBuilder conFechaCreacion(Date fechaCreacion){
        this.fechaCreacion = fechaCreacion;
        return  this;
    }
    public RolTestDataBuilder conFechaModificacion(Date fechaModificacion){
        this.fechaModificacion = fechaModificacion;
        return this;
    }
    public RolTestDataBuilder conIdReto(Long idReto){
        this.idReto = idReto;
        return this;
    }
    public RolDTO build(){
        return new RolDTO(idRol,
                nombre,
                usuarioCreador,
                usuarioModificador,
                fechaCreacion,
                fechaModificacion,
                idReto);
    }
}
