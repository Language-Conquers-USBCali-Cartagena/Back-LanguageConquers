package com.example.demo.serviceImpl.retoEstudiante.consultas;

import com.example.demo.dao.RetoDAO;
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
public class ManejadorListarPorIdRetoRetoEstudianteTest {
    private static final String ID_RETO_OBLIGATORIO = "Debe ingresar el id del reto.";
    private static final String RETOS_ESTUDIANTE_NO_ENCONTRADOS = "No hay estudiantes que hayan realizado ese reto.";
    private static final String ID_RETO_NO_EXISTE = "No se encontró un reto con ese id.";


    private static final Long ID_RETO = 1L;
    @Autowired
    RetoEstudianteMapper retoEstudianteMapper;
    @Autowired
    RetoEstudianteService retoEstudianteService;
    @MockBean
    RetoEstudianteDAO retoEstudianteDAO;
    @MockBean
    RetoDAO retoDAO;

    @Test
    @DisplayName("Debe listar los retos estudiante por id reto")
    void debeListarPorIdReto() throws Exception{
        List<RetoEstudianteDTO> retoEstudianteDTOS = new ArrayList<>();
        retoEstudianteDTOS.add(new RetoEstudianteTestDataBuilder().conIdRetoEstudiante(1L).build());
        retoEstudianteDTOS.add(new RetoEstudianteTestDataBuilder().conIdRetoEstudiante(2L).build());
        List<RetoEstudiante> retoEstudianteList = retoEstudianteMapper.toEntityList(retoEstudianteDTOS);
        Mockito.when(retoDAO.existsById(ID_RETO)).thenReturn(true);
        Mockito.when(retoEstudianteDAO.findByIdReto(ID_RETO)).thenReturn(retoEstudianteList);
        List<RetoEstudiante> respuesta = retoEstudianteService.listarPorIdReto(ID_RETO);
        assertEquals(respuesta, retoEstudianteList);
    }
    @Test
    @DisplayName("Debe fallar por idEstudiante null")
    void debefallarPorIdEstudianteNulo() throws Exception{
        List<RetoEstudianteDTO> retoEstudianteDTOS = new ArrayList<>();
        retoEstudianteDTOS.add(new RetoEstudianteTestDataBuilder().conIdRetoEstudiante(1L).build());
        retoEstudianteDTOS.add(new RetoEstudianteTestDataBuilder().conIdRetoEstudiante(2L).build());
        List<RetoEstudiante> retoEstudianteList = retoEstudianteMapper.toEntityList(retoEstudianteDTOS);
        Mockito.when(retoDAO.existsById(null)).thenReturn(true);
        Mockito.when(retoEstudianteDAO.findByIdEstudiante(null)).thenReturn(retoEstudianteList);
        Exception exception = assertThrows(Exception.class, () ->{
            retoEstudianteService.listarPorIdReto(null);
        });
        assertEquals(exception.getMessage(), ID_RETO_OBLIGATORIO);
    }
    @Test
    @DisplayName("Debe fallar por idReto no existe")
    void debefallarPorIdREtoNoExiste() throws Exception{
        List<RetoEstudianteDTO> retoEstudianteDTOS = new ArrayList<>();
        retoEstudianteDTOS.add(new RetoEstudianteTestDataBuilder().conIdRetoEstudiante(1L).build());
        retoEstudianteDTOS.add(new RetoEstudianteTestDataBuilder().conIdRetoEstudiante(2L).build());
        List<RetoEstudiante> retoEstudianteList = retoEstudianteMapper.toEntityList(retoEstudianteDTOS);
        Mockito.when(retoDAO.existsById(ID_RETO)).thenReturn(false);
        Mockito.when(retoEstudianteDAO.findByIdReto(ID_RETO)).thenReturn(retoEstudianteList);
        Exception exception = assertThrows(Exception.class, () ->{
            retoEstudianteService.listarPorIdReto(ID_RETO);
        });
        assertEquals(exception.getMessage(), ID_RETO_NO_EXISTE);
    }
    @Test
    @DisplayName("Debe fallar por lista vacia")
    void debefallarPorListaVacia() throws Exception{
        List<RetoEstudianteDTO> retoEstudianteDTOS = new ArrayList<>();
        retoEstudianteDTOS.add(new RetoEstudianteTestDataBuilder().conIdRetoEstudiante(1L).build());
        retoEstudianteDTOS.add(new RetoEstudianteTestDataBuilder().conIdRetoEstudiante(2L).build());
        List<RetoEstudiante> retoEstudianteList = retoEstudianteMapper.toEntityList(retoEstudianteDTOS);
        Mockito.when(retoDAO.existsById(ID_RETO)).thenReturn(true);
        Mockito.when(retoEstudianteDAO.findByIdReto(ID_RETO)).thenReturn(Collections.emptyList());
        Exception exception = assertThrows(Exception.class, () ->{
            retoEstudianteService.listarPorIdReto(ID_RETO);
        });
        assertEquals(exception.getMessage(), RETOS_ESTUDIANTE_NO_ENCONTRADOS);
    }
}
