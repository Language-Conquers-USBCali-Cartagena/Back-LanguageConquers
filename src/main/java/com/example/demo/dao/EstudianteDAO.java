package com.example.demo.dao;

import com.example.demo.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteDAO extends JpaRepository<Estudiante,Long> {
}
