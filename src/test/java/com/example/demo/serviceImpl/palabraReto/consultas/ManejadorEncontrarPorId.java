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
public class ManejadorEncontrarPorId {
    private static final  Long ID_RETO = 564654L;
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
    @DisplayName("Deberia buscar por id")
    void testDeberiaBuscarPorId() throws Exception{

        List<PalabraRetoDTO> palabraRetoDTO = new ArrayList<>();
        palabraRetoDTO.add(new PalabraRetoTestDataBuilder().build());
        palabraRetoDTO.add(new PalabraRetoTestDataBuilder().conIdPalabraReto(6165156L).build());
        List<PalabraReto> palabraRetos = palabraRetoMapper.toEntityList(palabraRetoDTO);
        Mockito.when(palabraRetoDAO.findByIdReto(ID_RETO)).thenReturn(palabraRetos);
        List<PalabraReto> resp = palabraRetoService.findbyIdReto(ID_RETO);
        assertEquals(resp, palabraRetos);
    }
}
