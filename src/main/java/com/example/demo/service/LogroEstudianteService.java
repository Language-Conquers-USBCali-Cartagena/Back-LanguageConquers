package com.example.demo.service;

import com.example.demo.model.LogroEstudiante;

import java.util.List;

public interface LogroEstudianteService {

    String save(LogroEstudiante logroEstudiante) throws Exception;

    String delete(Long idLogroEstudiante) throws Exception;

    String actualizar(LogroEstudiante logroEstudiante) throws Exception;
    List<LogroEstudiante> findAllByIdEstudiante (Long idEstudiante) throws Exception;
}
