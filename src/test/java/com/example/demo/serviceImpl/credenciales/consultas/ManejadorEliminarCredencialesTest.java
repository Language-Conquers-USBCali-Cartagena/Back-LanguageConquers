package com.example.demo.serviceImpl.credenciales.consultas;

import com.example.demo.dao.CredencialesDAO;
import com.example.demo.mapper.CredencialesMapper;
import com.example.demo.model.dto.CredencialesDTO;
import com.example.demo.service.CredencialesService;
import com.example.demo.serviceImpl.credenciales.testDataBuilder.CredencialesTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@SpringBootTest
public class ManejadorEliminarCredencialesTest {

    private static final String SE_ELIMINO_EXITOSAMENTE_LA_CREDENCIAL = "Se elimino exitosamente la credencial.";
    private static final String SE_DEBE_INGRESAR_EL_ID_DE_LA_CREDENCIAL_QUE_DESEA_ELIMINAR = "Se debe ingresar el id de la credencial que desea eliminar.";
    private static final String NO_SE_ENCONTRO_LA_CREDENCIAL_CON_ESE_ID = "No se encontró la credencial con ese id.";

    @Autowired
    CredencialesService credencialesService;

    @Autowired
    CredencialesMapper credencialesMapper;

    @MockBean
    private CredencialesDAO credencialesDAO;

    @Test
    @DisplayName("Deberia eliminar de manera exitosa la credencial")
    void deberiaEliminarCredencialExitosamente()throws Exception{
        CredencialesDTO credencialesDTO = new CredencialesTestDataBuilder().conIdCredencial(7437L).build();
        Mockito.when(credencialesDAO.existsById(credencialesDTO.getIdCredencial())).thenReturn(true);
        String eliminarCredencial = credencialesService.eliminar(credencialesDTO.getIdCredencial());
        Mockito.verify(credencialesDAO, Mockito.times(1)).deleteById(credencialesDTO.getIdCredencial());
        assertEquals(SE_ELIMINO_EXITOSAMENTE_LA_CREDENCIAL, eliminarCredencial);
    }

    @Test
    @DisplayName("Deberia fallar por idCredencial null")
    void deberiaFallarPorIdCredencialNull()throws Exception{
        CredencialesDTO credencialesDTO = new CredencialesTestDataBuilder().conIdCredencial(null).build();
        Exception exception = assertThrows(Exception.class, ()->{
            credencialesService.eliminar(credencialesDTO.getIdCredencial());
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_EL_ID_DE_LA_CREDENCIAL_QUE_DESEA_ELIMINAR);
    }

    @Test
    @DisplayName("Deberia fallar por idCredencial no existe")
    void deberiaFallarPorIdCredencialNoExiste()throws Exception{
        CredencialesDTO credencialesDTO = new CredencialesTestDataBuilder().conIdCredencial(5784L).build();
        Mockito.when(credencialesDAO.existsById(credencialesDTO.getIdCredencial())).thenReturn(false);
        Exception exception = assertThrows(Exception.class, ()->{
            credencialesService.eliminar(credencialesDTO.getIdCredencial());
        });
        assertEquals(exception.getMessage(), NO_SE_ENCONTRO_LA_CREDENCIAL_CON_ESE_ID);
    }
}
