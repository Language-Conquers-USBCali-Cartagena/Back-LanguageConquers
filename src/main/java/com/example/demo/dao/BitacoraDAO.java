package com.example.demo.dao;

import com.example.demo.model.Bitacora;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BitacoraDAO extends JpaRepository<Bitacora,Long> {
}
