package com.example.demo.dao;

import com.example.demo.model.Mision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MisionDAO extends JpaRepository<Mision, Long> {

    @Query(value = "select * from mision where id_curso = ?1", nativeQuery = true)
    List<Mision> findByIdCurso(Long idCurso)throws Exception;


}
