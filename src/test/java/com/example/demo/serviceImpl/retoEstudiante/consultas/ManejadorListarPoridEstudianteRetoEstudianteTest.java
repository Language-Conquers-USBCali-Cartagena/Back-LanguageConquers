package com.example.demo.serviceImpl.retoEstudiante.consultas;

import com.example.demo.dao.EstudianteDAO;
import com.example.demo.dao.RetoEstudianteDAO;
import com.example.demo.mapper.RetoEstudianteMapper;
import com.example.demo.model.RetoEstudiante;
import com.example.demo.model.dto.RetoEstudianteDTO;
import com.example.demo.service.RetoEstudianteService;
import com.example.demo.serviceImpl.retoEstudiante.testDataBuilder.RetoEstudianteTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;


@SpringBootTest
public class ManejadorListarPoridEstudianteRetoEstudianteTest {
    private static final String ID_ESTUDIANTE_OBLIGATORIO = "Debe ingresar el id del estudiante";
    private static final String RETOS_ESTUDIANTE_NO_ENCONTRADOS = "No se encontraron retos realizados por ese estudiante";
    private static final String ID_ESTUDIANTE_NO_EXISTE = "No se encontró un estudiante con ese id.";


    private static final Long ID_ESTUDIANTE = 1L;
    @Autowired
    RetoEstudianteMapper retoEstudianteMapper;
    @Autowired
    RetoEstudianteService retoEstudianteService;
    @MockBean
    RetoEstudianteDAO retoEstudianteDAO;
    @MockBean
    EstudianteDAO estudianteDAO;

    @Test
    @DisplayName("Debe listar los retos estudiante por id estudiante")
    void debeListarPorIdEstudiante() throws Exception{
        List<RetoEstudianteDTO> retoEstudianteDTOS = new ArrayList<>();
        retoEstudianteDTOS.add(new RetoEstudianteTestDataBuilder().conIdRetoEstudiante(1L).build());
        retoEstudianteDTOS.add(new RetoEstudianteTestDataBuilder().conIdRetoEstudiante(2L).build());
        List<RetoEstudiante> retoEstudianteList = retoEstudianteMapper.toEntityList(retoEstudianteDTOS);
        Mockito.when(estudianteDAO.existsById(ID_ESTUDIANTE)).thenReturn(true);
        Mockito.when(retoEstudianteDAO.findByIdEstudiante(ID_ESTUDIANTE)).thenReturn(retoEstudianteList);
        List<RetoEstudiante> respuesta = retoEstudianteService.listarPorIdEstudiante(ID_ESTUDIANTE);
        assertEquals(respuesta, retoEstudianteList);
    }
    @Test
    @DisplayName("Debe fallar por idEstudiante null")
    void debefallarPorIdEstudianteNulo() throws Exception{
        List<RetoEstudianteDTO> retoEstudianteDTOS = new ArrayList<>();
        retoEstudianteDTOS.add(new RetoEstudianteTestDataBuilder().conIdRetoEstudiante(1L).build());
        retoEstudianteDTOS.add(new RetoEstudianteTestDataBuilder().conIdRetoEstudiante(2L).build());
        List<RetoEstudiante> retoEstudianteList = retoEstudianteMapper.toEntityList(retoEstudianteDTOS);
        Mockito.when(estudianteDAO.existsById(null)).thenReturn(true);
        Mockito.when(retoEstudianteDAO.findByIdEstudiante(null)).thenReturn(retoEstudianteList);
        Exception exception = assertThrows(Exception.class, () ->{
            retoEstudianteService.listarPorIdEstudiante(null);
        });
        assertEquals(exception.getMessage(), ID_ESTUDIANTE_OBLIGATORIO);
    }
    @Test
    @DisplayName("Debe fallar por idEstudiante no existe")
    void debefallarPorIdEstudianteNoExiste() throws Exception{
        List<RetoEstudianteDTO> retoEstudianteDTOS = new ArrayList<>();
        retoEstudianteDTOS.add(new RetoEstudianteTestDataBuilder().conIdRetoEstudiante(1L).build());
        retoEstudianteDTOS.add(new RetoEstudianteTestDataBuilder().conIdRetoEstudiante(2L).build());
        List<RetoEstudiante> retoEstudianteList = retoEstudianteMapper.toEntityList(retoEstudianteDTOS);
        Mockito.when(estudianteDAO.existsById(ID_ESTUDIANTE)).thenReturn(false);
        Mockito.when(retoEstudianteDAO.findByIdEstudiante(ID_ESTUDIANTE)).thenReturn(retoEstudianteList);
        Exception exception = assertThrows(Exception.class, () ->{
            retoEstudianteService.listarPorIdEstudiante(ID_ESTUDIANTE);
        });
        assertEquals(exception.getMessage(), ID_ESTUDIANTE_NO_EXISTE);
    }
    @Test
    @DisplayName("Debe fallar por lista vacia")
    void debefallarPorListaVacia() throws Exception{
        List<RetoEstudianteDTO> retoEstudianteDTOS = new ArrayList<>();
        retoEstudianteDTOS.add(new RetoEstudianteTestDataBuilder().conIdRetoEstudiante(1L).build());
        retoEstudianteDTOS.add(new RetoEstudianteTestDataBuilder().conIdRetoEstudiante(2L).build());
        List<RetoEstudiante> retoEstudianteList = retoEstudianteMapper.toEntityList(retoEstudianteDTOS);
        Mockito.when(estudianteDAO.existsById(ID_ESTUDIANTE)).thenReturn(true);
        Mockito.when(retoEstudianteDAO.findByIdEstudiante(ID_ESTUDIANTE)).thenReturn(Collections.emptyList());
        Exception exception = assertThrows(Exception.class, () ->{
            retoEstudianteService.listarPorIdEstudiante(ID_ESTUDIANTE);
        });
        assertEquals(exception.getMessage(), RETOS_ESTUDIANTE_NO_ENCONTRADOS);
    }
}
