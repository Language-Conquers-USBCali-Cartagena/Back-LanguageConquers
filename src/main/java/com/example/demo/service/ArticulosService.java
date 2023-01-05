package com.example.demo.service;

import com.example.demo.model.Articulos;
import com.example.demo.model.dto.ArticulosDTO;

import java.util.List;

public interface ArticulosService {

    String registrar(Articulos articulos) throws Exception;

    String actualizar(ArticulosDTO articulosDTO) throws Exception;

    String eliminar(Long idArticulo) throws Exception;

    Articulos findById(Long idArticulo) throws Exception;
    List<Articulos> findAll() throws Exception;
}
