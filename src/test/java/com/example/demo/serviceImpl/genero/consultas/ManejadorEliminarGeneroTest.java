package com.example.demo.serviceImpl.genero.consultas;

import com.example.demo.dao.EstudianteDAO;
import com.example.demo.dao.GeneroDAO;
import com.example.demo.dao.ProfesorDAO;
import com.example.demo.mapper.GeneroMapper;
import com.example.demo.model.Estudiante;
import com.example.demo.model.Genero;
import com.example.demo.model.Profesor;
import com.example.demo.model.dto.EstudianteDTO;
import com.example.demo.model.dto.GeneroDTO;
import com.example.demo.service.GeneroService;
import com.example.demo.serviceImpl.genero.testDataBuilder.GeneroTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@SpringBootTest
public class ManejadorEliminarGeneroTest {

    public static final String SE_ELIMINO_EXITOSAMENTE_EL_GENERO ="Se elimino exitosamente el genero.";
    public static final String EL_ID_DEL_GENERO_ES_OBLIGATORIO ="El id del genero es obligatorio.";
    public static final String NO_SE_ENCONTRO_EL_GENERO_CON_ESE_ID ="No se encontró el genero con ese id.";
    public static final String NO_SE_PUEDE_ELIMINAR_EL_GENERO_PORQUE_ESTA_SIENDO_UTILIZADO_POR_UN_ESTUDIANTE = "No se puede eliminar el genero porque esta siendo utilizado por un estudiante.";
    public static final String NO_SE_PUEDE_ELIMINAR_EL_GENERO_PORQUE_ESTA_SIENDO_UTILIZADO_POR_UN_PROFESOR = "No se puede eliminar el genero porque esta siendo utilizado por un profesor.";
    @Autowired
    GeneroService generoService;
    @MockBean
    private GeneroDAO generoDAO;

    @MockBean
    private EstudianteDAO estudianteDAO;

    @MockBean
    private ProfesorDAO profesorDAO;
    @Autowired
    GeneroMapper generoMapper;

    @Test
    @DisplayName("Debería eliminar un genero exitosamente")
    void deberiaEliminarUnGeneroExitosamente()throws Exception{
        GeneroDTO generoDTO = new GeneroTestDataBuilder().conIdGenero(4343858L).build();
        Mockito.when(generoDAO.existsById(generoDTO.getIdGenero())).thenReturn(true);
        Mockito.when(estudianteDAO.findByIdGenero(generoDTO.getIdGenero())).thenReturn(Collections.emptyList());
        Mockito.when(profesorDAO.findByIdGenero(generoDTO.getIdGenero())).thenReturn(Collections.emptyList());
        String resultado = generoService.eliminar(generoDTO.getIdGenero());
        Mockito.verify(generoDAO, Mockito.times(1)).deleteById(generoDTO.getIdGenero());
        assertEquals(SE_ELIMINO_EXITOSAMENTE_EL_GENERO, resultado);
    }

    @Test
    @DisplayName("Debería fallar por idGenero nulo ")
    void deberiaFallarPorIdGeneroNull()throws Exception{
        GeneroDTO generoDTO = new GeneroTestDataBuilder().conIdGenero(null).build();
        Exception exception = assertThrows(Exception.class,() ->{
            generoService.eliminar(generoDTO.getIdGenero());
        });
        assertEquals(exception.getMessage(), EL_ID_DEL_GENERO_ES_OBLIGATORIO);
    }

    @Test
    @DisplayName("Debería fallar cuando no existe el idGenero")
    void deberiaFallarPorIdGeneroNoExistente()throws Exception{
        GeneroDTO generoDTO = new GeneroTestDataBuilder().conIdGenero(4374465L).build();
        Mockito.when(generoDAO.existsById(generoDTO.getIdGenero())).thenReturn(false);
        Exception exception = assertThrows(Exception.class,() ->{
            generoService.eliminar(generoDTO.getIdGenero());
        });
        assertEquals(exception.getMessage(), NO_SE_ENCONTRO_EL_GENERO_CON_ESE_ID);
    }

    @Test
    @DisplayName("Debería fallar cuando el idGenero esta siendo utilizado por un estudiante")
    void deberiaFallarPorIdGeneroUsadoPorEstudiante()throws Exception{
        GeneroDTO generoDTO = new GeneroTestDataBuilder().conIdGenero( 4343858L).build();
        Mockito.when(generoDAO.existsById(generoDTO.getIdGenero())).thenReturn(true);
        Mockito.when(estudianteDAO.findByIdGenero(generoDTO.getIdGenero())).thenReturn(Collections.singletonList(new Estudiante()));
        Exception exception = assertThrows(Exception.class,() ->{
            generoService.eliminar(generoDTO.getIdGenero());
        });
        assertEquals(exception.getMessage(), NO_SE_PUEDE_ELIMINAR_EL_GENERO_PORQUE_ESTA_SIENDO_UTILIZADO_POR_UN_ESTUDIANTE);
    }

    @Test
    @DisplayName("Debería fallar cuando el idGenero esta siendo utilizado por un profesor")
    void deberiaFallarPorIdGeneroUsadoPorProfesor()throws Exception{
        GeneroDTO generoDTO = new GeneroTestDataBuilder().conIdGenero( 4343858L).build();
        Mockito.when(generoDAO.existsById(generoDTO.getIdGenero())).thenReturn(true);
        Mockito.when(profesorDAO.findByIdGenero(generoDTO.getIdGenero())).thenReturn(Collections.singletonList(new Profesor()));
        Exception exception = assertThrows(Exception.class,() ->{
            generoService.eliminar(generoDTO.getIdGenero());
        });
        assertEquals(exception.getMessage(), NO_SE_PUEDE_ELIMINAR_EL_GENERO_PORQUE_ESTA_SIENDO_UTILIZADO_POR_UN_PROFESOR);
    }
}
