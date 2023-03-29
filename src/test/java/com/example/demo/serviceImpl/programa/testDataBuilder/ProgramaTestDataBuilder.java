package com.example.demo.serviceImpl.programa.testDataBuilder;

import com.example.demo.model.Programa;
import com.example.demo.model.dto.ProgramaDTO;

import java.time.LocalDate;
import java.util.Date;

public class ProgramaTestDataBuilder {

    private Long idPrograma;
    private String nombre;
    private String usuarioCreador;
    private String usuarioModificador;
    private Date fechaCreacion;
    private Date fechaModificacion;

    public ProgramaTestDataBuilder() {
        this.idPrograma = 1348857854L;
        this.nombre = "Test";
        this.usuarioCreador = "Test";
        this.usuarioModificador = "Test";
        this.fechaCreacion = new Date();
        this.fechaModificacion = new Date();;
    }

    public ProgramaTestDataBuilder conIdPrograma(Long idPrograma){
        this.idPrograma = idPrograma;
        return this;
    }

    public ProgramaTestDataBuilder conNombre(String nombre){
        this.nombre = nombre;
        return this;
    }

    public ProgramaTestDataBuilder conUsuarioCreador(String usuarioCreador){
        this.usuarioCreador= usuarioCreador;
        return this;
    }

    public ProgramaTestDataBuilder conFechaCreacion(Date fechaCreacion){
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public ProgramaTestDataBuilder conUsuarioModificador(String usuarioModificador){
        this.usuarioModificador = usuarioModificador;
        return this;
    }

    public ProgramaTestDataBuilder conFechaModificacion(Date fechaModificacion){
        this.fechaModificacion = fechaModificacion;
        return this;
    }

    public ProgramaDTO build(){
        return new ProgramaDTO(idPrograma,nombre,usuarioCreador,usuarioModificador, fechaCreacion, fechaModificacion);
    }
}
