package com.example.demo.serviceImpl.categoria.consultas.consultas;

import com.example.demo.dao.CategoriaDAO;
import com.example.demo.dao.EstadoDAO;
import com.example.demo.mapper.CategoriaMapper;
import com.example.demo.model.Categoria;
import com.example.demo.model.Genero;
import com.example.demo.model.dto.CategoriaDTO;
import com.example.demo.model.dto.GeneroDTO;
import com.example.demo.service.CategoriaService;
import com.example.demo.serviceImpl.categoria.testDataBuilder.CategoriaTestDataBuilder;
import com.example.demo.serviceImpl.genero.testDataBuilder.GeneroTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
public class ManejadorBuscarPorIdCategoriaTest {

    private static final String DEBE_INGRESAR_EL_ID_DE_LA_CATEGORIA = "Debe ingresar el id de la categoría.";
    private static final String LA_CATEGORIA_CON_ESE_ID_NO_EXISTE = "La categoría con ese id no existe.";
    @Autowired
    CategoriaMapper categoriaMapper;

    @Autowired
    CategoriaService categoriaService;

    @MockBean
    private CategoriaDAO categoriaDAO;

    @MockBean
    private EstadoDAO estadoDAO;

    @Test
    @DisplayName("Debería validar la existencia de una categoria")
    void deberiaValidarLaExistenciaDeUnaCategoria()throws Exception{

        CategoriaDTO categoriaDTO = new CategoriaTestDataBuilder()
                .conIdCategoria(4343858L).build();

        Categoria categoria = categoriaMapper.toEntity(categoriaDTO);
        Mockito.when(categoriaDAO.existsById(4343858L)).thenReturn(true);
        Mockito.when(categoriaDAO.findById(categoriaDTO.getIdCategoria())).thenReturn(Optional.of(categoria));
        Categoria categoriaServicio = categoriaService.findById(categoria.getIdCategoria());
        assertNotNull(categoriaServicio);
        assertEquals(categoria.getIdCategoria(), categoriaServicio.getIdCategoria());

    }
    @Test
    @DisplayName("Debería lanzar una excepcion si la categoria es null")
    void deberiaFallarPorIdCategoriaNull()throws Exception{

        CategoriaDTO categoriaDTO = new CategoriaTestDataBuilder()
                .conIdCategoria(null).build();

        Exception exception = assertThrows(Exception.class, ()->{
            categoriaService.findById(categoriaDTO.getIdCategoria());
        });
        assertEquals(exception.getMessage(),DEBE_INGRESAR_EL_ID_DE_LA_CATEGORIA);

    }
    @Test
    @DisplayName("Debería lanzar una excepcion si la categoria no existe")
    void deberiaFallarPorIdCategoriaNoExiste()throws Exception{

        CategoriaDTO categoriaDTO = new CategoriaTestDataBuilder()
                .conIdCategoria(874l).build();
        Mockito.when(categoriaDAO.existsById(categoriaDTO.getIdCategoria())).thenReturn(false);
        Exception exception = assertThrows(Exception.class, ()->{
            categoriaService.findById(categoriaDTO.getIdCategoria());
        });
        assertEquals(exception.getMessage(),LA_CATEGORIA_CON_ESE_ID_NO_EXISTE);

    }
}
