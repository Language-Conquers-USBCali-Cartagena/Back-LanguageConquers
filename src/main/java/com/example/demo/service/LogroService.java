package com.example.demo.service;

import com.example.demo.model.Logro;
import com.example.demo.model.dto.LogroDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LogroService {


    List<Logro> listarLogros() throws Exception;

    Page<Logro> pageLogros(Pageable pageable) throws Exception;

    String registrar(Logro logro) throws Exception;

    String eliminar(Long idLogro) throws Exception;

    String actualizar (LogroDTO logroDTO) throws Exception;
    Logro findById(Long idLogro) throws Exception;

    int logrosRegistrados()throws Exception;
}
