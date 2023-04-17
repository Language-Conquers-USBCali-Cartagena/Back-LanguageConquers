package com.example.demo.service;

import com.example.demo.model.CursoEstudiante;

import java.util.List;

public interface CursoEstudianteService {

    String crearCursoEstudiante(CursoEstudiante cursoEstudiante) throws Exception;
    List<CursoEstudiante> listarCursoEstudiante() throws Exception;

    CursoEstudiante findByIdEstudianteAndIdCurso(Long idCurso, Long idEstudiante) throws Exception;

    int estudiantesMatriculados (Long idCurso) throws Exception;
}
