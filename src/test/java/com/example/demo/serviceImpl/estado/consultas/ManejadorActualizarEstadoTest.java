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
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@SpringBootTest
public class ManejadorActualizarEstadoTest {

    public static final String SE_ACTUALIZO_EL_ESTADO  = "Se actualizo el estado.";
    public static final String SE_DEBE_INGRESAR_EL_ID_DEL_ESTADO_QUE_SE_DESEA_ACTUALIZAR = "Debe ingresar el id del estado que desea actualizar.";
    public static final String NO_EXISTE_EL_ESTADO_CON_ESE_ID = "No existe el estado con ese id.";
    public static final String SE_DEBE_INGRESAR_UN_ESTADO = "Se debe ingresar un estado.";
    public static final String SE_DEBE_INGRESAR_UN_ESTADO_VALIDO_NO_DEBE_SUPERAR_LOS_50_CARACTERES = "Se debe ingresar un estado válido, no debe superar los 50 caracteres.";
    public static final String SE_DEBE_INGRESAR_UN_USUARIO_MODIFICADOR_VALIDO = "Se debe ingresar un usuario modificador válido.";
    public static final String DEBE_INGRESAR_UNA_FECHA_DE_MODIFICACION ="Debe ingresar una fecha de modificación.";
    public static final String NO_PUEDE_INGRESAR_UNA_FECHA_QUE_AUN_NO_HA_SUCEDIDO = "No puede ingresar una fecha que aun no ha sucedido.";
    public static final String ESTADO_LARGO = "relfufirerkerejferewjdjhwejfhjejfjhdloisdusdndfdfid";
    public static final String USUARIO_MODIFICADOR_LARGO = "relfufirerkerejferewjdjhwejfhjejfjhdloisdusdndfdfid";

    @Autowired
    EstadoService estadoService;

    @MockBean
    private EstadoDAO estadoDAO;

    @Autowired
    EstadoMapper estadoMapper;

    @Test
    @DisplayName("Debería actualizar un estado de manera exitosa")
    void deberiaActualizarEstadoExitosamente()throws Exception{

        EstadoDTO estadoDTO = new EstadoTestDataBuilder().build();
        estadoDTO.setEstado("Inhabilitado");
        estadoDTO.setUsuarioModificador("Angela A");
        estadoDTO.setFechaModificacion(new Date());
        Estado estado = estadoMapper.toEntity(estadoDTO);
        Mockito.when(estadoDAO.existsById(2787L)).thenReturn(true);
        Mockito.when(estadoDAO.findById(estadoDTO.getIdEstado())).thenReturn(Optional.of(estado));
        Mockito.when(estadoDAO.save(Mockito.any(Estado.class))).thenReturn(estado);
        String actualizarEstado = estadoService.actualizar(estadoDTO);
        assertEquals(SE_ACTUALIZO_EL_ESTADO, actualizarEstado);
    }

