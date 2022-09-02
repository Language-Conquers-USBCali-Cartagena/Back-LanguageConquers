package com.example.demo.dao;

import com.example.demo.model.OrdenRetoIDE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrdenRetoIDEDAO extends JpaRepository<OrdenRetoIDE,Long> {
    @Query(value="SELECT * FROM public.orden_reto_ide\n" +
            "WHERE id_reto = ?1", nativeQuery = true)
    List<OrdenRetoIDE> encontrarPorIdReto(Long idReto) throws Exception;


}
