package com.example.demo.serviceImpl.categoria.testDataBuilder;

import com.example.demo.model.dto.CategoriaDTO;

import java.util.Date;

public class CategoriaTestDataBuilder {

    private Long idCategoria;
    private String nombre;
    private String descripcion;
    private String usuarioCreador;
    private String usuarioModificador;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Long idEstado;

    public CategoriaTestDataBuilder(){
        this.idCategoria = 2687L;
        this.nombre = "Objetos de combate";
        this.descripcion = "En esta categoría encontrarás elementos que te permitirán sobrevivir de animales salvajes ";
        this.idEstado = 2787L;
        this.usuarioCreador = "Angela";
        this.usuarioModificador = "Angela";
        this.fechaCreacion = new Date();
        this.fechaModificacion = new Date();
    }

    public CategoriaTestDataBuilder conIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
        return this;
    }

    public CategoriaTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public CategoriaTestDataBuilder conDescripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public CategoriaTestDataBuilder conUsuarioCreador(String usuarioCreador) {
        this.usuarioCreador = usuarioCreador;
        return this;
    }

    public CategoriaTestDataBuilder conUsuarioModificador(String usuarioModificador) {
        this.usuarioModificador = usuarioModificador;
        return this;
    }

    public CategoriaTestDataBuilder conFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public CategoriaTestDataBuilder conFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
        return this;
    }

    public CategoriaTestDataBuilder conIdEstado(Long idEstado) {
        this.idEstado = idEstado;
        return this;
    }

    public CategoriaDTO build(){
        return new CategoriaDTO(idCategoria, nombre,descripcion, usuarioCreador,usuarioModificador, fechaCreacion,fechaModificacion, idEstado);
    }
}
