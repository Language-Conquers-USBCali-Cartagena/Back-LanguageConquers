package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "palabra_reto")
public class PalabraReto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_palabra_reto", nullable = false)
    private Long idPalabraReto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_palabra", nullable = false)
    private PalabrasReservadas palabrasReservadas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reto", nullable = false)
    private Reto reto;
}
