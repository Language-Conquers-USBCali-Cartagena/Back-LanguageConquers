package com.example.demo.serviceImpl.reto.consultas;

import com.example.demo.dao.EstudianteDAO;
import com.example.demo.dao.RetoDAO;
import com.example.demo.dao.RetoEstudianteDAO;
import com.example.demo.mapper.RetoEstudianteMapper;
import com.example.demo.mapper.RetoMapper;
import com.example.demo.model.Reto;
import com.example.demo.model.RetoEstudiante;
import com.example.demo.model.dto.RetoDTO;
import com.example.demo.model.dto.RetoEstudianteDTO;
import com.example.demo.service.RetoService;
import com.example.demo.serviceImpl.reto.testDataBuilder.RetoTestDataBuilder;
import com.example.demo.serviceImpl.retoEstudiante.testDataBuilder.RetoEstudianteTestDataBuilder;
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
import static org.junit.Assert.assertThrows;

@SpringBootTest
public class ManejadorRetosPorEstudiante {
    private static final Long ID_ESTUDIANTE = 15L;
    public static final String ID_ESTUDIANTE_NO_EXISTE = "El id del estudiante no existe en la bd.";

    @Autowired
    RetoService retoService;
    @Autowired
    RetoMapper retoMapper;
    @Autowired
    RetoEstudianteMapper retoEstudianteMapper;
    @MockBean
    RetoDAO retoDAO;
    @MockBean
    EstudianteDAO estudianteDAO;
    @MockBean
    RetoEstudianteDAO retoEstudianteDAO;
    @Test
    @DisplayName("Deberia listar retos por estudiante")
    void deberiaListarRetosPorEstudiante() throws Exception{
        List<RetoDTO> retoDTOS = new ArrayList<>();
        retoDTOS.add(new RetoTestDataBuilder().conIdReto(14L).build());
        retoDTOS.add(new RetoTestDataBuilder().conIdReto(15L).build());
        List<Reto> retos = retoMapper.toEntityList(retoDTOS);
        List<RetoEstudianteDTO> retoEstudianteDTOs = new ArrayList<>();
        retoEstudianteDTOs.add(new RetoEstudianteTestDataBuilder().conIdReto(14L).build());
        retoEstudianteDTOs.add(new RetoEstudianteTestDataBuilder().conIdReto(15L).build());
        List<RetoEstudiante> retoEstudianteList = retoEstudianteMapper.toEntityList(retoEstudianteDTOs);
        List<RetoDTO> retosDTOs = new ArrayList<>();
        for (int i = 0; i< retos.size(); i++) {
            RetoDTO retoDTO = new RetoDTO(retos.get(i), retoEstudianteList.get(i));
            retosDTOs.add(retoDTO);
        }
        Mockito.when(estudianteDAO.existsById(ID_ESTUDIANTE)).thenReturn(true);
        Mockito.when(retoDAO.existsById(ID_ESTUDIANTE)).thenReturn(true);
        Mockito.when(retoEstudianteDAO.findByIdEstudiante(ID_ESTUDIANTE)).thenReturn(retoEstudianteList);
        Mockito.when(retoDAO.retoEstudiante(ID_ESTUDIANTE)).thenReturn(retos);
        List<RetoDTO> retoDTOList = retoService.retosPorEstudiante(ID_ESTUDIANTE);
        assertEquals(retosDTOs.get(1).getIdReto(), retoDTOList.get(1).getIdReto());
    }

    @Test
    @DisplayName("Deberia fallar por el estudiante no existe")
    void deberiaFallarPorElEstudianteNoExiste() throws Exception{
        List<RetoDTO> retoDTOS = new ArrayList<>();
        retoDTOS.add(new RetoTestDataBuilder().conIdReto(14L).build());
        retoDTOS.add(new RetoTestDataBuilder().conIdReto(15L).build());
        List<Reto> retos = retoMapper.toEntityList(retoDTOS);
        List<RetoEstudianteDTO> retoEstudianteDTOs = new ArrayList<>();
        retoEstudianteDTOs.add(new RetoEstudianteTestDataBuilder().conIdReto(14L).build());
        retoEstudianteDTOs.add(new RetoEstudianteTestDataBuilder().conIdReto(15L).build());
        List<RetoEstudiante> retoEstudianteList = retoEstudianteMapper.toEntityList(retoEstudianteDTOs);
        List<RetoDTO> retosDTOs = new ArrayList<>();
        for (int i = 0; i< retos.size(); i++) {
            RetoDTO retoDTO = new RetoDTO(retos.get(i), retoEstudianteList.get(i));
            retosDTOs.add(retoDTO);
        }
        Mockito.when(estudianteDAO.existsById(ID_ESTUDIANTE)).thenReturn(false);
        Mockito.when(retoDAO.existsById(ID_ESTUDIANTE)).thenReturn(true);
        Mockito.when(retoEstudianteDAO.findByIdEstudiante(ID_ESTUDIANTE)).thenReturn(retoEstudianteList);
        Mockito.when(retoDAO.retoEstudiante(ID_ESTUDIANTE)).thenReturn(retos);

        Exception exception = assertThrows(Exception.class, () -> {
            retoService.retosPorEstudiante(ID_ESTUDIANTE);
        });
        assertEquals(ID_ESTUDIANTE_NO_EXISTE, exception.getMessage());
    }
}
