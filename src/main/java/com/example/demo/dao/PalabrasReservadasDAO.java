package com.example.demo.dao;

import com.example.demo.model.PalabrasReservadas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PalabrasReservadasDAO extends JpaRepository<PalabrasReservadas, Long> {

    @Query(value = "SELECT (pr.id_palabra_reservada, pr.nombre, pr.categoria, \n" +
            "        pr.fecha_creacion, pr.fecha_modificacion, pr.orden, pr.usuario_creador)\n" +
            "FROM palabra_reto pre JOIN palabra_reservada pr on pre.id_palabra = pr.id_palabra_reservada WHERE id_reto = ?1",
    nativeQuery = true)
    public List<PalabrasReservadas> findByIdReto(Long idReto) throws Exception;
}
