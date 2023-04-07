package com.example.demo.serviceImpl.mision.testDataBuilder;

import com.example.demo.model.dto.MisionDTO;

import java.util.Date;

public class MisionTestDataBuilder {

    private Long idMision;
    private String nombre;
    private String imagen;
    private String usuarioCreador;
    private String usuarioModificador;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Long idCurso;

    public MisionTestDataBuilder() {
        this.idMision = 321564L;
        this.nombre = "prueba";
        this.imagen = "dggerge";
        this.usuarioCreador = "admin";
        this.usuarioModificador = "admin";
        this.fechaCreacion = new Date();
        this.fechaModificacion = new Date();
        this.idCurso = 56156L;
    }

    public MisionDTO build() {
        return new MisionDTO(idMision, nombre, imagen, usuarioCreador, usuarioModificador, fechaCreacion, fechaModificacion, idCurso);


    }

    public MisionTestDataBuilder conIdMision(Long idMision) {
        this.idMision = idMision;
        return this;
    }

    public MisionTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public MisionTestDataBuilder conImagen(String imagen) {
        this.imagen = imagen;
        return this;
    }

    public MisionTestDataBuilder conUsuarioCreador(String usuarioCreador) {
        this.usuarioCreador = usuarioCreador;
        return this;
    }

    public MisionTestDataBuilder conUsuarioModificador(String usuarioModificador) {
        this.usuarioModificador = usuarioModificador;
        return this;
    }

    public MisionTestDataBuilder conFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public MisionTestDataBuilder conFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
        return this;
    }

    public MisionTestDataBuilder conIdCurso(Long idCurso) {
        this.idCurso = idCurso;
        return this;
    }
}