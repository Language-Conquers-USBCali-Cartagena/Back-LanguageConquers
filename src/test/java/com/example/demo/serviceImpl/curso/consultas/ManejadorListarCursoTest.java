package com.example.demo.serviceImpl.curso.consultas;

import com.example.demo.dao.CursoDAO;
import com.example.demo.mapper.CursoMapper;
import com.example.demo.model.Curso;
import com.example.demo.model.dto.CursoDTO;
import com.example.demo.service.CursoService;
import com.example.demo.serviceImpl.curso.testDataBuilder.CursoTestDataBuilder;
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
public class ManejadorListarCursoTest {

    @Autowired
    CursoService cursoService;
    @Autowired
    CursoMapper cursoMapper;
    @MockBean
    private CursoDAO cursoDAO;

    @Test
    @DisplayName("Deberia listar los cursos existentes")
    void deberiaListarCursosExistentes()throws Exception{
        List<Curso>cursoList = new ArrayList<>();
        CursoDTO cursoDTO = new CursoTestDataBuilder()
                .conNombre("Bases")
                .conIdProfesor(23l)
                .conCantidadEstudiantes(20)
                .conInicioCurso(new Date())
                .conPassword("1234")
                .conProgreso(0)
                .conFinCurso(new Date(2023,9,12))
                .conUsuarioCreador("Angela")
                .conFechaCreacion(new Date())
                .build();
        Curso curso1 = cursoMapper.toEntity(cursoDTO);
        cursoList.add(curso1);
        CursoDTO cursoDTO2 = new CursoTestDataBuilder()
                .conNombre("Bases de datos")
                .conIdProfesor(3l)
                .conCantidadEstudiantes(20)
                .conInicioCurso(new Date())
                .conPassword("1234")
                .conProgreso(0)
                .conFinCurso(new Date(2023,9,12))
                .conUsuarioCreador("Angela")
                .conFechaCreacion(new Date())
                .build();
        Curso curso2 = cursoMapper.toEntity(cursoDTO2);
        cursoList.add(curso2);
        Mockito.when(cursoDAO.findAll()).thenReturn(cursoList);
        List<Curso> cursos = cursoService.findAll();
        assertEquals(cursoList,cursos);
    }

    @Test
    @DisplayName("Deberia devolver una lista vacia")
    void deberiaDevolverListaVaciaCuandoNoHayCursos() throws Exception {
        Mockito.when(cursoDAO.findAll()).thenReturn(new ArrayList<>());
        List<Curso> cursos = cursoService.findAll();
        assertTrue(cursos.isEmpty());
    }
}

