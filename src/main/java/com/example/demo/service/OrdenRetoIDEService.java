package com.example.demo.service;

import com.example.demo.model.OrdenRetoIDE;
import com.example.demo.model.dto.OrdenRetoIDEDTO;

import java.util.List;

public interface OrdenRetoIDEService {
    List<OrdenRetoIDEDTO> VerificarOrden (List<OrdenRetoIDEDTO> intento, Long idReto) throws Exception;

    List<OrdenRetoIDE> findAll() throws Exception;

    String crarOrdenRet(OrdenRetoIDE ordenRetoIDE) throws Exception;

    List<OrdenRetoIDE> findByIdReto(Long idReto) throws Exception;
}
