package com.example.demo.serviceImpl.articulosAdquiridos.consultas;

import com.example.demo.dao.ArticulosAdquiridosDAO;
import com.example.demo.dao.ArticulosDAO;
import com.example.demo.dao.EstudianteDAO;
import com.example.demo.mapper.ArticulosAdquiridosMapper;
import com.example.demo.model.dto.ArticulosAdquiridosDTO;
import com.example.demo.service.ArticulosAdquiridosService;
import com.example.demo.serviceImpl.articulosAdquiridos.testDataBuilder.ArticulosAdquiridosTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@SpringBootTest
public class ManejadorEliminarArticulosAdquiridosTest {

    public static final String EL_ARTICULO_ADQUIRIDO_SE_HA_ELIMINADO_EXITOSAMENTE = "El artículo adquirido se ha eliminado exitosamente.";
    public static final String EL_ARTICULO_ADQUIRIDO_CON_ESE_ID_NO_EXISTE = "El artrículo adquirido con ese id no existe.";
    public static final String SE_DEBE_INGRESAR_EL_ID_DEL_ARTICULO_ADQUIRIDO = "Se debe ingresar el id del artículo adquirido.";
    public static final Long ID_ARTICULO_ADQUIRIDO = 234984L;
    public static final Long ID_ARTICULO_ADQUIRIDO_MALO = 2384L;
    @MockBean
    private ArticulosAdquiridosDAO articulosAdquiridosDAO;
    @Autowired
    ArticulosAdquiridosService articulosAdquiridosService;

    @Autowired
    ArticulosAdquiridosMapper articulosAdquiridosMapper;

    @Test
    @DisplayName("Debería eliminar de manera exitosa un articulo adquirido")
    void deberiaEliminatArticuloAdquiridoExitosamente()throws Exception{

        ArticulosAdquiridosDTO articulosAdquiridosDTO = new ArticulosAdquiridosTestDataBuilder()
                .conIdArticuloAdquirido(ID_ARTICULO_ADQUIRIDO)
                .build();

        Mockito.when(articulosAdquiridosDAO.existsById(articulosAdquiridosDTO.getIdArticuloAdquirido())).thenReturn(true);
        String articuloAdquiridoEliminado = articulosAdquiridosService.eliminar(articulosAdquiridosDTO.getIdArticuloAdquirido());
        assertEquals(EL_ARTICULO_ADQUIRIDO_SE_HA_ELIMINADO_EXITOSAMENTE,articuloAdquiridoEliminado);
    }

    @Test
    @DisplayName("Debería fallar por idArticuloAdquirido null")
    void deberiaFallarPorIdArticuloAdquiridoNull()throws Exception{

        ArticulosAdquiridosDTO articulosAdquiridosDTO = new ArticulosAdquiridosTestDataBuilder()
                .conIdArticuloAdquirido(null)
                .build();
        //Mockito.when(articulosAdquiridosDAO.existsById(articulosAdquiridosDTO.getIdArticuloAdquirido())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, ()->{
            articulosAdquiridosService.eliminar(articulosAdquiridosDTO.getIdArticuloAdquirido());
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_EL_ID_DEL_ARTICULO_ADQUIRIDO);
    }
    @Test
    @DisplayName("Debería fallar por idArticuloAdquirido no existe")
    void deberiaFallarPorIdArticuloAdquiridoNoExiste()throws Exception{

        ArticulosAdquiridosDTO articulosAdquiridosDTO = new ArticulosAdquiridosTestDataBuilder()
                .conIdArticuloAdquirido(ID_ARTICULO_ADQUIRIDO_MALO)
                .build();
        Mockito.when(articulosAdquiridosDAO.existsById(articulosAdquiridosDTO.getIdArticuloAdquirido())).thenReturn(false);
        Exception exception = assertThrows(Exception.class, ()->{
            articulosAdquiridosService.eliminar(articulosAdquiridosDTO.getIdArticuloAdquirido());
        });
        assertEquals(exception.getMessage(), EL_ARTICULO_ADQUIRIDO_CON_ESE_ID_NO_EXISTE);
    }
}
