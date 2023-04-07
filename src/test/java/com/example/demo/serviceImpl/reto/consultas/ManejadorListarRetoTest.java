package com.example.demo.serviceImpl.reto.consultas;

import com.example.demo.dao.RetoDAO;
import com.example.demo.mapper.RetoMapper;
import com.example.demo.model.Reto;
import com.example.demo.model.dto.RetoDTO;
import com.example.demo.service.RetoService;
import com.example.demo.serviceImpl.reto.testDataBuilder.RetoTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class ManejadorListarRetoTest {


    @Autowired
    RetoService retoService;
    @Autowired
    RetoMapper retoMapper;
    @MockBean
    RetoDAO retoDAO;


    @Test
    @DisplayName("Deberia listar los retos")
    void deberiaListarLosRetos() throws Exception{
        List<RetoDTO> retoDTOS = new ArrayList<>();
        retoDTOS.add(new RetoTestDataBuilder().build());
        retoDTOS.add(new RetoTestDataBuilder().conIdReto(15L).build());
        List<Reto> retos = retoMapper.toEntityList(retoDTOS);
        Mockito.when(retoDAO.findAll()).thenReturn(retos);
        List<Reto> encontrarTodos = retoService.listReto();
        assertEquals(retos, encontrarTodos);
    }
}
