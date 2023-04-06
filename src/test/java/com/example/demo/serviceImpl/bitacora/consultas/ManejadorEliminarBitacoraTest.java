package com.example.demo.serviceImpl.bitacora.consultas;

import com.example.demo.dao.BitacoraDAO;
import com.example.demo.mapper.BitacoraMapper;
import com.example.demo.model.dto.BitacoraDTO;
import com.example.demo.service.BitacoraService;
import com.example.demo.serviceImpl.bitacora.testDataBuilder.BitacoraTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@SpringBootTest
public class ManejadorEliminarBitacoraTest {

    private static final String SE_ELIMINO_EXITOSAMENTE_LA_BITACORA = "Se elimino exitosamente la bitácora.";
    private static final String EL_ID_DEL_REGISTRO_EN_LA_BITACORA_ES_OBLIGATORIO = "El id del registro en la bitácora es obligatorio.";
    private static final String NO_SE_ENCONTRO_NINGUN_REGISTRO_EN_LA_BITACORA_CON_ESE_ID = "No se encontró ningún registro en la bitácora con ese id.";

    @Autowired
    BitacoraService bitacoraService;

    @MockBean
    private BitacoraDAO bitacoraDAO;


    @Autowired
    BitacoraMapper bitacoraMapper;

    @Test
    @DisplayName("Debería eliminar un registro en la bitacora exitosamente")
    void deberiaEliminarUnRegistroBitacoraExitosamente()throws Exception{
        BitacoraDTO bitacoraDTO = new BitacoraTestDataBuilder().conIdBitacora(4343858L).build();
        Mockito.when(bitacoraDAO.existsById(bitacoraDTO.getIdBitacora())).thenReturn(true);
        String resultado = bitacoraService.eliminar(bitacoraDTO.getIdBitacora());
        Mockito.verify(bitacoraDAO, Mockito.times(1)).deleteById(bitacoraDTO.getIdBitacora());
        assertEquals(SE_ELIMINO_EXITOSAMENTE_LA_BITACORA, resultado);
    }
    @Test
    @DisplayName("Debería fallar por idBitacora null")
    void deberiaFallarPorIdBitacoraNull()throws Exception{
        BitacoraDTO bitacoraDTO = new BitacoraTestDataBuilder().conIdBitacora(null).build();
        Exception exception = assertThrows(Exception.class, ()->{
            bitacoraService.eliminar(bitacoraDTO.getIdBitacora());
        });
        assertEquals(exception.getMessage(), EL_ID_DEL_REGISTRO_EN_LA_BITACORA_ES_OBLIGATORIO);
    }
    @Test
    @DisplayName("Debería fallar por idBitacora")
    void deberiaFallarPorIdBitacoraNoExiste()throws Exception{
        BitacoraDTO bitacoraDTO = new BitacoraTestDataBuilder().conIdBitacora(76L).build();
        Mockito.when(bitacoraDAO.existsById(bitacoraDTO.getIdBitacora())).thenReturn(false);
        Exception exception = assertThrows(Exception.class, ()->{
            bitacoraService.eliminar(bitacoraDTO.getIdBitacora());
        });
        assertEquals(exception.getMessage(), NO_SE_ENCONTRO_NINGUN_REGISTRO_EN_LA_BITACORA_CON_ESE_ID);
    }
}
