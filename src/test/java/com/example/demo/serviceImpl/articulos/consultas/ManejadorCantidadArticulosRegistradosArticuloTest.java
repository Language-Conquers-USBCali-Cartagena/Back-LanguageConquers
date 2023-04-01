package com.example.demo.serviceImpl.articulos.consultas;

import com.example.demo.dao.ArticulosDAO;
import com.example.demo.mapper.ArticulosMapper;
import com.example.demo.service.ArticulosService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class ManejadorCantidadArticulosRegistradosArticuloTest {
    @Autowired
    ArticulosService articulosService;

    @MockBean
    private ArticulosDAO articulosDAO;

    @Autowired
    ArticulosMapper articulosMapper;

    @Test
    @DisplayName("Deberia retornar la cantidad de articulos registrados")
    void deberiaRetornarCantidadArticulosRegistrados() throws Exception {
        Mockito.when(articulosDAO.articulosRegistrados()).thenReturn(5);
        int result = articulosService.articulosRegistrados();
        assertEquals(5,result);
    }

}
