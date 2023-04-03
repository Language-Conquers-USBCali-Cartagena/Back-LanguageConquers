package com.example.demo.service;

import com.example.demo.model.PalabrasReservadas;
import com.example.demo.model.Reto;
import com.example.demo.model.dto.PalabrasReservadasDTO;
import com.example.demo.model.dto.RetoDTO;

import java.util.List;

public interface RetoService {

    List<Reto> listReto() throws Exception;

    String registrar(Reto reto) throws Exception;

    String actualizar (Reto reto) throws Exception;
    String eliminar (Long idReto) throws Exception;
    Reto findById(Long idReto) throws Exception;
    int promedioMonedasRetos()throws Exception;
    int retosRegistrados()throws Exception;
    String completarReto(List<PalabrasReservadasDTO> palabrasReservadas, Boolean esBasico, Long retoId, Long estudianteId) throws Exception;
    List<RetoDTO> retosPorEstudiante(Long idEstudiante) throws Exception;


}
