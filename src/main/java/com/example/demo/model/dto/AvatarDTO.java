package com.example.demo.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class AvatarDTO implements Serializable {


    private static final long serialVersionUID = -6595843117620767086L;

    private Long idAvatar;
    private String nombreAvatar;
    private String imgAvatar;
    private String usuarioCreador;
    private String usuarioModificador;
    private Date fechaCreacion;
    private Date fechaModificacion;
}
