package com.example.demo.serviceImpl.credenciales.consultas;

import com.example.demo.dao.CredencialesDAO;
import com.example.demo.mapper.CredencialesMapper;
import com.example.demo.model.Credenciales;
import com.example.demo.model.dto.CredencialesDTO;
import com.example.demo.service.CredencialesService;
import com.example.demo.serviceImpl.credenciales.testDataBuilder.CredencialesTestDataBuilder;
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
public class ManejadorListarCredencialesTest {
    @Autowired
    CredencialesService credencialesService;

    @Autowired
    CredencialesMapper credencialesMapper;

    @MockBean
    private CredencialesDAO credencialesDAO;

    @Test
    @DisplayName("Deberia listar las credenciales existentes")
    void deberliaListarCredencialesExistentes()throws Exception{
        List<Credenciales> credencialesList = new ArrayList<>();
        CredencialesDTO credencialesDTO1 = new CredencialesTestDataBuilder()
                .conCuenta("angela26")
                .conPassword("76236")
                .conPlataforma("heroku")
                .conUrl("http://heroku.com")
                .conUsuarioCreador("Angela")
                .conFechaCreacion(new Date())
                .build();
        Credenciales credenciales1 = credencialesMapper.toEntity(credencialesDTO1);
        credencialesList.add(credenciales1);
        CredencialesDTO credencialesDTO2 = new CredencialesTestDataBuilder()
                .conCuenta("edede")
                .conPassword("dffdre4")
                .conPlataforma("heroku")
                .conUrl("http://heroku.com")
                .conUsuarioCreador("Angela")
                .conFechaCreacion(new Date())
                .build();
        Credenciales credenciales2 = credencialesMapper.toEntity(credencialesDTO2);
        credencialesList.add(credenciales2);
        Mockito.when(credencialesDAO.findAll()).thenReturn(credencialesList);
        List<Credenciales> credenciales = credencialesService.listar();
        assertEquals(credencialesList, credenciales);

    }

    @Test
    @DisplayName("Deberia retorna una lista vacia cuando no hay credenciales")
    void deberiaRetornarListaVaciaCredencias()throws Exception{
        Mockito.when(credencialesDAO.findAll()).thenReturn(new ArrayList<>());
        List<Credenciales>credencialesList = credencialesService.listar();
        assertTrue(credencialesList.isEmpty());
    }
}
