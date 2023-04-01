package com.example.demo.serviceImpl.estado.consultas;

import com.example.demo.dao.*;
import com.example.demo.mapper.EstadoMapper;
import com.example.demo.model.*;
import com.example.demo.model.dto.EstadoDTO;
import com.example.demo.service.EstadoService;
import com.example.demo.serviceImpl.estado.testDataBuilder.EstadoTestDataBuilder;
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
public class ManejadorEliminarEstadoTest {

    public static final String SE_ELIMINO_EXITOSAMENTE_EL_RETO = "Se elimino exitosamente el estado.";
    public static final String EL_ID_DEL_ESTADO_ES_OBLIGATORIO = "El id del estado es obligatorio.";
    public static final String NO_SE_ENCONTRO_EL_ESTADO_CON_ESE_ID = "No se encontró el estado con ese id.";
    public static final String NO_SE_PUEDE_ELIMINAR_EL_ESTADO_PORQUE_ESTA_SIENDO_UTILIZADO_EN_UNA_CATEGORIA ="No se puede eliminar el estado porque esta siendo utilizado en una categoría.";
    public static final String NO_SE_PUEDE_ELIMINAR_EL_ESTADO_PORQUE_ESTA_SIENDO_UTILIZADO_EN_UNA_MISION_ESTUDIANTE = "No se puede eliminar el estado porque esta siendo utilizado en misión estudiante.";
    public static final String NO_SE_PUEDE_ELIMINAR_EL_ESTADO_PORQUE_ESTA_SIENDO_UTILIZADO_EN_UN_RETO = "No se puede eliminar el estado porque esta siendo utilizado en un reto.";
    public static final String NO_SE_PUEDE_ELIMINAR_EL_ESTADO_PORQUE_ESTA_SIENDO_UTILIZADO_EN_UN_CURSO = "No se puede eliminar el estado porque esta siendo utilizado en un curso.";
    public static final String NO_SE_PUEDE_ELIMINAR_EL_ESTADO_PORQUE_ESTA_SIENDO_UTILIZADO_EN_UN_ESTUDIANTE ="No se puede eliminar el estado porque esta siendo utilizado en un estudiante.";
    public static final String NO_SE_PUEDE_ELIMINAR_EL_ESTADO_PORQUE_ESTA_SIENDO_UTILIZADO_EN_UN_RETO_ESTUDIANTE ="No se puede eliminar el estado porque esta siendo utilizado en un reto estudiante.";
    public static final String NO_SE_PUEDE_ELIMINAR_EL_ESTADO_PORQUE_ESTA_SIENDO_UTILIZADO_EN_UN_ARTICULO = "No se puede eliminar el estado porque esta siendo utilizado en un artículo.";

    @Autowired
    EstadoService estadoService;

    @MockBean
    private EstadoDAO estadoDAO;

    @MockBean
    private CategoriaDAO categoriaDAO;

    @MockBean
    private MisionEstudianteDAO misionEstudianteDAO;

    @MockBean
    private RetoDAO retoDAO;

    @MockBean
    private CursoDAO cursoDAO;

    @MockBean
    private EstudianteDAO estudianteDAO;

    @MockBean
    private RetoEstudianteDAO retoEstudianteDAO;

    @MockBean
    private ArticulosDAO articulosDAO;

    @Autowired
    EstadoMapper estadoMapper;

    @Test
    @DisplayName("Deberia elminiar exitosamente el estado")
    void deberiaEliminarElEstadoExitosamente()throws Exception{

        EstadoDTO estadoDTO = new EstadoTestDataBuilder().conIdEstado(2787L).build();
        Mockito.when(estadoDAO.existsById(estadoDTO.getIdEstado())).thenReturn(true);
        Mockito.when(categoriaDAO.findByIdEstado(estadoDTO.getIdEstado())).thenReturn(Collections.emptyList());
        Mockito.when(misionEstudianteDAO.findByIdEstado(estadoDTO.getIdEstado())).thenReturn(Collections.emptyList());
        Mockito.when(retoDAO.findByIdEstado(estadoDTO.getIdEstado())).thenReturn(Collections.emptyList());
        Mockito.when(cursoDAO.findByIdEstado(estadoDTO.getIdEstado())).thenReturn(Collections.emptyList());
        Mockito.when(estudianteDAO.findByIdEstado(estadoDTO.getIdEstado())).thenReturn(Collections.emptyList());
        Mockito.when(retoEstudianteDAO.findByIdEstado(estadoDTO.getIdEstado())).thenReturn(Collections.emptyList());
        Mockito.when(articulosDAO.findByIdEstado(estadoDTO.getIdEstado())).thenReturn(Collections.emptyList());
        String estadoEliminado = estadoService.eliminar(estadoDTO.getIdEstado());
        Mockito.verify(estadoDAO, Mockito.times(1)).deleteById(estadoDTO.getIdEstado());
        assertEquals(SE_ELIMINO_EXITOSAMENTE_EL_RETO, estadoEliminado);
    }

