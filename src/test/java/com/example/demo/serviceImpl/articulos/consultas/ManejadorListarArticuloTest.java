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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ManejadorListarArticuloTest {

    public static final String NO_HAY_ARTICULOS_DISPONIBLES = "No hay artículos disponibles.";
    @Autowired
    ArticulosService articulosService;

    @MockBean
    private ArticulosDAO articulosDAO;

    @Autowired
    ArticulosMapper articulosMapper;

    @Test
    @DisplayName("Debería listar los articulos existentes")
    void deberiaListarLosArticulosExistentes()throws Exception{
        List<Articulos> articulosList = new ArrayList<>();
        ArticulosDTO articulosDTO1 = new ArticuloTestDataBuilder()
                .conIdArticulo(34778L)
                .conNombre("Short")
                .conDescripcion("Prenda de vestir")
                .conImagen("short.png")
                .conPrecio(123.4)
                .conNivelValido(1)
                .conIdCategoria(1L)
                .conIdCategoria(2L).build();
        Articulos articulo1 = articulosMapper.toEntity(articulosDTO1);
        articulosList.add(articulo1);
        ArticulosDTO articulosDTO2 = new ArticuloTestDataBuilder()
                .conIdArticulo(3477458L)
                .conNombre("Blusa")
                .conDescripcion("Prenda de vestir")
                .conImagen("blusa.png")
                .conPrecio(12.4)
                .conNivelValido(1)
                .conIdCategoria(1L)
                .conIdCategoria(2L).build();
        Articulos articulo2 = articulosMapper.toEntity(articulosDTO2);
        articulosList.add(articulo2);
        Mockito.when(articulosDAO.findAll()).thenReturn(articulosList);
        List<Articulos> articulos = articulosService.findAll();
        assertEquals(articulosList, articulos);
    }

    @Test
    @DisplayName("Debería mostrar mensaje que no hay articulos disponibles")
    void deberiaMostrarMensajeListaVacia()throws Exception{
        List<Articulos> articulosList = new ArrayList<>();
        Mockito.when(articulosDAO.findAll()).thenReturn(articulosList);
        Exception exception = assertThrows(Exception.class, ()->{
             articulosService.findAll();
        });
        assertEquals(exception.getMessage(),NO_HAY_ARTICULOS_DISPONIBLES);
    }
}
