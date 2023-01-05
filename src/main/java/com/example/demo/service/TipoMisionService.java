package com.example.demo.service;

import com.example.demo.model.TipoMision;
import com.example.demo.model.dto.TipoMisionDTO;

import java.util.List;

public interface TipoMisionService {

    List<TipoMision> findAll() throws Exception;

    String registrar(TipoMision tipoMision) throws Exception;
    String actualizar (TipoMisionDTO tipoMisionDTO) throws Exception;
    String eliminar (Long idTipoMision)throws Exception;

    TipoMision findById(Long idTipoMision) throws Exception;
}
