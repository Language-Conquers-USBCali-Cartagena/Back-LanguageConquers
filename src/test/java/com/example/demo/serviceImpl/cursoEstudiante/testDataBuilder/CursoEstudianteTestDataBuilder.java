package com.example.demo.serviceImpl.cursoEstudiante.testDataBuilder;

import com.example.demo.model.dto.CursoEstudianteDTO;

import java.util.Date;

public class CursoEstudianteTestDataBuilder {

    private Long idCursoEstudiante;
    private int puntaje;
    private int nivel;
    private String usuarioCreador;
    private String usuarioModificador;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Long idCurso;
    private Long idEstudiante;

    public CursoEstudianteTestDataBuilder(){
        this.idCursoEstudiante = 27832L;
        this.puntaje = 400;
        this.nivel = 1;
        this.idCurso =97237L;
        this.idEstudiante =327873L;
        this.usuarioCreador ="Angela";
        this.usuarioModificador = "Angela";
        this.fechaCreacion = new Date();
        this.fechaModificacion = new Date();
    }

    public CursoEstudianteTestDataBuilder conIdCursoEstudiante(Long idCursoEstudiante) {
        this.idCursoEstudiante = idCursoEstudiante;
        return this;
    }

    public CursoEstudianteTestDataBuilder conPuntaje(int puntaje) {
        this.puntaje = puntaje;
        return this;
    }

    public CursoEstudianteTestDataBuilder conNivel(int nivel) {
        this.nivel = nivel;
        return this;
    }

    public CursoEstudianteTestDataBuilder conUsuarioCreador(String usuarioCreador) {
        this.usuarioCreador = usuarioCreador;
        return this;
    }

    public CursoEstudianteTestDataBuilder conUsuarioModificador(String usuarioModificador) {
        this.usuarioModificador = usuarioModificador;
        return this;
    }

    public CursoEstudianteTestDataBuilder conFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public CursoEstudianteTestDataBuilder conFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
        return this;
    }

    public CursoEstudianteTestDataBuilder conIdCurso(Long idCurso) {
        this.idCurso = idCurso;
        return this;
    }

    public CursoEstudianteTestDataBuilder conIdEstudiante(Long idEstudiante) {
        this.idEstudiante = idEstudiante;
        return this;
    }

    public CursoEstudianteDTO build(){
        return new CursoEstudianteDTO(idCursoEstudiante,puntaje,nivel,usuarioCreador,usuarioModificador,fechaCreacion,fechaModificacion,idCurso,idEstudiante);
    }
}
