package com.example.demo.serviceImpl.grupo.consultas;

import com.example.demo.dao.GrupoDAO;
import com.example.demo.dao.RetoEstudianteDAO;
import com.example.demo.mapper.GrupoMapper;
import com.example.demo.model.Grupo;
import com.example.demo.model.dto.GeneroDTO;
import com.example.demo.model.dto.GrupoDTO;
import com.example.demo.service.GrupoService;
import com.example.demo.serviceImpl.genero.testDataBuilder.GeneroTestDataBuilder;
import com.example.demo.serviceImpl.grupo.testDataBuilder.GrupoTestDataBuilder;
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
public class ManejadorListarGrupoTest {

    @Autowired
    GrupoService grupoService;

    @MockBean
    private GrupoDAO grupoDAO;

    @MockBean
    private RetoEstudianteDAO retoEstudianteDAO;

    @Autowired
    GrupoMapper grupoMapper;

    @Test
    @DisplayName("Deberia listar los grupos existentes")
    void deberiaListarGruposExistentes()throws Exception{
        List<Grupo>  grupoList = new ArrayList<>();
        GrupoDTO grupoDTO1 = new GrupoTestDataBuilder()
                .conIdGrupo(23L)
                .conNombre("Los fantasticos")
                .conUsuarioCreador("Angela")
                .conFechaCreacion(new Date()).build();
        Grupo grupo1 = grupoMapper.toEntity(grupoDTO1);
        grupoList.add(grupo1);
        GrupoDTO grupoDTO2 = new GrupoTestDataBuilder()
                .conIdGrupo(3L)
                .conNombre("Los supervivientes")
                .conUsuarioCreador("Angela")
                .conFechaCreacion(new Date()).build();
        Grupo grupo2 = grupoMapper.toEntity(grupoDTO2);
        grupoList.add(grupo2);
        Mockito.when(grupoDAO.findAll()).thenReturn(grupoList);
        List<Grupo> grupoListado = grupoService.listar();
        assertEquals(grupoList,grupoListado);
    }

    @Test
    @DisplayName("Deberia devolver una lista vacia cuando no hay grupos registrados")
    void deberiaDevolverUnaListaVaciaDeGrupo()throws Exception{
        Mockito.when(grupoDAO.findAll()).thenReturn(new ArrayList<>());
        List<Grupo> grupoList = grupoService.listar();
        assertTrue(grupoList.isEmpty());
    }
}
