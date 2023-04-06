package com.example.demo.serviceImpl.categoria.consultas.consultas;

import com.example.demo.dao.ArticulosDAO;
import com.example.demo.dao.CategoriaDAO;
import com.example.demo.dao.EstadoDAO;
import com.example.demo.mapper.CategoriaMapper;
import com.example.demo.model.Articulos;
import com.example.demo.model.dto.CategoriaDTO;
import com.example.demo.service.CategoriaService;
import com.example.demo.serviceImpl.categoria.testDataBuilder.CategoriaTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@SpringBootTest
public class ManejadorEliminarCategoriaTest {

    private static final String SE_ELIMINO_EXITOSAMENTE_LA_CATEGORIA = "Se elimino exitosamente la categoría.";
    private static final String EL_ID_DE_LA_CATEGORIA_ES_OBLIGATORIA = "El id de la categoría es obligatorio.";
    private static final String NO_SE_ENCONTRO_LA_CATEGORIA_CON_ESE_ID = "No se encontró la categoría con ese id.";
    private static final String NO_SE_PUEDE_ELIMINAR_LA_CATEGORIA_PORQUE_ESTA_SIENDO_UTILIZADA_POR_UN_ARTICULO = "No se puede eliminar la categoría porque esta siendo utilizada en un artículo.";

    @Autowired
    CategoriaMapper categoriaMapper;

    @Autowired
    CategoriaService categoriaService;

    @MockBean
    private CategoriaDAO categoriaDAO;

    @MockBean
    private EstadoDAO estadoDAO;

    @MockBean
    private ArticulosDAO articulosDAO;

    @Test
    @DisplayName("Deberia eliminar el articulo exitosamente")
    void deberiaEliminarArticuloExistente()throws Exception{
        CategoriaDTO categoriaDTO = new CategoriaTestDataBuilder().conIdCategoria(764L).build();
        Mockito.when(categoriaDAO.existsById(categoriaDTO.getIdCategoria())).thenReturn(true);
        Mockito.when(articulosDAO.findByIdCategoria(categoriaDTO.getIdCategoria())).thenReturn(Collections.emptyList());
        String categoriaEliminado = categoriaService.eliminar(categoriaDTO.getIdCategoria());
        Mockito.verify(categoriaDAO, Mockito.times(1)).deleteById(categoriaDTO.getIdCategoria());
        assertEquals(SE_ELIMINO_EXITOSAMENTE_LA_CATEGORIA, categoriaEliminado);
    }
    @Test
    @DisplayName("Deberia fallar por idCategoria null")
    void deberiaFallarPorIdCategoria()throws Exception{
        CategoriaDTO categoriaDTO = new CategoriaTestDataBuilder().conIdCategoria(null).build();
        Exception exception = assertThrows(Exception.class, ()->{
            categoriaService.eliminar(categoriaDTO.getIdCategoria());
        });
        assertEquals(exception.getMessage(),EL_ID_DE_LA_CATEGORIA_ES_OBLIGATORIA );
    }
    @Test
    @DisplayName("Deberia fallar por idCategoria no existente")
    void deberiaFallarPorIdCategoriaNoExistente()throws Exception{
        CategoriaDTO categoriaDTO = new CategoriaTestDataBuilder().conIdCategoria(75L).build();
        Mockito.when(categoriaDAO.existsById(categoriaDTO.getIdCategoria())).thenReturn(false);
        Exception exception = assertThrows(Exception.class, ()->{
            categoriaService.eliminar(categoriaDTO.getIdCategoria());
        });
        assertEquals(exception.getMessage(),NO_SE_ENCONTRO_LA_CATEGORIA_CON_ESE_ID);
    }
    @Test
    @DisplayName("Deberia fallar por idCategoria utilizado en un articulo")
    void deberiaFallarPorIdCategoriaUsadoEnArticulo()throws Exception{
        CategoriaDTO categoriaDTO = new CategoriaTestDataBuilder().conIdCategoria(75L).build();
        Mockito.when(categoriaDAO.existsById(categoriaDTO.getIdCategoria())).thenReturn(true);
        Mockito.when(articulosDAO.findByIdCategoria(categoriaDTO.getIdCategoria())).thenReturn(Collections.singletonList(new Articulos()));

        Exception exception = assertThrows(Exception.class, ()->{
            categoriaService.eliminar(categoriaDTO.getIdCategoria());
        });
        assertEquals(exception.getMessage(),NO_SE_PUEDE_ELIMINAR_LA_CATEGORIA_PORQUE_ESTA_SIENDO_UTILIZADA_POR_UN_ARTICULO);
    }
}

