package com.example.demo.serviceImpl.curso.consultas;

import com.example.demo.dao.CursoDAO;
import com.example.demo.mapper.CursoMapper;
import com.example.demo.model.Curso;
import com.example.demo.model.Genero;
import com.example.demo.model.dto.CursoDTO;
import com.example.demo.model.dto.GeneroDTO;
import com.example.demo.service.CursoService;
import com.example.demo.serviceImpl.curso.testDataBuilder.CursoTestDataBuilder;
import com.example.demo.serviceImpl.genero.testDataBuilder.GeneroTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@SpringBootTest
public class ManejadorListarPorIdCursoTest {

    public static final String DEBE_INGRESAR_EL_ID_DEL_CURSO = "Debe ingresar el id de un curso.";
    public static final String EL_CURSO_CON_ESE_ID_NO_EXISTE = "El curso con ese id no existe.";
    @Autowired
    CursoService cursoService;
    @Autowired
    CursoMapper cursoMapper;
    @MockBean
    private CursoDAO cursoDAO;

    @Test
    @DisplayName("Debería validar la existencia de un curso")
    void deberiaValidarLaExistenciaDeUnCurso()throws Exception{

        CursoDTO cursoDTO = new CursoTestDataBuilder().conIdCurso(4343858L).build();

        Curso curso = cursoMapper.toEntity(cursoDTO);
        Mockito.when(cursoDAO.existsById(curso.getIdCurso())).thenReturn(true);
        Mockito.when(cursoDAO.findById(cursoDTO.getIdCurso())).thenReturn(Optional.of(curso));
       Curso servicioCurso = cursoService.findById(curso.getIdCurso());
        assertNotNull(servicioCurso);
        assertEquals(curso.getIdCurso(), servicioCurso.getIdCurso());

    }

    @Test
    @DisplayName("Debería lanzar una excepción si el curso es nulo")
    void deberiaFallarSiIdCursoEsNull()throws Exception{

        CursoDTO cursoDTO = new CursoTestDataBuilder().conIdCurso(null).build();
        Exception exception = assertThrows(Exception.class, () ->{
            cursoService.findById(cursoDTO.getIdCurso());
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_EL_ID_DEL_CURSO);

    }

    @Test
    @DisplayName("Deberia lanzar una excepcion si el curso no existe")
    void deberiaFallarSiIdCursoNoExiste()throws Exception{

        CursoDTO cursoDTO = new CursoTestDataBuilder().conIdCurso(43458L).build();
        Exception exception = assertThrows(Exception.class, () ->{
            cursoService.findById(cursoDTO.getIdCurso());
        });
        assertEquals(exception.getMessage(),EL_CURSO_CON_ESE_ID_NO_EXISTE);

    }
}
