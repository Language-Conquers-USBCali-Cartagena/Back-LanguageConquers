package com.example.demo.service;

import com.example.demo.model.Estudiante;
import com.example.demo.model.dto.EstudianteDTO;

import java.util.List;

public interface EstudianteService {
    String crearEstudiante(Estudiante estudiante) throws Exception;
    String actualizar(EstudianteDTO estudianteDTO) throws Exception;
    String eliminar(Long idEstudiante) throws Exception;
    List<Estudiante> listar()throws Exception;
    Boolean existePorCorreo (String correo) throws Exception;
    Estudiante findByCorreo (String correo) throws Exception;
    Estudiante findById(Long idEstudiante) throws Exception;
    List<Estudiante> rankingEstudiante() throws Exception;

    int cantidadEstudiantes () throws Exception;
}
