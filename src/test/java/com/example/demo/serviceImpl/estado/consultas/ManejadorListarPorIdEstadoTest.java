package com.example.demo.serviceImpl.estado.consultas;

import com.example.demo.dao.EstadoDAO;
import com.example.demo.mapper.EstadoMapper;
import com.example.demo.model.Estado;
import com.example.demo.model.dto.EstadoDTO;
import com.example.demo.service.EstadoService;
import com.example.demo.serviceImpl.estado.testDataBuilder.EstadoTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@SpringBootTest
public class ManejadorListarPorIdEstadoTest {

    public static final String DEBE_INGRESAR_EL_ID_DE_UN_ESTADO = "Debe ingresar el id de un estado.";
    public static final String EL_ESTADO_CON_EL_ID_INGRESADO_NO_EXISTE = "El estado con el id ingresado no existe.";

    @Autowired
    EstadoService estadoService;

    @MockBean
    private EstadoDAO estadoDAO;

    @Autowired
    EstadoMapper estadoMapper;

    @Test
    @DisplayName("Debería devolver el estado exitosamente")
    void deberiaDevolverEstadoExitosamente()throws Exception{
        EstadoDTO estadoDTO = new EstadoTestDataBuilder().conIdEstado(2787L).build();
        Estado estado = estadoMapper.toEntity(estadoDTO);
        Mockito.when(estadoDAO.existsById(estado.getIdEstado())).thenReturn(true);
        Mockito.when(estadoDAO.findById(estado.getIdEstado())).thenReturn(Optional.of(estado));
        Estado estadoAMostrar = estadoService.findById(estado.getIdEstado());
        assertEquals(estadoDTO.getIdEstado(),estadoAMostrar.getIdEstado());
    }
    @Test
    @DisplayName("Debería fallar por id null")
    void deberiaFallarPorIdNull()throws Exception{
        EstadoDTO estadoDTO = new EstadoTestDataBuilder().conIdEstado(null).build();
        Exception exception = assertThrows(Exception.class, ()->{
            estadoService.findById(estadoDTO.getIdEstado());
        });
        assertEquals(exception.getMessage(),DEBE_INGRESAR_EL_ID_DE_UN_ESTADO);
    }

    @Test
    @DisplayName("Debería fallar por id no existente")
    void deberiaFallarPorIdEstadoNoExistente()throws Exception{
        EstadoDTO estadoDTO = new EstadoTestDataBuilder().conIdEstado(7343L).build();
        Mockito.when(estadoDAO.existsById(estadoDTO.getIdEstado())).thenReturn(false);
        Exception exception = assertThrows(Exception.class, ()->{
            estadoService.findById(estadoDTO.getIdEstado());
        });
        assertEquals(exception.getMessage(),EL_ESTADO_CON_EL_ID_INGRESADO_NO_EXISTE);
    }
}
