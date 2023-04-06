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

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class ManejadorActualizarBitacoraTest {

    private static final String SE_ACTUALIZO_EL_REGISTRO_EN_LA_BITACORA = "Se actualizo el registro en la bitácora.";
    private static final String DEBE_INGRESAR_EL_ID_DEL_REGISTRO_QUE_DESEA_MODIFICAR = "Debe ingresar el id del registro que desea modificar.";
    private static final String NO_EXISTE_UN_REGISTRO_EN_LA_BITACORA_CON_ESE_ID = "No existe un registro en la bitácora con ese id.";
    private static final String SE_DEBE_INGRESAR_UNA_FECHA_DE_INGRESO_VALIDA = "Se debe ingresar una fecha de ingreso válida.";
    private static final String SE_DEBE_INGRESAR_UNA_FECHA_DE_SALIDA_VALIDA = "Se debe ingresar una fecha de salida válida.";
    private static final String DEBE_INGRESAR_EL_ID_DE_UN_PROFESOR_O_ESTUDIANTE = "Debe ingresar el id de un profesor o estudiante.";
    private static final String DEBE_INGRESAR_UN_ID_VALIDO_DE_UN_ESTUDIANTE_O_DE_UN_PROFESOR = "Debe ingresar un id válido de un  estudiante o de un profesor.";
    private static final String DEBE_INGRESAR_UN_USUARIO_MODIFICADOR = "Debe ingresar un usuario modificador.";
    private static final String DEBE_INGRESAR_UN_USUARIO_CREADOR_QUE_NO_SUPERE_LOS_50_CARACTERES = "Debe ingresar un usuario modificador que no supere los 50 caracteres.";
    private static final String SE_DEBE_INGRESAR_UNA_FECHA_DE_CREACION_VALIDA = "Se debe ingresar una fecha de modificación válida.";
    private static final String NO_PUEDE_INGRESAR_UNA_FECHA_QUE_AUN_NO_HA_PASADO = "No puede ingresar una fecha que aun no ha sucedido.";
    private static final String USUARIO_CREADOR_MALO = "JKDFKJ  JDFH HF FH WEJ JHJHFJHJH GFHGSDJGF RERDG FJGJ";
    private static final String USUARIO_CREADOR = "Angela";
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
    @DisplayName("Deberia actualizar exitosamente el registro en la bitacora")
    void deberiaActualizarExitosamenteBitacora()throws Exception{

        BitacoraDTO bitacoraDTO = new BitacoraTestDataBuilder()
                .conIdUsuario(76L)
                .conFechaIngreso(new Date())
                .conFechaSalida(new Date())
                .conFechaCreacion(new Date())
                .conUsuarioCreador(USUARIO_CREADOR)
                .build();
        Bitacora bitacora = bitacoraMapper.toEntity(bitacoraDTO);
        Mockito.when(bitacoraDAO.existsById(bitacora.getIdBitacora())).thenReturn(true);
        Mockito.when(estudianteDAO.existsById(bitacora.getIdUsuario())).thenReturn(true);
        Mockito.when(bitacoraDAO.save(Mockito.any(Bitacora.class))).thenReturn(bitacora);
        String actualizarBitacora = bitacoraService.actualizar(bitacoraDTO);
        assertEquals(SE_ACTUALIZO_EL_REGISTRO_EN_LA_BITACORA, actualizarBitacora);
    }
}
