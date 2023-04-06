package com.example.demo.serviceImpl.retoEstudiante.consultas;

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

import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@SpringBootTest
public class ManejadorEncontrarRetoEstudiantePorId {
    public static final String ID_RETO_ESTUDIANTE_OBLIGATORIO = "Debe ingresar el id del reto estudiante.";
    public static final String RETO_ESTUDIANTE_NO_EXISTE = "El reto estudiante con id: 1 no existe.";


    @Autowired
    RetoEstudianteMapper retoEstudianteMapper;
    @Autowired
    RetoEstudianteService retoEstudianteService;
    @MockBean
    RetoEstudianteDAO retoEstudianteDAO;

    @Test
    @DisplayName("Deberia obtener reto por id")
    void deberiaObtenerRetoPorId() throws Exception{
        RetoEstudianteDTO retoEstudianteDTO = new RetoEstudianteTestDataBuilder().conIdRetoEstudiante(1L).build();
        RetoEstudiante retoEstudiante = retoEstudianteMapper.toEntity(retoEstudianteDTO);
        Mockito.when(retoEstudianteDAO.existsById(retoEstudianteDTO.getIdRetoEstudiante())).thenReturn(true);
        Mockito.when(retoEstudianteDAO.findById(retoEstudianteDTO.getIdEstudiante())).thenReturn(Optional.of(retoEstudiante));
        RetoEstudiante respuesta = retoEstudianteService.findById(1L);
        assertEquals(respuesta, retoEstudiante);
    }

    @Test
    @DisplayName("Deberia fallar por idRetoEstudiante nulo")
    void deberiaFallarPorIdRetoEstudianteNulo() throws Exception{
        RetoEstudianteDTO retoEstudianteDTO = new RetoEstudianteTestDataBuilder().conIdRetoEstudiante(null).build();
        RetoEstudiante retoEstudiante = retoEstudianteMapper.toEntity(retoEstudianteDTO);
        Mockito.when(retoEstudianteDAO.existsById(retoEstudianteDTO.getIdRetoEstudiante())).thenReturn(true);
        Mockito.when(retoEstudianteDAO.findById(retoEstudianteDTO.getIdEstudiante())).thenReturn(Optional.of(retoEstudiante));
        Exception exception = assertThrows(Exception.class, () -> {
            retoEstudianteService.findById(null);
        });
        assertEquals(exception.getMessage(), ID_RETO_ESTUDIANTE_OBLIGATORIO);
    }

    @Test
    @DisplayName("Deberia fallar por idRetoEstudiante no existe")
    void deberiaFallarPorIdRetoEstudianteNoExiste() throws Exception{
        RetoEstudianteDTO retoEstudianteDTO = new RetoEstudianteTestDataBuilder().conIdRetoEstudiante(1L).build();
        RetoEstudiante retoEstudiante = retoEstudianteMapper.toEntity(retoEstudianteDTO);
        Mockito.when(retoEstudianteDAO.existsById(retoEstudianteDTO.getIdRetoEstudiante())).thenReturn(false);
        Mockito.when(retoEstudianteDAO.findById(retoEstudianteDTO.getIdEstudiante())).thenReturn(Optional.of(retoEstudiante));
        Exception exception = assertThrows(Exception.class, () -> {
            retoEstudianteService.findById(1L);
        });
        assertEquals(exception.getMessage(), RETO_ESTUDIANTE_NO_EXISTE);
    }
}
