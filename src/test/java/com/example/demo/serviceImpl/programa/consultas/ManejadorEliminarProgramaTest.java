package com.example.demo.serviceImpl.programa.consultas;

import com.example.demo.dao.EstudianteDAO;
import com.example.demo.dao.ProgramaDAO;
import com.example.demo.mapper.ProgramaMapper;
import com.example.demo.model.Estudiante;
import com.example.demo.model.dto.ProgramaDTO;
import com.example.demo.service.ProgramaService;
import com.example.demo.serviceImpl.programa.testDataBuilder.ProgramaTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@SpringBootTest
public class ManejadorEliminarProgramaTest {

    private static final String SE_ELIMINO_EXITOSAMENTE_EL_PROGRAMA = "Se elimino exitosamente el programa.";
    private static final String DEBE_INGRESAR_EL_ID_DEL_PROGRAMA = "Debe ingresar el id del programa.";
    public static final String NO_SE_ENCONTRO_EL_PROGRAMA_CON_ESE_ID = "No se encontró el programa con ese id.";
    public static final String NO_SE_PUEDE_ELIMINAR_EL_PROGRAMA_PORQUE_ESTA_SIENDO_UTILIZADO_POR_UN_ESTUDIANTE = "No se puede eliminar el programa porque esta siendo utilizado en un estudiante.";

    @Autowired
    ProgramaService programaService;

    @MockBean
    private ProgramaDAO programaDAO;
    @MockBean
    private EstudianteDAO estudianteDAO;

    @Autowired
    ProgramaMapper programaMapper;

    @Test
    @DisplayName("Deberia eliminar exitosamente el programa")
    void deberiaEliminarExitosamenteElPrograma()throws Exception{
        ProgramaDTO programaDTO = new ProgramaTestDataBuilder().conIdPrograma(1348857854L).build();
        Mockito.when(programaDAO.existsById(programaDTO.getIdPrograma())).thenReturn(true);
        Mockito.when(estudianteDAO.findByIdPrograma(programaDTO.getIdPrograma())).thenReturn(Collections.emptyList());
        String eliminarPrograma = programaService.eliminar(programaDTO.getIdPrograma());
        Mockito.verify(programaDAO, Mockito.times(1)).deleteById(programaDTO.getIdPrograma());
        assertEquals(SE_ELIMINO_EXITOSAMENTE_EL_PROGRAMA, eliminarPrograma);
    }

    @Test
    @DisplayName("Deberia fallar por idPrograma null")
    void deberiaFallarPorIdProgramaNull()throws Exception{
        ProgramaDTO programaDTO = new ProgramaTestDataBuilder().conIdPrograma(null).build();
        Exception exception = assertThrows(Exception.class, ()->{
            programaService.eliminar(programaDTO.getIdPrograma());
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_EL_ID_DEL_PROGRAMA);
    }

    @Test
    @DisplayName("Deberia fallar cuando no existe el idPrograma")
    void deberiaFallarPorIdProgramaNoExistente()throws Exception{
        ProgramaDTO programaDTO = new ProgramaTestDataBuilder().conIdPrograma(748L).build();
        Mockito.when(programaDAO.existsById(programaDTO.getIdPrograma())).thenReturn(false);
        Exception exception = assertThrows(Exception.class, ()->{
            programaService.eliminar(programaDTO.getIdPrograma());
        });
        assertEquals(exception.getMessage(), NO_SE_ENCONTRO_EL_PROGRAMA_CON_ESE_ID);
    }

    @Test
    @DisplayName("Deberia fallar cuando se esta intentando eliminar un programa que esta siendo utilizado por un estudiante")
    void deberiaFallarPorIdProgramaUtilizadoPorEstudiante()throws Exception{
        ProgramaDTO programaDTO = new ProgramaTestDataBuilder().conIdPrograma(1348857854L).build();
        Mockito.when(programaDAO.existsById(programaDTO.getIdPrograma())).thenReturn(true);
        Mockito.when(estudianteDAO.findByIdPrograma(programaDTO.getIdPrograma())).thenReturn(Collections.singletonList(new Estudiante()));
        Exception exception = assertThrows(Exception.class, ()->{
            programaService.eliminar(programaDTO.getIdPrograma());
        });
        assertEquals(exception.getMessage(), NO_SE_PUEDE_ELIMINAR_EL_PROGRAMA_PORQUE_ESTA_SIENDO_UTILIZADO_POR_UN_ESTUDIANTE);
    }

}
