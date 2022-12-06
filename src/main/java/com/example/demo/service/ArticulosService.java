package com.example.demo.service;

import com.example.demo.model.Articulos;

import java.util.List;

public interface ArticulosService {

    String save(Articulos articulos) throws Exception;

    String update(Articulos articulos) throws Exception;

    String delete(Long idArticulo) throws Exception;

    List<Articulos> findAll() throws Exception;
}
