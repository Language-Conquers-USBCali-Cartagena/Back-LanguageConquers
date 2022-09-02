package com.example.demo.service;

import com.example.demo.model.Monedas;

import java.util.List;

public interface MonedasService {

    List<Monedas> findAlList() throws Exception;

    String crarMonedas(Monedas monedas) throws Exception;
}
