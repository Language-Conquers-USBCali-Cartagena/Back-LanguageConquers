package com.example.demo.serviceImpl.bitacora.consultas;

import com.example.demo.dao.BitacoraDAO;
import com.example.demo.dao.EstudianteDAO;
import com.example.demo.dao.ProfesorDAO;
import com.example.demo.mapper.BitacoraMapper;
import com.example.demo.model.Bitacora;
import com.example.demo.model.Genero;
import com.example.demo.model.dto.BitacoraDTO;
import com.example.demo.model.dto.GeneroDTO;
import com.example.demo.service.BitacoraService;
import com.example.demo.serviceImpl.bitacora.testDataBuilder.BitacoraTestDataBuilder;
import com.example.demo.serviceImpl.genero.testDataBuilder.GeneroTestDataBuilder;
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
public class ManejadorCrearBitacoraTest {

    private static final String SE_CREO_CORRECTAMENTE_EL_REGISTRO_EN_LA_BITACORA = "Se creo correctamente el registro en la bitácora.";
    private static final String SE_DEBE_INGRESAR_UNA_FECHA_DE_INGRESO_VALIDA = "Se debe ingresar una fecha de ingreso válida.";
    private static final String SE_DEBE_INGRESAR_UNA_FECHA_DE_SALIDA_VALIDA = "Se debe ingresar una fecha de salida válida.";
    private static final String DEBE_INGRESAR_EL_ID_DE_UN_PROFESOR_O_ESTUDIANTE = "Debe ingresar el id de un profesor o estudiante.";
    private static final String DEBE_INGRESAR_UN_ID_VALIDO_DE_UN_ESTUDIANTE_O_DE_UN_PROFESOR = "Debe ingresar un id válido de un  estudiante o de un profesor.";
    private static final String DEBE_INGRESAR_UN_USUARIO_CREADOR = "Debe ingresar un usuario creador.";
    private static final String DEBE_INGRESAR_UN_USUARIO_CREADOR_QUE_NO_SUPERE_LOS_50_CARACTERES = "Debe ingresar un usuario creador que no supere los 50 caracteres.";
    private static final String SE_DEBE_INGRESAR_UNA_FECHA_DE_CREACION_VALIDA = "Se debe ingresar una fecha de creación válida.";
    private static final String NO_PUEDE_INGRESAR_UNA_FECHA_QUE_AUN_NO_HA_PASADO = "No puede ingresar una fecha que aun no ha sucedido.";
    private static final String USUARIO_CREADOR_MALO = "JKDFKJ  JDFH HF FH WEJ JHJHFJHJH GFHGSDJGF RERDG FJGJ";
    @Autowired
    BitacoraService bitacoraService;

    @MockBean
    private BitacoraDAO bitacoraDAO;

    @MockBean
    private EstudianteDAO estudianteDAO;

    @MockBean
    private ProfesorDAO profesorDAO;

    @Autowired
    BitacoraMapper bitacoraMapper;

    @Test
    @DisplayName("Deberia crear una bitacora exitosamente con un id profesor")
    void deberiaCrearUnaBitacoraConIdProfesor()throws Exception{
        BitacoraDTO bitacoraDTO = new BitacoraTestDataBuilder().conIdUsuario(34l).build();
        Bitacora bitacora = bitacoraMapper.toEntity(bitacoraDTO);
        Mockito.when(profesorDAO.existsById(bitacora.getIdUsuario())).thenReturn(true);
        String crearBitacora = bitacoraService.registrar(bitacora);
        Mockito.when(bitacoraDAO.save(Mockito.any())).thenReturn(bitacora);
        assertEquals(SE_CREO_CORRECTAMENTE_EL_REGISTRO_EN_LA_BITACORA, crearBitacora);
    }

