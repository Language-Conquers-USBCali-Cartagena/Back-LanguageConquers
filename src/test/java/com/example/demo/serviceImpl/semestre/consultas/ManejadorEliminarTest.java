package com.example.demo.serviceImpl.semestre.consultas;

import com.example.demo.dao.EstudianteDAO;
import com.example.demo.dao.SemestreDAO;
import com.example.demo.mapper.SemestreMapper;
import com.example.demo.model.ArticulosAdquiridos;
import com.example.demo.model.Estudiante;
import com.example.demo.model.Semestre;
import com.example.demo.model.dto.ArticulosDTO;
import com.example.demo.model.dto.SemestreDTO;
import com.example.demo.service.SemestreService;
import com.example.demo.serviceImpl.articulos.testDataBuilder.ArticuloTestDataBuilder;
import com.example.demo.serviceImpl.semestre.testDataBuilder.SemestreTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@SpringBootTest
public class ManejadorEliminarTest {
    private static final String SE_ELIMINO_EXITOSAMENTE_EL_SEMESTRE = "Se elimino exitosamente el semestre.";
    private static final String NO_SE_ENCONTRO_EL_SEMESTRE_CON_ESE_ID = "No se encontró el semestre con ese id.";
    private static final String EL_ID_DEL_SEMESTRE_ES_OBLIGATORIO = "El id del semestre es obligatorio.";
    private static final String NO_SE_PUEDE_ELIMINAR_EL_SEMESTRE_PORQUE_ESTA_ASIGNADO_A_UN_ESTUDIANTE = "No se puede eliminar el semestre porque esta asignado a un estudiante.";
    @Autowired
    SemestreService semestreService;
    @Autowired
    SemestreMapper semestreMapper;
    @MockBean
    private SemestreDAO semestreDAO;
    @MockBean
    private EstudianteDAO estudianteDAO;

    @Test
    @DisplayName("Deberia eliminar un logro correctamente")
    void deberiaEliminarUnLogro() throws Exception{
        SemestreDTO semestreDTO = new SemestreTestDataBuilder().build();
        Mockito.when(semestreDAO.existsById(semestreDTO.getIdSemestre())).thenReturn(true);
        Mockito.when(estudianteDAO.findByIdSemestre(semestreDTO.getIdSemestre())).thenReturn(Collections.emptyList());
        String eliminarSemestre = semestreService.eliminar(semestreDTO.getIdSemestre());
        assertEquals(SE_ELIMINO_EXITOSAMENTE_EL_SEMESTRE, eliminarSemestre);
    }
    @Test
    @DisplayName("Deberia fallar porque idSemestre no existe")
    void deberiaFallarPorIdSemestreNoExiste() throws Exception {

        SemestreDTO semestreDTO = new SemestreTestDataBuilder().build();
        Mockito.when(semestreDAO.existsById(semestreDTO.getIdSemestre())).thenReturn(false);
        Exception exception = assertThrows(Exception.class, ()->{
            semestreService.eliminar(semestreDTO.getIdSemestre());
        });
        assertEquals(exception.getMessage(), NO_SE_ENCONTRO_EL_SEMESTRE_CON_ESE_ID);
    }
    @Test
    @DisplayName("Deberia fallar por idSemestre null")
    void deberiaFallarPorIdSemestreNull() throws Exception {

        SemestreDTO semestreDTO = new SemestreTestDataBuilder().conIdSemestre(null).build();
        Exception exception = assertThrows(Exception.class, ()->{
            semestreService.eliminar(semestreDTO.getIdSemestre());
        });
        assertEquals(exception.getMessage(), EL_ID_DEL_SEMESTRE_ES_OBLIGATORIO);
    }
    @Test
    @DisplayName("Deberia fallar cuando idSemestre esta siendo utilizado por un estudiante")
    void deberiaFallarPorIdSemestreUsadoPorEstudiante() throws Exception {

        SemestreDTO semestreDTO = new SemestreTestDataBuilder().build();
        Mockito.when(semestreDAO.existsById(semestreDTO.getIdSemestre())).thenReturn(true);
        Mockito.when(estudianteDAO.findByIdSemestre(semestreDTO.getIdSemestre())).thenReturn(Collections.singletonList(new Estudiante()));
        Exception exception = assertThrows(Exception.class, ()->{
            semestreService.eliminar(semestreDTO.getIdSemestre());
        });
        assertEquals(exception.getMessage(), NO_SE_PUEDE_ELIMINAR_EL_SEMESTRE_PORQUE_ESTA_ASIGNADO_A_UN_ESTUDIANTE);

    }

}
