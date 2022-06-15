package com.example.demo.dao;

import com.example.demo.model.Monedas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonedasDAO extends JpaRepository<Monedas,Long> {
}
