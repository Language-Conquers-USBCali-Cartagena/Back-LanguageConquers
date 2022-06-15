package com.example.demo.dao;

import com.example.demo.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioDAO extends JpaRepository<Comentario,Long> {
}
