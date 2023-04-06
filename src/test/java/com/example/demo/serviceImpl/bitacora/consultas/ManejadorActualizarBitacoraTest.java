package com.example.demo.serviceImpl.bitacora.consultas;

import com.example.demo.dao.BitacoraDAO;
import com.example.demo.dao.EstudianteDAO;
import com.example.demo.dao.ProfesorDAO;
import com.example.demo.mapper.BitacoraMapper;
import com.example.demo.model.Bitacora;
import com.example.demo.model.dto.BitacoraDTO;
import com.example.demo.service.BitacoraService;
import com.example.demo.serviceImpl.bitacora.testDataBuilder.BitacoraTestDataBuilder;
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
public class ManejadorActualizarBitacoraTest {

    private static final String SE_ACTUALIZO_EL_REGISTRO_EN_LA_BITACORA = "Se actualizo el registro en la bitácora.";
    private static final String DEBE_INGRESAR_EL_ID_DEL_REGISTRO_QUE_DESEA_MODIFICAR = "Debe ingresar el id del registro que desea modificar.";
    private static final String NO_EXISTE_UN_REGISTRO_EN_LA_BITACORA_CON_ESE_ID = "No existe un registro en la bitácora con ese id.";
    private static final String SE_DEBE_INGRESAR_UNA_FECHA_DE_INGRESO_VALIDA = "Se debe ingresar una fecha de ingreso válida.";
    private static final String SE_DEBE_INGRESAR_UNA_FECHA_DE_SALIDA_VALIDA = "Se debe ingresar una fecha de salida válida.";
    private static final String DEBE_INGRESAR_EL_ID_DE_UN_PROFESOR_O_ESTUDIANTE = "Debe ingresar el id de un profesor o estudiante.";
    private static final String DEBE_INGRESAR_UN_USUARIO_MODIFICADOR = "Debe ingresar un usuario modificador.";
    private static final String DEBE_INGRESAR_UN_USUARIO_MODIFICADOR_QUE_NO_SUPERE_LOS_50_CARACTERES = "Debe ingresar un usuario modificador que no supere los 50 caracteres.";
    private static final String SE_DEBE_INGRESAR_UNA_FECHA_DE_MODIFICACION_VALIDA = "Se debe ingresar una fecha de modificación válida.";
    private static final String NO_PUEDE_INGRESAR_UNA_FECHA_QUE_AUN_NO_HA_PASADO = "No puede ingresar una fecha que aun no ha sucedido.";
    private static final String USUARIO_MODIFICADOR_MALO = "JKDFKJ  JDFH HF FH WEJ JHJHFJHJH GFHGSDJGF RERDG FJGJ";
    private static final String USUARIO_CREADOR = "Angela";
    @Autowired
    BitacoraService bitacoraService;

    @MockBean
    private BitacoraDAO bitacoraDAO;

    @MockBean
    private EstudianteDAO estudianteDAO;

    @Autowired
    BitacoraMapper bitacoraMapper;

