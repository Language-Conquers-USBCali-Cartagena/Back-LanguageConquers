package com.example.demo.service;

import com.example.demo.model.Mision;

import java.util.List;

public interface MisionService {

    List<Mision> ListarMisiones() throws Exception;

    String crearMision(Mision mision) throws Exception;
}
