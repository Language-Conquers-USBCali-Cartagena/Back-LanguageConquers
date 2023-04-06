package com.example.demo.serviceImpl.semestre.consultas;

import com.example.demo.dao.EstudianteDAO;
import com.example.demo.dao.SemestreDAO;
import com.example.demo.mapper.SemestreMapper;
import com.example.demo.model.Semestre;
import com.example.demo.model.dto.SemestreDTO;
import com.example.demo.service.SemestreService;
import com.example.demo.serviceImpl.semestre.testDataBuilder.SemestreTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class ManejadorListarSemestre {

    @Autowired
    SemestreService semestreService;
    @Autowired
    SemestreMapper semestreMapper;
    @MockBean
    private SemestreDAO semestreDAO;


    @Test
    @DisplayName("Deberia listar semestres")
    void deberiaActualizarUnSemestre() throws Exception{
        List<SemestreDTO> semestreDTOS = new ArrayList<>();
        semestreDTOS.add(new SemestreTestDataBuilder().build());
        semestreDTOS.add(new SemestreTestDataBuilder().conIdSemestre(2L).build());
        List<Semestre> semestres = semestreMapper.toEntityList(semestreDTOS);
        Mockito.when(semestreDAO.findAll()).thenReturn(semestres);
        List<Semestre> listarSemestres= semestreService.listar();
        assertEquals(semestres, listarSemestres);

    }
}
