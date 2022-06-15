package com.example.demo.dao;

import com.example.demo.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoDAO extends JpaRepository<Curso,Long> {
}
