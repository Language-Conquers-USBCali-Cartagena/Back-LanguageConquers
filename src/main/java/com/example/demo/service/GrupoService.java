package com.example.demo.service;

import com.example.demo.model.Grupo;
import com.example.demo.model.dto.GrupoDTO;

import java.util.List;

public interface GrupoService {

    Grupo registrar(Grupo grupo)throws Exception;
    Grupo actualizar(GrupoDTO grupoDTO) throws Exception;
    void eliminar (Long idGrupo)throws Exception;
    List<Grupo>listar();
}
