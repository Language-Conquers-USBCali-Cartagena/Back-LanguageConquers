package com.example.demo.dao;

import com.example.demo.model.PalabraReto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PalabraRetoDAO extends JpaRepository<PalabraReto, Long> {

    @Query(value = "SELECT * FROM palabra_reto WHERE id_reto = ?1", nativeQuery = true)
    List<PalabraReto> findByIdReto(Long idReto) throws Exception;

}
