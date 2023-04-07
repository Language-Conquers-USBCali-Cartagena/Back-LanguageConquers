package com.example.demo.serviceImpl.rol.consultas;

import com.example.demo.dao.RetoDAO;
import com.example.demo.dao.RetoEstudianteDAO;
import com.example.demo.dao.RolDAO;
import com.example.demo.mapper.RolMapper;
import com.example.demo.model.RetoEstudiante;
import com.example.demo.model.dto.RolDTO;
import com.example.demo.service.RolService;
import com.example.demo.serviceImpl.rol.testdataBuilder.RolTestDataBuilder;
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
public class ManejadorEliminarRolTest {
    private static final String EL_ID_DEL_ROL_ES_OBLIGATORIO = "El id del rol es obligatorio.";
    private static final String NO_SE_ENCONTRO_EL_ROL_CON_ESE_ID = "No se encontró el rol con ese id.";
    private static final String NO_SE_PUEDE_ELIMINAR_EL_ROL_PORQUE_ESTA_ASIGNADO_EN_UN_RETO_ESTUDIANTE = "No se puede eliminar el rol porque esta asignado en un reto estudiante.";
    private static final String SE_ELIMINO_EXITOSAMENTE_EL_ROL = "Se elimino exitosamente el rol.";

    @Autowired
    RolService rolService;
    @Autowired
    RolMapper rolMapper;
    @MockBean
    RolDAO rolDAO;
    @MockBean
    RetoDAO retoDAO;
    @MockBean
    RetoEstudianteDAO retoEstudianteDAO;
    @Test
    @DisplayName("Se deberia eliminar el rol")
    void seDeberiaEliminarElRol() throws Exception{
        RolDTO rolDTO = new RolTestDataBuilder().build();
        Mockito.when(rolDAO.existsById(rolDTO.getIdRol())).thenReturn(true);
        Mockito.when(retoEstudianteDAO.findByIdRol(rolDTO.getIdRol())).thenReturn(Collections.emptyList());
        String eliminarSemestre = rolService.eliminar(rolDTO.getIdRol());
        assertEquals(SE_ELIMINO_EXITOSAMENTE_EL_ROL, eliminarSemestre);
    }
    @Test
    @DisplayName("Deberia fallar si idRol es nulo")
    void deberiaFallarPorIdRolnulo() throws Exception {

        RolDTO rolDTO = new RolTestDataBuilder().conIdRol(null).build();
        Mockito.when(rolDAO.existsById(rolDTO.getIdRol())).thenReturn(true);
        Mockito.when(retoEstudianteDAO.findByIdRol(rolDTO.getIdRol())).thenReturn(Collections.emptyList());
        Exception exception = assertThrows(Exception.class, ()->{
            rolService.eliminar(rolDTO.getIdRol());
        });
        assertEquals(EL_ID_DEL_ROL_ES_OBLIGATORIO, exception.getMessage());
    }
    @Test
    @DisplayName("Deberia fallar si el reto esta asignado a un reto estudiante")
    void deberiaFallarPorRetoAdignadoRetoEstudiante() throws Exception {

        RolDTO rolDTO = new RolTestDataBuilder().build();
        Mockito.when(rolDAO.existsById(rolDTO.getIdRol())).thenReturn(true);
        Mockito.when(retoEstudianteDAO.findByIdRol(rolDTO.getIdRol())).thenReturn(Collections.singletonList(new RetoEstudiante()));
        Exception exception = assertThrows(Exception.class, ()->{
            rolService.eliminar(rolDTO.getIdRol());
        });
        assertEquals(NO_SE_PUEDE_ELIMINAR_EL_ROL_PORQUE_ESTA_ASIGNADO_EN_UN_RETO_ESTUDIANTE, exception.getMessage());
    }
    @Test
    @DisplayName("Deberia fallar si id rol no existe")
    void deberiaFallarPorIdRolNoExiste() throws Exception {

        RolDTO rolDTO = new RolTestDataBuilder().build();
        Mockito.when(rolDAO.existsById(rolDTO.getIdRol())).thenReturn(false);
        Mockito.when(retoEstudianteDAO.findByIdRol(rolDTO.getIdRol())).thenReturn(Collections.singletonList(new RetoEstudiante()));
        Exception exception = assertThrows(Exception.class, ()->{
            rolService.eliminar(rolDTO.getIdRol());
        });
        assertEquals(NO_SE_ENCONTRO_EL_ROL_CON_ESE_ID, exception.getMessage());
    }



}
