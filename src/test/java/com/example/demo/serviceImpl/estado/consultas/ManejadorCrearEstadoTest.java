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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@SpringBootTest
public class ManejadorCrearEstadoTest {

    public static final String SE_REGISTRO_EL_ESTADO_EXITOSAMENTE = "Se registro el estado exitosamente.";
    public static final String SE_DEBE_INGRESAR_UN_ESTADO = "Se debe ingresar un estado.";
    public static final String SE_DEBE_INGRESAR_UN_ESTADO_VALIDO_NO_DEBE_SUPERAR_LOS_50_CARACTERES = "Se debe ingresar un estado válido, no debe superar los 50 caracteres.";
    public static final String SE_DEBE_INGRESAR_UN_USUARIO_CREADOR_VALIDO = "Se debe ingresar un usuario creador válido.";
    public static final String DEBE_INGRESAR_UNA_FECHA_DE_CREACION ="Debe ingresar una fecha de creación.";
    public static final String NO_PUEDE_INGRESAR_UNA_FECHA_QUE_AUN_NO_HA_SUCEDIDO = "No puede ingresar una fecha que aun no ha sucedido.";
    public static final String ESTADO_LARGO = "relfufirerkerejferewjdjhwejfhjejfjhdloisdusdndfdfid";
    public static final String USUARIO_CREADOR_LARGO = "relfufirerkerejferewjdjhwejfhjejfjhdloisdusdndfdfid";
    @Autowired
    EstadoService estadoService;

    @MockBean
    private EstadoDAO estadoDAO;

    @Autowired
    EstadoMapper estadoMapper;

    @Test
    @DisplayName("Debería crear exitosamente un estado")
    void deberiaCreaUnEstadoExitosamente()throws Exception{

        EstadoDTO estadoDTO = new EstadoTestDataBuilder().build();
        Estado estado = estadoMapper.toEntity(estadoDTO);
        String crearEstado = estadoService.registrar(estado);
        Mockito.when(estadoDAO.save(Mockito.any())).thenReturn(estado);
        assertEquals(SE_REGISTRO_EL_ESTADO_EXITOSAMENTE, crearEstado);
    }

    @Test
    @DisplayName("Debería fallar al intentar crear un estado con estado null")
    void deberiaFallarPorEstadoNull()throws Exception{

        EstadoDTO estadoDTO = new EstadoTestDataBuilder().conEstado(null).build();
        Estado estado = estadoMapper.toEntity(estadoDTO);
        Mockito.when(estadoDAO.save(Mockito.any())).thenReturn(estado);
        Exception exception = assertThrows(Exception.class, ()->{
            estadoService.registrar(estado);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UN_ESTADO);
    }

    @Test
    @DisplayName("Debería fallar al intentar crear un estado con estado vacío")
    void deberiaFallarPorEstadoVacio()throws Exception{

        EstadoDTO estadoDTO = new EstadoTestDataBuilder().conEstado("").build();
        Estado estado = estadoMapper.toEntity(estadoDTO);
        Mockito.when(estadoDAO.save(Mockito.any())).thenReturn(estado);
        Exception exception = assertThrows(Exception.class, ()->{
            estadoService.registrar(estado);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UN_ESTADO);
    }

    @Test
    @DisplayName("Debería fallar al intentar crear un estado con nombre estado largo")
    void deberiaFallarPorEstadoLargo()throws Exception{

        EstadoDTO estadoDTO = new EstadoTestDataBuilder().conEstado(ESTADO_LARGO).build();
        Estado estado = estadoMapper.toEntity(estadoDTO);
        Mockito.when(estadoDAO.save(Mockito.any())).thenReturn(estado);
        Exception exception = assertThrows(Exception.class, ()->{
            estadoService.registrar(estado);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UN_ESTADO_VALIDO_NO_DEBE_SUPERAR_LOS_50_CARACTERES);
    }

    @Test
    @DisplayName("Debería fallar al intentar crear un estado con usuario creador null")
    void deberiaFallarPorUsuarioCreadorNull()throws Exception {

        EstadoDTO estadoDTO = new EstadoTestDataBuilder().conUsuarioCreador(null).build();
        Estado estado = estadoMapper.toEntity(estadoDTO);
        Mockito.when(estadoDAO.save(Mockito.any())).thenReturn(estado);
        Exception exception = assertThrows(Exception.class, () -> {
            estadoService.registrar(estado);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UN_USUARIO_CREADOR_VALIDO);
    }

    @Test
    @DisplayName("Debería fallar al intentar crear un estado con usuario creador vacío")
    void deberiaFallarPorUsuarioCreadorVacio()throws Exception {

        EstadoDTO estadoDTO = new EstadoTestDataBuilder().conUsuarioCreador("").build();
        Estado estado = estadoMapper.toEntity(estadoDTO);
        Mockito.when(estadoDAO.save(Mockito.any())).thenReturn(estado);
        Exception exception = assertThrows(Exception.class, () -> {
            estadoService.registrar(estado);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UN_USUARIO_CREADOR_VALIDO);
    }

    @Test
    @DisplayName("Debería fallar al intentar crear un estado con usuario creador largo")
    void deberiaFallarPorUsuarioCreadorLargo()throws Exception {

        EstadoDTO estadoDTO = new EstadoTestDataBuilder().conUsuarioCreador(USUARIO_CREADOR_LARGO).build();
        Estado estado = estadoMapper.toEntity(estadoDTO);
        Mockito.when(estadoDAO.save(Mockito.any())).thenReturn(estado);
        Exception exception = assertThrows(Exception.class, () -> {
            estadoService.registrar(estado);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UN_USUARIO_CREADOR_VALIDO);
    }

    @Test
    @DisplayName("Debería fallar al intentar crear un estado con fecha creación null")
    void deberiaFallarPorFechaCreacionNull()throws Exception {

        EstadoDTO estadoDTO = new EstadoTestDataBuilder().conFechaCreacion(null).build();
        Estado estado = estadoMapper.toEntity(estadoDTO);
        Mockito.when(estadoDAO.save(Mockito.any())).thenReturn(estado);
        Exception exception = assertThrows(Exception.class, () -> {
            estadoService.registrar(estado);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UNA_FECHA_DE_CREACION);
    }

    @Test
    @DisplayName("Debería fallar al intentar crear un estado con fecha creación que aun no ha sucedido")
    void deberiaFallarPorFechaCreacionFutura()throws Exception {

        EstadoDTO estadoDTO = new EstadoTestDataBuilder().conFechaCreacion(new Date(2023,7,23)).build();
        Estado estado = estadoMapper.toEntity(estadoDTO);
        Mockito.when(estadoDAO.save(Mockito.any())).thenReturn(estado);
        Exception exception = assertThrows(Exception.class, () -> {
            estadoService.registrar(estado);
        });
        assertEquals(exception.getMessage(), NO_PUEDE_INGRESAR_UNA_FECHA_QUE_AUN_NO_HA_SUCEDIDO);
    }
}
