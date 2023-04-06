package com.example.demo.serviceImpl.retoEstudiante.consultas;

import com.example.demo.dao.RetoEstudianteDAO;
import com.example.demo.mapper.RetoEstudianteMapper;
import com.example.demo.model.RetoEstudiante;
import com.example.demo.model.dto.RetoEstudianteDTO;
import com.example.demo.service.RetoEstudianteService;
import com.example.demo.serviceImpl.retoEstudiante.testDataBuilder.RetoEstudianteTestDataBuilder;
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
public class ManejadorListarRetoEstudiante {
    @Autowired
    RetoEstudianteMapper retoEstudianteMapper;
    @Autowired
    RetoEstudianteService retoEstudianteService;
    @MockBean
    RetoEstudianteDAO retoEstudianteDAO;

    @Test
    @DisplayName("Deberia listar los retos estudiante")
    void deberiaListarLosRetoEstudiante() throws Exception{
        List<RetoEstudianteDTO> retoEstudianteDTOList = new ArrayList<>();
        retoEstudianteDTOList.add(new RetoEstudianteTestDataBuilder().build());
        retoEstudianteDTOList.add(new RetoEstudianteTestDataBuilder().conIdRetoEstudiante(2L).build());
        List<RetoEstudiante> retoEstudianteList = retoEstudianteMapper.toEntityList(retoEstudianteDTOList);
        Mockito.when(retoEstudianteDAO.findAll()).thenReturn(retoEstudianteList);
        List<RetoEstudiante> listartodos = retoEstudianteService.listar();
        assertEquals(listartodos, retoEstudianteList);
    }
}
