package com.example.demo.serviceImpl.genero.consultas;

import com.example.demo.dao.GeneroDAO;
import com.example.demo.mapper.GeneroMapper;
import com.example.demo.model.Genero;
import com.example.demo.model.dto.GeneroDTO;
import com.example.demo.service.GeneroService;
import com.example.demo.serviceImpl.genero.testDataBuilder.GeneroTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
public class ManejadorBuscarPorIdGeneroTest {

    @Autowired
    GeneroService generoService;

    @MockBean
    private GeneroDAO generoDAO;
    @Autowired
    GeneroMapper generoMapper;

    @Test
    @DisplayName("Debería validar la existencia de un genero")
    void deberiaValidarLaExistenciaDeUnGenero()throws Exception{

        GeneroDTO generoDTO = new GeneroTestDataBuilder().conIdGenero(4343858L).build();

        Genero genero = generoMapper.toEntity(generoDTO);
        Mockito.when(generoDAO.existsById(4343858L)).thenReturn(true);
        Mockito.when(generoDAO.findById(generoDTO.getIdGenero())).thenReturn(Optional.of(genero));
        Genero servicioGenero = generoService.findById(genero.getIdGenero());
        assertNotNull(servicioGenero);
        assertEquals(genero.getIdGenero(), servicioGenero.getIdGenero());

    }

    @Test
    @DisplayName("Debería lanzar una excepción si el genero es nulo")
    void deberiaFallarSiIdGeneroEsNull()throws Exception{

        GeneroDTO generoDTO = new GeneroTestDataBuilder().conIdGenero(null).build();
        Exception exception = assertThrows(Exception.class, () ->{
            generoService.findById(generoDTO.getIdGenero());
        });
        assertEquals(exception.getMessage(), "Debe ingresar el id de un genero.");

    }

    @Test
    @DisplayName("Deberia lanzar una escepcion si el genero no existe")
    void deberiaFallarSiIdGeneroNoExiste()throws Exception{

        GeneroDTO generoDTO = new GeneroTestDataBuilder().conIdGenero(45L).build();
        Exception exception = assertThrows(Exception.class, () ->{
            generoService.findById(generoDTO.getIdGenero());
        });
        assertEquals(exception.getMessage(), "El genero con id: " + generoDTO.getIdGenero() + " no existe.");

    }
}
