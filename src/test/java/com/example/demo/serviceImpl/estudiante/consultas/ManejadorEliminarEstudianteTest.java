package com.example.demo.serviceImpl.estudiante.consultas;

import com.example.demo.dao.*;
import com.example.demo.mapper.EstudianteMapper;
import com.example.demo.model.CursoEstudiante;
import com.example.demo.model.dto.EstudianteDTO;
import com.example.demo.service.EstudianteService;
import com.example.demo.serviceImpl.estudiante.testDataBuilder.EstudianteTestDataBuilder;
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
public class ManejadorEliminarEstudianteTest {

    private static final String EL_ID_DEL_ESTUDIANTE_ES_OBLIGATORIO = "El id del estudiante es obligatorio.";
    private static final String NO_SE_ENCONTRO_UN_ESTUDIANTE_CON_ESE_ID = "No se encontró un estudiante con ese id.";
    private static final String NO_SE_PUEDE_ELIMINAR_EL_ESTUDIANTE_PORQUE_SE_ENCUENTRA_ASOCIADO_A_UN_RETO_ESTUDIANTE ="No se puede eliminar el estudiante porque se encuentra asociado a un reto estudiante." ;
    private static final String NO_SE_PUEDE_ELIMINAR_EL_ESTUDIANTE_PORQUE_SE_ENCUENTRA_ASOCIADO_A_UN_ARTICULO_ADQUIRIDO ="No se puede eliminar el estudiante porque se encuentra asociado a un artículo adquirido.";
    private static final String NO_SE_PUEDE_ELIMINAR_EL_ESTUDIANTE_PORQUE_SE_ENCUENTRA_ASOCIADO_A_UN_CURSO_ESTUDIANTE = "No se puede eliminar el estudiante porque se encuentra asociado a un curso estudiante.";
    private static final String NO_SE_PUEDE_ELIMINAR_EL_ESTUDIANTE_PORQUE_SE_ENCUENTRA_ASOCIADO_A_UN_MISION_ESTUDIANTE = "No se puede eliminar el estudiante porque se encuentra asociado a un misión estudiante.";
    private static final String SE_ELIMINO_EXITOSAMENTE_EL_ESTUDIANTE = "Se elimino exitosamente el estudiante.";

    @Autowired
    EstudianteService estudianteService;
    @Autowired
    EstudianteMapper estudianteMapper;
    @MockBean
    private EstudianteDAO estudianteDAO;
    @MockBean
    private CursoEstudianteDAO cursoEstudianteDAO;
    @MockBean
    private MisionEstudianteDAO misionEstudianteDAO;
    @MockBean
    private ArticulosAdquiridosDAO articulosAdquiridosDAO;

    @MockBean
    private RetoEstudianteDAO retoEstudianteDAO;

    @MockBean
    private ProfesorDAO profesorDAO;

    @Test
    @DisplayName("Deberia eliminar exitosamente el estudiante")
    void deberiaEliminaEstudianteExitosamente()throws Exception{
        EstudianteDTO estudianteDTO = new EstudianteTestDataBuilder().conIdEstudiante(2376L).build();
        Mockito.when(estudianteDAO.existsById(estudianteDTO.getIdEstudiante())).thenReturn(true);
        Mockito.when(cursoEstudianteDAO.findByIdEstudiante(estudianteDTO.getIdEstudiante())).thenReturn(Collections.emptyList());
        Mockito.when(misionEstudianteDAO.findByIdEstudiante(estudianteDTO.getIdEstudiante())).thenReturn(Collections.emptyList());
        Mockito.when(articulosAdquiridosDAO.findByIdEstudiante(estudianteDTO.getIdEstudiante())).thenReturn(Collections.emptyList());
        Mockito.when(retoEstudianteDAO.findByIdEstudiante(estudianteDTO.getIdEstudiante())).thenReturn(Collections.emptyList());

        String resultado = estudianteService.eliminar(estudianteDTO.getIdEstudiante());
        Mockito.verify(estudianteDAO, Mockito.times(1)).deleteById(estudianteDTO.getIdEstudiante());
        assertEquals(SE_ELIMINO_EXITOSAMENTE_EL_ESTUDIANTE, resultado);
    }

    @Test
    @DisplayName("Deberia fallar por IdEstudiante null")
    void deberiaFallarPorIdEstudianteNull()throws Exception{
        EstudianteDTO estudianteDTO = new EstudianteTestDataBuilder().conIdEstudiante(null).build();
        Exception exception = assertThrows(Exception.class, ()->{
            estudianteService.eliminar(estudianteDTO.getIdEstudiante());
        });
        assertEquals(exception.getMessage(), EL_ID_DEL_ESTUDIANTE_ES_OBLIGATORIO);
    }

    @Test
    @DisplayName("Deberia fallar por IdEstudiante no existe")
    void deberiaFallarPorIdEstudianteNoExiste()throws Exception{
        EstudianteDTO estudianteDTO = new EstudianteTestDataBuilder().conIdEstudiante(85L).build();
        Mockito.when(estudianteDAO.existsById(estudianteDTO.getIdEstudiante())).thenReturn(false);
        Exception exception = assertThrows(Exception.class, ()->{
            estudianteService.eliminar(estudianteDTO.getIdEstudiante());
        });
        assertEquals(exception.getMessage(), NO_SE_ENCONTRO_UN_ESTUDIANTE_CON_ESE_ID);
    }

    @Test
    @DisplayName("Deberia fallar por IdEstudiante porque esta siendo utilizado por curso estudiante")
    void deberiaFallarPorIdEstudianteEstaSiendoUsadoCursoEstudiante()throws Exception{
        EstudianteDTO estudianteDTO = new EstudianteTestDataBuilder().conIdEstudiante(85L).build();
        Mockito.when(estudianteDAO.existsById(estudianteDTO.getIdEstudiante())).thenReturn(true);
        Mockito.when(cursoEstudianteDAO.findByIdEstudiante(estudianteDTO.getIdEstudiante())).thenReturn(Collections.singletonList(new CursoEstudiante()));
        Exception exception = assertThrows(Exception.class, ()->{
            estudianteService.eliminar(estudianteDTO.getIdEstudiante());
        });
        assertEquals(exception.getMessage(), NO_SE_PUEDE_ELIMINAR_EL_ESTUDIANTE_PORQUE_SE_ENCUENTRA_ASOCIADO_A_UN_CURSO_ESTUDIANTE);
    }


}
