package com.example.demo.dao;

import com.example.demo.model.ArticulosAdquiridos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticulosAdquiridosDAO extends JpaRepository<ArticulosAdquiridos, Long> {

    @Query(value = "select * from articulo_adquirido where id_estudiante = ?1", nativeQuery = true)
    List<ArticulosAdquiridos> findByIdEstudiante(Long idEstudiante) throws Exception;
}
