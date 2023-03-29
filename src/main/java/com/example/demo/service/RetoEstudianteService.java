package com.example.demo.service;


import com.example.demo.model.RetoEstudiante;
import com.example.demo.model.dto.RetoEstudianteDTO;

import java.util.List;

public interface RetoEstudianteService {

    String crearRetoEstudiante(RetoEstudiante retoEstudiante) throws Exception;
    String actualizar(RetoEstudianteDTO retoEstudianteDTO) throws Exception;
    String eliminar(Long idRetoEstudiante) throws Exception;
    List<RetoEstudiante> listar()throws Exception;

    RetoEstudiante findById(Long idRetoEstudiante) throws Exception;
    List<RetoEstudiante> listarPorIdEstudiante(Long idEstudiante) throws Exception;
    List<RetoEstudiante> listarPorIdReto(Long idReto)throws Exception;
    int promedioRetosCompletadosEstudiantes()throws Exception;
}
