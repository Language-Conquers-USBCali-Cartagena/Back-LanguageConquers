package com.example.demo.service;

import com.example.demo.model.Genero;
import com.example.demo.model.dto.GeneroDTO;

import java.util.List;

public interface GeneroService {

    Genero registrar(Genero genero) throws Exception;
    Genero actualizar(GeneroDTO generoDTO) throws Exception;
    void eliminar(Long idGenero) throws Exception;
    List<Genero> listar();
}
