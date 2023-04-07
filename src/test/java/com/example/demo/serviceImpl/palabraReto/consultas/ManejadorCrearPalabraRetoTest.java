package com.example.demo.serviceImpl.palabraReto.consultas;

import com.example.demo.dao.PalabraRetoDAO;
import com.example.demo.dao.PalabrasReservadasDAO;
import com.example.demo.dao.RetoDAO;
import com.example.demo.mapper.PalabraRetoMapper;
import com.example.demo.model.PalabraReto;
import com.example.demo.model.dto.PalabraRetoDTO;
import com.example.demo.service.PalabraRetoService;
import com.example.demo.serviceImpl.palabraReto.testDataBuilder.PalabraRetoTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ManejadorCrearPalabraRetoTest {
    private static final String RETO_PALABRA_CREADO_EXITOSAMENTE = "Se creo el reto palabra exitosamente";
    private static final String ID_RETO_NO_EXISTE = "El id del reto no existe";
    private static final String PALABRA_EN_MAYUSCULAS = "El id de la palabra reservada no existe";



    @Autowired
    PalabraRetoService palabraRetoService;
    @Autowired
    PalabraRetoMapper palabraRetoMapper;
    @MockBean
    PalabraRetoDAO palabraRetoDAO;
    @MockBean
    PalabrasReservadasDAO palabrasReservadasDAO;
    @MockBean
    RetoDAO retoDAO;

    @Test
    @DisplayName("Debe crear un palabra reto")
    void testDebeCrearPalabraReto() throws Exception{
        PalabraRetoDTO palabraRetoDTO = new PalabraRetoTestDataBuilder().build();
        PalabraReto palabraReto = palabraRetoMapper.toEntity(palabraRetoDTO);
        Mockito.when(retoDAO.existsById(palabraRetoDTO.getIdReto())).thenReturn(true);
        Mockito.when(palabrasReservadasDAO.existsById(palabraRetoDTO.getIdPalabrasReservadas())).thenReturn(true);
        Mockito.when(palabraRetoDAO.save(Mockito.any())).thenReturn(palabraReto);
        String resp = palabraRetoService.crearPalabraReto(palabraReto);
        assertEquals(resp, RETO_PALABRA_CREADO_EXITOSAMENTE);
    }
    @Test
    @DisplayName("Debe fallar por id reto no existe")
    void testDebeFallarPorIdRetoNoExiste() throws Exception{
        PalabraRetoDTO palabraRetoDTO = new PalabraRetoTestDataBuilder().build();
        PalabraReto palabraReto = palabraRetoMapper.toEntity(palabraRetoDTO);
        Mockito.when(retoDAO.existsById(palabraRetoDTO.getIdReto())).thenReturn(false);
        Mockito.when(palabrasReservadasDAO.existsById(palabraRetoDTO.getIdPalabrasReservadas())).thenReturn(true);
        Mockito.when(palabraRetoDAO.save(Mockito.any())).thenReturn(palabraReto);
        Exception exception = assertThrows(Exception.class, () -> {
            palabraRetoService.crearPalabraReto(palabraReto);
        });
        assertEquals(exception.getMessage(), ID_RETO_NO_EXISTE);
    }
    @Test
    @DisplayName("Debe fallar por id palabraReservada no existe")
    void testDebeFallarPorIdPalabraReservadaNoExiste() throws Exception{
        PalabraRetoDTO palabraRetoDTO = new PalabraRetoTestDataBuilder().build();
        PalabraReto palabraReto = palabraRetoMapper.toEntity(palabraRetoDTO);
        Mockito.when(retoDAO.existsById(palabraRetoDTO.getIdReto())).thenReturn(true);
        Mockito.when(palabrasReservadasDAO.existsById(palabraRetoDTO.getIdPalabrasReservadas())).thenReturn(false);
        Mockito.when(palabraRetoDAO.save(Mockito.any())).thenReturn(palabraReto);
        Exception exception = assertThrows(Exception.class, () -> {
            palabraRetoService.crearPalabraReto(palabraReto);
        });
        assertEquals(exception.getMessage(), PALABRA_EN_MAYUSCULAS);
    }
}