    @Test
    @DisplayName("Deberia falla por idEstado nulo")
    void deberiaFallarPorIdEstadoNulo()throws Exception{
        EstadoDTO estadoDTO = new EstadoTestDataBuilder().conIdEstado(null).build();
        Exception exception = assertThrows(Exception.class, ()->{
            estadoService.eliminar(estadoDTO.getIdEstado());
        });
        assertEquals(exception.getMessage(), EL_ID_DEL_ESTADO_ES_OBLIGATORIO);
    }
    @Test
    @DisplayName("Deberia falla por idEstado no existente")
    void deberiaFallarPorIdEstadoNoExistente()throws Exception{
        EstadoDTO estadoDTO = new EstadoTestDataBuilder().conIdEstado(548545L).build();
        Mockito.when(estadoDAO.existsById(estadoDTO.getIdEstado())).thenReturn(false);
        Exception exception = assertThrows(Exception.class, ()->{
            estadoService.eliminar(estadoDTO.getIdEstado());
        });
        assertEquals(exception.getMessage(), NO_SE_ENCONTRO_EL_ESTADO_CON_ESE_ID);
    }

    @Test
    @DisplayName("Deberia falla cuando idEstado esta siendo utilizado en una categoría")
    void deberiaFallarPorIdEstadoUsadoPorCategoria()throws Exception{
        EstadoDTO estadoDTO = new EstadoTestDataBuilder().conIdEstado(2787L).build();
        Mockito.when(estadoDAO.existsById(estadoDTO.getIdEstado())).thenReturn(true);
        Mockito.when(categoriaDAO.findByIdEstado(estadoDTO.getIdEstado())).thenReturn(Collections.singletonList(new Categoria()));
        Exception exception = assertThrows(Exception.class, ()->{
            estadoService.eliminar(estadoDTO.getIdEstado());
        });
        assertEquals(exception.getMessage(), NO_SE_PUEDE_ELIMINAR_EL_ESTADO_PORQUE_ESTA_SIENDO_UTILIZADO_EN_UNA_CATEGORIA);
    }

    @Test
    @DisplayName("Deberia falla cuando idEstado esta siendo utilizado en una misión estudiante")
    void deberiaFallarPorIdEstadoUsadoPorMisionEstudiante()throws Exception{
        EstadoDTO estadoDTO = new EstadoTestDataBuilder().conIdEstado(2787L).build();
        Mockito.when(estadoDAO.existsById(estadoDTO.getIdEstado())).thenReturn(true);
        Mockito.when(misionEstudianteDAO.findByIdEstado(estadoDTO.getIdEstado())).thenReturn(Collections.singletonList(new MisionEstudiante()));
        Exception exception = assertThrows(Exception.class, ()->{
            estadoService.eliminar(estadoDTO.getIdEstado());
        });
        assertEquals(exception.getMessage(), NO_SE_PUEDE_ELIMINAR_EL_ESTADO_PORQUE_ESTA_SIENDO_UTILIZADO_EN_UNA_MISION_ESTUDIANTE);
    }

