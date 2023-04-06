package com.example.demo.serviceImpl.palabrasReservadas.testDataBuilder;

import com.example.demo.model.dto.PalabrasReservadasDTO;

import java.util.Date;

public class PalabrasReservadasTestDataBuilder {

    private Long idPalabraReservada;
    private String nombre;
    private Integer orden;
    private Long padre;
    private Integer lista;
    private String categoria;
    private Double tiempo;
    private String usuarioCreador;
    private String usuarioModificador;
    private Date fechaCreacion;
    private Date fechaModificacion;

    public PalabrasReservadasTestDataBuilder() {
        this.idPalabraReservada = 1L;
        this.nombre = "Palabra";
        this.orden = 1;
        this.padre = null;
        this.lista = 1;
        this.categoria = "Categoria";
        this.tiempo = 2.5;
        this.usuarioCreador = "UsuarioCreador";
        this.usuarioModificador = "UsuarioModificador";
        this.fechaCreacion = new Date();
        this.fechaModificacion = new Date();
    }

    public PalabrasReservadasTestDataBuilder conIdPalabraReservada(Long idPalabraReservada) {
        this.idPalabraReservada = idPalabraReservada;
        return this;
    }

    public PalabrasReservadasTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public PalabrasReservadasTestDataBuilder conOrden(Integer orden) {
        this.orden = orden;
        return this;
    }

    public PalabrasReservadasTestDataBuilder conPadre(Long padre) {
        this.padre = padre;
        return this;
    }

    public PalabrasReservadasTestDataBuilder conLista(Integer lista) {
        this.lista = lista;
        return this;
    }

    public PalabrasReservadasTestDataBuilder conCategoria(String categoria) {
        this.categoria = categoria;
        return this;
    }

    public PalabrasReservadasTestDataBuilder conTiempo(Double tiempo) {
        this.tiempo = tiempo;
        return this;
    }

    public PalabrasReservadasTestDataBuilder conUsuarioCreador(String usuarioCreador) {
        this.usuarioCreador = usuarioCreador;
        return this;
    }

    public PalabrasReservadasTestDataBuilder conUsuarioModificador(String usuarioModificador) {
        this.usuarioModificador = usuarioModificador;
        return this;
    }

    public PalabrasReservadasTestDataBuilder conFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public PalabrasReservadasTestDataBuilder conFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
        return this;
    }

    public PalabrasReservadasDTO build() {
        return new PalabrasReservadasDTO(idPalabraReservada, nombre, orden, padre, lista, categoria, tiempo, usuarioCreador, usuarioModificador, fechaCreacion, fechaModificacion);
    }
}