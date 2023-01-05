package com.example.demo.service;

import com.example.demo.model.NivelMision;
import com.example.demo.model.dto.NivelMisionDTO;

import java.util.List;

public interface NivelMisionService {

    List<NivelMision> findAll() throws Exception;

    String Registrar(NivelMision nivelMision) throws Exception;
    String actualizar(NivelMisionDTO nivelMisionDTO) throws  Exception;
    String eliminar (Long idNivelMision) throws Exception;
    NivelMision findById(Long idNivelMision) throws Exception;
}
