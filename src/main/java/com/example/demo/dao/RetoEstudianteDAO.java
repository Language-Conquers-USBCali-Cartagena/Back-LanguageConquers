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
}
