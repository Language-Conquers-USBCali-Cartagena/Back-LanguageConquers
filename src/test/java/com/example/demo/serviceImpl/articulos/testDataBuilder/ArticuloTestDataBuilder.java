package com.example.demo.serviceImpl.articulos.testDataBuilder;

import com.example.demo.model.dto.ArticulosDTO;

import java.util.Date;

public class ArticuloTestDataBuilder {

    private Long idArticulo;
    private String nombre;
    private double precio;
    private int nivelValido;
    private String descripcion;
    private String imagen;
    private String usuarioCreador;
    private String usuarioModificador;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Long idEstado;
    private Long idCategoria;

    public ArticuloTestDataBuilder(){

        this.idArticulo = 267367L;
        this.nombre = "Camisa";
        this.precio = 23.3;
        this.nivelValido = 1;
        this.descripcion = "Camisa de hombre";
        this.imagen = "camisa.png";
        this.usuarioCreador = "Angela";
        this.usuarioModificador = "Angela";
        this.fechaCreacion = new Date();
        this.fechaModificacion = new Date();
        this.idCategoria =2687L;
        this.idEstado = 2787L;

    }

    public  ArticuloTestDataBuilder conIdArticulo(Long idArticulo){
        this.idArticulo = idArticulo;
        return this;
    }

    public ArticuloTestDataBuilder conNombre(String nombre){
        this.nombre = nombre;
        return this;
    }

    public ArticuloTestDataBuilder conPrecio(Double precio){
        this.precio = precio;
        return this;
    }

    public ArticuloTestDataBuilder conNivelValido(int nivelValido){
        this.nivelValido = nivelValido;
        return this;
    }

    public ArticuloTestDataBuilder conDescripcion(String descripcion){
        this.descripcion = descripcion;
        return this;
    }

    public ArticuloTestDataBuilder conImagen(String imagen){
        this.imagen = imagen;
        return this;
    }

    public ArticuloTestDataBuilder conIdEstado(Long idEstado){
        this.idEstado = idEstado;
        return this;
    }

    public ArticuloTestDataBuilder conIdCategoria(Long idCategoria){
        this.idCategoria = idCategoria;
        return this;
    }

    public ArticuloTestDataBuilder conUsuarioCreador(String usuarioCreador){
        this.usuarioCreador = usuarioCreador;
        return this;
    }
    public ArticuloTestDataBuilder conFechaCreacion(Date fechaCreacion){
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public ArticuloTestDataBuilder conUsuarioModificador(String usuarioModificador){
        this.usuarioModificador = usuarioModificador;
        return this;
    }
    public ArticuloTestDataBuilder conFechaModificación(Date fechaModificacion){
        this.fechaModificacion = fechaModificacion;
        return this;
    }

    public ArticulosDTO build(){
        return new ArticulosDTO(idArticulo,nombre, precio,nivelValido,descripcion,imagen,usuarioCreador, usuarioModificador, fechaCreacion, fechaModificacion, idEstado, idCategoria );
    }

}
