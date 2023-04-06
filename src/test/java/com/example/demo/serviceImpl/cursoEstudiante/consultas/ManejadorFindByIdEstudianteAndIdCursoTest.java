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

import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
public class ManejadorFindByIdEstudianteAndIdCursoTest {

    private static final String EL_CURSO_CON_ESE_ID_NO_EXISTE = "El curso con ese id no existe.";
    private static final String EL_ESTUDIANTE_CON_ESE_ID_NO_EXISTE = "El estudiante con ese id no existe";
    private static final String NO_EXISTE_ESTE_CURSO_ESTUDIANTE = "No existe este curso estudiante.";

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
    @DisplayName("Deberia validar la existencia del curso estudiante por idEstudiante y idCurso")
    void deberiaValidarExistenciaPorIdEstudianteAndIdCurso()throws Exception{

        CursoEstudianteDTO cursoEstudianteDTO = new CursoEstudianteTestDataBuilder()
                .conIdEstudiante(6L)
                .conIdCurso(67L)
                .build();
        CursoEstudiante cursoEstudiante = cursoEstudianteMapper.toEntity(cursoEstudianteDTO);
        Mockito.when(cursoEstudianteDAO.existsById(cursoEstudianteDTO.getIdCursoEstudiante())).thenReturn(true);
        Mockito.when(cursoEstudianteDAO.findById(cursoEstudianteDTO.getIdCursoEstudiante())).thenReturn(Optional.of(cursoEstudiante));
        Mockito.when(estudianteDAO.existsById(cursoEstudianteDTO.getIdEstudiante())).thenReturn(true);
        Mockito.when(cursoDAO.existsById(cursoEstudiante.getCurso().getIdCurso())).thenReturn(true);
        Mockito.when(cursoEstudianteDAO.findByIdCursoAndIdEstudiante(cursoEstudianteDTO.getIdCurso(), cursoEstudianteDTO.getIdEstudiante())).thenReturn(cursoEstudiante);

        CursoEstudiante resultado = cursoEstudianteService.findByIdEstudianteAndIdCurso(cursoEstudiante.getCurso().getIdCurso(), cursoEstudiante.getEstudiante().getIdEstudiante());
        assertNotNull(resultado);
        assertEquals(cursoEstudiante.getIdCursoEstudiante(), resultado.getIdCursoEstudiante());
    }

    @Test
    @DisplayName("Deberia fallar por idCurso no existe")
    void deberiaFallarPorIdCursoNoExiste()throws Exception{

        CursoEstudianteDTO cursoEstudianteDTO = new CursoEstudianteTestDataBuilder()
                .build();
        CursoEstudiante cursoEstudiante = cursoEstudianteMapper.toEntity(cursoEstudianteDTO);
        Mockito.when(cursoDAO.existsById(cursoEstudiante.getCurso().getIdCurso())).thenReturn(false);
        Exception exception = assertThrows(Exception.class, ()->{
            cursoEstudianteService.findByIdEstudianteAndIdCurso(cursoEstudiante.getCurso().getIdCurso(), cursoEstudiante.getEstudiante().getIdEstudiante());
        });
        assertEquals(exception.getMessage(), EL_CURSO_CON_ESE_ID_NO_EXISTE);
    }
    @Test
    @DisplayName("Deberia fallar por idEstudiante no existe")
    void deberiaFallarPorIdEstudianteNoExiste()throws Exception{

        CursoEstudianteDTO cursoEstudianteDTO = new CursoEstudianteTestDataBuilder()
                .build();
        CursoEstudiante cursoEstudiante = cursoEstudianteMapper.toEntity(cursoEstudianteDTO);
        Mockito.when(cursoDAO.existsById(cursoEstudiante.getCurso().getIdCurso())).thenReturn(true);
        Mockito.when(estudianteDAO.existsById(cursoEstudianteDTO.getIdEstudiante())).thenReturn(false);
        Exception exception = assertThrows(Exception.class, ()->{
            cursoEstudianteService.findByIdEstudianteAndIdCurso(cursoEstudiante.getCurso().getIdCurso(), cursoEstudiante.getEstudiante().getIdEstudiante());
        });
        assertEquals(exception.getMessage(), EL_ESTUDIANTE_CON_ESE_ID_NO_EXISTE);
    }

    @Test
    @DisplayName("Deberia fallar por idCursoEstudiante no existe")
    void deberiaFallarPorIdCursoEstudianteNoExiste()throws Exception{

        CursoEstudianteDTO cursoEstudianteDTO = new CursoEstudianteTestDataBuilder()
                .build();
        CursoEstudiante cursoEstudiante = cursoEstudianteMapper.toEntity(cursoEstudianteDTO);
        Mockito.when(cursoDAO.existsById(cursoEstudiante.getCurso().getIdCurso())).thenReturn(true);
        Mockito.when(estudianteDAO.existsById(cursoEstudianteDTO.getIdEstudiante())).thenReturn(true);
        Mockito.when(cursoEstudianteDAO.existsById(cursoEstudianteDTO.getIdCursoEstudiante())).thenReturn(false);
        Exception exception = assertThrows(Exception.class, ()->{
            cursoEstudianteService.findByIdEstudianteAndIdCurso(cursoEstudiante.getCurso().getIdCurso(), cursoEstudiante.getEstudiante().getIdEstudiante());
        });
        assertEquals(exception.getMessage(),NO_EXISTE_ESTE_CURSO_ESTUDIANTE);
    }
}
