package com.example.demo.service;

import com.example.demo.model.LogroEstudiante;
import com.example.demo.model.dto.LogroEstudianteDTO;

import java.util.List;

public interface LogroEstudianteService {

    String save(LogroEstudiante logroEstudiante) throws Exception;

     String saveByDTO(LogroEstudianteDTO logroEstudianteDTO) throws Exception;
    String delete(Long idLogroEstudiante) throws Exception;

    String actualizar(LogroEstudiante logroEstudiante) throws Exception;
    List<LogroEstudiante> findAllByIdEstudiante (Long idEstudiante) throws Exception;
    LogroEstudiante findByEstudianteAndLogro(Long idEstudiante, Long idLogro) throws Exception;
    String logroArticulos(Long idEstudiante) throws Exception;
    String logroAhorrador(Long idEstudiante) throws Exception;
    String logroPerfeccionista(Long idEstudiante, Long idReto) throws Exception;

}
