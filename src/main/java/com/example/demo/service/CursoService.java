package com.example.demo.service;

import com.example.demo.model.Curso;
import com.example.demo.model.dto.CursoDTO;

import java.util.List;

public interface CursoService {

    String crearCurso(Curso curso) throws Exception;

    List<Curso> findByCorreoEstudiante (String correoEstudiante) throws Exception;

    List<Curso> findAll() throws Exception;
}
