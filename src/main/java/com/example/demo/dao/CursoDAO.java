package com.example.demo.dao;

import com.example.demo.model.Curso;
import com.example.demo.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CursoDAO extends JpaRepository<Curso,Long> {

    @Query(value = "select * from curso where id_estado = ?1", nativeQuery = true)
    List<Curso> findByIdEstado(Long idEstado) throws Exception;
}
