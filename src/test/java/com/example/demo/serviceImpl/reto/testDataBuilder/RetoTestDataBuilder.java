package com.example.demo.serviceImpl.reto.testDataBuilder;

import com.example.demo.model.dto.RetoDTO;

import java.util.Date;

public class RetoTestDataBuilder {
    private Long idReto;
    private String nombreReto;
    private String descripcion;
    private int moneda;
    private int maximoIntentos;
    private String descripcionTeoria;
    private Date fechaInicio;
    private Date fechaLimite;
    private String solucion;
    private boolean esGrupal;
    private String usuarioCreador;
    private String usuarioModificador;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private String urlVideo1;
    private String urlVideo2;
    private String imagenTema1;
    private String imagenTema2;
    private Long idMision;
    private Long idEstado;
    private Long idCurso;
    private Long idEstadoRetoEstu;

    public RetoTestDataBuilder() {
        this.idReto = 1651651651L;
        this.nombreReto = "test";
        this.descripcion = "test";
        this.moneda = 1500;
        this.maximoIntentos = 15;
        this.descripcionTeoria = "test";
        this.fechaInicio = new Date();
        this.fechaLimite = new Date();
        this.solucion = "test";
        this.esGrupal = false;
        this.usuarioCreador = "juan";
        this.usuarioModificador = "juan";
        this.fechaCreacion = new Date();
        this.fechaModificacion = new Date();
        this.urlVideo1 = "vide";
        this.urlVideo2 = "vide";
        this.imagenTema1 = "imagen";
        this.imagenTema2 = "imagen";
        this.idMision = 1L;
        this.idEstado = 1L;
        this.idCurso = 1L;
        this.idEstadoRetoEstu = 1L;
    }

    public RetoTestDataBuilder conIdReto(Long idReto) {
        this.idReto = idReto;
        return this;
    }

    public RetoTestDataBuilder conNombreReto(String nombreReto) {
        this.nombreReto = nombreReto;
        return this;
    }

    public RetoTestDataBuilder conDescripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public RetoTestDataBuilder conMoneda(int moneda) {
        this.moneda = moneda;
        return this;
    }

    public RetoTestDataBuilder conMaximoIntentos(int maximoIntentos) {
        this.maximoIntentos = maximoIntentos;
        return this;
    }

    public RetoTestDataBuilder conDescripcionTeoria(String descripcionTeoria) {
        this.descripcionTeoria = descripcionTeoria;
        return this;
    }

    public RetoTestDataBuilder conFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
        return this;
    }

    public RetoTestDataBuilder conFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
        return this;
    }

    public RetoTestDataBuilder conSolucion(String solucion) {
        this.solucion = solucion;
        return this;
    }

    public RetoTestDataBuilder conEsGrupal(boolean esGrupal) {
        this.esGrupal = esGrupal;
        return this;
    }

    public RetoTestDataBuilder conUsuarioCreador(String usuarioCreador) {
        this.usuarioCreador = usuarioCreador;
        return this;
    }

    public RetoTestDataBuilder conUsuarioModificador(String usuarioModificador) {
        this.usuarioModificador = usuarioModificador;
        return this;
    }

    public RetoTestDataBuilder conFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public RetoTestDataBuilder conFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
        return this;
    }

    public RetoTestDataBuilder conUrlVideo1(String urlVideo1) {
        this.urlVideo1 = urlVideo1;
        return this;
    }

    public RetoTestDataBuilder conUrlVideo2(String urlVideo2) {
        this.urlVideo2 = urlVideo2;
        return this;
    }

    public RetoTestDataBuilder conImagenTema1(String imagenTema1) {
        this.imagenTema1 = imagenTema1;
        return this;
    }

    public RetoTestDataBuilder conImagenTema2(String imagenTema2) {
        this.imagenTema2 = imagenTema2;
        return this;
    }

    public RetoTestDataBuilder conIdMision(Long idMision) {
        this.idMision = idMision;
        return this;
    }

    public RetoTestDataBuilder conIdEstado(Long idEstado) {
        this.idEstado = idEstado;
        return this;
    }

    public RetoTestDataBuilder conIdCurso(Long idCurso) {
        this.idCurso = idCurso;
        return this;
    }

    public RetoTestDataBuilder conIdEstadoRetoEstu(Long idEstadoRetoEstu) {
        this.idEstadoRetoEstu = idEstadoRetoEstu;
        return this;
    }

    public RetoDTO build(){
        return new RetoDTO(
                idReto,
                nombreReto,
                descripcion,
                moneda,
                maximoIntentos,
                descripcionTeoria,
                fechaInicio,
                fechaLimite,
                solucion,
                esGrupal,
                usuarioCreador,
                usuarioModificador,
                fechaCreacion,
                fechaModificacion,
                urlVideo1,
                urlVideo2,
                imagenTema1,
                imagenTema2,
                idMision,
                idEstado,
                idCurso,
                idEstadoRetoEstu
        );
    }
}
