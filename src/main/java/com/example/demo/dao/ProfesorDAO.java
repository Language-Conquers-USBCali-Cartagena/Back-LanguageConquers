package com.example.demo.dao;

import com.example.demo.model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesorDAO extends JpaRepository<Profesor,Long> {
}
