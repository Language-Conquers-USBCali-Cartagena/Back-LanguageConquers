package com.example.demo.service;

import com.example.demo.model.Estado;
import com.example.demo.model.dto.EstadoDTO;

import java.util.List;

public interface EstadoService {

    Estado registrar (Estado estado) throws Exception;
    Estado actualizar(EstadoDTO estadoDTO) throws Exception;
    void eliminar(Long idEstado) throws Exception;
    List<Estado>listar();
}
