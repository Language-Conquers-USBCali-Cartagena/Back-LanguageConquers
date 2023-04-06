package com.example.demo.serviceImpl.cursoEstudiante.consultas;

import com.example.demo.dao.CursoDAO;
import com.example.demo.dao.CursoEstudianteDAO;
import com.example.demo.dao.EstudianteDAO;
import com.example.demo.mapper.CursoEstudianteMapper;
import com.example.demo.model.CursoEstudiante;
import com.example.demo.model.dto.CursoEstudianteDTO;
import com.example.demo.service.CursoEstudianteService;
import com.example.demo.serviceImpl.cursoEstudiante.testDataBuilder.CursoEstudianteTestDataBuilder;
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
public class ManejadorListarCursoEstudianteTest {

    @Autowired
    CursoEstudianteService cursoEstudianteService;

    @Autowired
    CursoEstudianteMapper cursoEstudianteMapper;

    @MockBean
    private CursoEstudianteDAO cursoEstudianteDAO;
    @MockBean
    private EstudianteDAO estudianteDAO;

    @MockBean
    private CursoDAO cursoDAO;

    @Test
    @DisplayName("Deberia listar los cursoEstudiantes existentes")
    void deberiaListarCursosEstudiantesExitosamente()throws Exception{
        List<CursoEstudiante> cursoEstudiantes = new ArrayList<>();
        CursoEstudianteDTO cursoEstudianteDTO = new CursoEstudianteTestDataBuilder()
                .conIdCursoEstudiante(23l)
                .conIdCurso(45L)
                .conIdEstudiante(3L)
                .conNivel(1)
                .conPuntaje(300)
                .conUsuarioCreador("Angela")
                .conFechaCreacion(new Date())
                .build();
        CursoEstudiante cursoEstudiante1 = cursoEstudianteMapper.toEntity(cursoEstudianteDTO);
        cursoEstudiantes.add(cursoEstudiante1);
        CursoEstudianteDTO cursoEstudianteDTO2 = new CursoEstudianteTestDataBuilder()
                .conIdCursoEstudiante(26l)
                .conIdCurso(5L)
                .conIdEstudiante(7L)
                .conNivel(1)
                .conPuntaje(600)
                .conUsuarioCreador("Angela")
                .conFechaCreacion(new Date())
                .build();
        CursoEstudiante cursoEstudiante2 = cursoEstudianteMapper.toEntity(cursoEstudianteDTO2);
        cursoEstudiantes.add(cursoEstudiante2);
        Mockito.when(cursoEstudianteDAO.findAll()).thenReturn(cursoEstudiantes);
        List<CursoEstudiante> cursoEstudianteList = cursoEstudianteService.listarCursoEstudiante();
        assertEquals(cursoEstudiantes, cursoEstudianteList);
    }

    @Test
    @DisplayName("Deberia devolver una lista vacia cuando no hay cursos estudiantes")
    void deberiaDevolverListaVaciaCursoEstudiate()throws Exception{
        Mockito.when(cursoEstudianteDAO.findAll()).thenReturn(new ArrayList<>());
        List<CursoEstudiante>cursoEstudiantes = cursoEstudianteService.listarCursoEstudiante();
        assertTrue(cursoEstudiantes.isEmpty());
    }
}
