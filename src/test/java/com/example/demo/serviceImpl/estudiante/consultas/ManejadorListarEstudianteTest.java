package com.example.demo.serviceImpl.estudiante.consultas;

import com.example.demo.dao.EstudianteDAO;
import com.example.demo.mapper.EstudianteMapper;
import com.example.demo.model.Estudiante;
import com.example.demo.model.dto.EstudianteDTO;
import com.example.demo.service.EstudianteService;
import com.example.demo.serviceImpl.estudiante.testDataBuilder.EstudianteTestDataBuilder;
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
public class ManejadorListarEstudianteTest {

    @Autowired
    EstudianteService estudianteService;
    @Autowired
    EstudianteMapper estudianteMapper;
    @MockBean
    private EstudianteDAO estudianteDAO;


    @Test
    @DisplayName("Deberia listar los estudiantes registrados")
    void deberiaListarEstudiantesRegistrados()throws Exception{
        List<Estudiante> estudianteList = new ArrayList<>();
        EstudianteDTO estudianteDTO1 = new EstudianteTestDataBuilder()
                .conNombre("Pepito")
                .conApellido("Perez")
                .conNickName("pepitoP33")
                .conCorreo("pepito@gmail.com")
                .conPuntaje(0)
                .conMonedasObtenidas(0)
                .conIdAvatar(1L)
                .conIdGenero(1L)
                .conIdEstado(1L)
                .conIdPrograma(1L)
                .conFechaNacimiento(new Date(2000,7,31))
                .conIdSemestre(1l)
                .conUsuarioCreador("Angela")
                .conFechaCreacion(new Date())
                .build();
        Estudiante estudiante1 = estudianteMapper.toEntity(estudianteDTO1);
        estudianteList.add(estudiante1);
        EstudianteDTO estudianteDTO2 = new EstudianteTestDataBuilder()
                .conNombre("Camila")
                .conApellido("Lopez")
                .conNickName("cmaiP33")
                .conCorreo("camila@gmail.com")
                .conPuntaje(0)
                .conMonedasObtenidas(0)
                .conIdAvatar(1L)
                .conIdGenero(1L)
                .conIdEstado(1L)
                .conIdPrograma(1L)
                .conFechaNacimiento(new Date(2000,7,31))
                .conIdSemestre(1l)
                .conUsuarioCreador("Angela")
                .conFechaCreacion(new Date())
                .build();
        Estudiante estudiante2 = estudianteMapper.toEntity(estudianteDTO2);
        estudianteList.add(estudiante2);
        Mockito.when(estudianteDAO.findAll()).thenReturn(estudianteList);
        List<Estudiante> estudiantes = estudianteService.listar();
        assertEquals(estudianteList, estudiantes);
    }

    @Test
    @DisplayName("Deberia devolver una lista vacia cuando no hay estudiantes")
    void deberiaDevolverListaVacia()throws Exception{
        Mockito.when(estudianteDAO.findAll()).thenReturn(new ArrayList<>());
        List<Estudiante> estudiantes = estudianteService.listar();
        assertTrue(estudiantes.isEmpty());
    }
}
