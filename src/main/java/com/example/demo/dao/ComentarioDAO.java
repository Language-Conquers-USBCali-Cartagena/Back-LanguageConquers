package com.example.demo.dao;

import com.example.demo.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComentarioDAO extends JpaRepository<Comentario,Long> {

    @Query(value = "select * from comentario where id_estudiante = ?1", nativeQuery = true)
    List<Comentario> findByIdEstudiante(Long idEstudiante) throws Exception;

    @Query(value = "select * from comentario where id_profesor = ?1", nativeQuery = true)
    List<Comentario> findByIdProfesor(Long idProfesor) throws Exception;

}


