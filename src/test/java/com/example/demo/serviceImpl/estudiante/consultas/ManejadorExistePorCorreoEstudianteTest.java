package com.example.demo.serviceImpl.estudiante.consultas;

import com.example.demo.dao.EstudianteDAO;
import com.example.demo.mapper.EstudianteMapper;
import com.example.demo.model.dto.EstadoDTO;
import com.example.demo.model.dto.EstudianteDTO;
import com.example.demo.service.EstudianteService;
import com.example.demo.serviceImpl.estudiante.testDataBuilder.EstudianteTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
public class ManejadorExistePorCorreoEstudianteTest {
    @Autowired
    EstudianteService estudianteService;
    @Autowired
    EstudianteMapper estudianteMapper;
    @MockBean
    private EstudianteDAO estudianteDAO;

    @Test
    @DisplayName("Deberia Validar existencia de estudiante por correo")
    void deberiaValidarExistenciaPorCorreo()throws Exception{
        EstudianteDTO estudianteDTO = new EstudianteTestDataBuilder()
                .conCorreo("angelamaria731@hotmail.com").build();
        Mockito.when(estudianteDAO.existsByCorreo(estudianteDTO.getCorreo())).thenReturn(true);
        assertTrue(estudianteService.existePorCorreo(estudianteDTO.getCorreo()));
    }

}
