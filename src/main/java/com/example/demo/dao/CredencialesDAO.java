package com.example.demo.dao;

import com.example.demo.model.Credenciales;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredencialesDAO extends JpaRepository<Credenciales,Long> {
}
