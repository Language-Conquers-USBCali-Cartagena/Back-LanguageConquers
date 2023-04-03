package com.example.demo.serviceImpl.articulosAdquiridos.consultas;

import com.example.demo.dao.ArticulosAdquiridosDAO;
import com.example.demo.mapper.ArticulosAdquiridosMapper;
import com.example.demo.model.ArticulosAdquiridos;
import com.example.demo.model.dto.ArticulosAdquiridosDTO;
import com.example.demo.service.ArticulosAdquiridosService;
import com.example.demo.serviceImpl.articulosAdquiridos.testDataBuilder.ArticulosAdquiridosTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.input.LineSeparatorDetector;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ManejadorListarArticulosAdquiridosTest {

    public static final String NO_HAY_ARTICULOS_ADQUIRIDOS_DISPONIBLES = "No hay artículos adquiridos disponibles.";
    public static final Long ID_ARTICULO_ADQUIRIDO = 734984L;
    public static final Long ID_ARTICULO = 2344984L;
    public static final Long ID_ESTUDIANTE = 238734L;
    public static final Long ID_ARTICULO_ADQUIRIDO2 = 554984L;
    public static final Long ID_ARTICULO2 = 2384L;
    public static final Long ID_ESTUDIANTE2 = 2334L;

    public static final String USUARIO_CREADOR = "Angela";

    @MockBean
    private ArticulosAdquiridosDAO articulosAdquiridosDAO;
    @Autowired
    ArticulosAdquiridosService articulosAdquiridosService;

    @Autowired
    ArticulosAdquiridosMapper articulosAdquiridosMapper;

    @Test
    @DisplayName("Debería listar los articulos adquiridos disponibles")
    void deberiaListarArticulosAdquiridosDisponibles()throws Exception{

        List<ArticulosAdquiridos> articulosAdquiridosList = new ArrayList<>();
        ArticulosAdquiridosDTO articulosAdquiridosDTO1 = new ArticulosAdquiridosTestDataBuilder()
                .conIdArticuloAdquirido(ID_ARTICULO_ADQUIRIDO)
                .conIdArticulos(ID_ARTICULO)
                .conIdEstudiante(ID_ESTUDIANTE)
                .conUsuarioCreador(USUARIO_CREADOR)
                .conFechaCreacion(new Date())
                .build();
        ArticulosAdquiridos articulosAdquiridos1 = articulosAdquiridosMapper.toEntity(articulosAdquiridosDTO1);
        articulosAdquiridosList.add(articulosAdquiridos1);
        ArticulosAdquiridosDTO articulosAdquiridosDTO2 = new ArticulosAdquiridosTestDataBuilder()
                .conIdArticuloAdquirido(ID_ARTICULO_ADQUIRIDO2)
                .conIdArticulos(ID_ARTICULO2)
                .conIdEstudiante(ID_ESTUDIANTE2)
                .conUsuarioCreador(USUARIO_CREADOR)
                .conFechaCreacion(new Date())
                .build();
        ArticulosAdquiridos articulosAdquiridos2 =articulosAdquiridosMapper.toEntity(articulosAdquiridosDTO2);
        articulosAdquiridosList.add(articulosAdquiridos2);

        Mockito.when(articulosAdquiridosDAO.findAll()).thenReturn(articulosAdquiridosList);
        List<ArticulosAdquiridos> articulosAdquiridos = articulosAdquiridosService.findAll();
        assertEquals(articulosAdquiridosList,articulosAdquiridos);
    }

    @Test
    @DisplayName("Deberia mostrar un mensaje cuando no hay articulos adquiridos")
    void deberiaDevolverListaVaciaCuandoNoHayArticulosAdquiridos()throws Exception{

        List<ArticulosAdquiridos> articulosAdquiridosList = new ArrayList<>();
        Mockito.when(articulosAdquiridosDAO.findAll()).thenReturn(articulosAdquiridosList);
        Exception exception = assertThrows(Exception.class, ()->{
            articulosAdquiridosService.findAll();
        });
        assertEquals(exception.getMessage(), NO_HAY_ARTICULOS_ADQUIRIDOS_DISPONIBLES);

    }



}
