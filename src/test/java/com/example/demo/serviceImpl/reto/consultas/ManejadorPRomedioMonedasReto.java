package com.example.demo.serviceImpl.reto.consultas;

import com.example.demo.dao.RetoDAO;
import com.example.demo.mapper.RetoMapper;
import com.example.demo.service.RetoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class ManejadorPRomedioMonedasReto {
    private static final int MONEDAS = 15000;
    @Autowired
    RetoService retoService;
    @Autowired
    RetoMapper retoMapper;
    @MockBean
    RetoDAO retoDAO;

    @Test
    @DisplayName("deberia devolver el promedio de monedas")
    void deberiaDevolverElPromedioDeMonedas() throws Exception{
        Mockito.when(retoDAO.promedioMonedasRetos()).thenReturn(MONEDAS);
        int promedioMonedasReto = retoService.promedioMonedasRetos();
        assertEquals(promedioMonedasReto, MONEDAS);
    }
}
