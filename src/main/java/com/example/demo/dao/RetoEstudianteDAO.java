package com.example.demo.dao;

import com.example.demo.model.RetoEstudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RetoEstudianteDAO extends JpaRepository<RetoEstudiante,Long> {
}