    @Test
    @DisplayName("Deberia falla cuando idEstado esta siendo utilizado en un reto")
    void deberiaFallarPorIdEstadoUsadoPorReto()throws Exception{
        EstadoDTO estadoDTO = new EstadoTestDataBuilder().conIdEstado(2787L).build();
        Mockito.when(estadoDAO.existsById(estadoDTO.getIdEstado())).thenReturn(true);
        Mockito.when(retoDAO.findByIdEstado(estadoDTO.getIdEstado())).thenReturn(Collections.singletonList(new Reto()));
        Exception exception = assertThrows(Exception.class, ()->{
            estadoService.eliminar(estadoDTO.getIdEstado());
        });
        assertEquals(exception.getMessage(), NO_SE_PUEDE_ELIMINAR_EL_ESTADO_PORQUE_ESTA_SIENDO_UTILIZADO_EN_UN_RETO);
    }

    @Test
    @DisplayName("Deberia falla cuando idEstado esta siendo utilizado en un curso")
    void deberiaFallarPorIdEstadoUsadoPorCurso()throws Exception{
        EstadoDTO estadoDTO = new EstadoTestDataBuilder().conIdEstado(2787L).build();
        Mockito.when(estadoDAO.existsById(estadoDTO.getIdEstado())).thenReturn(true);
        Mockito.when(cursoDAO.findByIdEstado(estadoDTO.getIdEstado())).thenReturn(Collections.singletonList(new Curso()));
        Exception exception = assertThrows(Exception.class, ()->{
            estadoService.eliminar(estadoDTO.getIdEstado());
        });
        assertEquals(exception.getMessage(), NO_SE_PUEDE_ELIMINAR_EL_ESTADO_PORQUE_ESTA_SIENDO_UTILIZADO_EN_UN_CURSO);
    }

    @Test
    @DisplayName("Deberia falla cuando idEstado esta siendo utilizado en un estudiante")
    void deberiaFallarPorIdEstadoUsadoPorEstudiante()throws Exception{
        EstadoDTO estadoDTO = new EstadoTestDataBuilder().conIdEstado(2787L).build();
        Mockito.when(estadoDAO.existsById(estadoDTO.getIdEstado())).thenReturn(true);
        Mockito.when(estudianteDAO.findByIdEstado(estadoDTO.getIdEstado())).thenReturn(Collections.singletonList(new Estudiante()));
        Exception exception = assertThrows(Exception.class, ()->{
            estadoService.eliminar(estadoDTO.getIdEstado());
        });
        assertEquals(exception.getMessage(), NO_SE_PUEDE_ELIMINAR_EL_ESTADO_PORQUE_ESTA_SIENDO_UTILIZADO_EN_UN_ESTUDIANTE);
    }

    @Test
    @DisplayName("Deberia falla cuando idEstado esta siendo utilizado en un reto estudiante")
    void deberiaFallarPorIdEstadoUsadoPorRetoEstudiante()throws Exception{
        EstadoDTO estadoDTO = new EstadoTestDataBuilder().conIdEstado(2787L).build();
        Mockito.when(estadoDAO.existsById(estadoDTO.getIdEstado())).thenReturn(true);
        Mockito.when(retoEstudianteDAO.findByIdEstado(estadoDTO.getIdEstado())).thenReturn(Collections.singletonList(new RetoEstudiante()));
        Exception exception = assertThrows(Exception.class, ()->{
            estadoService.eliminar(estadoDTO.getIdEstado());
        });
        assertEquals(exception.getMessage(), NO_SE_PUEDE_ELIMINAR_EL_ESTADO_PORQUE_ESTA_SIENDO_UTILIZADO_EN_UN_RETO_ESTUDIANTE);
    }

    @Test
    @DisplayName("Deberia falla cuando idEstado esta siendo utilizado en un articulo")
    void deberiaFallarPorIdEstadoUsadoPorArticulo()throws Exception{
        EstadoDTO estadoDTO = new EstadoTestDataBuilder().conIdEstado(2787L).build();
        Mockito.when(estadoDAO.existsById(estadoDTO.getIdEstado())).thenReturn(true);
        Mockito.when(articulosDAO.findByIdEstado(estadoDTO.getIdEstado())).thenReturn(Collections.singletonList(new Articulos()));
        Exception exception = assertThrows(Exception.class, ()->{
            estadoService.eliminar(estadoDTO.getIdEstado());
        });
        assertEquals(exception.getMessage(), NO_SE_PUEDE_ELIMINAR_EL_ESTADO_PORQUE_ESTA_SIENDO_UTILIZADO_EN_UN_ARTICULO);
    }
}
