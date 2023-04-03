package com.example.demo.service;

import com.example.demo.model.Curso;
import com.example.demo.model.dto.CursoDTO;

import java.util.List;

public interface CursoService {

    String registrar(Curso curso) throws Exception;
    String actualizar (CursoDTO cursoDTO) throws Exception;
    String eliminar (Long idCurso) throws Exception;
    List<Curso> findByCorreoEstudiante (String correoEstudiante) throws Exception;
    List<Curso> findAll() throws Exception;
    Curso findById(Long idCurso) throws Exception;
    List<Curso> findByIdProfesor(Long idProfesor) throws Exception;
    Integer progresoCursoPorEstudiante(Long idCurso, Long idEstudiante) throws Exception;



}
