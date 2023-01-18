package com.example.demo.service;

import com.example.demo.model.Categoria;
import com.example.demo.model.dto.CategoriaDTO;

import java.util.List;

public interface CategoriaService {

    String registrar(Categoria categoria) throws Exception;
    String actualizar(CategoriaDTO categoriaDTO) throws Exception;
    String eliminar (Long idCategoria) throws Exception;
    List<Categoria> findAll() throws Exception;
    Categoria findById(Long idCategoria) throws Exception;

}
