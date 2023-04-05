package com.example.demo.dao;

import com.example.demo.model.Estudiante;
import com.example.demo.model.Logro;
import com.example.demo.model.LogroEstudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LogroEstudianteDAO extends JpaRepository<LogroEstudiante, Long> {

    List<LogroEstudiante> findByEstudiante (Estudiante estudiante);

    @Query(value = "SELECT * FROM logro_estudiante WHERE id_estudiante = ?1 AND id_logro =?2", nativeQuery = true)
    LogroEstudiante findByEstudianteAndLogro (Long idEstudiante, Long idLogro);
}
