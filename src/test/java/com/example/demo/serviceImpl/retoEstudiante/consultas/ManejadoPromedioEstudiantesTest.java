package com.example.demo.serviceImpl.retoEstudiante.consultas;

import com.example.demo.dao.RetoEstudianteDAO;
import com.example.demo.mapper.RetoEstudianteMapper;
import com.example.demo.service.RetoEstudianteService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.Assert.assertEquals;
@SpringBootTest
public class ManejadoPromedioEstudiantesTest {
    @Autowired
    RetoEstudianteMapper retoEstudianteMapper;
    @Autowired
    RetoEstudianteService retoEstudianteService;
    @MockBean
    RetoEstudianteDAO retoEstudianteDAO;

    @Test
    @DisplayName("Debe obtener el promedio de retos completados")
    void debeObtenerElPromedioDeRetosCompletados() throws Exception{
        int promedioRetos = 15;
        Mockito.when(retoEstudianteDAO.promedioRetosCompletadosEstudiantes()).thenReturn(promedioRetos);
        int resp = retoEstudianteService.promedioRetosCompletadosEstudiantes();
        assertEquals(resp, promedioRetos);
    }
}
