package com.example.demo.serviceImpl.estudiante.consultas;

import com.example.demo.dao.EstudianteDAO;
import com.example.demo.mapper.EstudianteMapper;
import com.example.demo.model.Estudiante;
import com.example.demo.model.dto.EstudianteDTO;
import com.example.demo.service.EstudianteService;
import com.example.demo.serviceImpl.estudiante.testDataBuilder.EstudianteTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ManejadorRankingEstudianteTest {

    private static final String NO_HAY_ESTUDIANTES = "No hay estudiantes.";
    @Autowired
    EstudianteService estudianteService;
    @Autowired
    EstudianteMapper estudianteMapper;
    @MockBean
    private EstudianteDAO estudianteDAO;

    @Test
    @DisplayName("Deberia listar estudiantes del ranking")
    void deberiaListarRanking()throws Exception{

        List<Estudiante> estudiantes = new ArrayList<>();
        EstudianteDTO estudianteDTO1 = new EstudianteTestDataBuilder()
                .conNombre("Pepito")
                .conApellido("Perez")
                .conNickName("pepitoP33")
                .conCorreo("pepito@gmail.com")
                .conPuntaje(500)
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
        estudiantes.add(estudiante1);
        EstudianteDTO estudianteDTO2 = new EstudianteTestDataBuilder()
                .conNombre("Camila")
                .conApellido("Lopez")
                .conNickName("cmaiP33")
                .conCorreo("camila@gmail.com")
                .conPuntaje(400)
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
        estudiantes.add(estudiante2);
        EstudianteDTO estudianteDTO3 = new EstudianteTestDataBuilder()
                .conNombre("Camila")
                .conApellido("Lopez")
                .conNickName("cmaiP33")
                .conCorreo("camila@gmail.com")
                .conPuntaje(400)
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
        Estudiante estudiante3 = estudianteMapper.toEntity(estudianteDTO3);
        estudiantes.add(estudiante3);
        Mockito.when(estudianteDAO.rankingEstudiantes()).thenReturn(estudiantes);
        List<Estudiante> estudiantesList = estudianteService.rankingEstudiante();
        assertEquals(estudiantes, estudiantesList);
    }

    @Test
    @DisplayName("Deberia lanzar excepción por lista vacia ")
    void deberiaLanzarExcepcionPorListaVacia() throws Exception {
        List<Estudiante> estudiantes = new ArrayList<>();
        Mockito.when(estudianteDAO.rankingEstudiantes()).thenReturn(estudiantes);
        Exception exception = assertThrows(Exception.class,()->{
            estudianteService.rankingEstudiante();
        });
        assertEquals(exception.getMessage(), NO_HAY_ESTUDIANTES);

    }
}
