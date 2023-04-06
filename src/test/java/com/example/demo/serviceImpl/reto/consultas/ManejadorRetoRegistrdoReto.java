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
public class ManejadorRetoRegistrdoReto {
    private static final int RETOS_REGISTRADOS = 15;
    @Autowired
    RetoService retoService;
    @Autowired
    RetoMapper retoMapper;
    @MockBean
    RetoDAO retoDAO;

    @Test
    @DisplayName("Deberia entregar el numero de registrados")
    void deberiaEntregarNumeroRegistrados() throws Exception{
        Mockito.when(retoDAO.retosRegistrados()).thenReturn(RETOS_REGISTRADOS);
        int retosRegistrdos = retoService.retosRegistrados();
        assertEquals(retosRegistrdos, RETOS_REGISTRADOS);
    }
}
