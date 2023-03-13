package com.example.demo.service;

import com.example.demo.model.Reto;
import com.example.demo.model.dto.RetoDTO;

import java.util.List;

public interface RetoService {

    List<Reto> listReto() throws Exception;

    String registrar(Reto reto) throws Exception;

    String actualizar (RetoDTO retoDTO) throws Exception;
    String eliminar (Long idReto) throws Exception;
    Reto findById(Long idReto) throws Exception;

    String habilitarReto(RetoDTO reto) throws  Exception;
}
