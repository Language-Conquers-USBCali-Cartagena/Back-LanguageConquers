package com.example.demo.service;

import com.example.demo.model.Reto;

import java.util.List;

public interface RetoService {

    List<Reto> listReto() throws Exception;

    String crearreto(Reto reto) throws Exception;
}
