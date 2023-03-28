package com.example.demo.Curso;

import com.example.demo.dao.*;
import com.example.demo.model.Curso;
import com.example.demo.service.CursoService;
import com.example.demo.service.serviceImpl.CursoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EntidadCursoTest {

    @Mock
    private CursoDAO cursoDAO;

    @InjectMocks
    CursoServiceImpl cursoServiceImpl;

    @Autowired
    EstadoDAO estadoDAO;

    @Autowired
    ProfesorDAO profesorDAO;

    @Autowired
    RetoDAO retoDAO;

    @Autowired
    MisionDAO misionDAO;

    private Curso curso;



    @Test
    public void registrarCursoExitoso() throws Exception {
        // Arrange
        Curso curso = new CursoTestDataBuilder()
                .conIdCurso(10L)
                .conNombre("Introduccion a la programación")
                .conFechaCreacion(new Date(2022,8,02))
                .conFechaFinCurso(new Date(2023,2,12))
                .conPassword("1234")
                .conProgreso(0)
                .conCantidadEstudiantes(30)
                .conUsuarioCreador("Angela")
                .conFechaCreacion(new Date(2023,2,23))
                .conIdEstado(1L)
                .conIdProfesor(1L)
                .build();
        Mockito.when(cursoDAO.save(curso)).thenReturn(curso);
        // Act
        String resultado = cursoServiceImpl.registrar(curso);
        // Assert

        assertEquals("Se creo exitosamente el curso.", resultado);
        /*
        Mockito.verify(cursoDAO, Mockito.times(1)).save(curso);
    */
    }
      /*
    @Test
    public void registrar_validacionesCrearFallanPorProgreso() throws Exception {
        // Arrange
        Curso curso = new Curso(1L,
                "Curso Java",
                "1234",
                14,
                new Date(2023,3,2),
                new Date(2023,8,12),
                10,
                1L,
                1L,
                "Angela", new Date());

        // Act
        String resultado = cursoService.registrar(curso);

        // Assert
        assertEquals("Al crear el curso se crea con el progreso en el 0%.", resultado);
        verifyNoInteractions(cursoDAO);
    }

    @Test
    public void registrar_fallaAlGuardarCurso() throws Exception {
        // Arrange
        Curso curso = new Curso("Java", "Aprende a programar en Java", 4);
        doThrow(new Exception("No se pudo guardar el curso.")).when(cursoDAO).save(curso);

        // Act
        String resultado = cursoService.registrar(curso);

        // Assert
        assertEquals("No se pudo guardar el curso.", resultado);
        verify(cursoDAO, times(1)).save(curso);
    }*/

}