    @Test
    @DisplayName("Debería fallar al intentar actualizar por IdEstado Null")
    void deberiaFallarPorIdEstadoNull()throws Exception{

        EstadoDTO estadoDTO = new EstadoTestDataBuilder().conIdEstado(null).build();
        estadoDTO.setEstado("Inhabilitado");
        estadoDTO.setUsuarioModificador("Angela A");
        estadoDTO.setFechaModificacion(new Date());
        Estado estado = estadoMapper.toEntity(estadoDTO);
        Mockito.when(estadoDAO.findById(estadoDTO.getIdEstado())).thenReturn(Optional.of(estado));
        Mockito.when(estadoDAO.save(Mockito.any(Estado.class))).thenReturn(estado);
        Exception exception = assertThrows(Exception.class, ()->{
            estadoService.actualizar(estadoDTO);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_EL_ID_DEL_ESTADO_QUE_SE_DESEA_ACTUALIZAR);
    }

    @Test
    @DisplayName("Debería fallar por IdEstado no existente ")
    void deberiaFallarPorIdEstadoNoExiste()throws Exception{

        EstadoDTO estadoDTO = new EstadoTestDataBuilder().conIdEstado(387634L).build();
        estadoDTO.setEstado("Inhabilitado");
        estadoDTO.setUsuarioModificador("Angela A");
        estadoDTO.setFechaModificacion(new Date());
        Estado estado = estadoMapper.toEntity(estadoDTO);
        Mockito.when(estadoDAO.existsById(estadoDTO.getIdEstado())).thenReturn(false);
        Mockito.when(estadoDAO.save(Mockito.any(Estado.class))).thenReturn(estado);
        Exception exception = assertThrows(Exception.class, ()->{
            estadoService.actualizar(estadoDTO);
        });
        assertEquals(exception.getMessage(), NO_EXISTE_EL_ESTADO_CON_ESE_ID);
    }
    @Test
    @DisplayName("Debería fallar por Estado null")
    void deberiaFallarPorEstadoNull()throws Exception{

        EstadoDTO estadoDTO = new EstadoTestDataBuilder().conIdEstado(2787L).build();
        estadoDTO.setEstado(null);
        estadoDTO.setUsuarioModificador("Angela A");
        estadoDTO.setFechaModificacion(new Date());
        Estado estado = estadoMapper.toEntity(estadoDTO);
        Mockito.when(estadoDAO.existsById(estadoDTO.getIdEstado())).thenReturn(true);
        Mockito.when(estadoDAO.save(Mockito.any(Estado.class))).thenReturn(estado);
        Exception exception = assertThrows(Exception.class, ()->{
            estadoService.actualizar(estadoDTO);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UN_ESTADO);
    }

    @Test
    @DisplayName("Debería fallar por Estado vacio")
    void deberiaFallarPorEstadoVacio()throws Exception{

        EstadoDTO estadoDTO = new EstadoTestDataBuilder().conIdEstado(2787L).build();
        estadoDTO.setEstado("");
        estadoDTO.setUsuarioModificador("Angela A");
        estadoDTO.setFechaModificacion(new Date());
        Estado estado = estadoMapper.toEntity(estadoDTO);
        Mockito.when(estadoDAO.existsById(estadoDTO.getIdEstado())).thenReturn(true);
        Mockito.when(estadoDAO.save(Mockito.any(Estado.class))).thenReturn(estado);
        Exception exception = assertThrows(Exception.class, ()->{
            estadoService.actualizar(estadoDTO);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UN_ESTADO);
    }

    @Test
    @DisplayName("Debería fallar por nombre del estado largo")
    void deberiaFallarPorEstadoLargo()throws Exception{

        EstadoDTO estadoDTO = new EstadoTestDataBuilder().conIdEstado(2787L).build();
        estadoDTO.setEstado(ESTADO_LARGO);
        estadoDTO.setUsuarioModificador("Angela A");
        estadoDTO.setFechaModificacion(new Date());
        Estado estado = estadoMapper.toEntity(estadoDTO);
        Mockito.when(estadoDAO.existsById(estadoDTO.getIdEstado())).thenReturn(true);
        Mockito.when(estadoDAO.save(Mockito.any(Estado.class))).thenReturn(estado);
        Exception exception = assertThrows(Exception.class, ()->{
            estadoService.actualizar(estadoDTO);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UN_ESTADO_VALIDO_NO_DEBE_SUPERAR_LOS_50_CARACTERES);
    }

    @Test
    @DisplayName("Debería fallar por usuario modificador null")
    void deberiaFallarPorUsuarioModificadorNull()throws Exception{

        EstadoDTO estadoDTO = new EstadoTestDataBuilder().conIdEstado(2787L).build();
        estadoDTO.setEstado("Inhabilitado");
        estadoDTO.setUsuarioModificador(null);
        estadoDTO.setFechaModificacion(new Date());
        Estado estado = estadoMapper.toEntity(estadoDTO);
        Mockito.when(estadoDAO.existsById(estadoDTO.getIdEstado())).thenReturn(true);
        Mockito.when(estadoDAO.save(Mockito.any(Estado.class))).thenReturn(estado);
        Exception exception = assertThrows(Exception.class, ()->{
            estadoService.actualizar(estadoDTO);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UN_USUARIO_MODIFICADOR_VALIDO);
    }

    @Test
    @DisplayName("Debería fallar por usuario modificador vacío")
    void deberiaFallarPorUsuarioModificadorVacio()throws Exception{

        EstadoDTO estadoDTO = new EstadoTestDataBuilder().conIdEstado(2787L).build();
        estadoDTO.setEstado("Inhabilitado");
        estadoDTO.setUsuarioModificador("");
        estadoDTO.setFechaModificacion(new Date());
        Estado estado = estadoMapper.toEntity(estadoDTO);
        Mockito.when(estadoDAO.existsById(estadoDTO.getIdEstado())).thenReturn(true);
        Mockito.when(estadoDAO.save(Mockito.any(Estado.class))).thenReturn(estado);
        Exception exception = assertThrows(Exception.class, ()->{
            estadoService.actualizar(estadoDTO);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UN_USUARIO_MODIFICADOR_VALIDO);
    }

    @Test
    @DisplayName("Debería fallar por usuario modificador largo")
    void deberiaFallarPorUsuarioModificadorLargo()throws Exception{

        EstadoDTO estadoDTO = new EstadoTestDataBuilder().conIdEstado(2787L).build();
        estadoDTO.setEstado("Inhabilitado");
        estadoDTO.setUsuarioModificador(USUARIO_MODIFICADOR_LARGO);
        estadoDTO.setFechaModificacion(new Date());
        Estado estado = estadoMapper.toEntity(estadoDTO);
        Mockito.when(estadoDAO.existsById(estadoDTO.getIdEstado())).thenReturn(true);
        Mockito.when(estadoDAO.save(Mockito.any(Estado.class))).thenReturn(estado);
        Exception exception = assertThrows(Exception.class, ()->{
            estadoService.actualizar(estadoDTO);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UN_USUARIO_MODIFICADOR_VALIDO);
    }

    @Test
    @DisplayName("Debería fallar por fecha modificación null")
    void deberiaFallarPorFechaModificacionNull()throws Exception{

        EstadoDTO estadoDTO = new EstadoTestDataBuilder().conIdEstado(2787L).build();
        estadoDTO.setEstado("Inhabilitado");
        estadoDTO.setUsuarioModificador("Angela");
        estadoDTO.setFechaModificacion(null);
        Estado estado = estadoMapper.toEntity(estadoDTO);
        Mockito.when(estadoDAO.existsById(estadoDTO.getIdEstado())).thenReturn(true);
        Mockito.when(estadoDAO.save(Mockito.any(Estado.class))).thenReturn(estado);
        Exception exception = assertThrows(Exception.class, ()->{
            estadoService.actualizar(estadoDTO);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UNA_FECHA_DE_MODIFICACION);
    }
    @Test
    @DisplayName("Debería fallar por fecha modificación que aun no ha sucedido")
    void deberiaFallarPorFechaModificacionFutura()throws Exception{

        EstadoDTO estadoDTO = new EstadoTestDataBuilder().conIdEstado(2787L).build();
        estadoDTO.setEstado("Inhabilitado");
        estadoDTO.setUsuarioModificador("Angela");
        estadoDTO.setFechaModificacion(new Date(2023,9,12));
        Estado estado = estadoMapper.toEntity(estadoDTO);
        Mockito.when(estadoDAO.existsById(estadoDTO.getIdEstado())).thenReturn(true);
        Mockito.when(estadoDAO.save(Mockito.any(Estado.class))).thenReturn(estado);
        Exception exception = assertThrows(Exception.class, ()->{
            estadoService.actualizar(estadoDTO);
        });
        assertEquals(exception.getMessage(), NO_PUEDE_INGRESAR_UNA_FECHA_QUE_AUN_NO_HA_SUCEDIDO);
    }
}
