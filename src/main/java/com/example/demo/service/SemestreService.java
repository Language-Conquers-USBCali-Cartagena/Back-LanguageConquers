package com.example.demo.service;

import com.example.demo.model.Semestre;
import com.example.demo.model.dto.SemestreDTO;

import java.util.List;

public interface SemestreService {

    String registrar (Semestre semestreDTO) throws Exception;
    String actualizar (Semestre semestre)throws Exception;
    String eliminar(Long idSemestre) throws Exception;

    Semestre findById(Long idSemestre) throws Exception;
    List<Semestre> listar()throws Exception;


}
