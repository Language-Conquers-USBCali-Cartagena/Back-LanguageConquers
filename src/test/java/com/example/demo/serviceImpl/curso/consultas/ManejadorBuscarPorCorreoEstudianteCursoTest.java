package com.example.demo.serviceImpl.curso.consultas;

import com.example.demo.dao.CursoDAO;
import com.example.demo.dao.ProfesorDAO;
import com.example.demo.mapper.CursoMapper;
import com.example.demo.model.Curso;
import com.example.demo.model.dto.CursoDTO;
import com.example.demo.service.CursoService;
import com.example.demo.serviceImpl.curso.testDataBuilder.CursoTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
public class ManejadorBuscarPorCorreoEstudianteCursoTest {

    @Autowired
    CursoService cursoService;
    @Autowired
    CursoMapper cursoMapper;
    @MockBean
    private CursoDAO cursoDAO;
    @MockBean
    private ProfesorDAO profesorDAO;

    @Test
    @DisplayName("Deberia devolver el curso")
    void deberiaDelvorlverCursoPorEstudiante()throws Exception{
        String correoEstudiante = "correo@ejemplo.com";
        CursoDTO cursoDTO = new CursoTestDataBuilder().build();
        Curso curso = cursoMapper.toEntity(cursoDTO);
        Mockito.when(cursoDAO.findByCorreoEstudiante(correoEstudiante)).thenReturn(Collections.singletonList(curso));
        List<Curso> resultado = cursoService.findByCorreoEstudiante(correoEstudiante);
        assertEquals(Collections.singletonList(curso), resultado);

    }

}
