package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "tipo_mision")
public class TipoMision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_mision", nullable = false)
    private Long idTipoMision;

    public Long getIdTipoMision() {
        return idTipoMision;
    }

    public void setIdTipoMision(Long idTipoMision) {
        this.idTipoMision = idTipoMision;
    }
}
