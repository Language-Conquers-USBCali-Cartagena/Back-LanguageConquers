package com.example.demo.serviceImpl.estudiante.testDataBuilder;

import com.example.demo.model.dto.EstudianteDTO;

import java.util.Date;

public class EstudianteTestDataBuilder {

    private Long idEstudiante;
    private String nombre;
    private String apellido;
    private String nickName;
    private int puntaje;
    private int monedasObtenidas;
    private Date fechaNacimiento;
    private String correo;
    private String usuarioCreador;
    private String usuarioModificador;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Long idPrograma;
    private Long idEstado;
    private Long idSemestre;
    private Long idAvatar;
    private Long idGenero;

    public EstudianteTestDataBuilder(){
        this.idEstudiante = 372684L;
        this.nombre = "Angela";
        this.apellido = "Acosta";
        this.nickName = "angela2318";
        this.puntaje = 300;
        this.monedasObtenidas = 0;
        this.fechaNacimiento = new Date(2000,07,31);
        this.correo = "angelamaria731@hotmail.com";
        this.usuarioCreador = "Angela";
        this.usuarioModificador = "Angela";
        this.fechaCreacion = new Date();
        this.fechaModificacion = new Date();
        this.idPrograma = 7238737L;
        this.idEstado =7438L;
        this.idSemestre =8783487L;
        this.idAvatar =7372684L;
        this.idGenero = 347847L;
    }

    public EstudianteTestDataBuilder conIdEstudiante(Long idEstudiante) {
        this.idEstudiante = idEstudiante;
        return this;
    }

    public EstudianteTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public EstudianteTestDataBuilder conApellido(String apellido) {
        this.apellido = apellido;
        return this;
    }

    public EstudianteTestDataBuilder conNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public EstudianteTestDataBuilder conPuntaje(int puntaje) {
        this.puntaje = puntaje;
        return this;
    }

    public EstudianteTestDataBuilder conMonedasObtenidas(int monedasObtenidas) {
        this.monedasObtenidas = monedasObtenidas;
        return this;
    }

    public EstudianteTestDataBuilder conFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
        return this;
    }

    public EstudianteTestDataBuilder conCorreo(String correo) {
        this.correo = correo;
        return this;
    }

    public EstudianteTestDataBuilder conUsuarioCreador(String usuarioCreador) {
        this.usuarioCreador = usuarioCreador;
        return this;
    }

    public EstudianteTestDataBuilder conUsuarioModificador(String usuarioModificador) {
        this.usuarioModificador = usuarioModificador;
        return this;
    }

    public EstudianteTestDataBuilder conFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public EstudianteTestDataBuilder conFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
        return this;
    }

    public EstudianteTestDataBuilder conIdPrograma(Long idPrograma) {
        this.idPrograma = idPrograma;
        return this;
    }

    public EstudianteTestDataBuilder conIdEstado(Long idEstado) {
        this.idEstado = idEstado;
        return this;
    }

    public EstudianteTestDataBuilder conIdSemestre(Long idSemestre) {
        this.idSemestre = idSemestre;
        return this;
    }

    public EstudianteTestDataBuilder conIdAvatar(Long idAvatar) {
        this.idAvatar = idAvatar;
        return this;
    }

    public EstudianteTestDataBuilder conIdGenero(Long idGenero) {
        this.idGenero = idGenero;
        return this;
    }

    public EstudianteDTO build(){
        return new EstudianteDTO(idEstudiante,nombre,apellido,nickName,puntaje,monedasObtenidas,fechaNacimiento,correo,usuarioCreador,usuarioModificador,fechaCreacion,fechaModificacion,idPrograma,idEstado,idSemestre,idAvatar,idGenero);
    }
}
