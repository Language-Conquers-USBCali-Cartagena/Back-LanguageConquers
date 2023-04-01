package com.example.demo.serviceImpl.articulosAdquiridos.consultas;

import com.example.demo.dao.ArticulosAdquiridosDAO;
import com.example.demo.dao.ArticulosDAO;
import com.example.demo.dao.EstudianteDAO;
import com.example.demo.mapper.ArticulosAdquiridosMapper;
import com.example.demo.model.Articulos;
import com.example.demo.model.ArticulosAdquiridos;
import com.example.demo.model.Estudiante;
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
public class ManejadorCrearArticulosAdquiridosTest {

    public static final String SE_CREO_EXITOSAMENTE_EL_ARTICULO_ADQUIRIDO = "Se creo correctamente el artículo adquirido.";
    public static final String DEBE_INGRESAR_UN_ARTICULO_VALIDO = "Debe ingresar un artículo válido.";
    public static final String DEBE_INGRESAR_UN_ID_ARTICULO_QUE_ESTE_REGISTRADO = "Debe ingresar un id Articulo que este registrado.";
    public static final String DEBE_INGRESAR_UN_ESTUDIANTE_VALIDO = "Debe ingresar un estudiante válido.";
    public static final String DEBE_INGRESAR_EL_ID_DE_UN_ESTUDIANTE_QUE_ESTE_REGISTRADO = "Debe ingresar el id de un estudiante que este registrado.";
    public static final String SE_DEBE_INGRESAR_UN_USUARIO_CREADOR_DEL_ARTICULO_VALIDO = "Se debe ingresar un usuario creador del artículo válido.";
    public static final String SE_DEBE_INGRESAR_UNA_FECHA_VALIDA = "Se debe ingresar una fecha válida.";
    public static final String NO_PUEDE_INGRESAR_UNA_FECHA_QUE_AUN_NO_HA_SUCEDIDO = "No puede ingresar una fecha que aun no ha sucedido.";

    @MockBean
    private  ArticulosAdquiridosDAO articulosAdquiridosDAO;
    @MockBean
    private EstudianteDAO estudianteDAO;
    @MockBean
    private ArticulosDAO articulosDAO;

    @Autowired
    ArticulosAdquiridosService articulosAdquiridosService;

    @Autowired
    ArticulosAdquiridosMapper articulosAdquiridosMapper;

    @Test
    @DisplayName("Debería crear un articulo adquirido exitosamente")
    void deberiaCrearArticuloAdquiridoExitosamente()throws Exception{
        ArticulosAdquiridosDTO articulosAdquiridosDTO = new ArticulosAdquiridosTestDataBuilder().build();
        ArticulosAdquiridos articulosAdquiridos = articulosAdquiridosMapper.toEntity(articulosAdquiridosDTO);
        Mockito.when(articulosDAO.findById(articulosAdquiridosDTO.getIdArticulos())).thenReturn(Optional.of(new Articulos()));
        Mockito.when(estudianteDAO.findById(articulosAdquiridosDTO.getIdEstudiante())).thenReturn(Optional.of(new Estudiante()));
        Mockito.when(articulosAdquiridosDAO.save(Mockito.any())).thenReturn(articulosAdquiridos);
        String crearArticuloAdquirido = articulosAdquiridosService.registrar(articulosAdquiridos);
        assertEquals(SE_CREO_EXITOSAMENTE_EL_ARTICULO_ADQUIRIDO, crearArticuloAdquirido);
    }

    @Test
    @DisplayName("Debería fallar por idEstudiante null ")
    void deberiaFallarPorIdEstudianteNull()throws Exception{

        ArticulosAdquiridosDTO articulosAdquiridosDTO = new ArticulosAdquiridosTestDataBuilder()
                .conIdEstudiante(null).build();
        ArticulosAdquiridos articulosAdquiridos = articulosAdquiridosMapper.toEntity(articulosAdquiridosDTO);
        Mockito.when(articulosAdquiridosDAO.save(Mockito.any())).thenReturn(articulosAdquiridos);
        Exception exception = assertThrows(Exception.class, ()->{
            articulosAdquiridosService.registrar(articulosAdquiridos);
        });
        assertEquals(exception.getMessage(),DEBE_INGRESAR_UN_ESTUDIANTE_VALIDO );
    }

