package com.example.demo.service;

import com.example.demo.model.Articulos;
import com.example.demo.model.dto.ArticulosDTO;

import java.util.List;

public interface ArticulosService {

    List<Articulos> articulosObtenidos(Long idEstudiante) throws Exception;

    List<Articulos> articulosNoObtenidos(Long idEstudiante) throws Exception;
    String registrar(Articulos articulos) throws Exception;

    String actualizar(Articulos articulos) throws Exception;

    String eliminar(Long idArticulo) throws Exception;

    Articulos findById(Long idArticulo) throws Exception;
    List<Articulos> findAll() throws Exception;

    int articulosRegistrados()throws Exception;
}
