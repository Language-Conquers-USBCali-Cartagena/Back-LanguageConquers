package com.example.demo.service;

import com.example.demo.model.Logro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LogroService {


    List<Logro> listarLogros() throws Exception;

    Page<Logro> pageLogros(Pageable pageable) throws Exception;

    String save(Logro logro) throws Exception;

    String delete(Long idLogro) throws Exception;

    String update (Logro logro) throws Exception;
}
