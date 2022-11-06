package com.example.demo.model.dto;

import com.example.demo.model.Estudiante;
import com.example.demo.model.Logro;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class LogroEstudianteDTO implements Serializable {


    private static final long serialVersionUID = -2653304020662126067L;

    private Long idLogroEstudiante;
    private String usuarioCreador;
    private String usuarioModificador;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Long idEstudiante;
    private Long idLogro;
}
