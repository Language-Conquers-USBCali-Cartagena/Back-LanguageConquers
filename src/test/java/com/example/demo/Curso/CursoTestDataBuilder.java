package com.example.demo.Curso;

import com.example.demo.model.Curso;

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
        this.idCurso = 1L;
        this.nombre = "Introduccion a la programación";
        this.password = "1234";
        this.cantidadEstudiantes = 30;
        this.inicioCurso = new Date(2022,8,02);
        this.finCurso = new Date(2023,2,12);
        this.progreso = 0;
        this.usuarioCreador = "Angela";
        this.fechaCreacion = new Date(2023,2,23);
        this.idEstado = 1L;
        this.idProfesor = 1L;
        this.usuarioModificador = "Angela Acosta";
        this.fechaModificacion = new Date(2023,3,13);
    }


    public CursoTestDataBuilder conIdCurso(Long idCurso){
        this.idCurso = idCurso;
        return this;
    }

    public CursoTestDataBuilder conNombre(String nombre){
        this.nombre = nombre;
        return this;
    }

    public CursoTestDataBuilder conPassword(String password){
        this.password = password;
        return this;
    }
    public CursoTestDataBuilder conCantidadEstudiantes(int cantidadEstudiantes){
        this.cantidadEstudiantes = cantidadEstudiantes;
        return this;
    }
    public CursoTestDataBuilder conFechaInicioCurso(Date inicioCurso){
        this.inicioCurso = inicioCurso;
        return this;
    }

    public CursoTestDataBuilder conFechaFinCurso (Date finCurso){
        this.finCurso = finCurso;
        return this;
    }

    public CursoTestDataBuilder conProgreso(int progreso){
        this.progreso = progreso;
        return this;
    }

    public CursoTestDataBuilder conIdProfesor(Long idProfesor){
        this.idProfesor = idProfesor;
        return this;
    }

    public CursoTestDataBuilder conIdEstado(Long idEstado){
        this.idEstado = idEstado;
        return this;
    }

    public CursoTestDataBuilder conUsuarioCreador(String usuarioCreador){
        this.usuarioCreador = usuarioCreador;
        return this;
    }

    public CursoTestDataBuilder conUsuarioModificador(String usuarioModificador){
        this.usuarioModificador = usuarioModificador;
        return this;
    }
    public CursoTestDataBuilder conFechaCreacion(Date fechaCreacion){
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public CursoTestDataBuilder conFechaModificacion(Date fechaModificacion){
        this.fechaModificacion = fechaModificacion;
        return this;
    }


}
