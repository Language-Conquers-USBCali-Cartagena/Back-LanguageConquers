package com.example.demo.serviceImpl.cursoEstudiante.consultas;

import com.example.demo.dao.CursoDAO;
import com.example.demo.dao.CursoEstudianteDAO;
import com.example.demo.dao.EstudianteDAO;
import com.example.demo.mapper.CursoEstudianteMapper;
import com.example.demo.model.Curso;
import com.example.demo.model.CursoEstudiante;
import com.example.demo.model.Estudiante;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@SpringBootTest
public class ManejadorCrearCursoEstudianteTest {

    private static final String SE_CREO_EXITOSAMENTE_EL_CURSO_ESTUDIANTE = "Se creo exitosamente el curso estudiante.";
    private static final String EL_ESTUDIANTE_ASIGNADO_NO_SE_ENCUENTRA_REGISTRADO = "El estudiante asignado no se encuentra registrado.";
    private static final String EL_CURSO_ASIGNADO_NO_EXISTE = "El curso asignado no existe.";

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
    @DisplayName("Deberia crear exitosamente el cursoEstudiante")
    void deberiaCrearExitosamenteCursoEstudiante()throws Exception{
        CursoEstudianteDTO cursoEstudianteDTO = new CursoEstudianteTestDataBuilder().build();
        CursoEstudiante cursoEstudiante = cursoEstudianteMapper.toEntity(cursoEstudianteDTO);
        Mockito.when(estudianteDAO.existsById(cursoEstudiante.getEstudiante().getIdEstudiante())).thenReturn(true);
        Mockito.when(estudianteDAO.findById(cursoEstudiante.getEstudiante().getIdEstudiante())).thenReturn(Optional.of(new Estudiante()));
        Mockito.when(cursoDAO.existsById(cursoEstudiante.getCurso().getIdCurso())).thenReturn(true);
        Mockito.when(cursoDAO.findById(cursoEstudiante.getCurso().getIdCurso())).thenReturn(Optional.of(new Curso()));
        String crearCursoEstudiante = cursoEstudianteService.crearCursoEstudiante(cursoEstudiante);

        Mockito.when(cursoEstudianteDAO.save(Mockito.any())).thenReturn(cursoEstudiante);
        assertEquals(SE_CREO_EXITOSAMENTE_EL_CURSO_ESTUDIANTE, crearCursoEstudiante);
    }

    @Test
    @DisplayName("Deberia fallar por idEstudiante no existe")
    void deberiaFallarPorIdEstudianteNoExiste()throws Exception{
        CursoEstudianteDTO cursoEstudianteDTO = new CursoEstudianteTestDataBuilder().build();
        CursoEstudiante cursoEstudiante = cursoEstudianteMapper.toEntity(cursoEstudianteDTO);
        Mockito.when(estudianteDAO.existsById(cursoEstudiante.getEstudiante().getIdEstudiante())).thenReturn(false);
        Mockito.when(cursoEstudianteDAO.save(Mockito.any())).thenReturn(cursoEstudiante);
        Exception exception = assertThrows(Exception.class, ()->{
            cursoEstudianteService.crearCursoEstudiante(cursoEstudiante);
        });
        assertEquals(exception.getMessage(), EL_ESTUDIANTE_ASIGNADO_NO_SE_ENCUENTRA_REGISTRADO);
    }

    @Test
    @DisplayName("Deberia fallar por idCurso no existe")
    void deberiaFallarPorIdCursoNoExiste()throws Exception{
        CursoEstudianteDTO cursoEstudianteDTO = new CursoEstudianteTestDataBuilder().build();
        CursoEstudiante cursoEstudiante = cursoEstudianteMapper.toEntity(cursoEstudianteDTO);
        Mockito.when(estudianteDAO.existsById(cursoEstudiante.getEstudiante().getIdEstudiante())).thenReturn(true);
        Mockito.when(cursoDAO.existsById(cursoEstudiante.getCurso().getIdCurso())).thenReturn(false);
        Mockito.when(cursoEstudianteDAO.save(Mockito.any())).thenReturn(cursoEstudiante);
        Exception exception = assertThrows(Exception.class, ()->{
            cursoEstudianteService.crearCursoEstudiante(cursoEstudiante);
        });
        assertEquals(exception.getMessage(), EL_CURSO_ASIGNADO_NO_EXISTE);
    }
}
