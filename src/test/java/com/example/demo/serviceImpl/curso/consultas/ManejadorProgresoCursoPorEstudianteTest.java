package com.example.demo.serviceImpl.curso.consultas;

import com.example.demo.dao.CursoDAO;
import com.example.demo.dao.EstadoDAO;
import com.example.demo.dao.EstudianteDAO;
import com.example.demo.dao.ProfesorDAO;
import com.example.demo.mapper.CursoMapper;
import com.example.demo.model.dto.CursoDTO;
import com.example.demo.service.CursoService;
import com.example.demo.serviceImpl.curso.testDataBuilder.CursoTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@SpringBootTest
public class ManejadorProgresoCursoPorEstudianteTest {

    private static final String EL_CURSO_CON_ESE_ID_NO_EXISTE = "El curso con ese id no existe.";
    private static final String EL_ESTUDIANTE_CON_ESE_ID_NO_EXISTE = "El estudiante con ese id no existe.";
    @Autowired
    CursoService cursoService;
    @Autowired
    CursoMapper cursoMapper;
    @MockBean
    private CursoDAO cursoDAO;
    @MockBean
    private ProfesorDAO profesorDAO;
    @MockBean
    private EstudianteDAO estudianteDAO;

    @Test
    @DisplayName("Deberia actualizar progreso del estudiante dentro del curso")
    void deberiActualizarProgresoEstudiante()throws Exception{
        Long idEstudiante = 2L;
        Integer progresoEsperado = 30;
        CursoDTO cursoDTO = new CursoTestDataBuilder()
                .conIdCurso(1L)
                .build();
        Mockito.when(cursoDAO.existsById(cursoDTO.getIdCurso())).thenReturn(true);
        Mockito.when(estudianteDAO.existsById(idEstudiante)).thenReturn(true);
        Mockito.when(cursoDAO.progresoCursosEstudiante(cursoDTO.getIdCurso(), idEstudiante)).thenReturn(progresoEsperado);
        Integer progresoReal = cursoService.progresoCursoPorEstudiante(cursoDTO.getIdCurso(), idEstudiante);
        assertEquals(progresoEsperado, progresoReal);
    }

    @Test
    @DisplayName("Deberia fallar por idCurso no existe")
    void deberiFallarPorIdCursoNoExiste()throws Exception{
        Long idEstudiante = 2L;
        Integer progresoEsperado = 30;
        CursoDTO cursoDTO = new CursoTestDataBuilder()
                .conIdCurso(1L)
                .build();
        Mockito.when(cursoDAO.existsById(cursoDTO.getIdCurso())).thenReturn(false);
       Exception exception = assertThrows(Exception.class, ()->{
           cursoService.progresoCursoPorEstudiante(cursoDTO.getIdCurso(), idEstudiante);
       });
        assertEquals(exception.getMessage(),EL_CURSO_CON_ESE_ID_NO_EXISTE );
    }
    @Test
    @DisplayName("Deberia fallar por idEstudiante no existe")
    void deberiFallarPorIdEstudianteNoExiste()throws Exception{
        Long idEstudiante = 2L;
        Integer progresoEsperado = 30;
        CursoDTO cursoDTO = new CursoTestDataBuilder()
                .conIdCurso(1L)
                .build();
        Mockito.when(cursoDAO.existsById(cursoDTO.getIdCurso())).thenReturn(true);
        Mockito.when(estudianteDAO.existsById(idEstudiante)).thenReturn(false);
        Exception exception = assertThrows(Exception.class, ()->{
            cursoService.progresoCursoPorEstudiante(cursoDTO.getIdCurso(), idEstudiante);
        });
        assertEquals(exception.getMessage(),EL_ESTUDIANTE_CON_ESE_ID_NO_EXISTE );
    }
}
