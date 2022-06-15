package com.example.demo.dao;

import com.example.demo.model.Mision;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MisionDAO extends JpaRepository<Mision, Long> {
}
