package com.example.demo.dao;

import com.example.demo.model.ArticulosAdquiridos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticulosAdquiridosDAO extends JpaRepository<ArticulosAdquiridos, Long> {

    @Query(value = "select * from articulo_adquirido where id_estudiante = ?1", nativeQuery = true)
    List<ArticulosAdquiridos> findByIdEstudiante(Long idEstudiante) throws Exception;

    @Query(value = "select * from articulo_adquirido where id_articulo = ?1", nativeQuery = true)
    List<ArticulosAdquiridos> findByIdArticulo(Long idArticulo) throws Exception;

    @Query(value = "SELECT * FROM articulo_adquirido WHERE id_estudiante = ?1 AND id_articulo = ?2", nativeQuery = true)
    ArticulosAdquiridos findByIdArticuloAndIdEstudiante(Long idEstudiante, Long idArticulo) throws Exception;
}
