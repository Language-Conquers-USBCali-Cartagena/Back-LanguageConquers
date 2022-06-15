package com.example.demo.dao;

import com.example.demo.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoDAO extends JpaRepository<Estado, Long> {
}
