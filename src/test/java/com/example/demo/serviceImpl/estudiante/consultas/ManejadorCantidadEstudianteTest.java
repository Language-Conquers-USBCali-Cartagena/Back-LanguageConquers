package com.example.demo.serviceImpl.estudiante.consultas;

import com.example.demo.dao.EstudianteDAO;
import com.example.demo.mapper.EstudianteMapper;
import com.example.demo.service.EstudianteService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class ManejadorCantidadEstudianteTest {
    @Autowired
    EstudianteService estudianteService;
    @Autowired
    EstudianteMapper estudianteMapper;
    @MockBean
    private EstudianteDAO estudianteDAO;

    @Test
    @DisplayName("Deberia retornar la cantidad de estudiantes registrados")
    void deberiaRetornarCantidadEstudiantesRegistrados()throws Exception{
        Mockito.when(estudianteDAO.totalEstudiante()).thenReturn(5);
        int result = estudianteService.cantidadEstudiantes();
        assertEquals(5, result);
    }
}
