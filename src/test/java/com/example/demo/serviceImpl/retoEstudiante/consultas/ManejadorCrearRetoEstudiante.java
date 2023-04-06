package com.example.demo.serviceImpl.retoEstudiante.consultas;

import com.example.demo.dao.EstadoDAO;
import com.example.demo.dao.EstudianteDAO;
import com.example.demo.dao.RetoDAO;
import com.example.demo.dao.RetoEstudianteDAO;
import com.example.demo.mapper.RetoEstudianteMapper;
import com.example.demo.model.*;
import com.example.demo.model.dto.RetoDTO;
import com.example.demo.model.dto.RetoEstudianteDTO;
import com.example.demo.model.dto.SemestreDTO;
import com.example.demo.service.RetoEstudianteService;
import com.example.demo.serviceImpl.reto.testDataBuilder.RetoTestDataBuilder;
import com.example.demo.serviceImpl.retoEstudiante.testDataBuilder.RetoEstudianteTestDataBuilder;
import com.example.demo.serviceImpl.semestre.testDataBuilder.SemestreTestDataBuilder;
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
public class ManejadorCrearRetoEstudiante {
    public static final String RETO_ESTUDIANTE_CREADO_EXITOSAMENTE = "Se creo exitosamente el reto estudiante!";
    public static final String PUNTAJE_NEGATIVO = "No se puede asignar un puntaje negativo al reto estudiante.";
    public static final String ID_ESTADO_REQUERIDO = "Debe ingresar un idEstado.";
    public static final String ID_ESTUDIANTE_REQUERIDO = "Debe ingresar un idEstudiante";
    public static final String ID_RETO_REQUERIDO = "Debe ingresar un idReto.";
    public static final String ID_ESTADO_INVALIDO = "Debe ingresar un idEstado válido.";
    public static final String ID_ESTUDIANTE_NO_VALIDO = "Debe ingresar un idEstudiante válido.";
    public static final String NOMBRE_USUARIO_CREACION_LARGO = "El nombre del usuario creador es muy largo, solo se aceptan 50 caracteres.";
    public static final String DEBE_INGRESAR_FECHA_CREACION = "Debe ingresar una fecha de creación.";
    public static final String ID_RETO_NO_VALIDO = "Debe ingresar un idReto válido.";
    public static final String FECHA_CREACION_INVALIDA = "No se puede asignar una fecha de creación que aun no ha sucedido.";
    private static final String TEXTO_MAS_CINCUENTA_CARACTERES = "DGSDGSDSDAGSFDGDFGSDFFGDFSGDFGDFGDFGDFGDFGDFGDSGDFDVSDDSBDSBSDBSVDVSDVSDVSDVSDV";
    private static final Date FECHA_FUTURA= new Date(3500, 12, 12);
    private static final String VACIO = "";
    public static final String DEBE_INGRESAR_NOMBRE_USUARIO_CREADOR = "Debe ingresar el nombre del usuario creador.";
    @Autowired
    RetoEstudianteMapper retoEstudianteMapper;
    @Autowired
    RetoEstudianteService retoEstudianteService;
    @MockBean
    RetoEstudianteDAO retoEstudianteDAO;
    @MockBean
    EstadoDAO estadoDAO;
    @MockBean
    EstudianteDAO estudianteDAO;
    @MockBean
    RetoDAO retoDAO;

