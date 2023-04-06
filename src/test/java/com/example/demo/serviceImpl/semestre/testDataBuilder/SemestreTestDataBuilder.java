package com.example.demo.serviceImpl.semestre.testDataBuilder;

import com.example.demo.model.dto.SemestreDTO;

import java.util.Date;

public class SemestreTestDataBuilder {
    private Long idSemestre;
    private String nombre;
    private String usuarioCreador;
    private String usuarioModificador;
    private Date fechaCreacion;
    private Date fechaModificacion;

    public SemestreTestDataBuilder() {
        this.idSemestre = 1500045465465L;
        this.nombre = "pepito";
        this.usuarioCreador = "admin";
        this.usuarioModificador = "admin";
        this.fechaCreacion = new Date();
        this.fechaModificacion = new Date();
    }

    public SemestreTestDataBuilder conIdSemestre(Long idSemestre){
        this.idSemestre = idSemestre;
        return this;
    }
    public SemestreTestDataBuilder conNombre(String nombre){
        this.nombre = nombre;
        return this;
    }
    public SemestreTestDataBuilder conUsuarioCreador(String usuarioCreador){
        this.usuarioCreador = usuarioCreador;
        return this;
    }
    public SemestreTestDataBuilder conUsuarioModificador(String usuarioModificador){
        this.usuarioModificador = usuarioModificador;
        return this;
    }
    public SemestreTestDataBuilder conFechaCreacion(Date fechaCreacion){
        this.fechaCreacion = fechaCreacion;
        return this;
    }
    public SemestreTestDataBuilder conFechaModificacion(Date fechaModificacion){
        this.fechaModificacion = fechaModificacion;
        return this;
    }

    public SemestreDTO build(){
        return new SemestreDTO(idSemestre,
                nombre,
                usuarioCreador,
                usuarioModificador,
                fechaCreacion,
                fechaModificacion);
    }
}
