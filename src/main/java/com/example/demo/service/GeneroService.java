package com.example.demo.service;

import com.example.demo.model.Genero;
import com.example.demo.model.dto.GeneroDTO;

import java.util.List;

public interface GeneroService {

    String registrar(Genero genero) throws Exception;
    String actualizar(GeneroDTO generoDTO) throws Exception;
    String eliminar(Long idGenero) throws Exception;
    Genero findById(Long idGenero) throws Exception;
    List<Genero> listar()throws Exception;
}
