package com.example.demo.serviceImpl.genero.consultas;

import com.example.demo.dao.EstudianteDAO;
import com.example.demo.dao.GeneroDAO;
import com.example.demo.dao.ProfesorDAO;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ManejadorListarGeneroTest {

    @Autowired
    GeneroService generoService;
    @MockBean
    private GeneroDAO generoDAO;
    @Autowired
    GeneroMapper generoMapper;

    @Test
    @DisplayName("Deberia listar los generos existentes")
    void deberiaListarGenerosExitosamente() throws Exception {
        List<Genero> generosDePrueba = new ArrayList<>();
        GeneroDTO generoDTO1 = new GeneroTestDataBuilder()
                .conIdGenero(1L)
                .conGenero("Masculino")
                .conFechaCreacion(new Date())
                .conUsuarioCreador("Angela").build();
        Genero genero1 = generoMapper.toEntity(generoDTO1);
        generosDePrueba.add(genero1);
        GeneroDTO generoDTO2 = new GeneroTestDataBuilder()
                .conIdGenero(2L)
                .conGenero("Femenino")
                .conUsuarioCreador("Angela")
                .conFechaCreacion(new Date()).build();
        Genero genero2 = generoMapper.toEntity(generoDTO2);
        generosDePrueba.add(genero2);
        Mockito.when(generoDAO.findAll()).thenReturn(generosDePrueba);
        List<Genero> generosListados = generoService.listar();
        assertEquals(generosDePrueba, generosListados);
    }

    @Test
    @DisplayName("Debería devolver una lista vacia cuando no hay generos")
    void deberiaDevolverListarVaciaCuandoNoHayGeneros() throws Exception {
        Mockito.when(generoDAO.findAll()).thenReturn(new ArrayList<>());
        List<Genero> generosListados = generoService.listar();
        assertTrue(generosListados.isEmpty());
    }
}
