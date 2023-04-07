package com.example.demo.serviceImpl.mision.consultas;

import com.example.demo.dao.CursoDAO;
import com.example.demo.dao.MisionDAO;
import com.example.demo.dao.MisionEstudianteDAO;
import com.example.demo.dao.RetoDAO;
import com.example.demo.mapper.MisionMapper;
import com.example.demo.model.MisionEstudiante;
import com.example.demo.model.Reto;
import com.example.demo.model.dto.MisionDTO;
import com.example.demo.service.MisionService;
import com.example.demo.serviceImpl.mision.testDataBuilder.MisionTestDataBuilder;
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
public class ManejadorEliminarMisionTest {
    private static final String LA_MISION_SE_HA_ELIMINADO = "La misión se ha eliminado exitosamente.";
    private static final String SE_DEBE_INGRESAR_EL_ID_MISION ="Se debe ingresar el id de la misión.";
    private static final String LA_MISION_NO_EXISTE = "La misión con id: 321564 no existe.";
    private static final String NO_SE_PUEDE_ELIMINAR_MISION_POR_ESTUDIANTE ="No se puede eliminar la misión porque esta siendo utilizada en un reto estudiante.";
    private static final String NO_SE_PUEDE_ELIMINAR_MISION_POR_RETO= "No se puede eliminar la misión porque esta siendo utilizada en un reto.";
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
    @DisplayName("Debe eliminar la mision")
    void debeEliminarLaMision() throws Exception{
        MisionDTO misionDTO = new MisionTestDataBuilder().build();
        Mockito.when(misionDAO.existsById(misionDTO.getIdMision())).thenReturn(true);
        Mockito.when(misionEstudianteDAO.findByIdMision(misionDTO.getIdMision())).thenReturn(Collections.emptyList());
        Mockito.when(retoDAO.findByIdMision(misionDTO.getIdMision())).thenReturn(Collections.emptyList());
        String resp = misionService.eliminar(misionDTO.getIdMision());
        assertEquals(resp, LA_MISION_SE_HA_ELIMINADO);
    }
    @Test
    @DisplayName("Debe fallar idMision nula")
    void debeFallarIdMisionNula() throws Exception{
        MisionDTO misionDTO = new MisionTestDataBuilder().conIdMision(null).build();
        Mockito.when(misionDAO.existsById(misionDTO.getIdMision())).thenReturn(true);
        Mockito.when(misionEstudianteDAO.findByIdMision(misionDTO.getIdMision())).thenReturn(Collections.emptyList());
        Mockito.when(retoDAO.findByIdMision(misionDTO.getIdMision())).thenReturn(Collections.emptyList());
        Exception exception = assertThrows(Exception.class, () -> {
            misionService.eliminar(misionDTO.getIdMision());
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_EL_ID_MISION);
    }
    @Test
    @DisplayName("Debe fallar mision dao no existe")
    void debeFallarMisionDaoNoExiste() throws Exception{
        MisionDTO misionDTO = new MisionTestDataBuilder().build();
        Mockito.when(misionDAO.existsById(misionDTO.getIdMision())).thenReturn(false);
        Mockito.when(misionEstudianteDAO.findByIdMision(misionDTO.getIdMision())).thenReturn(Collections.emptyList());
        Mockito.when(retoDAO.findByIdMision(misionDTO.getIdMision())).thenReturn(Collections.emptyList());
        Exception exception = assertThrows(Exception.class, () -> {
            misionService.eliminar(misionDTO.getIdMision());
        });
        assertEquals(exception.getMessage(), LA_MISION_NO_EXISTE);
    }
    @Test
    @DisplayName("Debe fallar por estudiante utiliza")
    void debeFallarPorestudianteUtiliza() throws Exception{
        MisionDTO misionDTO = new MisionTestDataBuilder().build();
        Mockito.when(misionDAO.existsById(misionDTO.getIdMision())).thenReturn(true);
        Mockito.when(misionEstudianteDAO.findByIdMision(misionDTO.getIdMision())).thenReturn(Collections.singletonList(new MisionEstudiante()));
        Mockito.when(retoDAO.findByIdMision(misionDTO.getIdMision())).thenReturn(Collections.emptyList());
        Exception exception = assertThrows(Exception.class, () -> {
            misionService.eliminar(misionDTO.getIdMision());
        });
        assertEquals(exception.getMessage(), NO_SE_PUEDE_ELIMINAR_MISION_POR_ESTUDIANTE);
    }
    @Test
    @DisplayName("Debe fallar por reto utiliza")
    void debeFallarPorRetoUtiliza() throws Exception{
        MisionDTO misionDTO = new MisionTestDataBuilder().build();
        Mockito.when(misionDAO.existsById(misionDTO.getIdMision())).thenReturn(true);
        Mockito.when(misionEstudianteDAO.findByIdMision(misionDTO.getIdMision())).thenReturn(Collections.emptyList());
        Mockito.when(retoDAO.findByIdMision(misionDTO.getIdMision())).thenReturn(Collections.singletonList(new Reto()));
        Exception exception = assertThrows(Exception.class, () -> {
            misionService.eliminar(misionDTO.getIdMision());
        });
        assertEquals(exception.getMessage(), NO_SE_PUEDE_ELIMINAR_MISION_POR_RETO);
    }
}
