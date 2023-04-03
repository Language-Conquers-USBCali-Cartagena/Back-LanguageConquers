package com.example.demo.service.serviceImpl;

import com.example.demo.dao.AdministradoDAO;
import com.example.demo.model.Administrador;
import com.example.demo.service.AdministradorService;
import com.example.demo.util.Validaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Scope("singleton")
@Service
public class AdministradorServiceImpl implements AdministradorService {
    @Autowired
    AdministradoDAO administradoDAO;
    @Override
    public Administrador getAdministradorPorCorreo(String correo) throws Exception {
        if(!Validaciones.formatoCorreoValido(correo)){
            throw new Exception("El formato del correo no es válido.");
        }
        Administrador administrador = administradoDAO.findByCorreo(correo);
        if(administrador.getNombre().equals(null)){
            throw new Exception("No existe administrador registrado con este correo");
        }
        return  administrador;
    }
}
