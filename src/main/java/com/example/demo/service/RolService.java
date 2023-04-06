package com.example.demo.service;

import com.example.demo.model.Rol;
import com.example.demo.model.dto.RolDTO;

import java.util.List;

public interface RolService {

    String registrar(Rol rol) throws Exception;
    String actualizar(Rol rol) throws Exception;
    String eliminar(Long idRol) throws Exception;
    List<Rol>listar() throws Exception;
}
