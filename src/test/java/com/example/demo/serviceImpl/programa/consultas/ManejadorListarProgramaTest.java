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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ManejadorListarProgramaTest {

    @Autowired
    ProgramaService programaService;

    @MockBean
    private ProgramaDAO programaDAO;
    @Autowired
    ProgramaMapper programaMapper;

    @Test
    @DisplayName("Deberia listar los programas exitosamente")
    void deberiaListarProgramasExitosamente() throws Exception {
        List<Programa> programaList = new ArrayList<>();
        ProgramaDTO programaDTO1 = new ProgramaTestDataBuilder()
                .conIdPrograma(1l)
                .conNombre("Ingenieria Multimedia")
                .conUsuarioCreador("Angela")
                .conFechaCreacion(new Date())
                .build();
        Programa programa1 = programaMapper.toEntity(programaDTO1);
        programaList.add(programa1);
        ProgramaDTO programaDTO2 = new ProgramaTestDataBuilder()
                .conIdPrograma(2l)
                .conNombre("Ingenieria Biomedica")
                .conUsuarioCreador("Angela")
                .conFechaCreacion(new Date())
                .build();
        Programa programa2 = programaMapper.toEntity(programaDTO2);
        programaList.add(programa2);
        Mockito.when(programaDAO.findAll()).thenReturn(programaList);
        List<Programa> programaListados = programaService.listar();
        assertEquals(programaList, programaListados);

    }

    @Test
    @DisplayName("Deberia devolver una lista vacia cuando no hay programas")
    void deberiaDevolverListaVaciaCuandoNoHayProgramas()throws Exception{
        Mockito.when(programaDAO.findAll()).thenReturn(new ArrayList<>());
        List<Programa> programaList = programaService.listar();
        assertTrue(programaList.isEmpty());
    }
}
