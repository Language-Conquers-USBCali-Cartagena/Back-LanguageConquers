package com.example.demo.serviceImpl.retoEstudiante.consultas;

import com.example.demo.dao.RetoEstudianteDAO;
import com.example.demo.mapper.RetoEstudianteMapper;
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
public class ManejadorEliminarRetoEstudiante {
    public static final String RETO_ESTUDIANTE_ELIMINADO_EXITOSAMENTE = "Se elimino exitosamente el reto estudiante";
    public static final String ID_RETO_ESTUDIANTE_OBLIGATORIO = "El id del reto estudiante es obligatorio.";
    public static final String RETO_ESTUDIANTE_NO_ENCONTRADO = "No se encontró un reto estudiante con ese id.";
    @Autowired
    RetoEstudianteMapper retoEstudianteMapper;
    @Autowired
    RetoEstudianteService retoEstudianteService;
    @MockBean
    RetoEstudianteDAO retoEstudianteDAO;

    @Test
    @DisplayName("Deberia eliminar el retoEstudiante")
    void deberiaEliminarElRetoEstudiante() throws Exception{
        RetoEstudianteDTO retoEstudianteDTO = new RetoEstudianteTestDataBuilder().build();
        Mockito.when(retoEstudianteDAO.existsById(retoEstudianteDTO.getIdRetoEstudiante())).thenReturn(true);
        String respuesta = retoEstudianteService.eliminar(retoEstudianteDTO.getIdRetoEstudiante());
        assertEquals(respuesta, RETO_ESTUDIANTE_ELIMINADO_EXITOSAMENTE);
    }
    @Test
    @DisplayName("Deberia fallar por id reto estudiante nulo")
    void deberiaFallarPorIdRetoEstudianteNulo() throws Exception{
        RetoEstudianteDTO retoEstudianteDTO = new RetoEstudianteTestDataBuilder().conIdRetoEstudiante(null).build();
        Mockito.when(retoEstudianteDAO.existsById(retoEstudianteDTO.getIdRetoEstudiante())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, () ->{
            retoEstudianteService.eliminar(retoEstudianteDTO.getIdRetoEstudiante());
        });
        assertEquals(exception.getMessage(), ID_RETO_ESTUDIANTE_OBLIGATORIO);
    }
    @Test
    @DisplayName("Deberia fallar por id reto estudiante no existe")
    void deberiaFallarPorIdRetoEstudianteNoExiste() throws Exception{
        RetoEstudianteDTO retoEstudianteDTO = new RetoEstudianteTestDataBuilder().build();
        Mockito.when(retoEstudianteDAO.existsById(retoEstudianteDTO.getIdRetoEstudiante())).thenReturn(false);
        Exception exception = assertThrows(Exception.class, () ->{
            retoEstudianteService.eliminar(retoEstudianteDTO.getIdRetoEstudiante());
        });
        assertEquals(exception.getMessage(), RETO_ESTUDIANTE_NO_ENCONTRADO);
    }
}
