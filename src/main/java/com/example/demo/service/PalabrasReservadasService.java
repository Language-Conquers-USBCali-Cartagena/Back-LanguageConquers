package com.example.demo.service;

import com.example.demo.model.PalabrasReservadas;

import java.util.List;

public interface PalabrasReservadasService {

    List<PalabrasReservadas> findAll() throws Exception;

    String crearPalabraResevada(PalabrasReservadas palabrasReservadas) throws Exception;
}
