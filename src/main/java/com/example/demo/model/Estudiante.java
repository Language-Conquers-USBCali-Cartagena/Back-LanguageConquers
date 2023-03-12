package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "estudiante")
public class Estudiante {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuarios_seq")
    @SequenceGenerator(name = "usuarios_seq", sequenceName = "usuarios_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "id_estudiante", nullable = false)
    private Long idEstudiante;
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 50)
    private String apellido;

    @Column(name = "nick_name", nullable = false, length = 50)
    private String nickName;

    @Column(name = "puntaje", nullable = false)
    private int puntaje;

    @Column(name = "monedasObtenidas", nullable = false)
    private int monedasObtenidas;

    @Column(name = "fecha_nacimiento", nullable = false)
    private Date fechaNacimiento;

    @Column(name = "correo", nullable = false, length = 50)
    private String correo;

    @Column(name = "usuario_creador", nullable = false, length = 50)
    private String usuarioCreador;

    @Column(name = "usuario_modificador", length = 50)
    private String usuarioModificador;

    @Column(name = "fecha_creacion", nullable = false)
    private Date fechaCreacion;

    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_programa", nullable = false)
    private Programa programa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado", nullable = false)
    private Estado estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_semestre", nullable = false)
    private Semestre semestre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_avatar", nullable = false)
    private Avatar avatar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_genero", nullable = false)
    private Genero genero;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "estudiante")
    List<CursoEstudiante> cursoEstudiantes = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "estudiante")
    List<MisionEstudiante> misionEstudiantes = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "estudiante")
    List<ArticulosAdquiridos> articulosAdquiridos = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "estudiante")
    List<RetoEstudiante> retoEstudiantes = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "estudiante")
    List<LogroEstudiante> logroEstudiantes = new ArrayList<>();

}
