package com.example.demo.service;

import com.example.demo.model.Rol;
import com.example.demo.model.dto.RolDTO;

import java.util.List;

public interface RolService {

    Rol registrar(Rol rol) throws Exception;
    Rol actualizar(RolDTO rolDTO) throws Exception;
    void eliminar(Long idRol) throws Exception;
    List<Rol>listar() throws Exception;
}
