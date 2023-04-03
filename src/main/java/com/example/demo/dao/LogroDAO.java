package com.example.demo.dao;

import com.example.demo.model.Logro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LogroDAO extends JpaRepository<Logro, Long> {

    @Query(value = "SELECT * FROM logro", nativeQuery = true)
    Page<Logro> findAll(Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM LOGRO", nativeQuery = true)
    int logrosRegistrados()throws Exception;

    @Query(value = "SELECT * FROM logro WHERE id_logro NOT IN( SELECT id_logro FROM logro_estudiante WHERE id_estudiante = ?1);", nativeQuery = true)
    List<Logro> logrosNoObtenidos(Long idEstudiante);

    @Query(value = "SELECT l.id_logro, l.descripcion, l.fecha_creacion, l.fecha_modificacion, l.imagen, l.nombre, l.usuario_creador, l.usuario_modificador\n" +
            "FROM logro l INNER JOIN logro_estudiante le on (l.id_logro = le.id_logro)\n" +
            "WHERE id_estudiante = ?1", nativeQuery = true)
    List<Logro> logrosObtenidos(Long idEstudiante) throws Exception;

    @Query(value = "SELECT ((SELECT COUNT(*) FROM logro_estudiante INNER JOIN logro l on l.id_logro = logro_estudiante.id_logro\n" +
            "        WHERE id_estudiante = ?1)*100)/(SELECT COUNT(*) FROM logro)", nativeQuery = true)
    Integer progresoLogros(Long idEstudiante) throws Exception;

}
