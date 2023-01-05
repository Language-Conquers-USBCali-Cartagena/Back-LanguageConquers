package com.example.demo.service;

import com.example.demo.model.Monedas;
import com.example.demo.model.dto.MonedasDTO;

import java.util.List;

public interface MonedasService {

    List<Monedas> findAlList() throws Exception;

    String registrar(Monedas monedas) throws Exception;
    String actualizar (MonedasDTO monedasDTO)throws Exception;
    String eliminar (Long idMonedas) throws Exception;
    Monedas findById(Long idMonedas)throws Exception;
}
