package com.example.demo.serviceImpl.articulosAdquiridos.consultas;

import com.example.demo.dao.ArticulosAdquiridosDAO;
import com.example.demo.mapper.ArticulosAdquiridosMapper;
import com.example.demo.model.ArticulosAdquiridos;
import com.example.demo.model.dto.ArticulosAdquiridosDTO;
import com.example.demo.service.ArticulosAdquiridosService;
import com.example.demo.serviceImpl.articulosAdquiridos.testDataBuilder.ArticulosAdquiridosTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@SpringBootTest
public class ManejadorBuscarPorIdArticulosAdquiridosTest {

    public static final String SE_DEBE_INGRESAR_ID_DEL_ARTICULO_ADQUIRIDO = "Se debe ingresar el id del artículo adquirido.";
    public static final String EL_ARTICULO_ADQUIRIDO_CON_ESE_ID_NO_EXISTE = "El artículo adquirido con ese id no existe.";
    public static final Long ID_ARTICULO_ADQUIRIDO = 734984L;
    public static final Long ID_ARTICULO_ADQUIRIDO_NO_EXISTENTE = 744L;
    @MockBean
    private ArticulosAdquiridosDAO articulosAdquiridosDAO;
    @Autowired
    ArticulosAdquiridosService articulosAdquiridosService;

    @Autowired
    ArticulosAdquiridosMapper articulosAdquiridosMapper;

    @Test
    @DisplayName("Deberia mostrar el articulo adquirido exitosamente")
    void deberiaMostrarArticuloAdquiridoExitosamente()throws Exception{

        ArticulosAdquiridosDTO articulosAdquiridosDTO = new ArticulosAdquiridosTestDataBuilder()
                .conIdArticuloAdquirido(ID_ARTICULO_ADQUIRIDO)
                .build();
        ArticulosAdquiridos articulosAdquiridos = articulosAdquiridosMapper.toEntity(articulosAdquiridosDTO);

        Mockito.when(articulosAdquiridosDAO.existsById(articulosAdquiridos.getIdArticuloAdquirido())).thenReturn(true);
        Mockito.when(articulosAdquiridosDAO.findById(articulosAdquiridos.getIdArticuloAdquirido())).thenReturn(Optional.of(articulosAdquiridos));

        ArticulosAdquiridos articulosAdquiridosMostrar = articulosAdquiridosService.findById(articulosAdquiridos.getIdArticuloAdquirido());
        assertEquals(articulosAdquiridosDTO.getIdArticuloAdquirido(), articulosAdquiridosMostrar.getIdArticuloAdquirido());
    }

    @Test
    @DisplayName("Deberia fallar por IdArticuloAdquirido null")
    void deberiaFallarPorIdArticuloAdquiridoNull()throws Exception{

        ArticulosAdquiridosDTO articulosAdquiridosDTO = new ArticulosAdquiridosTestDataBuilder()
                .conIdArticuloAdquirido(null)
                .build();
        Exception exception = assertThrows(Exception.class, ()->{
            articulosAdquiridosService.findById(articulosAdquiridosDTO.getIdArticuloAdquirido());
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_ID_DEL_ARTICULO_ADQUIRIDO);
    }

    @Test
    @DisplayName("Deberia fallar por IdArticuloAdquirido no existe")
    void deberiaFallarPorIdArticuloAdquiridoNoExiste()throws Exception{

        ArticulosAdquiridosDTO articulosAdquiridosDTO = new ArticulosAdquiridosTestDataBuilder()
                .conIdArticuloAdquirido(ID_ARTICULO_ADQUIRIDO_NO_EXISTENTE)
                .build();
        Mockito.when(articulosAdquiridosDAO.existsById(articulosAdquiridosDTO.getIdArticuloAdquirido())).thenReturn(false);
        Exception exception = assertThrows(Exception.class, ()->{
            articulosAdquiridosService.findById(articulosAdquiridosDTO.getIdArticuloAdquirido());
        });
        assertEquals(exception.getMessage(), EL_ARTICULO_ADQUIRIDO_CON_ESE_ID_NO_EXISTE);
    }
}
