package com.example.demo.dao;

import com.example.demo.model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneroDAO extends JpaRepository<Genero,Long> {
}
