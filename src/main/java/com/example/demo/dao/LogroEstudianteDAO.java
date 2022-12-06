package com.example.demo.dao;

import com.example.demo.model.Estudiante;
import com.example.demo.model.LogroEstudiante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogroEstudianteDAO extends JpaRepository<LogroEstudiante, Long> {

    List<LogroEstudiante> findByEstudiante (Estudiante estudiante);


}
