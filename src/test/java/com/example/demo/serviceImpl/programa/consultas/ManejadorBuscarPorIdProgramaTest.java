package com.example.demo.serviceImpl.programa.consultas;

import com.example.demo.dao.ProgramaDAO;
import com.example.demo.mapper.ProgramaMapper;
import com.example.demo.model.Programa;
import com.example.demo.model.dto.ProgramaDTO;
import com.example.demo.service.ProgramaService;
import com.example.demo.serviceImpl.programa.testDataBuilder.ProgramaTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
public class ManejadorBuscarPorIdProgramaTest {

    private static final String DEBE_INGRESAR_EL_ID_DEL_PROGRAMA="Debe ingresar el id del programa.";
    private static final String NO_SEENCONTRO_EL_PROGRAMA_CON_ESE_ID = "No se encontró el programa con ese id.";
    @Autowired
    ProgramaService programaService;

    @MockBean
    private ProgramaDAO programaDAO;
    @Autowired
    ProgramaMapper programaMapper;

    @Test
    @DisplayName("Deberia validar la existencia de un programa")
    void deberiaValidarExistenciaDeUnPrograma()throws Exception{

        ProgramaDTO programaDTO = new ProgramaTestDataBuilder().conIdPrograma(1348857854L).build();
        Programa programa = programaMapper.toEntity(programaDTO);
        Mockito.when(programaDAO.existsById(programaDTO.getIdPrograma())).thenReturn(true);
        Mockito.when(programaDAO.findById(programaDTO.getIdPrograma())).thenReturn(Optional.of(programa));
        Programa programaEncontrado = programaService.findById(programa.getIdPrograma());
        assertNotNull(programaEncontrado);
        assertEquals(programa.getIdPrograma(), programaEncontrado.getIdPrograma());
    }
    @Test
    @DisplayName("Deberia lanzar si el idPrograma es null ")
    void deberiaFallarPorIdProgramaNull()throws Exception{

        ProgramaDTO programaDTO = new ProgramaTestDataBuilder().conIdPrograma(null).build();
        Exception exception = assertThrows(Exception.class, ()->{
            programaService.findById(programaDTO.getIdPrograma());
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_EL_ID_DEL_PROGRAMA);
    }
    @Test
    @DisplayName("Deberia lanzar si el idPrograma no existe")
    void deberiaFallarPorIdProgramaNoExiste()throws Exception{

        ProgramaDTO programaDTO = new ProgramaTestDataBuilder().conIdPrograma(386L).build();
        Mockito.when(programaDAO.existsById(programaDTO.getIdPrograma())).thenReturn(false);
        Exception exception = assertThrows(Exception.class, ()->{
            programaService.findById(programaDTO.getIdPrograma());
        });
        assertEquals(exception.getMessage(), NO_SEENCONTRO_EL_PROGRAMA_CON_ESE_ID);
    }

}
