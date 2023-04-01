package com.example.demo.service;

import com.example.demo.model.ArticulosAdquiridos;
import com.example.demo.model.dto.ArticulosAdquiridosDTO;

import java.util.List;

public interface ArticulosAdquiridosService {

    String registrar (ArticulosAdquiridos articulosAdquiridos)throws Exception;
    String actualizar (ArticulosAdquiridos articulosAdquiridos) throws Exception;
    String eliminar (Long idArticuloA) throws Exception;
    String eliminarPorIds(Long idEstudiante, Long idArticulo) throws Exception;
    ArticulosAdquiridos findById(Long idArticuloA) throws Exception;
    List<ArticulosAdquiridos> findAll()throws Exception;
    Integer comprar(Long idEstudiante, Long idArticulo) throws Exception;
}
