package com.example.demo.service;

import com.example.demo.model.TipoMision;

import java.util.List;

public interface TipoMisionService {

    List<TipoMision> findAll() throws Exception;

    String crearTipoMision(TipoMision tipoMision) throws Exception;
}
