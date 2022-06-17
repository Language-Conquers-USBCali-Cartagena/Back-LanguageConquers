package com.example.demo.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class ComentarioDTO implements Serializable {


    private static final long serialVersionUID = 712086259349447539L;

    private Long idComentario;
    private String comentario;
    private Date fecha;
    private String usuarioCreador;
    private String usuarioModificador;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Long idReto;
    private Long idProfesor;
    private Long idEstudiante;
}
