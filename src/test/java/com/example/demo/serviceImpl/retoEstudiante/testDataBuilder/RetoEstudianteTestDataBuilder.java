package com.example.demo.serviceImpl.retoEstudiante.testDataBuilder;

import com.example.demo.model.dto.RetoEstudianteDTO;

import java.util.Date;

public class RetoEstudianteTestDataBuilder {
    private Long idRetoEstudiante;
    private int puntaje;
    private Date fechaEntrega;
    private String usuarioCreador;
    private String usuarioModificador;
    private Integer intentos;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Long idReto;
    private Long idEstado;
    private Long idEstudiante;
    private Long idRol;
    private Long idGrupo;

    public RetoEstudianteTestDataBuilder() {
        this.idRetoEstudiante = 6156165165L;
        this.puntaje = 5000;
        this.fechaEntrega = new Date();
        this.usuarioCreador = "admin";
        this.usuarioModificador = "admin";
        this.intentos = 15;
        this.fechaCreacion = new Date();
        this.fechaModificacion = new Date();
        this.idReto = 1L;
        this.idEstado = 1L;
        this.idEstudiante = 1L;
        this.idRol = 1L;
        this.idGrupo = 1L;
    }

    public RetoEstudianteTestDataBuilder conIdRetoEstudiante(Long idRetoEstudiante) {
        this.idRetoEstudiante = idRetoEstudiante;
        return this;
    }

    public RetoEstudianteTestDataBuilder conPuntaje(int puntaje) {
        this.puntaje = puntaje;
        return this;
    }

    public RetoEstudianteTestDataBuilder conFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
        return this;
    }

    public RetoEstudianteTestDataBuilder conUsuarioCreador(String usuarioCreador) {
        this.usuarioCreador = usuarioCreador;
        return this;
    }

    public RetoEstudianteTestDataBuilder conUsuarioModificador(String usuarioModificador) {
        this.usuarioModificador = usuarioModificador;
        return this;
    }

    public RetoEstudianteTestDataBuilder conIntentos(Integer intentos) {
        this.intentos = intentos;
        return this;
    }

    public RetoEstudianteTestDataBuilder conFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public RetoEstudianteTestDataBuilder conFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
        return this;
    }

    public RetoEstudianteTestDataBuilder conIdReto(Long idReto) {
        this.idReto = idReto;
        return this;
    }

    public RetoEstudianteTestDataBuilder conIdEstado(Long idEstado) {
        this.idEstado = idEstado;
        return this;
    }

    public RetoEstudianteTestDataBuilder conIdEstudiante(Long idEstudiante) {
        this.idEstudiante = idEstudiante;
        return this;
    }

    public RetoEstudianteTestDataBuilder conIdRol(Long idRol) {
        this.idRol = idRol;
        return this;
    }

    public RetoEstudianteTestDataBuilder conIdGrupo(Long idGrupo) {
        this.idGrupo = idGrupo;
        return this;
    }

    public RetoEstudianteDTO build() {
        return new RetoEstudianteDTO(idRetoEstudiante, puntaje, fechaEntrega, usuarioCreador, usuarioModificador,
                intentos, fechaCreacion, fechaModificacion, idReto, idEstado, idEstudiante, idRol, idGrupo);
    }
}