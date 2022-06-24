package com.example.demo.service;

import com.example.demo.model.Programa;
import com.example.demo.model.dto.ProgramaDTO;

import java.util.List;

public interface ProgramaService {

    Programa registrar (Programa programa) throws Exception;
    Programa actualizar (ProgramaDTO programaDTO) throws Exception;
    void eliminar (Long idPrograma) throws Exception;
    List<Programa> listar();
}
