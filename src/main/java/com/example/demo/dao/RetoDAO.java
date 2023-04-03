package com.example.demo.dao;

import com.example.demo.model.Estudiante;
import com.example.demo.model.Reto;
import com.example.demo.model.dto.RetoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RetoDAO extends JpaRepository<Reto,Long> {

    @Query(value = "select * from reto where id_estado = ?1", nativeQuery = true)
    List<Reto> findByIdEstado(Long idEstado) throws Exception;
    @Query(value = "select * from reto where id_curso = ?1", nativeQuery = true)
    List<Reto> findByIdCurso(Long idCurso) throws Exception;

    @Query(value = "select * from reto where id_mision = ?1", nativeQuery = true)
    List<Reto> findByIdMision(Long idMision) throws Exception;

    @Query(value = "SELECT AVG(moneda) FROM reto", nativeQuery = true)
    int promedioMonedasRetos()throws Exception;

    @Query(value = "select count(*) from reto", nativeQuery = true)
    int retosRegistrados()throws Exception;

    @Query(value = "SELECT r.id_reto, r.descripcion, r.es_grupal, r.fecha_creacion, r.fecha_inicio, r.fecha_limite, r.fecha_modificacion,\n" +
            "       r.maximo_intentos, r.nombre_reto, r.nr_estudiantes_grupo, r.usuario_creador, r.usuario_modificador, r.id_curso,\n" +
            "       r.id_estado, r.id_mision, r.moneda, r.solucion, r.descripcion_teoria, r.imagen_tema1, r.imagen_tema2, r.url_video1,\n" +
            "       r.url_video2, r.nivel\n" +
            "FROM reto r\n" +
            "INNER JOIN reto_estudiante re on (r.id_reto = re.id_reto)\n" +
            "WHERE re.id_estudiante = ?1", nativeQuery = true)
    List<Reto> retoEstudiante(Long idEstudiante) throws Exception;

}