    @Test
    @DisplayName("Debería fallar por idEstudiante negativo")
    void deberiaFallarPorIdEstudianteNegativo()throws Exception{

        ArticulosAdquiridosDTO articulosAdquiridosDTO = new ArticulosAdquiridosTestDataBuilder()
                .conIdEstudiante(-739L).build();
        ArticulosAdquiridos articulosAdquiridos = articulosAdquiridosMapper.toEntity(articulosAdquiridosDTO);
        Mockito.when(articulosAdquiridosDAO.save(Mockito.any())).thenReturn(articulosAdquiridos);
        Exception exception = assertThrows(Exception.class, ()->{
            articulosAdquiridosService.registrar(articulosAdquiridos);
        });
        assertEquals(exception.getMessage(),DEBE_INGRESAR_UN_ESTUDIANTE_VALIDO );
    }


    @Test
    @DisplayName("Debería fallar por idEstudiante no existe ")
    void deberiaFallarPorIdEstudianteNoExiste()throws Exception{

        ArticulosAdquiridosDTO articulosAdquiridosDTO = new ArticulosAdquiridosTestDataBuilder()
                .conIdEstudiante(8523L).build();
        ArticulosAdquiridos articulosAdquiridos = articulosAdquiridosMapper.toEntity(articulosAdquiridosDTO);
        Mockito.when(articulosDAO.existsById(articulosAdquiridosDTO.getIdArticulos())).thenReturn(true);
        Mockito.when(articulosDAO.findById(articulosAdquiridosDTO.getIdArticulos())).thenReturn(Optional.of(new Articulos()));
        Mockito.when(estudianteDAO.existsById(articulosAdquiridosDTO.getIdEstudiante())).thenReturn(false);
        //Mockito.when(estudianteDAO.findById(articulosAdquiridosDTO.getIdEstudiante())).thenReturn(Optional.of(new Estudiante()));
        Mockito.when(articulosAdquiridosDAO.save(Mockito.any())).thenReturn(articulosAdquiridos);
        Exception exception = assertThrows(Exception.class, ()->{
            articulosAdquiridosService.registrar(articulosAdquiridos);
        });
        assertEquals(exception.getMessage(),DEBE_INGRESAR_EL_ID_DE_UN_ESTUDIANTE_QUE_ESTE_REGISTRADO );
    }
//    @Test
//    @DisplayName("Debería fallar por idArticulo negativo.")
//    void deberiaFallarPorIdArticuloNegativo()throws Exception{
//
//        ArticulosAdquiridosDTO articulosAdquiridosDTO = new ArticulosAdquiridosTestDataBuilder()
//                .conIdArticulos(-789L).build();
//        ArticulosAdquiridos articulosAdquiridos = articulosAdquiridosMapper.toEntity(articulosAdquiridosDTO);
//        Mockito.when(articulosAdquiridosDAO.save(Mockito.any())).thenReturn(articulosAdquiridos);
//        Exception exception = assertThrows(Exception.class, ()->{
//            articulosAdquiridosService.registrar(articulosAdquiridos);
//        });
//        assertEquals(exception.getMessage(),DEBE_INGRESAR_UN_ARTICULO_VALIDO );
//    }
//    @Test
//    @DisplayName("Debería fallar por idArticulo no existente")
//    void deberiaFallarPorIdArticuloNoExistente()throws Exception{
//
//        ArticulosAdquiridosDTO articulosAdquiridosDTO = new ArticulosAdquiridosTestDataBuilder()
//                .conIdArticulos(74589L).build();
//        ArticulosAdquiridos articulosAdquiridos = articulosAdquiridosMapper.toEntity(articulosAdquiridosDTO);
//        Mockito.when(articulosDAO.findById(articulosAdquiridosDTO.getIdArticulos())).thenReturn(Optional.of(new Articulos()));
//        Mockito.when(estudianteDAO.findById(articulosAdquiridosDTO.getIdEstudiante())).thenReturn(Optional.of(new Estudiante()));
//        Mockito.when(articulosAdquiridosDAO.save(Mockito.any())).thenReturn(articulosAdquiridos);
//        Exception exception = assertThrows(Exception.class, ()->{
//            articulosAdquiridosService.registrar(articulosAdquiridos);
//        });
//        assertEquals(exception.getMessage(),DEBE_INGRESAR_UN_ID_ARTICULO_QUE_ESTE_REGISTRADO );
//    }
}
