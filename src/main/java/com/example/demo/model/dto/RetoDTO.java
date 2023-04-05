package com.example.demo.model.dto;

import com.example.demo.model.Reto;
import com.example.demo.model.RetoEstudiante;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RetoDTO implements Serializable {

    public RetoDTO(Reto reto, RetoEstudiante retoEstudiante) {
        this.idReto = reto.getIdReto();
        this.nombreReto = reto.getNombreReto();
        this.descripcion = reto.getDescripcion();
        this.moneda = reto.getMoneda();
        this.maximoIntentos = reto.getMaximoIntentos();
        this.descripcionTeoria = reto.getDescripcionTeoria();
        this.fechaInicio = reto.getFechaInicio();
        this.fechaLimite = reto.getFechaLimite();
        this.solucion = reto.getSolucion();
        this.esGrupal = reto.getEsGrupal();
        this.usuarioCreador = reto.getUsuarioCreador();
        this.usuarioModificador = reto.getUsuarioModificador();
        this.fechaCreacion = reto.getFechaCreacion();
        this.fechaModificacion = reto.getFechaModificacion();
        this.urlVideo1 = reto.getUrlVideo1();
        this.urlVideo2 = reto.getUrlVideo2();
        this.imagenTema1 = reto.getImagenTema1();
        this.imagenTema2 = reto.getImagenTema2();
        this.idMision = reto.getMision().getIdMision();
        this.idEstado = reto.getEstado().getIdEstado();
        this.idCurso = reto.getCurso().getIdCurso();
        this.idEstadoRetoEstu = retoEstudiante.getEstado().getIdEstado();
    }

    private static final long serialVersionUID = -706319021765545474L;

    private Long idReto;
    private String nombreReto;
    private String descripcion;
    private int moneda;
    private int maximoIntentos;
    private String descripcionTeoria;
    private Date fechaInicio;
    private Date fechaLimite;
    private String solucion;
    private boolean esGrupal;

    private String usuarioCreador;
    private String usuarioModificador;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private String urlVideo1;
    private String urlVideo2;
    private String imagenTema1;
    private String imagenTema2;
    private Long idMision;
    private Long idEstado;
    private Long idCurso;
    private Long idEstadoRetoEstu;
}
