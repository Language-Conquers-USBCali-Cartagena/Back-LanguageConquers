package com.example.demo.dao;

import com.example.demo.model.CursoEstudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CursoEstudianteDAO extends JpaRepository<CursoEstudiante,Long> {

    @Query(value = "select * from curso_estudiante where id_estudiante = ?1", nativeQuery = true)
    List<CursoEstudiante> findByIdEstudiante(Long idEstudiante) throws Exception;

    @Query(value = "select * from curso_estudiante where id_curso = ?1", nativeQuery = true)
    List<CursoEstudiante> findByIdCurso(Long idCurso) throws Exception;

    @Query(value = "SELECT * FROM curso_estudiante WHERE id_curso = ?1 AND id_estudiante = ?2", nativeQuery = true)
    CursoEstudiante findByIdCursoAndIdEstudiante(Long idCurso, Long idEstudiante) throws Exception;

    @Query(value = "select count(*) from curso_estudiante where id_curso = ?1", nativeQuery = true)
    int estudiantesMatriculados (Long idCurso) throws Exception;

}
