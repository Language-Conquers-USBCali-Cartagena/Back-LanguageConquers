package com.example.demo.serviceImpl.rol.consultas;

import com.example.demo.dao.RolDAO;
import com.example.demo.mapper.RolMapper;
import com.example.demo.model.Rol;
import com.example.demo.model.dto.RolDTO;
import com.example.demo.service.RolService;
import com.example.demo.serviceImpl.rol.testdataBuilder.RolTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class ManejadorListarRol {
    @Autowired
    RolService rolService;
    @Autowired
    RolMapper rolMapper;
    @MockBean
    RolDAO rolDAO;

    @Test
    @DisplayName("Deberia listar los roles")
    void deberiaListarroles() throws Exception{
        List<RolDTO> rolDTOS = new ArrayList<>();
        rolDTOS.add(new RolTestDataBuilder().build());
        rolDTOS.add(new RolTestDataBuilder().conIdRol(15L).build());
        List<Rol> rols = rolMapper.toEntityList(rolDTOS);
        Mockito.when(rolDAO.findAll()).thenReturn(rols);
        List<Rol> listaRoles = rolService.listar();
        assertEquals(listaRoles, rols);
    }
}
