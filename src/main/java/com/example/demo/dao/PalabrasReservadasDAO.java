package com.example.demo.dao;

import com.example.demo.model.PalabrasReservadas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PalabrasReservadasDAO extends JpaRepository<PalabrasReservadas, Long> {
}
