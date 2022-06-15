package com.example.demo.dao;

import com.example.demo.model.Semestre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SemestreDAO extends JpaRepository<Semestre,Long> {
}
