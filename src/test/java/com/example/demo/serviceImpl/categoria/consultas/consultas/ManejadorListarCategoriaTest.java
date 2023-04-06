package com.example.demo.serviceImpl.categoria.consultas.consultas;

import com.example.demo.dao.CategoriaDAO;
import com.example.demo.dao.EstadoDAO;
import com.example.demo.mapper.CategoriaMapper;
import com.example.demo.model.Categoria;
import com.example.demo.model.dto.CategoriaDTO;
import com.example.demo.service.CategoriaService;
import com.example.demo.serviceImpl.categoria.testDataBuilder.CategoriaTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ManejadorListarCategoriaTest {

    @Autowired
    CategoriaMapper categoriaMapper;

    @Autowired
    CategoriaService categoriaService;

    @MockBean
    private CategoriaDAO categoriaDAO;

    @MockBean
    private EstadoDAO estadoDAO;

    @Test
    @DisplayName("Deberia listar las categorias existentes")
    void deberiaListarArticulosExitosamente()throws Exception{
        List<Categoria> categoriaList = new ArrayList<>();
        CategoriaDTO categoriaDTO1 = new CategoriaTestDataBuilder()
                .conNombre("Zapatos")
                .conDescripcion("Categoria de calzado")
                .conIdEstado(34L)
                .conUsuarioCreador("Angela")
                .conFechaCreacion(new Date())
                .build();
        Categoria categoria1 = categoriaMapper.toEntity(categoriaDTO1);
        categoriaList.add(categoria1);
        CategoriaDTO categoriaDTO2 = new CategoriaTestDataBuilder()
                .conNombre("Accesorios")
                .conDescripcion("Categoria para accesorios")
                .conIdEstado(34L)
                .conUsuarioCreador("Angela")
                .conFechaCreacion(new Date())
                .build();
        Categoria categoria2 = categoriaMapper.toEntity(categoriaDTO2);
        categoriaList.add(categoria2);
        Mockito.when(categoriaDAO.findAll()).thenReturn(categoriaList);
        List<Categoria>categoriaList1 = categoriaService.findAll();
        assertEquals(categoriaList, categoriaList1);
    }

    @Test
    @DisplayName("Deberia devolver una lista vacia cuando no hay categorias")
    void deberiaDevolverListaVaciaCuandoNoHayCategorias()throws Exception{
        Mockito.when(categoriaDAO.findAll()).thenReturn(new ArrayList<>());
        List<Categoria> categoriaList = categoriaService.findAll();
        assertTrue(categoriaList.isEmpty());
    }
}
