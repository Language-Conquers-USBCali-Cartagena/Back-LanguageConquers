package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "reto_estudiante")
public class RetoEstudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reto_estudiante", nullable = false)
    private Long idRetoEstudiante;

    private int puntaje;

}
