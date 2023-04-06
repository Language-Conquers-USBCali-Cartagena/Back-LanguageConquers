package com.example.demo.serviceImpl.curso.consultas;

import com.example.demo.dao.CursoDAO;
import com.example.demo.dao.ProfesorDAO;
import com.example.demo.mapper.CursoMapper;
import com.example.demo.model.Curso;
import com.example.demo.model.Profesor;
import com.example.demo.model.dto.CursoDTO;
import com.example.demo.service.CursoService;
import com.example.demo.serviceImpl.curso.testDataBuilder.CursoTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@SpringBootTest
public class ManejadorListarPorIdProfesorCursoTest {

    public static final String DEBE_INGRESAR_EL_ID_DEL_PROFESOR = "Debe ingresar el id del profesor";
    public static final String EL_PROFESOR_CON_ESE_ID_NO_EXISTE = "El profesor con ese id  no existe.";


    @Autowired
    CursoService cursoService;
    @Autowired
    CursoMapper cursoMapper;
    @MockBean
    private CursoDAO cursoDAO;
    @MockBean
    private ProfesorDAO profesorDAO;


    @Test
    @DisplayName("Debería validar la existencia de un curso por profesor")
    void deberiaValidarLaExistenciaDeUnCursoPorIdProfesor()throws Exception{

        CursoDTO cursoDTO = new CursoTestDataBuilder().conIdProfesor(4343858L).build();
        Curso curso = cursoMapper.toEntity(cursoDTO);
        Mockito.when(profesorDAO.existsById(cursoDTO.getIdProfesor())).thenReturn(true);
        List<Curso> servicioCurso =cursoService.findByIdProfesor(cursoDTO.getIdProfesor());
        assertNotNull(servicioCurso);

    }

    @Test
    @DisplayName("Debería fallar por idProfesor null")
    void deberiaFallarPorIdProfesorNull()throws Exception{

        CursoDTO cursoDTO = new CursoTestDataBuilder().conIdProfesor(null).build();
        Curso curso = cursoMapper.toEntity(cursoDTO);
        Exception exception = assertThrows(Exception.class, ()->{
            cursoService.findByIdProfesor(cursoDTO.getIdProfesor());
        });
        assertEquals(exception.getMessage(),DEBE_INGRESAR_EL_ID_DEL_PROFESOR);

    }

    @Test
    @DisplayName("Debería fallar por idProfesor no existe")
    void deberiaFallarPorIdProfesorNoExiste()throws Exception{

        CursoDTO cursoDTO = new CursoTestDataBuilder().conIdProfesor(852L).build();
        Curso curso = cursoMapper.toEntity(cursoDTO);
        Mockito.when(profesorDAO.existsById(cursoDTO.getIdProfesor())).thenReturn(false);
        Exception exception = assertThrows(Exception.class, ()->{
            cursoService.findByIdProfesor(cursoDTO.getIdProfesor());
        });
        assertEquals(exception.getMessage(),EL_PROFESOR_CON_ESE_ID_NO_EXISTE);

    }


}
