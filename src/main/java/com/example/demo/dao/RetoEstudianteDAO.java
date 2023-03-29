package com.example.demo.dao;


import com.example.demo.model.RetoEstudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RetoEstudianteDAO extends JpaRepository<RetoEstudiante,Long> {

    @Query(value = "select * from reto_estudiante where id_estado = ?1", nativeQuery = true)
    List<RetoEstudiante> findByIdEstado(Long idEstado) throws Exception;

    @Query(value = "select * from reto_estudiante where id_grupo = ?1", nativeQuery = true)
    List<RetoEstudiante> findByIdGrupo(Long idGrupo) throws Exception;

    @Query(value = "select * from reto_estudiante where id_rol = ?1", nativeQuery = true)
    List<RetoEstudiante> findByIdRol(Long idRol) throws Exception;

    @Query(value = "select * from reto_estudiante where id_estudiante = ?1", nativeQuery = true)
    List<RetoEstudiante> findByIdEstudiante(Long idEstudiante) throws Exception;

    @Query(value = "select * from reto_estudiante where id_reto = ?1", nativeQuery = true)
    List<RetoEstudiante> findByIdReto(Long idReto) throws Exception;

    @Query(value = "SELECT COALESCE(AVG(retos_completados),0) as promedio_retos_completados FROM (select count(*) as retos_completados from reto_estudiante where id_estado = 3 group by id_estudiante) as t", nativeQuery = true)
    int promedioRetosCompletadosEstudiantes()throws Exception;


}
