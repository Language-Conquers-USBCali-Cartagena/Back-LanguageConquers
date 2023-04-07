package com.example.demo.serviceImpl.logro.testDataBuilder;

import com.example.demo.model.dto.LogroDTO;

import java.util.Date;

public class LogroTestDataBuilder {
    private Long idLogro;
    private String nombre;
    private String imagen;
    private String descripcion;
    private String usuarioCreador;
    private String usuarioModificador;
    private Date fechaCreacion;
    private Date fechaModificacion;

    public LogroTestDataBuilder() {
        idLogro = 5645464L;
        nombre = "Logro de prueba";
        imagen = "imagen_de_prueba.jpg";
        descripcion = "Este es un logro de prueba";
        usuarioCreador = "admin";
        usuarioModificador = "admin";
        fechaCreacion = new Date();
        fechaModificacion = new Date();
    }

    public LogroTestDataBuilder conId(Long id) {
        this.idLogro = id;
        return this;
    }

    public LogroTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public LogroTestDataBuilder conImagen(String imagen) {
        this.imagen = imagen;
        return this;
    }

    public LogroTestDataBuilder conDescripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public LogroTestDataBuilder conUsuarioCreador(String usuarioCreador) {
        this.usuarioCreador = usuarioCreador;
        return this;
    }

    public LogroTestDataBuilder conUsuarioModificador(String usuarioModificador) {
        this.usuarioModificador = usuarioModificador;
        return this;
    }

    public LogroTestDataBuilder conFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public LogroTestDataBuilder conFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
        return this;
    }

    public LogroDTO build() {
        return new LogroDTO(
                idLogro,
                nombre,
                imagen,
                descripcion,
                usuarioCreador,
                usuarioModificador,
                fechaCreacion,
                fechaModificacion);

    }
}
