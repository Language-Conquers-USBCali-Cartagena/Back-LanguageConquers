package com.example.demo.service;

import com.example.demo.model.Mision;
import com.example.demo.model.dto.MisionDTO;

import java.util.List;

public interface MisionService {


    List<Mision> ListarMisiones() throws Exception;

    String registrar(Mision mision) throws Exception;
    String actualizar(MisionDTO misionDTO) throws Exception;
    String eliminar (Long idMision) throws Exception;
    Mision findById(Long idMision) throws Exception;

}
