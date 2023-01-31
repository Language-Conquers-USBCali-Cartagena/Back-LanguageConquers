package com.example.demo.dao;


import com.example.demo.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RolDAO extends JpaRepository<Rol,Long> {
    @Query(value = "select * from rol where id_reto = ?1", nativeQuery = true)
    List<Rol> findByIdReto(Long idReto) throws Exception;
}