    @Test
    @DisplayName("Deberia actualizar exitosamente el registro en la bitacora")
    void deberiaActualizarExitosamenteBitacora()throws Exception{

        BitacoraDTO bitacoraDTO = new BitacoraTestDataBuilder()
                .conIdBitacora(324L)
                .conIdUsuario(76L)
                .conFechaIngreso(new Date())
                .conFechaSalida(new Date())
                .conFechaCreacion(new Date())
                .conUsuarioCreador(USUARIO_CREADOR)
                .build();
        Bitacora bitacora = bitacoraMapper.toEntity(bitacoraDTO);
        Mockito.when(bitacoraDAO.existsById(bitacora.getIdBitacora())).thenReturn(true);
        Mockito.when(bitacoraDAO.findById(bitacora.getIdBitacora())).thenReturn(Optional.of(bitacora));
        Mockito.when(estudianteDAO.existsById(bitacora.getIdUsuario())).thenReturn(true);
        Mockito.when(bitacoraDAO.save(Mockito.any(Bitacora.class))).thenReturn(bitacora);
        String actualizarBitacora = bitacoraService.actualizar(bitacoraDTO);
        assertEquals(SE_ACTUALIZO_EL_REGISTRO_EN_LA_BITACORA, actualizarBitacora);
    }
    @Test
    @DisplayName("Deberia fallar por Id bitacora null")
    void deberiaFallaPorIdBitacoraNull()throws Exception{

        BitacoraDTO bitacoraDTO = new BitacoraTestDataBuilder()
                .conIdBitacora(null)
                .build();
        Bitacora bitacora = bitacoraMapper.toEntity(bitacoraDTO);
        Mockito.when(bitacoraDAO.findById(bitacora.getIdBitacora())).thenReturn(Optional.of(bitacora));
        Exception exception = assertThrows(Exception.class, ()->{
            bitacoraService.actualizar(bitacoraDTO);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_EL_ID_DEL_REGISTRO_QUE_DESEA_MODIFICAR);
    }
    @Test
    @DisplayName("Deberia fallar por Id bitacora no existe")
    void deberiaFallaPorIdBitacoraNoExiste()throws Exception{

        BitacoraDTO bitacoraDTO = new BitacoraTestDataBuilder()
                .conIdBitacora(876L)
                .build();
        Bitacora bitacora = bitacoraMapper.toEntity(bitacoraDTO);
        Mockito.when(bitacoraDAO.existsById(bitacora.getIdBitacora())).thenReturn(false);
        Exception exception = assertThrows(Exception.class, ()->{
            bitacoraService.actualizar(bitacoraDTO);
        });
        assertEquals(exception.getMessage(), NO_EXISTE_UN_REGISTRO_EN_LA_BITACORA_CON_ESE_ID);
    }

    @Test
    @DisplayName("Deberia fallar por fecha ingreso null")
    void deberiaFallaPorFechaIngresoNull()throws Exception{

        BitacoraDTO bitacoraDTO = new BitacoraTestDataBuilder()
                .conFechaIngreso(null)
                .build();
        Bitacora bitacora = bitacoraMapper.toEntity(bitacoraDTO);
        Mockito.when(bitacoraDAO.existsById(bitacora.getIdBitacora())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, ()->{
            bitacoraService.actualizar(bitacoraDTO);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UNA_FECHA_DE_INGRESO_VALIDA);
    }
    @Test
    @DisplayName("Deberia fallar por fecha salida")
    void deberiaFallaPorFechaSalidaNull()throws Exception{

        BitacoraDTO bitacoraDTO = new BitacoraTestDataBuilder()
                .conFechaSalida(null)
                .build();
        Bitacora bitacora = bitacoraMapper.toEntity(bitacoraDTO);
        Mockito.when(bitacoraDAO.existsById(bitacora.getIdBitacora())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, ()->{
            bitacoraService.actualizar(bitacoraDTO);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UNA_FECHA_DE_SALIDA_VALIDA);
    }
    @Test
    @DisplayName("Deberia fallar por idUsuario null")
    void deberiaFallaPorIdUsuarioNull()throws Exception{

        BitacoraDTO bitacoraDTO = new BitacoraTestDataBuilder()
                .conIdUsuario(null)
                .build();
        Bitacora bitacora = bitacoraMapper.toEntity(bitacoraDTO);
        Mockito.when(bitacoraDAO.existsById(bitacora.getIdBitacora())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, ()->{
            bitacoraService.actualizar(bitacoraDTO);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_EL_ID_DE_UN_PROFESOR_O_ESTUDIANTE);
    }
    @Test
    @DisplayName("Deberia fallar por usuario modificador null")
    void deberiaFallaPorUsuarioModificadorNull()throws Exception{

        BitacoraDTO bitacoraDTO = new BitacoraTestDataBuilder()
                .conUsuarioModificador(null)
                .build();
        Bitacora bitacora = bitacoraMapper.toEntity(bitacoraDTO);
        Mockito.when(bitacoraDAO.existsById(bitacora.getIdBitacora())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, ()->{
            bitacoraService.actualizar(bitacoraDTO);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UN_USUARIO_MODIFICADOR);
    }
    @Test
    @DisplayName("Deberia fallar por usuario modificador vacio")
    void deberiaFallaPorUsuarioModificadorVacio()throws Exception{

        BitacoraDTO bitacoraDTO = new BitacoraTestDataBuilder()
                .conUsuarioModificador("")
                .build();
        Bitacora bitacora = bitacoraMapper.toEntity(bitacoraDTO);
        Mockito.when(bitacoraDAO.existsById(bitacora.getIdBitacora())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, ()->{
            bitacoraService.actualizar(bitacoraDTO);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UN_USUARIO_MODIFICADOR);
    }
    @Test
    @DisplayName("Deberia fallar por usuario modificador largo")
    void deberiaFallaPorUsuarioModificadorLargo()throws Exception{

        BitacoraDTO bitacoraDTO = new BitacoraTestDataBuilder()
                .conUsuarioModificador(USUARIO_MODIFICADOR_MALO)
                .build();
        Bitacora bitacora = bitacoraMapper.toEntity(bitacoraDTO);
        Mockito.when(bitacoraDAO.existsById(bitacora.getIdBitacora())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, ()->{
            bitacoraService.actualizar(bitacoraDTO);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UN_USUARIO_MODIFICADOR_QUE_NO_SUPERE_LOS_50_CARACTERES);
    }
    @Test
    @DisplayName("Deberia fallar por fecha modificacion null")
    void deberiaFallaPorFechaModificacionNull()throws Exception{

        BitacoraDTO bitacoraDTO = new BitacoraTestDataBuilder()
                .conFechaModificacion(null)
                .build();
        Bitacora bitacora = bitacoraMapper.toEntity(bitacoraDTO);
        Mockito.when(bitacoraDAO.existsById(bitacora.getIdBitacora())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, ()->{
            bitacoraService.actualizar(bitacoraDTO);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UNA_FECHA_DE_MODIFICACION_VALIDA);
    }
    @Test
    @DisplayName("Deberia fallar por fecha modificacion futura")
    void deberiaFallaPorFechaModificacionFutura()throws Exception{

        BitacoraDTO bitacoraDTO = new BitacoraTestDataBuilder()
                .conFechaModificacion(new Date(2023,8,15))
                .build();
        Bitacora bitacora = bitacoraMapper.toEntity(bitacoraDTO);
        Mockito.when(bitacoraDAO.existsById(bitacora.getIdBitacora())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, ()->{
            bitacoraService.actualizar(bitacoraDTO);
        });
        assertEquals(exception.getMessage(), NO_PUEDE_INGRESAR_UNA_FECHA_QUE_AUN_NO_HA_PASADO);
    }
}
