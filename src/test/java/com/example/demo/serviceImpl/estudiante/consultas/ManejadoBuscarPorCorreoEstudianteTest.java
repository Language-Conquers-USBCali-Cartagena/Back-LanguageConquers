package com.example.demo.serviceImpl.estudiante.consultas;

import com.example.demo.dao.EstudianteDAO;
import com.example.demo.mapper.EstudianteMapper;
import com.example.demo.model.Estudiante;
import com.example.demo.model.dto.EstudianteDTO;
import com.example.demo.service.EstudianteService;
import com.example.demo.serviceImpl.estudiante.testDataBuilder.EstudianteTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
@SpringBootTest
public class ManejadoBuscarPorCorreoEstudianteTest {

    private static final String EL_FORMATO_DEL_CORREO_NO_ES_VALIDO = "El formato del correo no es válido.";
    private static final String NO_EXISTE_ESTUDIANTE_REGISTRADO_CON_ESE_CORREO = "No existe estudiante registrado con este correo";
    @Autowired
    EstudianteService estudianteService;
    @Autowired
    EstudianteMapper estudianteMapper;
    @MockBean
    private EstudianteDAO estudianteDAO;

    @Test
    @DisplayName("Deberia validar la existencia de un estudiante por correo")
    void deberiaValidarLaExistenciaDeUnEstudiantePorCorreo() throws Exception {

        EstudianteDTO estudianteDTO = new EstudianteTestDataBuilder()
                .build();
        Estudiante estudiante = estudianteMapper.toEntity(estudianteDTO);
        Mockito.when(estudianteDAO.findByCorreo(estudianteDTO.getCorreo())).thenReturn(estudiante);

        Estudiante estudianteEncontrado = estudianteService.findByCorreo(estudiante.getCorreo());
        //assertNotNull(estudianteEncontrado);
        assertEquals(estudianteDTO.getIdEstudiante(), estudianteEncontrado.getIdEstudiante());
    }

    /*
    @Test
    @DisplayName("Deberia fallar por formato correo invalido")
    void deberiaFallarPorCorreoMalo() throws Exception {

        EstudianteDTO estudianteDTO = new EstudianteTestDataBuilder()
                .conCorreo("jdkj.com")
                .build();
        Estudiante estudiante = estudianteMapper.toEntity(estudianteDTO);
        Exception exception = assertThrows(Exception.class, () -> {
            estudianteService.findByCorreo(estudiante.getCorreo());
        });
        assertEquals(exception.getMessage(), EL_FORMATO_DEL_CORREO_NO_ES_VALIDO);
    }

    @Test
    @DisplayName("Deberia fallar por formato correo invalido")
    void deberiaFallarPorCorreoVacio() throws Exception {

        EstudianteDTO estudianteDTO = new EstudianteTestDataBuilder()
                .conCorreo("")
                .build();
        Estudiante estudiante = estudianteMapper.toEntity(estudianteDTO);
        Mockito.when(estudianteDAO.findByCorreo(estudiante.getCorreo())).thenReturn(new Estudiante());
        Exception exception = assertThrows(Exception.class, () -> {
            estudianteService.findByCorreo(estudiante.getCorreo());
        });
        assertEquals(exception.getMessage(), NO_EXISTE_ESTUDIANTE_REGISTRADO_CON_ESE_CORREO);
    }
    */
}
