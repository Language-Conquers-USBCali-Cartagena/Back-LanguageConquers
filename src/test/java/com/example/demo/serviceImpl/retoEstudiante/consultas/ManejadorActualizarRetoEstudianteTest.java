package com.example.demo.serviceImpl.retoEstudiante.consultas;

import com.example.demo.dao.EstadoDAO;
import com.example.demo.dao.EstudianteDAO;
import com.example.demo.dao.RetoDAO;
import com.example.demo.dao.RetoEstudianteDAO;
import com.example.demo.mapper.RetoEstudianteMapper;
import com.example.demo.model.Estado;
import com.example.demo.model.Estudiante;
import com.example.demo.model.Reto;
import com.example.demo.model.RetoEstudiante;
import com.example.demo.model.dto.RetoEstudianteDTO;
import com.example.demo.service.RetoEstudianteService;
import com.example.demo.serviceImpl.retoEstudiante.testDataBuilder.RetoEstudianteTestDataBuilder;
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
public class ManejadorActualizarRetoEstudianteTest {
    private static final String RETO_ESTUDIANTE_ACTUALIZADO_EXITOSAMENTE = "Se actualizo exitosamente el reto estudiante";
    private static final String PUNTAJE_NEGATIVO = "No se puede asignar un puntaje negativo al reto estudiante.";
    private static final String ID_ESTADO_REQUERIDO = "Debe ingresar un idEstado.";
    private static final String ID_ESTUDIANTE_REQUERIDO = "Debe ingresar un idEstudiante";
    private static final String ID_RETO_REQUERIDO = "Debe ingresar un idReto.";
    private static final String ID_ESTADO_INVALIDO = "Debe ingresar un idEstado válido";
    private static final String ID_ESTUDIANTE_NO_VALIDO = "Debe ingresar un idEstudiante válido.";
    private static final String NOMBRE_USUARIO_MODIFICACION_LARGO = "El nombre del usuario modificador es muy largo, solo se aceptan 50 caracteres.";
    private static final String DEBE_INGRESAR_FECHA_MODIFICACION = "Debe ingresar una fecha de modificación.";
    private static final String ID_RETO_NO_VALIDO = "Debe ingresar un idReto válido.";
    private static final String FECHA_MODIFICACION_INVALIDA = "No se puede asignar una fecha de modificación que aun no ha sucedido.";
    private static final String TEXTO_MAS_CINCUENTA_CARACTERES = "DGSDGSDSDAGSFDGDFGSDFFGDFSGDFGDFGDFGDFGDFGDFGDSGDFDVSDDSBDSBSDBSVDVSDVSDVSDVSDV";
    private static final Date FECHA_FUTURA= new Date(3500, 12, 12);
    private static final String VACIO = "";
    private static final String DEBE_INGRESAR_NOMBRE_USUARIO_MODIFICADOR = "Debe ingresar el nombre del usuario modificador.";
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
    @DisplayName("Deberia actualizar el retoEstudainte")
    void deberiaActualizarElRetoEstudiante() throws Exception{
        RetoEstudianteDTO retoEstudianteDTO = new RetoEstudianteTestDataBuilder().build();
        RetoEstudiante retoEstudiante = retoEstudianteMapper.toEntity(retoEstudianteDTO);
        Mockito.when(estadoDAO.findById(retoEstudianteDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(retoDAO.findById(retoEstudianteDTO.getIdReto())).thenReturn(Optional.of(new Reto()));
        Mockito.when(estudianteDAO.findById(retoEstudianteDTO.getIdEstudiante())).thenReturn(Optional.of(new Estudiante()));
        Mockito.when(retoEstudianteDAO.save(Mockito.any())).thenReturn(retoEstudiante);
        String crearRetoEstudiante = retoEstudianteService.actualizar(retoEstudiante);
        assertEquals(RETO_ESTUDIANTE_ACTUALIZADO_EXITOSAMENTE, crearRetoEstudiante);
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
            retoEstudianteService.actualizar(retoEstudiante);
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
            retoEstudianteService.actualizar(retoEstudiante);
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
            retoEstudianteService.actualizar(retoEstudiante);
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
            retoEstudianteService.actualizar(retoEstudiante);
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
            retoEstudianteService.actualizar(retoEstudiante);
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
            retoEstudianteService.actualizar(retoEstudiante);
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
            retoEstudianteService.actualizar(retoEstudiante);
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
            retoEstudianteService.actualizar(retoEstudiante);
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
            retoEstudianteService.actualizar(retoEstudiante);
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
            retoEstudianteService.actualizar(retoEstudiante);
        });
        assertEquals(exception.getMessage(), ID_RETO_NO_VALIDO );
    }
    @Test
    @DisplayName("Debe fallar por usuario creado nulo")
    void debeFallarPorUsuarioCreadorNulo() throws Exception{
        RetoEstudianteDTO retoEstudianteDTO = new RetoEstudianteTestDataBuilder().conUsuarioModificador(null).build();
        RetoEstudiante retoEstudiante = retoEstudianteMapper.toEntity(retoEstudianteDTO);
        Mockito.when(estadoDAO.findById(retoEstudianteDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(retoDAO.findById(retoEstudianteDTO.getIdReto())).thenReturn(Optional.of(new Reto()));
        Mockito.when(estudianteDAO.findById(retoEstudianteDTO.getIdEstudiante())).thenReturn(Optional.of(new Estudiante()));
        Mockito.when(retoEstudianteDAO.save(Mockito.any())).thenReturn(retoEstudiante);
        Exception exception = assertThrows(Exception.class, () -> {
            retoEstudianteService.actualizar(retoEstudiante);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_NOMBRE_USUARIO_MODIFICADOR );
    }
    @Test
    @DisplayName("Debe fallar por usuario creado vacio")
    void debeFallarPorUsuarioCreadorVacio() throws Exception{
        RetoEstudianteDTO retoEstudianteDTO = new RetoEstudianteTestDataBuilder().conUsuarioModificador(VACIO).build();
        RetoEstudiante retoEstudiante = retoEstudianteMapper.toEntity(retoEstudianteDTO);
        Mockito.when(estadoDAO.findById(retoEstudianteDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(retoDAO.findById(retoEstudianteDTO.getIdReto())).thenReturn(Optional.of(new Reto()));
        Mockito.when(estudianteDAO.findById(retoEstudianteDTO.getIdEstudiante())).thenReturn(Optional.of(new Estudiante()));
        Mockito.when(retoEstudianteDAO.save(Mockito.any())).thenReturn(retoEstudiante);
        Exception exception = assertThrows(Exception.class, () -> {
            retoEstudianteService.actualizar(retoEstudiante);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_NOMBRE_USUARIO_MODIFICADOR );
    }
    @Test
    @DisplayName("Debe fallar por usuario muchos caracteres")
    void debeFallarPorUsuarioCreadorMuchosCaracteres() throws Exception{
        RetoEstudianteDTO retoEstudianteDTO = new RetoEstudianteTestDataBuilder().conUsuarioModificador(TEXTO_MAS_CINCUENTA_CARACTERES).build();
        RetoEstudiante retoEstudiante = retoEstudianteMapper.toEntity(retoEstudianteDTO);
        Mockito.when(estadoDAO.findById(retoEstudianteDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(retoDAO.findById(retoEstudianteDTO.getIdReto())).thenReturn(Optional.of(new Reto()));
        Mockito.when(estudianteDAO.findById(retoEstudianteDTO.getIdEstudiante())).thenReturn(Optional.of(new Estudiante()));
        Mockito.when(retoEstudianteDAO.save(Mockito.any())).thenReturn(retoEstudiante);
        Exception exception = assertThrows(Exception.class, () -> {
            retoEstudianteService.actualizar(retoEstudiante);
        });
        assertEquals(exception.getMessage(), NOMBRE_USUARIO_MODIFICACION_LARGO );
    }
    @Test
    @DisplayName("Debe fallar por fecha nula")
    void debeFallarPorfechaNula() throws Exception{
        RetoEstudianteDTO retoEstudianteDTO = new RetoEstudianteTestDataBuilder().conFechaModificacion(null).build();
        RetoEstudiante retoEstudiante = retoEstudianteMapper.toEntity(retoEstudianteDTO);
        Mockito.when(estadoDAO.findById(retoEstudianteDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(retoDAO.findById(retoEstudianteDTO.getIdReto())).thenReturn(Optional.of(new Reto()));
        Mockito.when(estudianteDAO.findById(retoEstudianteDTO.getIdEstudiante())).thenReturn(Optional.of(new Estudiante()));
        Mockito.when(retoEstudianteDAO.save(Mockito.any())).thenReturn(retoEstudiante);
        Exception exception = assertThrows(Exception.class, () -> {
            retoEstudianteService.actualizar(retoEstudiante);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_FECHA_MODIFICACION );
    }
    @Test
    @DisplayName("Debe fallar por fecha futura")
    void debeFallarPorfechaFutura() throws Exception{
        RetoEstudianteDTO retoEstudianteDTO = new RetoEstudianteTestDataBuilder().conFechaModificacion(FECHA_FUTURA).build();
        RetoEstudiante retoEstudiante = retoEstudianteMapper.toEntity(retoEstudianteDTO);
        Mockito.when(estadoDAO.findById(retoEstudianteDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(retoDAO.findById(retoEstudianteDTO.getIdReto())).thenReturn(Optional.of(new Reto()));
        Mockito.when(estudianteDAO.findById(retoEstudianteDTO.getIdEstudiante())).thenReturn(Optional.of(new Estudiante()));
        Mockito.when(retoEstudianteDAO.save(Mockito.any())).thenReturn(retoEstudiante);
        Exception exception = assertThrows(Exception.class, () -> {
            retoEstudianteService.actualizar(retoEstudiante);
        });
        assertEquals(exception.getMessage(), FECHA_MODIFICACION_INVALIDA );
    }
}
