package com.example.demo.dao;

import com.example.demo.model.Estudiante;
import com.example.demo.model.Reto;
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





}
