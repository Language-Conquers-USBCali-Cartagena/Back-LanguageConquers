package com.example.demo.dao;

import com.example.demo.model.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrupoDAO extends JpaRepository<Grupo,Long> {
}
