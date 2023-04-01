package com.example.demo.serviceImpl.credenciales.testDataBuilder;

import com.example.demo.model.dto.CredencialesDTO;

import java.util.Date;

public class CredencialesTestDataBuilder {

    private Long idCredencial;
    private String cuenta;
    private String password;
    private String url;
    private String plataforma;
    private String usuarioCreador;
    private String usuarioModificador;
    private Date fechaCreacion;
    private Date fechaModificacion;

    public CredencialesTestDataBuilder(){
        this.idCredencial = 2380L;
        this.cuenta = "languageConquers@gmail.com";
        this.password = "32893";
        this.url = "https://herokuapp.com";
        this.plataforma = "Heroku";
        this.usuarioCreador = "Angela";
        this.usuarioModificador = "Angela";
        this.fechaCreacion = new Date();
        this.fechaModificacion = new Date();
    }

    public CredencialesTestDataBuilder conIdCredencial(Long idCredencial) {
        this.idCredencial = idCredencial;
        return this;
    }

    public CredencialesTestDataBuilder conCuenta(String cuenta) {
        this.cuenta = cuenta;
        return this;
    }

    public CredencialesTestDataBuilder conPassword(String password) {
        this.password = password;
        return this;
    }

    public CredencialesTestDataBuilder conUrl(String url) {
        this.url = url;
        return this;
    }

    public CredencialesTestDataBuilder conPlataforma(String plataforma) {
        this.plataforma = plataforma;
        return this;
    }

    public CredencialesTestDataBuilder conUsuarioCreador(String usuarioCreador) {
        this.usuarioCreador = usuarioCreador;
        return this;
    }

    public CredencialesTestDataBuilder consuarioModificador(String usuarioModificador) {
        this.usuarioModificador = usuarioModificador;
        return this;
    }

    public CredencialesTestDataBuilder conFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public CredencialesTestDataBuilder conFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
        return this;
    }

    public CredencialesDTO build(){
        return new CredencialesDTO(idCredencial,cuenta, password,url,plataforma, usuarioCreador,usuarioModificador,fechaCreacion,fechaModificacion);
    }


}
