package com.example.demo.service;

import com.example.demo.model.Bitacora;
import com.example.demo.model.dto.BitacoraDTO;

import java.util.List;

public interface BitacoraService {

    Bitacora registrar(Bitacora bitacora) throws Exception;
    Bitacora actualizar(BitacoraDTO bitacoraDTO) throws Exception;
    void eliminar (Long idBitacora) throws Exception;
    List<Bitacora> listar();
}
