package com.example.demo.dao;

import com.example.demo.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EstudianteDAO extends JpaRepository<Estudiante,Long> {

    @Query(value = "select * from estudiante where id_avatar = ?1", nativeQuery = true)
    List<Estudiante> findByIdAvatar(Long idAvatar) throws Exception;

    @Query(value = "select * from estudiante where id_genero = ?1", nativeQuery = true)
    List<Estudiante> findByIdGenero(Long idGenero) throws Exception;

    @Query(value = "select * from estudiante where id_estado = ?1", nativeQuery = true)
    List<Estudiante> findByIdEstado(Long idEstado) throws Exception;

    @Query(value = "select * from estudiante where id_programa = ?1", nativeQuery = true)
    List<Estudiante> findByIdPrograma(Long idPrograma) throws Exception;

    @Query(value = "select * from estudiante where id_semestre = ?1", nativeQuery = true)
    List<Estudiante> findByIdSemestre(Long idSemestre) throws Exception;

    Boolean existsByCorreo(String correo) throws Exception;

    Estudiante findByCorreo(String correo) throws Exception;

    @Query(value = "SELECT * FROM estudiante ORDER BY puntaje DESC", nativeQuery = true)
    List<Estudiante> rankingEstudiantes () throws Exception;
    @Query(value = "SELECT COUNT(*) From estudiante", nativeQuery = true)
    int totalEstudiante()throws Exception;
}
