package com.example.demo.service;

import com.example.demo.model.Credenciales;
import com.example.demo.model.dto.CredencialesDTO;

import java.util.List;

public interface CredencialesService {

    String crear(Credenciales credenciales) throws Exception;
    String actualizar(CredencialesDTO credencialesDTO) throws Exception;
    String eliminar(Long idCredencial) throws Exception;
    List<Credenciales> listar()throws Exception;
}
