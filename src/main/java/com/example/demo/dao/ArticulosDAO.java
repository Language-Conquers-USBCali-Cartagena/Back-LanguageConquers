package com.example.demo.dao;

import com.example.demo.model.Articulos;
import com.example.demo.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticulosDAO extends JpaRepository<Articulos, Long> {
    @Query(value = "select * from articulo where id_estado = ?1", nativeQuery = true)
    List<Articulos> findByIdEstado(Long idEstado) throws Exception;
}
