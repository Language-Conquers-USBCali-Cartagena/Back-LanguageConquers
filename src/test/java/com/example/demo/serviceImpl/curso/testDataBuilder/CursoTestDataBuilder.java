package com.example.demo.serviceImpl.curso.testDataBuilder;

import com.example.demo.model.dto.CursoDTO;

import java.util.Date;

public class CursoTestDataBuilder {

    private Long idCurso;
    private String nombre;
    private String password;
    private int cantidadEstudiantes;
    private Date inicioCurso;
    private Date finCurso;
    private int progreso;
    private String usuarioCreador;
    private String usuarioModificador;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Long idEstado;
    private Long idProfesor;

    public CursoTestDataBuilder(){
        this.idCurso = 32787L;
        this.nombre = "Base de datos";
        this.password = "1234";
        this.cantidadEstudiantes = 20;
        this.inicioCurso = new Date();
        this.finCurso = new Date(2023,9,12);
        this.progreso = 0;
        this.usuarioCreador = "Angela";
        this.usuarioModificador = "Angela";
        this.fechaCreacion = new Date();
        this.fechaModificacion = new Date();
        this.idEstado = 87437L;
        this.idProfesor =838L;
    }

    public CursoTestDataBuilder conIdCurso(Long idCurso) {
        this.idCurso = idCurso;
        return this;
    }

    public CursoTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public CursoTestDataBuilder conPassword(String password) {
        this.password = password;
        return this;
    }

    public CursoTestDataBuilder conCantidadEstudiantes(int cantidadEstudiantes) {
        this.cantidadEstudiantes = cantidadEstudiantes;
        return this;
    }

    public CursoTestDataBuilder conInicioCurso(Date inicioCurso) {
        this.inicioCurso = inicioCurso;
        return this;
    }

    public CursoTestDataBuilder conFinCurso(Date finCurso) {
        this.finCurso = finCurso;
        return this;
    }

    public CursoTestDataBuilder conProgreso(int progreso) {
        this.progreso = progreso;
        return this;
    }

    public CursoTestDataBuilder conUsuarioCreador(String usuarioCreador) {
        this.usuarioCreador = usuarioCreador;
        return this;
    }

    public CursoTestDataBuilder conUsuarioModificador(String usuarioModificador) {
        this.usuarioModificador = usuarioModificador;
        return this;
    }

    public CursoTestDataBuilder conFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public CursoTestDataBuilder conFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
        return this;
    }

    public CursoTestDataBuilder conIdEstado(Long idEstado) {
        this.idEstado = idEstado;
        return this;
    }

    public CursoTestDataBuilder conIdProfesor(Long idProfesor) {
        this.idProfesor = idProfesor;
        return this;
    }

    public CursoDTO build(){
        return new CursoDTO(idCurso,nombre,password,cantidadEstudiantes,inicioCurso,finCurso,progreso,usuarioCreador,usuarioModificador,fechaCreacion,fechaModificacion,idEstado,idProfesor);
    }
}
