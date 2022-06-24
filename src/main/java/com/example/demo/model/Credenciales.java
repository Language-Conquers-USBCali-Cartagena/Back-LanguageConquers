package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="credenciales")
public class Credenciales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_credencial", nullable = false)
    private Long idCredencial;

    @Column(name = "cuenta", nullable = false, length = 80)
    private String cuenta;

    @Column(name = "password", nullable = false, length = 80)
    private String password;

    @Column(name = "url", nullable = false, length = 150)
    private String url;

    @Column(name = "plataforma", nullable = false, length = 80)
    private String plataforma;



}
