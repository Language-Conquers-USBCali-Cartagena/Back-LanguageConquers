package com.example.demo.service;

import com.example.demo.model.PalabraReto;

import java.util.List;

public interface PalabraRetoService {

    List<PalabraReto> findAll() throws Exception;

    String crearPalabraReto(PalabraReto palabraReto) throws Exception;

    String editarPalabraReto(PalabraReto palabraReto) throws Exception;

    String eliminarPalabraReto(Long idPalabraReto) throws Exception;

    List<PalabraReto> findbyIdReto (Long idReto) throws Exception;
}
