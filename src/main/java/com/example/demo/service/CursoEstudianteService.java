package com.example.demo.service;

import com.example.demo.model.CursoEstudiante;

import java.util.List;

public interface CursoEstudianteService {

    String crearCursoEstudiante(CursoEstudiante cursoEstudiante) throws Exception;
    List<CursoEstudiante> listarCursoEstudiante() throws Exception;
}
