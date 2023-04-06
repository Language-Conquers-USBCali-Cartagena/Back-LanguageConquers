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

import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
public class ManejadorBuscarPorIdEstudianteTest {

    private static final String DEBE_INGRESAR_EL_ID_DE_UN_ESTUDIANTE = "Debe ingresar el id de un estudiante.";
    private static final String EL_ESTUDIANTE_CON_ESE_ID_NO_EXISTE = "El estudiante con  ese id no existe.";
    @Autowired
    EstudianteService estudianteService;
    @Autowired
    EstudianteMapper estudianteMapper;
    @MockBean
    private EstudianteDAO estudianteDAO;

    @Test
    @DisplayName("Deberia validar la existencia de un estudiante")
    void deberiaValidarLaExistenciaDeUnEstudiante()throws Exception{
        EstudianteDTO estudianteDTO = new EstudianteTestDataBuilder().conIdEstudiante(84L).build();
        Estudiante estudiante = estudianteMapper.toEntity(estudianteDTO);
        Mockito.when(estudianteDAO.existsById(estudiante.getIdEstudiante())).thenReturn(true);
        Mockito.when(estudianteDAO.findById(estudiante.getIdEstudiante())).thenReturn(Optional.of(estudiante));
        Estudiante estudianteEncontrado = estudianteService.findById(estudiante.getIdEstudiante());
        assertNotNull(estudianteEncontrado);
        assertEquals(estudiante.getIdEstudiante(), estudianteEncontrado.getIdEstudiante());
    }

    @Test
    @DisplayName("Deberia fallar por idEstudiante null")
    void deberiaFallarPorIdEstudianteNull()throws Exception{
        EstudianteDTO estudianteDTO = new EstudianteTestDataBuilder().conIdEstudiante(null).build();
       Exception exception = assertThrows(Exception.class, ()->{
           estudianteService.findById(estudianteDTO.getIdEstudiante());
       });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_EL_ID_DE_UN_ESTUDIANTE);
    }

    @Test
    @DisplayName("Deberia fallar por idEstudiante no existe")
    void deberiaFallarPorIdEstudianteNoExiste()throws Exception{
        EstudianteDTO estudianteDTO = new EstudianteTestDataBuilder().conIdEstudiante(85L).build();
        Mockito.when(estudianteDAO.existsById(estudianteDTO.getIdEstudiante())).thenReturn(false);
        Exception exception = assertThrows(Exception.class, ()->{
            estudianteService.findById(estudianteDTO.getIdEstudiante());
        });
        assertEquals(exception.getMessage(), EL_ESTUDIANTE_CON_ESE_ID_NO_EXISTE);
    }
}
