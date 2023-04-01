package com.example.demo.serviceImpl.articulosAdquiridos.testDataBuilder;

import com.example.demo.model.dto.ArticulosAdquiridosDTO;

import java.util.Date;

public class ArticulosAdquiridosTestDataBuilder {

    private Long idArticuloAdquirido;
    private String usuarioCreador;
    private String usuarioModificador;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Long idEstudiante;
    private Long idArticulos;

    public ArticulosAdquiridosTestDataBuilder(){
        this.idArticuloAdquirido = 234984L;
        this.idArticulos = 264L;
        this.idEstudiante = 238734L;
        this.usuarioCreador = "Angela";
        this.usuarioModificador = "Angela";
        this.fechaCreacion = new Date();
        this.fechaModificacion = new Date();
    }

    public ArticulosAdquiridosTestDataBuilder  conIdArticuloAdquirido(Long idArticuloAdquirido) {
        this.idArticuloAdquirido = idArticuloAdquirido;
        return this;
    }

    public ArticulosAdquiridosTestDataBuilder  conUsuarioCreador(String usuarioCreador) {
        this.usuarioCreador = usuarioCreador;
        return this;
    }

    public ArticulosAdquiridosTestDataBuilder  conUsuarioModificador(String usuarioModificador) {
        this.usuarioModificador = usuarioModificador;
        return this;
    }

    public ArticulosAdquiridosTestDataBuilder  conFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public ArticulosAdquiridosTestDataBuilder  conFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
        return this;
    }

    public ArticulosAdquiridosTestDataBuilder  conIdEstudiante(Long idEstudiante) {
        this.idEstudiante = idEstudiante;
        return this;
    }

    public ArticulosAdquiridosTestDataBuilder  conIdArticulos(Long idArticulos) {
        this.idArticulos = idArticulos;
        return this;
    }

    public ArticulosAdquiridosDTO build(){
        return new ArticulosAdquiridosDTO(idArticuloAdquirido, usuarioCreador, usuarioModificador,fechaCreacion,fechaModificacion, idEstudiante,idArticulos);
    }
}
