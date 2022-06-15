package com.example.demo.dao;

import com.example.demo.model.Articulos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticulosDAO extends JpaRepository<Articulos, Long> {
}
