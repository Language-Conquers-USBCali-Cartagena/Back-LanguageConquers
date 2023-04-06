package com.example.demo.serviceImpl.bitacora.consultas;

import com.example.demo.dao.BitacoraDAO;
import com.example.demo.mapper.BitacoraMapper;
import com.example.demo.model.Bitacora;
import com.example.demo.model.Genero;
import com.example.demo.model.dto.BitacoraDTO;
import com.example.demo.model.dto.GeneroDTO;
import com.example.demo.service.BitacoraService;
import com.example.demo.serviceImpl.bitacora.testDataBuilder.BitacoraTestDataBuilder;
import com.example.demo.serviceImpl.genero.testDataBuilder.GeneroTestDataBuilder;
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
public class ManejadorListarBitacoraTest {

    @Autowired
    BitacoraService bitacoraService;

    @MockBean
    private BitacoraDAO bitacoraDAO;


    @Autowired
    BitacoraMapper bitacoraMapper;

    @Test
    @DisplayName("Deberia listar los registros de la bitacora existentes")
    void deberiaListarRegistrosBitacorasExitosamente() throws Exception {
        List<Bitacora> bitacoras = new ArrayList<>();
        BitacoraDTO bitacoraDTO1 = new BitacoraTestDataBuilder()
                .conIdBitacora(1L)
                .conIdUsuario(23l)
                .conFechaIngreso(new Date())
                .conFechaSalida(new Date())
                .conUsuarioCreador("Angela")
                .conFechaCreacion(new Date())
                .build();
        Bitacora bitacora1 = bitacoraMapper.toEntity(bitacoraDTO1);
        bitacoras.add(bitacora1);
        BitacoraDTO bitacoraDTO2 = new BitacoraTestDataBuilder()
                .conIdBitacora(3L)
                .conIdUsuario(223l)
                .conFechaIngreso(new Date())
                .conFechaSalida(new Date())
                .conUsuarioCreador("Angela")
                .conFechaCreacion(new Date())
                .build();
        Bitacora bitacora2 = bitacoraMapper.toEntity(bitacoraDTO2);
        bitacoras.add(bitacora2);
        Mockito.when(bitacoraDAO.findAll()).thenReturn(bitacoras);
        List<Bitacora> bitacoraList = bitacoraService.listar();
        assertEquals(bitacoras, bitacoraList);
    }

    @Test
    @DisplayName("Deberia devolver lista vacia cuando no hay registros de bitacora")
    void deberiaDevolverListaVaciaCuandoNoHayRegistros()throws Exception{
        Mockito.when(bitacoraDAO.findAll()).thenReturn(new ArrayList<>());
        List<Bitacora> bitacoras = bitacoraService.listar();
        assertTrue(bitacoras.isEmpty());
    }

}
