package com.example.demo.serviceImpl.curso.consultas;

import com.example.demo.dao.*;
import com.example.demo.mapper.CursoMapper;
import com.example.demo.model.Curso;
import com.example.demo.model.CursoEstudiante;
import com.example.demo.model.Mision;
import com.example.demo.model.Reto;
import com.example.demo.model.dto.CursoDTO;
import com.example.demo.service.CursoService;
import com.example.demo.serviceImpl.curso.testDataBuilder.CursoTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@SpringBootTest
public class ManejadorEliminarCursoTest {

    private static final String SE_DEBE_INGRESAR_EL_ID_DEL_CURSO = "Se debe de ingresar el id del curso.";
    private static final String EL_CURSO_CON_ESE_ID_NO_EXISTE = "El curso con ese id no existe.";
    private static final String NO_SE_PUEDE_ELIMINAR_EL_CURSO_POR_QUE_ESTA_SIENDO_UTILIZADO_EN_UN_RETO = "No se puede eliminar el curso porque esta siendo utilizado en un reto.";
    private static final String NO_SE_PUEDE_ELIMINAR_EL_CURSO_POR_QUE_ESTA_SIENDO_UTILIZADO_EN_UNA_MISION = "No se puede eliminar el curso porque esta siendo utilizado en una misión.";
    private static final String NO_SE_PUEDE_ELIMINAR_EL_CURSO_POR_QUE_ESTA_SIENDO_UTILIZADO_EN_UN_CURSO_ESTUDIANTE = "No se puede eliminar el curso porque esta siendo utilizado en un curso estudiante.";
    private static final String SE_ELIMINO_EXITOSAMENTE_EL_CURSO = "Se elimino exitosamente el curso.";

    @Autowired
    CursoService cursoService;
    @Autowired
    CursoMapper cursoMapper;
    @MockBean
    private CursoDAO cursoDAO;
    @MockBean
    private ProfesorDAO profesorDAO;
    @MockBean
    private EstadoDAO estadoDAO;
    @MockBean
    private RetoDAO retoDAO;

    @MockBean
    private MisionDAO misionDAO;

    @MockBean
    private CursoEstudianteDAO cursoEstudianteDAO;

    @Test
    @DisplayName("Deberia eliminar un curso correctamente")
    void deberiaEliminarExitosamenteCurso()throws Exception{
        CursoDTO cursoDTO = new CursoTestDataBuilder()
                .conIdCurso(3847L)
                .build();
        Mockito.when(cursoDAO.existsById(cursoDTO.getIdCurso())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(cursoDTO.getIdEstado())).thenReturn(true);
        Mockito.when(profesorDAO.existsById(cursoDTO.getIdProfesor())).thenReturn(true);
        String resultado = cursoService.eliminar(cursoDTO.getIdCurso());
        Mockito.verify(cursoDAO, Mockito.times(1)).deleteById(cursoDTO.getIdCurso());
        assertEquals(SE_ELIMINO_EXITOSAMENTE_EL_CURSO, resultado);
    }
    @Test
    @DisplayName("Deberia fallar por IdCurso null")
    void deberiaFallarPorIdCursoNull()throws Exception{
        CursoDTO cursoDTO = new CursoTestDataBuilder()
                .conIdCurso(null)
                .build();
      Exception exception = assertThrows(Exception.class, ()->{
          cursoService.eliminar(cursoDTO.getIdCurso());
      });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_EL_ID_DEL_CURSO);
    }

    @Test
    @DisplayName("Deberia fallar por IdCurso no existe")
    void deberiaFallarPorIdCursoNoExiste()throws Exception{
        CursoDTO cursoDTO = new CursoTestDataBuilder()
                .conIdCurso(74L)
                .build();
        Mockito.when(cursoDAO.existsById(cursoDTO.getIdCurso())).thenReturn(false);
        Exception exception = assertThrows(Exception.class, ()->{
            cursoService.eliminar(cursoDTO.getIdCurso());
        });
        assertEquals(exception.getMessage(), EL_CURSO_CON_ESE_ID_NO_EXISTE);
    }
    @Test
    @DisplayName("Deberia fallar por que IdCurso esta siendo utilizado por un reto")
    void deberiaFallarPorIdCursoUtilizadoPorReto()throws Exception{
        CursoDTO cursoDTO = new CursoTestDataBuilder()
                .build();
        Mockito.when(cursoDAO.existsById(cursoDTO.getIdCurso())).thenReturn(true);
        Mockito.when(retoDAO.findByIdCurso(cursoDTO.getIdCurso())).thenReturn(Collections.singletonList(new Reto()));
        Exception exception = assertThrows(Exception.class, ()->{
            cursoService.eliminar(cursoDTO.getIdCurso());
        });
        assertEquals(exception.getMessage(), NO_SE_PUEDE_ELIMINAR_EL_CURSO_POR_QUE_ESTA_SIENDO_UTILIZADO_EN_UN_RETO);
    }
    @Test
    @DisplayName("Deberia fallar por que IdCurso esta siendo utilizado por una mision")
    void deberiaFallarPorIdCursoUtilizadoPorMision()throws Exception{
        CursoDTO cursoDTO = new CursoTestDataBuilder()
                .build();
        Mockito.when(cursoDAO.existsById(cursoDTO.getIdCurso())).thenReturn(true);
        Mockito.when(misionDAO.findByIdCurso(cursoDTO.getIdCurso())).thenReturn(Collections.singletonList(new Mision()));
        Exception exception = assertThrows(Exception.class, ()->{
            cursoService.eliminar(cursoDTO.getIdCurso());
        });
        assertEquals(exception.getMessage(), NO_SE_PUEDE_ELIMINAR_EL_CURSO_POR_QUE_ESTA_SIENDO_UTILIZADO_EN_UNA_MISION);
    }

    @Test
    @DisplayName("Deberia fallar por que IdCurso esta siendo utilizado por un curso estudiante")
    void deberiaFallarPorIdCursoUtilizadoPorCursoEstudiante()throws Exception{
        CursoDTO cursoDTO = new CursoTestDataBuilder()
                .build();
        Mockito.when(cursoDAO.existsById(cursoDTO.getIdCurso())).thenReturn(true);
        Mockito.when(cursoEstudianteDAO.findByIdCurso(cursoDTO.getIdCurso())).thenReturn(Collections.singletonList(new CursoEstudiante()));
        Exception exception = assertThrows(Exception.class, ()->{
            cursoService.eliminar(cursoDTO.getIdCurso());
        });
        assertEquals(exception.getMessage(), NO_SE_PUEDE_ELIMINAR_EL_CURSO_POR_QUE_ESTA_SIENDO_UTILIZADO_EN_UN_CURSO_ESTUDIANTE);
    }


}
