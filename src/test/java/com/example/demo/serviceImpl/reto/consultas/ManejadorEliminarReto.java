package com.example.demo.serviceImpl.reto.consultas;

import com.example.demo.dao.*;
import com.example.demo.mapper.RetoMapper;
import com.example.demo.model.RetoEstudiante;
import com.example.demo.model.Rol;
import com.example.demo.model.dto.RetoDTO;
import com.example.demo.model.dto.SemestreDTO;
import com.example.demo.service.RetoService;
import com.example.demo.serviceImpl.reto.testDataBuilder.RetoTestDataBuilder;
import com.example.demo.serviceImpl.semestre.testDataBuilder.SemestreTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@SpringBootTest
public class ManejadorEliminarReto {
    public static final String RETO_ELIMINADO_EXITOSAMENTE = "El reto se elimino exitosamente.";
    public static final String NO_SE_PUEDE_ELIMINAR_RETO_EN_USO = "No se puede eliminar el reto porque está siendo utilizado por un reto estudiante.";
    public static final String ID_RETO_NO_EXISTE = "El id del reto no existe.";
    public static final String RETO_NO_ENCONTRADO = "No se encontró un reto con el id: 15.";
    public static final String RETO_EN_USO_POR_ROL = "No se puede eliminar el reto porque está siendo utilizado por un rol.";



    @Autowired
    RetoService retoService;
    @Autowired
    RetoMapper retoMapper;
    @MockBean
    RetoDAO retoDAO;
    @MockBean
    RetoEstudianteDAO retoEstudianteDAO;
    @MockBean
    RolDAO rolDAO;

    @Test
    @DisplayName("Deberia eliminar el reto")
    void deberiaEliminarelReto() throws Exception{
        Long idReto = 15L;
        Mockito.when(retoDAO.existsById(idReto)).thenReturn(true);
        Mockito.when(retoEstudianteDAO.findByIdReto(idReto)).thenReturn(Collections.emptyList());
        Mockito.when(rolDAO.findByIdReto(idReto)).thenReturn(Collections.emptyList());
        String eliminarReto = retoService.eliminar(idReto);
        assertEquals(RETO_ELIMINADO_EXITOSAMENTE, eliminarReto );
    }

    @Test
    @DisplayName("Deberia fallar por id reto nullo")
    void deberiaFallarPorIdretoNulo() throws Exception {
        RetoDTO retoDTO = new RetoTestDataBuilder().conIdReto(null).build();
        Mockito.when(retoDAO.existsById(retoDTO.getIdReto())).thenReturn(true);
        Mockito.when(retoEstudianteDAO.findByIdReto(retoDTO.getIdReto())).thenReturn(Collections.emptyList());
        Mockito.when(rolDAO.findByIdReto(retoDTO.getIdReto())).thenReturn(Collections.emptyList());
        Exception exception = assertThrows(Exception.class, ()->{
            retoService.eliminar(retoDTO.getIdReto());
        });
        assertEquals(exception.getMessage(), ID_RETO_NO_EXISTE);
    }
    @Test
    @DisplayName("Deberia fallar por id reto no existe")
    void deberiaFallarPorIdretoNoExiste() throws Exception {
        RetoDTO retoDTO = new RetoTestDataBuilder().conIdReto(15L).build();
        Mockito.when(retoDAO.existsById(retoDTO.getIdReto())).thenReturn(false);
        Mockito.when(retoEstudianteDAO.findByIdReto(retoDTO.getIdReto())).thenReturn(Collections.emptyList());
        Mockito.when(rolDAO.findByIdReto(retoDTO.getIdReto())).thenReturn(Collections.emptyList());
        Exception exception = assertThrows(Exception.class, ()->{
            retoService.eliminar(retoDTO.getIdReto());
        });
        assertEquals(exception.getMessage(), RETO_NO_ENCONTRADO );
    }
    @Test
    @DisplayName("Deberia fallar por reto utilizado por reto estudiante")
    void deberiaFallarPorRetoUtilizadoRetoEstudiante() throws Exception {
        RetoDTO retoDTO = new RetoTestDataBuilder().build();
        Mockito.when(retoDAO.existsById(retoDTO.getIdReto())).thenReturn(true);
        Mockito.when(retoEstudianteDAO.findByIdReto(retoDTO.getIdReto())).thenReturn(Collections.singletonList(new RetoEstudiante()));
        Mockito.when(rolDAO.findByIdReto(retoDTO.getIdReto())).thenReturn(Collections.emptyList());
        Exception exception = assertThrows(Exception.class, ()->{
            retoService.eliminar(retoDTO.getIdReto());
        });
        assertEquals(exception.getMessage(), NO_SE_PUEDE_ELIMINAR_RETO_EN_USO );
    }
    @Test
    @DisplayName("Deberia fallar por reto utilizado por rol")
    void deberiaFallarPorRetoUtilizadoRol() throws Exception {
        RetoDTO retoDTO = new RetoTestDataBuilder().build();
        Mockito.when(retoDAO.existsById(retoDTO.getIdReto())).thenReturn(true);
        Mockito.when(retoEstudianteDAO.findByIdReto(retoDTO.getIdReto())).thenReturn(Collections.emptyList());
        Mockito.when(rolDAO.findByIdReto(retoDTO.getIdReto())).thenReturn(Collections.singletonList(new Rol()));
        Exception exception = assertThrows(Exception.class, ()->{
            retoService.eliminar(retoDTO.getIdReto());
        });
        assertEquals(exception.getMessage(), RETO_EN_USO_POR_ROL );
    }
}