    @Test
    @DisplayName("Deberia crear el retoEstudainte")
    void deberiaCerarElRetoEstudiante() throws Exception{
        RetoEstudianteDTO retoEstudianteDTO = new RetoEstudianteTestDataBuilder().build();
        RetoEstudiante retoEstudiante = retoEstudianteMapper.toEntity(retoEstudianteDTO);
        Mockito.when(estadoDAO.findById(retoEstudianteDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(retoDAO.findById(retoEstudianteDTO.getIdReto())).thenReturn(Optional.of(new Reto()));
        Mockito.when(estudianteDAO.findById(retoEstudianteDTO.getIdEstudiante())).thenReturn(Optional.of(new Estudiante()));
        Mockito.when(retoEstudianteDAO.save(Mockito.any())).thenReturn(retoEstudiante);
        String crearRetoEstudiante = retoEstudianteService.crearRetoEstudiante(retoEstudiante);
        assertEquals(RETO_ESTUDIANTE_CREADO_EXITOSAMENTE, crearRetoEstudiante);
    }
    @Test
    @DisplayName("Debe fallar por puntaje menor a cero")
    void debeFallarPorPuntajeMenorACero() throws Exception{
        RetoEstudianteDTO retoEstudianteDTO = new RetoEstudianteTestDataBuilder().conPuntaje(-1).build();
        RetoEstudiante retoEstudiante = retoEstudianteMapper.toEntity(retoEstudianteDTO);
        Mockito.when(estadoDAO.findById(retoEstudianteDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(retoDAO.findById(retoEstudianteDTO.getIdReto())).thenReturn(Optional.of(new Reto()));
        Mockito.when(estudianteDAO.findById(retoEstudianteDTO.getIdEstudiante())).thenReturn(Optional.of(new Estudiante()));
        Mockito.when(retoEstudianteDAO.save(Mockito.any())).thenReturn(retoEstudiante);
        Exception exception = assertThrows(Exception.class, () -> {
            retoEstudianteService.crearRetoEstudiante(retoEstudiante);
        });
        assertEquals(exception.getMessage(), PUNTAJE_NEGATIVO);
    }
    @Test
    @DisplayName("Debe fallar por estado nulo")
    void debeFallarPorEstadoNulo() throws Exception{
        RetoEstudianteDTO retoEstudianteDTO = new RetoEstudianteTestDataBuilder().conIdEstado(null).build();
        RetoEstudiante retoEstudiante = retoEstudianteMapper.toEntity(retoEstudianteDTO);
        Mockito.when(estadoDAO.findById(retoEstudianteDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(retoDAO.findById(retoEstudianteDTO.getIdReto())).thenReturn(Optional.of(new Reto()));
        Mockito.when(estudianteDAO.findById(retoEstudianteDTO.getIdEstudiante())).thenReturn(Optional.of(new Estudiante()));
        Mockito.when(retoEstudianteDAO.save(Mockito.any())).thenReturn(retoEstudiante);
        Exception exception = assertThrows(Exception.class, () -> {
            retoEstudianteService.crearRetoEstudiante(retoEstudiante);
        });
        assertEquals(exception.getMessage(), ID_ESTADO_REQUERIDO);
    }
    @Test
    @DisplayName("Debe fallar por estudiante nulo")
    void debeFallarPorEstudianteNulo() throws Exception{
        RetoEstudianteDTO retoEstudianteDTO = new RetoEstudianteTestDataBuilder().conIdEstudiante(null).build();
        RetoEstudiante retoEstudiante = retoEstudianteMapper.toEntity(retoEstudianteDTO);
        Mockito.when(estadoDAO.findById(retoEstudianteDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(retoDAO.findById(retoEstudianteDTO.getIdReto())).thenReturn(Optional.of(new Reto()));
        Mockito.when(estudianteDAO.findById(retoEstudianteDTO.getIdEstudiante())).thenReturn(Optional.of(new Estudiante()));
        Mockito.when(retoEstudianteDAO.save(Mockito.any())).thenReturn(retoEstudiante);
        Exception exception = assertThrows(Exception.class, () -> {
            retoEstudianteService.crearRetoEstudiante(retoEstudiante);
        });
        assertEquals(exception.getMessage(), ID_ESTUDIANTE_REQUERIDO );
    }
    @Test
    @DisplayName("Debe fallar por reto nulo")
    void debeFallarPorRetoNulo() throws Exception{
        RetoEstudianteDTO retoEstudianteDTO = new RetoEstudianteTestDataBuilder().conIdReto(null).build();
        RetoEstudiante retoEstudiante = retoEstudianteMapper.toEntity(retoEstudianteDTO);
        Mockito.when(estadoDAO.findById(retoEstudianteDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(retoDAO.findById(retoEstudianteDTO.getIdReto())).thenReturn(Optional.of(new Reto()));
        Mockito.when(estudianteDAO.findById(retoEstudianteDTO.getIdEstudiante())).thenReturn(Optional.of(new Estudiante()));
        Mockito.when(retoEstudianteDAO.save(Mockito.any())).thenReturn(retoEstudiante);
        Exception exception = assertThrows(Exception.class, () -> {
            retoEstudianteService.crearRetoEstudiante(retoEstudiante);
        });
        assertEquals(exception.getMessage(), ID_RETO_REQUERIDO );
    }
    @Test
    @DisplayName("Debe fallar por idEstado menor a cero")
    void debeFallarPorIdEstadoMenorCero() throws Exception{
        RetoEstudianteDTO retoEstudianteDTO = new RetoEstudianteTestDataBuilder().conIdEstado(-1L).build();
        RetoEstudiante retoEstudiante = retoEstudianteMapper.toEntity(retoEstudianteDTO);
        Mockito.when(estadoDAO.findById(retoEstudianteDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(retoDAO.findById(retoEstudianteDTO.getIdReto())).thenReturn(Optional.of(new Reto()));
        Mockito.when(estudianteDAO.findById(retoEstudianteDTO.getIdEstudiante())).thenReturn(Optional.of(new Estudiante()));
        Mockito.when(retoEstudianteDAO.save(Mockito.any())).thenReturn(retoEstudiante);
        Exception exception = assertThrows(Exception.class, () -> {
            retoEstudianteService.crearRetoEstudiante(retoEstudiante);
        });
        assertEquals(exception.getMessage(), ID_ESTADO_INVALIDO );
    }
    @Test
    @DisplayName("Debe fallar por idEstudiante menor a cero")
    void debeFallarPorIdEstudianteMenorCero() throws Exception{
        RetoEstudianteDTO retoEstudianteDTO = new RetoEstudianteTestDataBuilder().conIdEstudiante(-1L).build();
        RetoEstudiante retoEstudiante = retoEstudianteMapper.toEntity(retoEstudianteDTO);
        Mockito.when(estadoDAO.findById(retoEstudianteDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(retoDAO.findById(retoEstudianteDTO.getIdReto())).thenReturn(Optional.of(new Reto()));
        Mockito.when(estudianteDAO.findById(retoEstudianteDTO.getIdEstudiante())).thenReturn(Optional.of(new Estudiante()));
        Mockito.when(retoEstudianteDAO.save(Mockito.any())).thenReturn(retoEstudiante);
        Exception exception = assertThrows(Exception.class, () -> {
            retoEstudianteService.crearRetoEstudiante(retoEstudiante);
        });
        assertEquals(exception.getMessage(), ID_ESTUDIANTE_NO_VALIDO );
    }
    @Test
    @DisplayName("Debe fallar por idReto menor a cero")
    void debeFallarPorIdREtoMenorCero() throws Exception{
        RetoEstudianteDTO retoEstudianteDTO = new RetoEstudianteTestDataBuilder().conIdReto(-1L).build();
        RetoEstudiante retoEstudiante = retoEstudianteMapper.toEntity(retoEstudianteDTO);
        Mockito.when(estadoDAO.findById(retoEstudianteDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(retoDAO.findById(retoEstudianteDTO.getIdReto())).thenReturn(Optional.of(new Reto()));
        Mockito.when(estudianteDAO.findById(retoEstudianteDTO.getIdEstudiante())).thenReturn(Optional.of(new Estudiante()));
        Mockito.when(retoEstudianteDAO.save(Mockito.any())).thenReturn(retoEstudiante);
        Exception exception = assertThrows(Exception.class, () -> {
            retoEstudianteService.crearRetoEstudiante(retoEstudiante);
        });
        assertEquals(exception.getMessage(), ID_RETO_NO_VALIDO );
    }
    @Test
    @DisplayName("Debe fallar por estado empty")
    void debeFallarPorEstadoEmpty() throws Exception{
        RetoEstudianteDTO retoEstudianteDTO = new RetoEstudianteTestDataBuilder().build();
        RetoEstudiante retoEstudiante = retoEstudianteMapper.toEntity(retoEstudianteDTO);
        Mockito.when(estadoDAO.findById(retoEstudianteDTO.getIdEstado())).thenReturn(Optional.empty());
        Mockito.when(retoDAO.findById(retoEstudianteDTO.getIdReto())).thenReturn(Optional.of(new Reto()));
        Mockito.when(estudianteDAO.findById(retoEstudianteDTO.getIdEstudiante())).thenReturn(Optional.of(new Estudiante()));
        Mockito.when(retoEstudianteDAO.save(Mockito.any())).thenReturn(retoEstudiante);
        Exception exception = assertThrows(Exception.class, () -> {
            retoEstudianteService.crearRetoEstudiante(retoEstudiante);
        });
        assertEquals(exception.getMessage(), ID_ESTADO_INVALIDO );
    }
    @Test
    @DisplayName("Debe fallar por estudiante empty")
    void debeFallarPorEstudianteEmpty() throws Exception{
        RetoEstudianteDTO retoEstudianteDTO = new RetoEstudianteTestDataBuilder().build();
        RetoEstudiante retoEstudiante = retoEstudianteMapper.toEntity(retoEstudianteDTO);
        Mockito.when(estadoDAO.findById(retoEstudianteDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(retoDAO.findById(retoEstudianteDTO.getIdReto())).thenReturn(Optional.of(new Reto()));
        Mockito.when(estudianteDAO.findById(retoEstudianteDTO.getIdEstudiante())).thenReturn(Optional.empty());
        Mockito.when(retoEstudianteDAO.save(Mockito.any())).thenReturn(retoEstudiante);
        Exception exception = assertThrows(Exception.class, () -> {
            retoEstudianteService.crearRetoEstudiante(retoEstudiante);
        });
        assertEquals(exception.getMessage(), ID_ESTUDIANTE_NO_VALIDO );
    }
    @Test
    @DisplayName("Debe fallar por reto empty")
    void debeFallarPorREtoEmpty() throws Exception{
        RetoEstudianteDTO retoEstudianteDTO = new RetoEstudianteTestDataBuilder().build();
        RetoEstudiante retoEstudiante = retoEstudianteMapper.toEntity(retoEstudianteDTO);
        Mockito.when(estadoDAO.findById(retoEstudianteDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(retoDAO.findById(retoEstudianteDTO.getIdReto())).thenReturn(Optional.empty());
        Mockito.when(estudianteDAO.findById(retoEstudianteDTO.getIdEstudiante())).thenReturn(Optional.of(new Estudiante()));
        Mockito.when(retoEstudianteDAO.save(Mockito.any())).thenReturn(retoEstudiante);
        Exception exception = assertThrows(Exception.class, () -> {
            retoEstudianteService.crearRetoEstudiante(retoEstudiante);
        });
        assertEquals(exception.getMessage(), ID_RETO_NO_VALIDO );
    }
    @Test
    @DisplayName("Debe fallar por usuario creado nulo")
    void debeFallarPorUsuarioCreadorNulo() throws Exception{
        RetoEstudianteDTO retoEstudianteDTO = new RetoEstudianteTestDataBuilder().conUsuarioCreador(null).build();
        RetoEstudiante retoEstudiante = retoEstudianteMapper.toEntity(retoEstudianteDTO);
        Mockito.when(estadoDAO.findById(retoEstudianteDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(retoDAO.findById(retoEstudianteDTO.getIdReto())).thenReturn(Optional.of(new Reto()));
        Mockito.when(estudianteDAO.findById(retoEstudianteDTO.getIdEstudiante())).thenReturn(Optional.of(new Estudiante()));
        Mockito.when(retoEstudianteDAO.save(Mockito.any())).thenReturn(retoEstudiante);
        Exception exception = assertThrows(Exception.class, () -> {
            retoEstudianteService.crearRetoEstudiante(retoEstudiante);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_NOMBRE_USUARIO_CREADOR );
    }
    @Test
    @DisplayName("Debe fallar por usuario creado vacio")
    void debeFallarPorUsuarioCreadorVacio() throws Exception{
        RetoEstudianteDTO retoEstudianteDTO = new RetoEstudianteTestDataBuilder().conUsuarioCreador(VACIO).build();
        RetoEstudiante retoEstudiante = retoEstudianteMapper.toEntity(retoEstudianteDTO);
        Mockito.when(estadoDAO.findById(retoEstudianteDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(retoDAO.findById(retoEstudianteDTO.getIdReto())).thenReturn(Optional.of(new Reto()));
        Mockito.when(estudianteDAO.findById(retoEstudianteDTO.getIdEstudiante())).thenReturn(Optional.of(new Estudiante()));
        Mockito.when(retoEstudianteDAO.save(Mockito.any())).thenReturn(retoEstudiante);
        Exception exception = assertThrows(Exception.class, () -> {
            retoEstudianteService.crearRetoEstudiante(retoEstudiante);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_NOMBRE_USUARIO_CREADOR );
    }
    @Test
    @DisplayName("Debe fallar por usuario muchos caracteres")
    void debeFallarPorUsuarioCreadorMuchosCaracteres() throws Exception{
        RetoEstudianteDTO retoEstudianteDTO = new RetoEstudianteTestDataBuilder().conUsuarioCreador(TEXTO_MAS_CINCUENTA_CARACTERES).build();
        RetoEstudiante retoEstudiante = retoEstudianteMapper.toEntity(retoEstudianteDTO);
        Mockito.when(estadoDAO.findById(retoEstudianteDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(retoDAO.findById(retoEstudianteDTO.getIdReto())).thenReturn(Optional.of(new Reto()));
        Mockito.when(estudianteDAO.findById(retoEstudianteDTO.getIdEstudiante())).thenReturn(Optional.of(new Estudiante()));
        Mockito.when(retoEstudianteDAO.save(Mockito.any())).thenReturn(retoEstudiante);
        Exception exception = assertThrows(Exception.class, () -> {
            retoEstudianteService.crearRetoEstudiante(retoEstudiante);
        });
        assertEquals(exception.getMessage(), NOMBRE_USUARIO_CREACION_LARGO );
    }
    @Test
    @DisplayName("Debe fallar por fecha nula")
    void debeFallarPorfechaNula() throws Exception{
        RetoEstudianteDTO retoEstudianteDTO = new RetoEstudianteTestDataBuilder().conFechaCreacion(null).build();
        RetoEstudiante retoEstudiante = retoEstudianteMapper.toEntity(retoEstudianteDTO);
        Mockito.when(estadoDAO.findById(retoEstudianteDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(retoDAO.findById(retoEstudianteDTO.getIdReto())).thenReturn(Optional.of(new Reto()));
        Mockito.when(estudianteDAO.findById(retoEstudianteDTO.getIdEstudiante())).thenReturn(Optional.of(new Estudiante()));
        Mockito.when(retoEstudianteDAO.save(Mockito.any())).thenReturn(retoEstudiante);
        Exception exception = assertThrows(Exception.class, () -> {
            retoEstudianteService.crearRetoEstudiante(retoEstudiante);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_FECHA_CREACION );
    }
    @Test
    @DisplayName("Debe fallar por fecha futura")
    void debeFallarPorfechaFutura() throws Exception{
        RetoEstudianteDTO retoEstudianteDTO = new RetoEstudianteTestDataBuilder().conFechaCreacion(FECHA_FUTURA).build();
        RetoEstudiante retoEstudiante = retoEstudianteMapper.toEntity(retoEstudianteDTO);
        Mockito.when(estadoDAO.findById(retoEstudianteDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(retoDAO.findById(retoEstudianteDTO.getIdReto())).thenReturn(Optional.of(new Reto()));
        Mockito.when(estudianteDAO.findById(retoEstudianteDTO.getIdEstudiante())).thenReturn(Optional.of(new Estudiante()));
        Mockito.when(retoEstudianteDAO.save(Mockito.any())).thenReturn(retoEstudiante);
        Exception exception = assertThrows(Exception.class, () -> {
            retoEstudianteService.crearRetoEstudiante(retoEstudiante);
        });
        assertEquals(exception.getMessage(), FECHA_CREACION_INVALIDA );
    }
}
