package com.example.demo.serviceImpl.avatar.testDataBuilder;

import com.example.demo.model.dto.AvatarDTO;

import java.util.Date;

public class AvatarTestDataBuilder {
    private Long idAvatar;
    private String nombreAvatar;
    private String imgAvatar;
    private String usuarioCreador;
    private String usuarioModificador;
    private Date fechaCreacion;
    private Date fechaModificacion;

    public AvatarTestDataBuilder(){
        this.idAvatar = 2377647L;
        this.nombreAvatar = "Leñador";
        this.imgAvatar = "lenador.png";
        this.usuarioCreador = "Angela";
        this.usuarioModificador = "Angela";
        this.fechaCreacion = new Date();
        this.fechaModificacion = new Date();
    }

    public AvatarTestDataBuilder conIdAvatar(Long idAvatar) {
        this.idAvatar = idAvatar;
        return this;
    }

    public AvatarTestDataBuilder conNombreAvatar(String nombreAvatar) {
        this.nombreAvatar = nombreAvatar;
        return this;
    }

    public AvatarTestDataBuilder conImgAvatar(String imgAvatar) {
        this.imgAvatar = imgAvatar;
        return this;
    }

    public AvatarTestDataBuilder conUsuarioCreador(String usuarioCreador) {
        this.usuarioCreador = usuarioCreador;
        return this;
    }

    public AvatarTestDataBuilder conUsuarioModificador(String usuarioModificador) {
        this.usuarioModificador = usuarioModificador;
        return this;
    }

    public AvatarTestDataBuilder conFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public AvatarTestDataBuilder conFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
        return this;
    }

    public AvatarDTO build(){
        return new AvatarDTO(idAvatar,nombreAvatar,imgAvatar,usuarioCreador,usuarioModificador,fechaCreacion,fechaModificacion);
    }
}
