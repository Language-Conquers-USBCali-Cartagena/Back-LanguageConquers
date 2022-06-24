package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "monedas")
public class Monedas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_moneda", nullable = false)
    private Long idMoneda;

    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    @Column(name = "img_monedas", nullable = false, length = 80)
    private String imgMonedas;

    @Column(name = "usuario_creador", nullable = false, length = 50)
    private String usuarioCreador;

    @Column(name = "usuario_modificador", length = 50)
    private String usuarioModificador;

    @Column(name = "fecha_creacion", nullable = false)
    private Date fechaCreacion;

    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "monedas")
    List<Mision> misiones = new ArrayList<>();
}
