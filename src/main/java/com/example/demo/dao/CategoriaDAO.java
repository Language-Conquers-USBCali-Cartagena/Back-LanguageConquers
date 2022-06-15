package com.example.demo.dao;

import com.example.demo.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaDAO extends JpaRepository<Categoria,Long> {
}
