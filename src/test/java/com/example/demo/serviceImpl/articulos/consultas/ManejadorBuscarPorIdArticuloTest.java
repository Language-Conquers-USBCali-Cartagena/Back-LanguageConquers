package com.example.demo.serviceImpl.articulos.consultas;

import com.example.demo.dao.ArticulosDAO;
import com.example.demo.dao.CategoriaDAO;
import com.example.demo.dao.EstadoDAO;
import com.example.demo.mapper.ArticulosMapper;
import com.example.demo.model.Articulos;
import com.example.demo.model.dto.ArticulosDTO;
import com.example.demo.service.ArticulosService;
import com.example.demo.serviceImpl.articulos.testDataBuilder.ArticuloTestDataBuilder;
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
public class ManejadorBuscarPorIdArticuloTest {

    public static final String SE_DEBE_INGRESAR_EL_ID_DEL_ARTICULO = "Se debe ingresar el id del artículo.";
    public static final String EL_ARTICULO_CON_ESE_ID_NO_EXISTE= "El articulo con ese id no existe.";
    @Autowired
    ArticulosService articulosService;

    @MockBean
    private ArticulosDAO articulosDAO;

    @Autowired
    ArticulosMapper articulosMapper;

    @Test
    @DisplayName("Deberia mostrar el articulo.")
    void deberiaMostrarArticulo()throws Exception{
        ArticulosDTO articulosDTO = new ArticuloTestDataBuilder().conIdArticulo(267367L).build();
        Articulos articulos = articulosMapper.toEntity(articulosDTO);
        Mockito.when(articulosDAO.existsById(articulosDTO.getIdArticulo())).thenReturn(true);
        Mockito.when(articulosDAO.findById(articulosDTO.getIdArticulo())).thenReturn(Optional.of(articulos));
        Articulos articulo = articulosService.findById(articulosDTO.getIdArticulo());
        assertEquals(articulosDTO.getIdArticulo(), articulo.getIdArticulo());
    }
    @Test
    @DisplayName("Deberia fallar por el idArticulo null.")
    void deberiaFallarPorIdArticuloNull()throws Exception{
        ArticulosDTO articulosDTO = new ArticuloTestDataBuilder().conIdArticulo(null).build();
        Exception exception = assertThrows(Exception.class, ()->{
            articulosService.findById(articulosDTO.getIdArticulo());
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_EL_ID_DEL_ARTICULO);
    }
    @Test
    @DisplayName("Deberia fallar por el idArticulo no existe.")
    void deberiaFallarPorIdArticuloNoExiste()throws Exception{
        ArticulosDTO articulosDTO = new ArticuloTestDataBuilder().conIdArticulo(584L).build();
        Mockito.when(articulosDAO.existsById(articulosDTO.getIdArticulo())).thenReturn(false);
        Exception exception = assertThrows(Exception.class, ()->{
            articulosService.findById(articulosDTO.getIdArticulo());
        });
        assertEquals(exception.getMessage(), EL_ARTICULO_CON_ESE_ID_NO_EXISTE);
    }
}
