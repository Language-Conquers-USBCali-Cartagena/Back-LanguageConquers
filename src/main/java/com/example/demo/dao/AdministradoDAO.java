package com.example.demo.dao;

import com.example.demo.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministradoDAO extends JpaRepository<Administrador, Long> {

    Administrador findByCorreo(String correo) throws Exception;
}
