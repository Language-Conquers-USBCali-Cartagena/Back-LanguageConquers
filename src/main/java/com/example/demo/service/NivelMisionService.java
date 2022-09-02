package com.example.demo.service;

import com.example.demo.model.NivelMision;

import java.util.List;

public interface NivelMisionService {

    List<NivelMision> findAll() throws Exception;

    String crearNivelMision(NivelMision nivelMision) throws Exception;
}
