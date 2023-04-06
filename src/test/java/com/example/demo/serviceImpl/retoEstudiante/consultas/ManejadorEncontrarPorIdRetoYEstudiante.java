package com.example.demo.serviceImpl.retoEstudiante.consultas;

import com.example.demo.dao.EstudianteDAO;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@SpringBootTest
public class ManejadorEncontrarPorIdRetoYEstudiante {
    private static final String ID_ESTUDIANTE_OBLIGATORIO = "Debe ingresar el id del estudiante";
    private static final String RETO_NO_EXISTE = "No existe reto con ese id.";
    private static final String RETOS_ESTUDIANTE_NO_ENCONTRADOS = "No se encontraron retos realizados por ese estudiante";
    private static final String NO_EXISTE_ESTUDIANTE_CON_ESE_ID = "No existe estudiante con ese id.";

    private static final String ID_ESTUDIANTE_NO_EXISTE = "No se encontró un estudiante con ese id.";
    private static final Long ID_RETO = 1L;
    private static final Long ID_ESTUDIANTE = 1L;
    @Autowired
    RetoEstudianteMapper retoEstudianteMapper;
    @Autowired
    RetoEstudianteService retoEstudianteService;
    @MockBean
    RetoEstudianteDAO retoEstudianteDAO;
    @MockBean
    RetoDAO retoDAO;
    @MockBean
    EstudianteDAO estudianteDAO;

    @Test
    @DisplayName("Debe listar los retos estudiante por id reto, estudiante")
    void debeListarPorIdRetoIDEstudiante() throws Exception{
        RetoEstudianteDTO retoEstudianteDTOS = new RetoEstudianteTestDataBuilder().conIdRetoEstudiante(1L).build();
        RetoEstudiante retoEstudiante = retoEstudianteMapper.toEntity(retoEstudianteDTOS);
        Mockito.when(retoDAO.existsById(ID_RETO)).thenReturn(true);
        Mockito.when(estudianteDAO.existsById(ID_ESTUDIANTE)).thenReturn(true);
        Mockito.when(retoEstudianteDAO.findByIdRetoAndIdEstuduante(ID_RETO, ID_ESTUDIANTE)).thenReturn(retoEstudiante);
        RetoEstudiante respuesta = retoEstudianteService.findByIdRetoAndIdEstudiante(ID_RETO, ID_ESTUDIANTE);
        assertEquals(respuesta, retoEstudiante);
    }
    @Test
    @DisplayName("Debe fallar por idREto no existe")
    void debefallarPorIdREtoNoExiste() throws Exception{
        RetoEstudianteDTO retoEstudianteDTOS = new RetoEstudianteTestDataBuilder().conIdRetoEstudiante(1L).build();
        RetoEstudiante retoEstudiante = retoEstudianteMapper.toEntity(retoEstudianteDTOS);
        Mockito.when(retoDAO.existsById(ID_RETO)).thenReturn(false);
        Mockito.when(estudianteDAO.existsById(ID_ESTUDIANTE)).thenReturn(true);
        Mockito.when(retoEstudianteDAO.findByIdRetoAndIdEstuduante(ID_RETO, ID_ESTUDIANTE)).thenReturn(retoEstudiante);
        Exception exception = assertThrows(Exception.class, () ->{
            retoEstudianteService.findByIdRetoAndIdEstudiante(ID_RETO, ID_ESTUDIANTE);
        });
        assertEquals(exception.getMessage(),RETO_NO_EXISTE );
    }
    @Test
    @DisplayName("Debe fallar por idEstudiante no existe")
    void debefallarPorIdEstudianteNoExiste() throws Exception{
        RetoEstudianteDTO retoEstudianteDTOS = new RetoEstudianteTestDataBuilder().conIdRetoEstudiante(1L).build();
        RetoEstudiante retoEstudiante = retoEstudianteMapper.toEntity(retoEstudianteDTOS);
        Mockito.when(retoDAO.existsById(ID_RETO)).thenReturn(true);
        Mockito.when(estudianteDAO.existsById(ID_ESTUDIANTE)).thenReturn(false);
        Mockito.when(retoEstudianteDAO.findByIdRetoAndIdEstuduante(ID_RETO, ID_ESTUDIANTE)).thenReturn(retoEstudiante);
        Exception exception = assertThrows(Exception.class, () ->{
            retoEstudianteService.findByIdRetoAndIdEstudiante(ID_RETO, ID_ESTUDIANTE);
        });
        assertEquals(exception.getMessage(),NO_EXISTE_ESTUDIANTE_CON_ESE_ID );
    }

}
