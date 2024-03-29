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

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ManejadorBuscarTodosPalabraRetoTest {

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
    @DisplayName("Se debe listar los palabrasReto")
    void testDebeListarPalabrasReto() throws Exception{
        List<PalabraRetoDTO> palabraRetoDTOs = new ArrayList<>();
        palabraRetoDTOs.add(new PalabraRetoTestDataBuilder().build());
        palabraRetoDTOs.add(new PalabraRetoTestDataBuilder().conIdPalabraReto(15L).build());
        List<PalabraReto> palabraRetos = palabraRetoMapper.toEntityList(palabraRetoDTOs);
        Mockito.when(palabraRetoDAO.findAll()).thenReturn(palabraRetos);
        List<PalabraReto> resp = palabraRetoService.findAll();
        assertEquals(resp, palabraRetos);
    }
}
