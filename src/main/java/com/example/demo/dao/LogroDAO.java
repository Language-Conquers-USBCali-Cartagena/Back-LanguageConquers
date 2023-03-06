package com.example.demo.dao;

import com.example.demo.model.Logro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LogroDAO extends JpaRepository<Logro, Long> {

    @Query(value = "SELECT * FROM logro", nativeQuery = true)
    Page<Logro> findAll(Pageable pageable);


}
