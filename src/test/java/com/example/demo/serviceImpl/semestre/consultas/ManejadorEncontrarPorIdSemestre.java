package com.example.demo.serviceImpl.semestre.consultas;

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
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@SpringBootTest
public class ManejadorEncontrarPorIdSemestre {
    private static final String DEBE_INGRESAR_ID_SEMESTRE = "Debe ingresar el id de un semestre.";
    private static final String NO_EXISTE_SEMESTRE_CON_ID = "El semestre con id: 15 no existe.";
    @Autowired
    SemestreService semestreService;
    @Autowired
    SemestreMapper semestreMapper;
    @MockBean
    private SemestreDAO semestreDAO;

    @Test
    @DisplayName("Deberia listar semestres")
    void deberiaActualizarUnSemestre() throws Exception{
        SemestreDTO semestreDTO = new SemestreTestDataBuilder().build();
        Semestre semestre = semestreMapper.toEntity(semestreDTO);
        Mockito.when(semestreDAO.existsById(semestre.getIdSemestre())).thenReturn(true);
        Mockito.when(semestreDAO.findById(semestre.getIdSemestre())).thenReturn(Optional.of(semestre));
        Semestre buscarSemestre= semestreService.findById(semestre.getIdSemestre());
        assertEquals(semestre, buscarSemestre);

    }
    @Test
    @DisplayName("Deberia fallar por id semestre nulo")
    void deberiaFallarPorIdSemestrenulo() throws Exception{
        SemestreDTO semestreDTO = new SemestreTestDataBuilder().conIdSemestre(null).build();
        Semestre semestre = semestreMapper.toEntity(semestreDTO);
        Mockito.when(semestreDAO.existsById(semestre.getIdSemestre())).thenReturn(true);
        Mockito.when(semestreDAO.findById(semestre.getIdSemestre())).thenReturn(Optional.of(semestre));
        Exception exception = assertThrows(Exception.class, ()->{
            semestreService.findById(semestre.getIdSemestre());
        });
        assertEquals(DEBE_INGRESAR_ID_SEMESTRE, exception.getMessage());
    }
    @Test
    @DisplayName("Deberia fallar por id semestre no existe")
    void deberiaFallarPorIdSemestreNoExiste() throws Exception{
        SemestreDTO semestreDTO = new SemestreTestDataBuilder().conIdSemestre(15L).build();
        Semestre semestre = semestreMapper.toEntity(semestreDTO);
        Mockito.when(semestreDAO.existsById(semestre.getIdSemestre())).thenReturn(false);
        Mockito.when(semestreDAO.findById(semestre.getIdSemestre())).thenReturn(Optional.of(semestre));
        Exception exception = assertThrows(Exception.class, ()->{
            semestreService.findById(semestre.getIdSemestre());
        });
        assertEquals(NO_EXISTE_SEMESTRE_CON_ID, exception.getMessage());
    }

}
