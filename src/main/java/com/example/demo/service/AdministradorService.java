package com.example.demo.service;

import com.example.demo.model.Administrador;

public interface AdministradorService {

    Administrador getAdministradorPorCorreo(String correo) throws Exception;
}
