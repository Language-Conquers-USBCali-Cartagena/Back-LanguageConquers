package com.example.demo.dao;

import com.example.demo.model.CursoEstudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoEstudianteDAO extends JpaRepository<CursoEstudiante,Long> {
}
