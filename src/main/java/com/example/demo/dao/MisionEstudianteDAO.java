package com.example.demo.dao;

import com.example.demo.model.MisionEstudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MisionEstudianteDAO extends JpaRepository<MisionEstudiante,Long> {

    @Query(value = "select * from mision_estudiante where id_estado = ?1", nativeQuery = true)
    List<MisionEstudiante> findByIdEstado(Long idEstado) throws Exception;

    @Query(value = "select * from mision_estudiante where id_estudiante = ?1", nativeQuery = true)
    List<MisionEstudiante> findByIdEstudiante(Long idEstudiante) throws Exception;
}
