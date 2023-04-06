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
public class ManejadorPromedioMonedasEstudianteTest {

    @Autowired
    EstudianteService estudianteService;
    @Autowired
    EstudianteMapper estudianteMapper;
    @MockBean
    private EstudianteDAO estudianteDAO;

    @Test
    @DisplayName("Deberia retornar el promedio de monedas de los de estudiantes")
    void deberiaRetornarPromedioMonedasEstudiantes()throws Exception{
        Mockito.when(estudianteDAO.promedioMonedasGanadasEstudiantes()).thenReturn(10);
        int result = estudianteService.promedioMonedasGanadasEstudiantes();
        assertEquals(10, result);
    }
}
