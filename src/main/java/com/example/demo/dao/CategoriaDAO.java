package com.example.demo.dao;

import com.example.demo.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoriaDAO extends JpaRepository<Categoria,Long> {

    @Query(value = "select * from categoria where id_estado = ?1", nativeQuery = true)
    List<Categoria> findByIdEstado(Long idEstado) throws Exception;
}
