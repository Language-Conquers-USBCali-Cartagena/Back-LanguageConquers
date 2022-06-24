package com.example.demo.dao;


import com.example.demo.model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfesorDAO extends JpaRepository<Profesor,Long> {

    @Query(value = "select * from profesor where id_genero = ?1", nativeQuery = true)
    List<Profesor> findByIdGenero(Long idGenero) throws Exception;

}
