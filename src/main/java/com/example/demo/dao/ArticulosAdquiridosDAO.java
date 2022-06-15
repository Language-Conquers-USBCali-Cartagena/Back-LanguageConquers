package com.example.demo.dao;

import com.example.demo.model.ArticulosAdquiridos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticulosAdquiridosDAO extends JpaRepository<ArticulosAdquiridos, Long> {
}
