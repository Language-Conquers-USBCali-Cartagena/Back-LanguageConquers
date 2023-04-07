package com.example.demo.serviceImpl.mision.consultas;

import com.example.demo.dao.CursoDAO;
import com.example.demo.dao.MisionDAO;
import com.example.demo.dao.MisionEstudianteDAO;
import com.example.demo.dao.RetoDAO;
import com.example.demo.mapper.MisionMapper;
import com.example.demo.model.Mision;
import com.example.demo.model.dto.MisionDTO;
import com.example.demo.service.MisionService;
import com.example.demo.serviceImpl.mision.testDataBuilder.MisionTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ManejadorEncontrarPorIdMisionTest {
    private static final String DEBE_INGRESAR_EL_ID_MISION = "Debe ingresar el id de una misión";
    private static final String  LA_MISION_NO_EXISTE= "La misión con id: 321564 no existe.";
    @Autowired
    MisionMapper misionMapper;
    @Autowired
    MisionService misionService;
    @MockBean
    MisionDAO misionDAO;
    @MockBean
    CursoDAO cursoDAO;
    @MockBean
    MisionEstudianteDAO misionEstudianteDAO;
    @MockBean
    RetoDAO retoDAO;

    @Test
    @DisplayName("Deberia listar por id")
    void testDeberiaListarPorId() throws Exception{
        MisionDTO misionDTO = new MisionTestDataBuilder().build();
        Mision mision = misionMapper.toEntity(misionDTO);
        Mockito.when(misionDAO.existsById(misionDTO.getIdMision())).thenReturn(true);
        Mockito.when(misionDAO.findById(misionDTO.getIdMision())).thenReturn(Optional.of(mision));
        Mision resp = misionService.findById(mision.getIdMision());
        assertEquals(resp, mision);
    }
    @Test
    @DisplayName("Deberia fallar id mision nula")
    void testDeberiaFallarIdMisionnula() throws Exception{
        MisionDTO misionDTO = new MisionTestDataBuilder().conIdMision(null).build();
        Mision mision = misionMapper.toEntity(misionDTO);
        Mockito.when(misionDAO.existsById(misionDTO.getIdMision())).thenReturn(true);
        Mockito.when(misionDAO.findById(misionDTO.getIdMision())).thenReturn(Optional.of(mision));
        Exception exception = assertThrows(Exception.class, () ->{
            misionService.findById(mision.getIdMision());
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_EL_ID_MISION);
    }
    @Test
    @DisplayName("Deberia fallar mision no existe")
    void testDeberiaFallarIdMisioNoExiste() throws Exception{
        MisionDTO misionDTO = new MisionTestDataBuilder().build();
        Mision mision = misionMapper.toEntity(misionDTO);
        Mockito.when(misionDAO.existsById(misionDTO.getIdMision())).thenReturn(false);
        Mockito.when(misionDAO.findById(misionDTO.getIdMision())).thenReturn(Optional.of(mision));
        Exception exception = assertThrows(Exception.class, () ->{
            misionService.findById(mision.getIdMision());
        });
        assertEquals(exception.getMessage(), LA_MISION_NO_EXISTE);
    }

}
