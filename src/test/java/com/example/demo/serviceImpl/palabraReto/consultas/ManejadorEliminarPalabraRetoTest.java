package com.example.demo.serviceImpl.palabraReto.consultas;

import com.example.demo.dao.PalabraRetoDAO;
import com.example.demo.dao.PalabrasReservadasDAO;
import com.example.demo.dao.RetoDAO;
import com.example.demo.mapper.PalabraRetoMapper;
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
public class ManejadorEliminarPalabraRetoTest {
    private static final String TEXTO_ELIMINACION_RETO_PALABRA = "Se elimino el reto palabra exitosamente";
    private static final String MENSAJE_ID_RETO_PALABRA_NO_EXISTE = "El id de reto palabra no existe";

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
    @DisplayName("Deberia eliminar la palabra reto")
    void testDeberiaEliminarPalabraReto() throws Exception{
        PalabraRetoDTO palabraRetoDTO = new PalabraRetoTestDataBuilder().conIdPalabraReto(65165161L).build();
        Mockito.when(palabraRetoDAO.existsById(palabraRetoDTO.getIdPalabraReto())).thenReturn(true);
        String resp = palabraRetoService.eliminarPalabraReto(palabraRetoDTO.getIdPalabraReto());
        assertEquals(resp, TEXTO_ELIMINACION_RETO_PALABRA);
    }
    @Test
    @DisplayName("Deberia fallar por id palabra reto no existe")
    void testDeberiaFallarPorIdPalabraRetoNoExiste() throws Exception{
        PalabraRetoDTO palabraRetoDTO = new PalabraRetoTestDataBuilder().conIdPalabraReto(65165161L).build();
        Mockito.when(palabraRetoDAO.existsById(palabraRetoDTO.getIdPalabraReto())).thenReturn(false);
        Exception exception = assertThrows(Exception.class, () ->{
            palabraRetoService.eliminarPalabraReto(palabraRetoDTO.getIdPalabraReto());
        });
        assertEquals(exception.getMessage(), MENSAJE_ID_RETO_PALABRA_NO_EXISTE);
    }
}
