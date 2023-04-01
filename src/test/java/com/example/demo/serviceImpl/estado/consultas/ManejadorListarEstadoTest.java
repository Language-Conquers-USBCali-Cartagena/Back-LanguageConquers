package com.example.demo.serviceImpl.estado.consultas;

import com.example.demo.dao.EstadoDAO;
import com.example.demo.mapper.EstadoMapper;
import com.example.demo.model.Estado;
import com.example.demo.model.dto.EstadoDTO;
import com.example.demo.service.EstadoService;
import com.example.demo.serviceImpl.estado.testDataBuilder.EstadoTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ManejadorListarEstadoTest {


    @Autowired
    EstadoService estadoService;

    @MockBean
    private EstadoDAO estadoDAO;

    @Autowired
    EstadoMapper estadoMapper;

    @Test
    @DisplayName("Deberia listar los estados existentes")
    void deberiaListarEstadoExitosamente()throws Exception{
        List<Estado> estadosPrueba = new ArrayList<>();
        EstadoDTO estadoDTO1 = new EstadoTestDataBuilder()
                .conIdEstado(1L)
                .conEstado("Pausado")
                .conUsuarioCreador("Angela")
                .conFechaCreacion(new Date()).build();
        Estado estado1 = estadoMapper.toEntity(estadoDTO1);
        estadosPrueba.add(estado1);
        EstadoDTO estadoDTO2 = new EstadoTestDataBuilder()
                .conIdEstado(2L)
                .conEstado("Cancelado")
                .conUsuarioCreador("Angela")
                .conFechaCreacion(new Date()).build();
        Estado estado2 = estadoMapper.toEntity(estadoDTO2);
        estadosPrueba.add(estado2);
        Mockito.when(estadoDAO.findAll()).thenReturn(estadosPrueba);
        List<Estado> estadoList = estadoService.listar();
        assertEquals(estadosPrueba, estadoList);
    }

    @Test
    @DisplayName("Debería devolver una lista vacía cuando no hay estados")
    void deberiaDevolverListaVaciaCuandoNoHayEstados()throws Exception{
        Mockito.when(estadoDAO.findAll()).thenReturn(new ArrayList<>());
        List<Estado> estadoList = estadoService.listar();
        assertTrue(estadoList.isEmpty());
    }
}
