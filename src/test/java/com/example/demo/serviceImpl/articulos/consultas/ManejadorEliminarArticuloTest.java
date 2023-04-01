package com.example.demo.serviceImpl.articulos.consultas;

import com.example.demo.dao.ArticulosAdquiridosDAO;
import com.example.demo.dao.ArticulosDAO;
import com.example.demo.dao.CategoriaDAO;
import com.example.demo.dao.EstadoDAO;
import com.example.demo.mapper.ArticulosMapper;
import com.example.demo.model.ArticulosAdquiridos;
import com.example.demo.model.dto.ArticulosDTO;
import com.example.demo.service.ArticulosService;
import com.example.demo.serviceImpl.articulos.testDataBuilder.ArticuloTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@SpringBootTest
public class ManejadorEliminarArticuloTest {

    public static final String EL_ARTICULO_SE_HA_ELIMINADO_EXITOSAMENTE = "El artículo se ha eliminado exitosamente.";
    public static final String SE_DEBE_INGRESAR_EL_ID_DEL_ARTICULO = "Se debe ingresar el id del artículo.";
    public static final String EL_ARTICULO_CON_ESE_ID_NO_EXISTE = "El artículo con ese id no existe.";
    public static final String NO_SE_PUEDE_ELIMINAR_EL_ARTICULO_PORQUE_SE_ENCUENTRA_ASOCIADO_A_UN_ARTICULO_ADQUIRIDO = "No se puede eliminar el artículo porque se encuentra asociado a un articulo adquirido de un estudiante.";
    @Autowired
    ArticulosService articulosService;

    @MockBean
    private ArticulosDAO articulosDAO;

    @MockBean
    private CategoriaDAO categoriaDAO;

    @MockBean
    private ArticulosAdquiridosDAO articulosAdquiridosDAO;

    @Autowired
    ArticulosMapper articulosMapper;

    @Test
    @DisplayName("Deberia eliminarse exitosamente un articulo")
    void deberiaEliminarArticuloExitosamente() throws Exception {

        ArticulosDTO articulosDTO = new ArticuloTestDataBuilder().conIdArticulo(267367L).build();
        Mockito.when(articulosDAO.existsById(articulosDTO.getIdArticulo())).thenReturn(true);
        Mockito.when(articulosAdquiridosDAO.findByIdArticulo(articulosDTO.getIdArticulo())).thenReturn(Collections.emptyList());
        String articuloEliminado = articulosService.eliminar(articulosDTO.getIdArticulo());
        Mockito.verify(articulosDAO, Mockito.times(1)).deleteById(articulosDTO.getIdArticulo());
        assertEquals(EL_ARTICULO_SE_HA_ELIMINADO_EXITOSAMENTE, articuloEliminado);

    }
    @Test
    @DisplayName("Deberia fallar por idArticulo null")
    void deberiaFallarPorIdArticuloNull() throws Exception {

        ArticulosDTO articulosDTO = new ArticuloTestDataBuilder().conIdArticulo(null).build();
        Exception exception = assertThrows(Exception.class, ()->{
            articulosService.eliminar(articulosDTO.getIdArticulo());
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_EL_ID_DEL_ARTICULO);

    }

    @Test
    @DisplayName("Deberia fallar porque idArticulo no existe")
    void deberiaFallarPorIdArticuloNoExiste() throws Exception {

        ArticulosDTO articulosDTO = new ArticuloTestDataBuilder().conIdArticulo(5857L).build();
        Mockito.when(articulosDAO.existsById(articulosDTO.getIdArticulo())).thenReturn(false);
        Exception exception = assertThrows(Exception.class, ()->{
            articulosService.eliminar(articulosDTO.getIdArticulo());
        });
        assertEquals(exception.getMessage(), EL_ARTICULO_CON_ESE_ID_NO_EXISTE);

    }

    @Test
    @DisplayName("Deberia fallar cuando idArticulo esta siendo utilizado por un articulo adquirido de un estudiante")
    void deberiaFallarPorIdArticuloUsadoPorArticuloAdquirido() throws Exception {

        ArticulosDTO articulosDTO = new ArticuloTestDataBuilder().conIdArticulo(267367L).build();
        Mockito.when(articulosDAO.existsById(articulosDTO.getIdArticulo())).thenReturn(true);
        Mockito.when(articulosAdquiridosDAO.findByIdArticulo(articulosDTO.getIdArticulo())).thenReturn(Collections.singletonList(new ArticulosAdquiridos()));
        Exception exception = assertThrows(Exception.class, ()->{
            articulosService.eliminar(articulosDTO.getIdArticulo());
        });
        assertEquals(exception.getMessage(), NO_SE_PUEDE_ELIMINAR_EL_ARTICULO_PORQUE_SE_ENCUENTRA_ASOCIADO_A_UN_ARTICULO_ADQUIRIDO);

    }

}
