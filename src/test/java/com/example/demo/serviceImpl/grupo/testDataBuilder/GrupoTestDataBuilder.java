package com.example.demo.serviceImpl.grupo.testDataBuilder;

import com.example.demo.model.dto.GrupoDTO;

import java.util.Date;

public class GrupoTestDataBuilder {

    private Long idGrupo;
    private String nombre;
    private String usuarioCreador;
    private String usuarioModificador;
    private Date fechaCreacion;
    private Date fechaModificacion;

    public GrupoTestDataBuilder(){
        this.idGrupo =783787L;
        this.nombre = "Los fantasticos";
        this.usuarioCreador = "Angela";
        this.usuarioModificador = "Angela";
        this.fechaCreacion = new Date();
        this.fechaModificacion = new Date();
    }

    public GrupoTestDataBuilder conIdGrupo(Long idGrupo) {
        this.idGrupo = idGrupo;
        return this;
    }

    public GrupoTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public GrupoTestDataBuilder conUsuarioCreador(String usuarioCreador) {
        this.usuarioCreador = usuarioCreador;
        return this;
    }

    public GrupoTestDataBuilder conUsuarioModificador(String usuarioModificador) {
        this.usuarioModificador = usuarioModificador;
        return this;
    }

    public GrupoTestDataBuilder conFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public GrupoTestDataBuilder conFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
        return this;
    }

    public GrupoDTO build(){
        return new GrupoDTO(idGrupo,nombre,usuarioCreador,usuarioModificador,fechaCreacion,fechaModificacion);
    }
}
