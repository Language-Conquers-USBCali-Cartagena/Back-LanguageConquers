package com.example.demo.service;

import com.example.demo.model.Profesor;
import com.example.demo.model.dto.ProfesorDTO;

import java.util.List;

public interface ProfesorService {
    String registarProfesor(Profesor profesor) throws Exception;
    String actualizar(ProfesorDTO profesorDTO)throws Exception;
    void eliminar(Long idProfesor)throws Exception;

    List<Profesor>listar() throws Exception;

    Boolean existePorCorreo(String correo) throws Exception;

    Profesor findByCorreo(String correo) throws Exception;
}
