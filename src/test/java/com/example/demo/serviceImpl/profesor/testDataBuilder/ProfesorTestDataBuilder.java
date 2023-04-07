package com.example.demo.serviceImpl.profesor.testDataBuilder;

import com.example.demo.model.dto.ProfesorDTO;

import java.util.Date;

public class ProfesorTestDataBuilder {
    private Long idProfesor;
    private String nombre;
    private String apellido;
    private String correo;
    private String foto;
    private String usuarioCreador;
    private String usuarioModificador;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Long idGenero;

    public ProfesorTestDataBuilder() {
        this.idProfesor = 1L;
        this.nombre = "Juan";
        this.apellido = "Pérez";
        this.correo = "juan.perez@gmail.com";
        this.foto = "https://myphoto.com/juanperez.jpg";
        this.usuarioCreador = "admin";
        this.usuarioModificador = "admin";
        this.fechaCreacion = new Date();
        this.fechaModificacion = new Date();
        this.idGenero = 1L;
    }

    public ProfesorTestDataBuilder conIdProfesor(Long idProfesor) {
        this.idProfesor = idProfesor;
        return this;
    }

    public ProfesorTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public ProfesorTestDataBuilder conApellido(String apellido) {
        this.apellido = apellido;
        return this;
    }

    public ProfesorTestDataBuilder conCorreo(String correo) {
        this.correo = correo;
        return this;
    }

    public ProfesorTestDataBuilder conFoto(String foto) {
        this.foto = foto;
        return this;
    }

    public ProfesorTestDataBuilder conUsuarioCreador(String usuarioCreador) {
        this.usuarioCreador = usuarioCreador;
        return this;
    }

    public ProfesorTestDataBuilder conUsuarioModificador(String usuarioModificador) {
        this.usuarioModificador = usuarioModificador;
        return this;
    }

    public ProfesorTestDataBuilder conFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public ProfesorTestDataBuilder conFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
        return this;
    }

    public ProfesorTestDataBuilder conIdGenero(Long idGenero) {
        this.idGenero = idGenero;
        return this;
    }

    public ProfesorDTO build() {
        return new ProfesorDTO(idProfesor, nombre, apellido, correo, foto, usuarioCreador, usuarioModificador,
                fechaCreacion, fechaModificacion, idGenero);
    }
}