    @Test
    @DisplayName("Deberia crear una bitacora exitosamente con un id estudiante")
    void deberiaCrearUnaBitacoraConIdEstudiante()throws Exception{
        BitacoraDTO bitacoraDTO = new BitacoraTestDataBuilder().conIdUsuario(34l).build();
        Bitacora bitacora = bitacoraMapper.toEntity(bitacoraDTO);
        Mockito.when(estudianteDAO.existsById(bitacora.getIdUsuario())).thenReturn(true);
        String crearBitacora = bitacoraService.registrar(bitacora);
        Mockito.when(bitacoraDAO.save(Mockito.any())).thenReturn(bitacora);
        assertEquals(SE_CREO_CORRECTAMENTE_EL_REGISTRO_EN_LA_BITACORA, crearBitacora);
    }

    @Test
    @DisplayName("Deberia lanzar un error por idUsuario null")
    void deberiaFallarPorUsuarioNulo()throws Exception{
        BitacoraDTO bitacoraDTO = new BitacoraTestDataBuilder().conIdUsuario(null).build();
        Bitacora bitacora = bitacoraMapper.toEntity(bitacoraDTO);
        Mockito.when(bitacoraDAO.save(Mockito.any())).thenReturn(bitacora);
        Exception exception = assertThrows(Exception.class, ()->{
            bitacoraService.registrar(bitacora);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_EL_ID_DE_UN_PROFESOR_O_ESTUDIANTE);
    }

    @Test
    @DisplayName("Deberia lanzar un error por fecha ingreso null")
    void deberiaFallarPorFechaIngresoNull()throws Exception{
        BitacoraDTO bitacoraDTO = new BitacoraTestDataBuilder().conFechaIngreso(null).build();
        Bitacora bitacora = bitacoraMapper.toEntity(bitacoraDTO);
        Mockito.when(bitacoraDAO.save(Mockito.any())).thenReturn(bitacora);
        Exception exception = assertThrows(Exception.class, ()->{
            bitacoraService.registrar(bitacora);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UNA_FECHA_DE_INGRESO_VALIDA);
    }

    @Test
    @DisplayName("Deberia lanzar un error por fecha salida null")
    void deberiaFallarPorFechaSalidaNull()throws Exception{
        BitacoraDTO bitacoraDTO = new BitacoraTestDataBuilder().conFechaSalida(null).build();
        Bitacora bitacora = bitacoraMapper.toEntity(bitacoraDTO);
        Mockito.when(bitacoraDAO.save(Mockito.any())).thenReturn(bitacora);
        Exception exception = assertThrows(Exception.class, ()->{
            bitacoraService.registrar(bitacora);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UNA_FECHA_DE_SALIDA_VALIDA);
    }

    @Test
    @DisplayName("Deberia lanzar un error por id estudiante no existente")
    void deberiaFallarPorIdEstudianteNoExiste()throws Exception{
        BitacoraDTO bitacoraDTO = new BitacoraTestDataBuilder().conIdUsuario(378L).build();
        Bitacora bitacora = bitacoraMapper.toEntity(bitacoraDTO);
        Mockito.when(estudianteDAO.existsById(bitacora.getIdUsuario())).thenReturn(false);
        Mockito.when(bitacoraDAO.save(Mockito.any())).thenReturn(bitacora);
        Exception exception = assertThrows(Exception.class, ()->{
            bitacoraService.registrar(bitacora);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UN_ID_VALIDO_DE_UN_ESTUDIANTE_O_DE_UN_PROFESOR);
    }

    @Test
    @DisplayName("Deberia lanzar un error por id profesor no existente")
    void deberiaFallarPorIdProfesorNoExiste()throws Exception{
        BitacoraDTO bitacoraDTO = new BitacoraTestDataBuilder().conIdUsuario(378L).build();
        Bitacora bitacora = bitacoraMapper.toEntity(bitacoraDTO);
        Mockito.when(profesorDAO.existsById(bitacora.getIdUsuario())).thenReturn(false);
        Mockito.when(bitacoraDAO.save(Mockito.any())).thenReturn(bitacora);
        Exception exception = assertThrows(Exception.class, ()->{
            bitacoraService.registrar(bitacora);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UN_ID_VALIDO_DE_UN_ESTUDIANTE_O_DE_UN_PROFESOR);
    }

    @Test
    @DisplayName("Deberia lanzar un error por usuario creador null")
    void deberiaFallarPorUsuarioCreadorNull()throws Exception{
        BitacoraDTO bitacoraDTO = new BitacoraTestDataBuilder().conUsuarioCreador(null).build();
        Bitacora bitacora = bitacoraMapper.toEntity(bitacoraDTO);
        Mockito.when(profesorDAO.existsById(bitacora.getIdUsuario())).thenReturn(true);
        Mockito.when(bitacoraDAO.save(Mockito.any())).thenReturn(bitacora);
        Exception exception = assertThrows(Exception.class, ()->{
            bitacoraService.registrar(bitacora);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UN_USUARIO_CREADOR);
    }

    @Test
    @DisplayName("Deberia lanzar un error por usuario creador vacio")
    void deberiaFallarPorUsuarioCreadorVacio()throws Exception{
        BitacoraDTO bitacoraDTO = new BitacoraTestDataBuilder().conUsuarioCreador("").build();
        Bitacora bitacora = bitacoraMapper.toEntity(bitacoraDTO);
        Mockito.when(profesorDAO.existsById(bitacora.getIdUsuario())).thenReturn(true);
        Mockito.when(bitacoraDAO.save(Mockito.any())).thenReturn(bitacora);
        Exception exception = assertThrows(Exception.class, ()->{
            bitacoraService.registrar(bitacora);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UN_USUARIO_CREADOR);
    }

    @Test
    @DisplayName("Deberia lanzar un error por usuario creador vacio")
    void deberiaFallarPorUsuarioCreadorLargo()throws Exception{
        BitacoraDTO bitacoraDTO = new BitacoraTestDataBuilder().conUsuarioCreador(USUARIO_CREADOR_MALO).build();
        Bitacora bitacora = bitacoraMapper.toEntity(bitacoraDTO);
        Mockito.when(profesorDAO.existsById(bitacora.getIdUsuario())).thenReturn(true);
        Mockito.when(bitacoraDAO.save(Mockito.any())).thenReturn(bitacora);
        Exception exception = assertThrows(Exception.class, ()->{
            bitacoraService.registrar(bitacora);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UN_USUARIO_CREADOR_QUE_NO_SUPERE_LOS_50_CARACTERES);
    }

    @Test
    @DisplayName("Deberia lanzar un error por fecha creacion null")
    void deberiaFallarPorFechaCreacionNull()throws Exception{
        BitacoraDTO bitacoraDTO = new BitacoraTestDataBuilder().conFechaCreacion(null).build();
        Bitacora bitacora = bitacoraMapper.toEntity(bitacoraDTO);
        Mockito.when(profesorDAO.existsById(bitacora.getIdUsuario())).thenReturn(true);
        Mockito.when(bitacoraDAO.save(Mockito.any())).thenReturn(bitacora);
        Exception exception = assertThrows(Exception.class, ()->{
            bitacoraService.registrar(bitacora);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UNA_FECHA_DE_CREACION_VALIDA);
    }

    @Test
    @DisplayName("Deberia lanzar un error por fecha creacion futura")
    void deberiaFallarPorFechaCreacionFutura()throws Exception{
        BitacoraDTO bitacoraDTO = new BitacoraTestDataBuilder().conFechaCreacion(new Date(2023,8,14)).build();
        Bitacora bitacora = bitacoraMapper.toEntity(bitacoraDTO);
        Mockito.when(profesorDAO.existsById(bitacora.getIdUsuario())).thenReturn(true);
        Mockito.when(bitacoraDAO.save(Mockito.any())).thenReturn(bitacora);
        Exception exception = assertThrows(Exception.class, ()->{
            bitacoraService.registrar(bitacora);
        });
        assertEquals(exception.getMessage(), NO_PUEDE_INGRESAR_UNA_FECHA_QUE_AUN_NO_HA_PASADO);
    }
}
