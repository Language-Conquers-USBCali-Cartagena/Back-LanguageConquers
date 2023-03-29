package com.example.demo.dao;

import com.example.demo.model.Programa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramaDAO extends JpaRepository<Programa,Long> {

}
