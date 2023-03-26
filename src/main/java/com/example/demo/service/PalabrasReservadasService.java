package com.example.demo.service;

import com.example.demo.model.PalabrasReservadas;
import com.example.demo.model.dto.PalabrasReservadasDTO;

import java.util.List;

public interface PalabrasReservadasService {

    List<PalabrasReservadas> findAll() throws Exception;

    String crearPalabraResevada(PalabrasReservadas palabrasReservadas) throws Exception;

    String procesarPalabraReservada(List<PalabrasReservadasDTO> palabrasReservadasDTOs, Boolean esBasico) throws Exception;

    List<PalabrasReservadas> findByIdReto(Long idReto) throws Exception;

}
