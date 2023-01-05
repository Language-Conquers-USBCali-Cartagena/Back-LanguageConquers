package com.example.demo.service;

import com.example.demo.model.Programa;
import com.example.demo.model.dto.ProgramaDTO;

import java.util.List;

public interface ProgramaService {

    String registrar (Programa programa) throws Exception;
    String actualizar (ProgramaDTO programaDTO) throws Exception;
    String eliminar (Long idPrograma) throws Exception;
    Programa findById(Long idPrograma) throws Exception;
    List<Programa> listar()throws Exception;
}
