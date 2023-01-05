package com.example.demo.service;

import com.example.demo.model.Bitacora;
import com.example.demo.model.dto.BitacoraDTO;

import java.util.List;

public interface BitacoraService {

    String registrar(Bitacora bitacora) throws Exception;
    String actualizar(BitacoraDTO bitacoraDTO) throws Exception;
    String eliminar (Long idBitacora) throws Exception;
    List<Bitacora> listar()throws Exception;
}
