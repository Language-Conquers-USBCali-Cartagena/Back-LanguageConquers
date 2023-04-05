package com.example.demo.serviceImpl.grupo.consultas;

import com.example.demo.dao.GrupoDAO;
import com.example.demo.dao.RetoEstudianteDAO;
import com.example.demo.mapper.GrupoMapper;
import com.example.demo.model.RetoEstudiante;
import com.example.demo.model.dto.GrupoDTO;
import com.example.demo.service.GrupoService;
import com.example.demo.serviceImpl.grupo.testDataBuilder.GrupoTestDataBuilder;
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
public class ManejadorEliminarGrupoTest {

    private static final String SE_ELIMINO_EXITOSAMENTE_EL_GRUPO = "Se elimino exitosamente el grupo.";
    private static final String EL_ID_DEL_GRUPO_ES_OBLIGATORIO = "El id del grupo es obligatorio.";
    private static final String NO_SE_ENCONTRO_UN_GRUPO_CON_ESE_ID = "No se encontró un grupo con ese id.";
    private static final String NO_SE_PUEDE_ELIMINAR_EL_GRUPO_YA_QUE_ESTA_SIENDO_UTILIZADO_POR_UN_RETO_ESTUDIANTE = "No se puede eliminar el grupo ya que esta asignado a un reto estudiante.";

    @Autowired
    GrupoService grupoService;

    @MockBean
    private GrupoDAO grupoDAO;

    @MockBean
    private RetoEstudianteDAO retoEstudianteDAO;

    @Autowired
    GrupoMapper grupoMapper;

    @Test
    @DisplayName("Deberia eliminar exitosamente un grupo")
    void deberiaEliminarGrupoExitosamente()throws Exception{
        GrupoDTO grupoDTO = new GrupoTestDataBuilder().conIdGrupo(783787L).build();
        Mockito.when(grupoDAO.existsById(grupoDTO.getIdGrupo())).thenReturn(true);
        Mockito.when(retoEstudianteDAO.findByIdGrupo(grupoDTO.getIdGrupo())).thenReturn(Collections.emptyList());
        String eliminarGrupo = grupoService.eliminar(grupoDTO.getIdGrupo());
        Mockito.verify(grupoDAO, Mockito.times(1)).deleteById(grupoDTO.getIdGrupo());
        assertEquals(SE_ELIMINO_EXITOSAMENTE_EL_GRUPO, eliminarGrupo);
    }

    @Test
    @DisplayName("Deberia fallar por idGrupo null")
    void deberiaFallarPorIdGrupoNull()throws Exception{
        GrupoDTO grupoDTO = new GrupoTestDataBuilder().conIdGrupo(null).build();
        Exception exception = assertThrows(Exception.class, ()->{
            grupoService.eliminar(grupoDTO.getIdGrupo());
        });
        assertEquals(exception.getMessage(), EL_ID_DEL_GRUPO_ES_OBLIGATORIO);
    }
    @Test
    @DisplayName("Deberia fallar por idGrupo No Existente ")
    void deberiaFallarPorIdGrupoNoExistente()throws Exception{
        GrupoDTO grupoDTO = new GrupoTestDataBuilder().conIdGrupo(87637L).build();
        Mockito.when(grupoDAO.existsById(grupoDTO.getIdGrupo())).thenReturn(false);
        Exception exception = assertThrows(Exception.class, ()->{
            grupoService.eliminar(grupoDTO.getIdGrupo());
        });
        assertEquals(exception.getMessage(), NO_SE_ENCONTRO_UN_GRUPO_CON_ESE_ID);
    }

    @Test
    @DisplayName("Deberia fallar por idGrupo usado en reto estudiante ")
    void deberiaFallarPorIdGrupoUtilizadoRetoEsudiante()throws Exception{
        GrupoDTO grupoDTO = new GrupoTestDataBuilder().conIdGrupo(87637L).build();
        Mockito.when(grupoDAO.existsById(grupoDTO.getIdGrupo())).thenReturn(true);
        Mockito.when(retoEstudianteDAO.findByIdGrupo(grupoDTO.getIdGrupo())).thenReturn(Collections.singletonList(new RetoEstudiante()));
        Exception exception = assertThrows(Exception.class, ()->{
            grupoService.eliminar(grupoDTO.getIdGrupo());
        });
        assertEquals(exception.getMessage(), NO_SE_PUEDE_ELIMINAR_EL_GRUPO_YA_QUE_ESTA_SIENDO_UTILIZADO_POR_UN_RETO_ESTUDIANTE);
    }
}
