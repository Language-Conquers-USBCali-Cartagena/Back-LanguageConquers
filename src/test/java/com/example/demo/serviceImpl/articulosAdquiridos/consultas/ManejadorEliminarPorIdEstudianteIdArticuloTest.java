package com.example.demo.serviceImpl.articulosAdquiridos.consultas;

import com.example.demo.dao.ArticulosAdquiridosDAO;
import com.example.demo.dao.ArticulosDAO;
import com.example.demo.dao.EstudianteDAO;
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
public class ManejadorEliminarPorIdEstudianteIdArticuloTest {
    public static final String EL_ESTUDIANTE_CON_ESE_ID_NO_EXISTE = "El estudiante con ese id no existe.";
    public static final String EL_ARTICULO_CON_ESE_ID_NO_EXISTE = "El articulo con ese id no existe.";
    public static final String NO_SE_ENCONTRO_NINGUN_REGISTRO_CORRESPONDIENTE_EN_LA_BASE_DE_DATOS = "No se encontró ningún registro correspondiente en la base de datos.";
    public static final String SE_ELIMINO_EL_ARTICULO_ADQUIRIDO_CORRECTAMENTE = "Se elimino el articulo correctamente";
    public static final Long ID_ARTICULO_ADQUIRIDO = 234984L;
    public static final Long ID_ESTUDIANTE = 23224L;
    public static final Long ID_ESTUDIANTE_NO_EXISTE = 2553224L;
    public static final Long ID_ARTICULO = 2384L;
    @MockBean
    private ArticulosAdquiridosDAO articulosAdquiridosDAO;

    @MockBean
    private EstudianteDAO estudianteDAO;

    @MockBean
    private ArticulosDAO articulosDAO;
    @Autowired
    ArticulosAdquiridosService articulosAdquiridosService;

    @Autowired
    ArticulosAdquiridosMapper articulosAdquiridosMapper;

    @Test
    @DisplayName("Debería eliminar el articulo adquirido exitosamente")
    void deberiaEliminarArticuloAdquiridoExitosamente()throws Exception{

        ArticulosAdquiridosDTO articulosAdquiridosDTO = new ArticulosAdquiridosTestDataBuilder()
                .conIdArticuloAdquirido(ID_ARTICULO_ADQUIRIDO)
                .conIdEstudiante(ID_ESTUDIANTE)
                .conIdArticulos(ID_ARTICULO)
                .build();
        ArticulosAdquiridos articulosAdquiridos = articulosAdquiridosMapper.toEntity(articulosAdquiridosDTO);
        Mockito.when(estudianteDAO.existsById(articulosAdquiridosDTO.getIdEstudiante())).thenReturn(true);
        Mockito.when(articulosDAO.existsById(articulosAdquiridosDTO.getIdArticulos())).thenReturn(true);
        Mockito.when(articulosAdquiridosDAO.findByIdArticuloAndIdEstudiante(articulosAdquiridosDTO.getIdEstudiante(), articulosAdquiridosDTO.getIdArticulos())).thenReturn(articulosAdquiridos);
        String articuloAdquiridoEliminado = articulosAdquiridosService.eliminarPorIds(articulosAdquiridosDTO.getIdEstudiante(), articulosAdquiridosDTO.getIdArticulos());
        assertEquals(SE_ELIMINO_EL_ARTICULO_ADQUIRIDO_CORRECTAMENTE,articuloAdquiridoEliminado);
    }

    @Test
    @DisplayName("Debería fallar por idEstudiante no existente")
    void deberiaFallarPorIdEstudainteNoExiste()throws Exception{

        ArticulosAdquiridosDTO articulosAdquiridosDTO = new ArticulosAdquiridosTestDataBuilder()
                .conIdArticuloAdquirido(ID_ARTICULO_ADQUIRIDO)
                .conIdEstudiante(ID_ESTUDIANTE_NO_EXISTE)
                .conIdArticulos(ID_ARTICULO)
                .build();
        Mockito.when(estudianteDAO.existsById(articulosAdquiridosDTO.getIdEstudiante())).thenReturn(false);
        Exception exception = assertThrows(Exception.class, ()->{
            articulosAdquiridosService.eliminarPorIds(articulosAdquiridosDTO.getIdEstudiante(), articulosAdquiridosDTO.getIdArticulos());
        });
        assertEquals(exception.getMessage(), EL_ESTUDIANTE_CON_ESE_ID_NO_EXISTE);
    }
    @Test
    @DisplayName("Debería fallar por idArticulo no existente")
    void deberiaFallarPorIdArticuloNoExiste()throws Exception{

        ArticulosAdquiridosDTO articulosAdquiridosDTO = new ArticulosAdquiridosTestDataBuilder()
                .conIdArticuloAdquirido(ID_ARTICULO_ADQUIRIDO)
                .conIdEstudiante(ID_ESTUDIANTE)
                .conIdArticulos(ID_ARTICULO)
                .build();
        Mockito.when(estudianteDAO.existsById(articulosAdquiridosDTO.getIdEstudiante())).thenReturn(true);
        Mockito.when(articulosDAO.existsById(articulosAdquiridosDTO.getIdArticulos())).thenReturn(false);
        Exception exception = assertThrows(Exception.class, ()->{
            articulosAdquiridosService.eliminarPorIds(articulosAdquiridosDTO.getIdEstudiante(), articulosAdquiridosDTO.getIdArticulos());
        });
        assertEquals(exception.getMessage(), EL_ARTICULO_CON_ESE_ID_NO_EXISTE);
    }
    @Test
    @DisplayName("Debería fallar por no devolver el articulo articulo")
    void deberiaFallarPorNoDevolverArticuloAdquirido()throws Exception{

        ArticulosAdquiridosDTO articulosAdquiridosDTO = new ArticulosAdquiridosTestDataBuilder()
                .conIdArticuloAdquirido(ID_ARTICULO_ADQUIRIDO)
                .conIdEstudiante(ID_ESTUDIANTE)
                .conIdArticulos(ID_ARTICULO)
                .build();
        ArticulosAdquiridos articulosAdquiridos = articulosAdquiridosMapper.toEntity(articulosAdquiridosDTO);
        Mockito.when(estudianteDAO.existsById(articulosAdquiridosDTO.getIdEstudiante())).thenReturn(true);
        Mockito.when(articulosDAO.existsById(articulosAdquiridosDTO.getIdArticulos())).thenReturn(true);
        Mockito.when(articulosAdquiridosDAO.findByIdArticuloAndIdEstudiante(articulosAdquiridosDTO.getIdEstudiante(), articulosAdquiridosDTO.getIdArticulos())).thenReturn(null);

        Exception exception = assertThrows(Exception.class, ()->{
            articulosAdquiridosService.eliminarPorIds(articulosAdquiridosDTO.getIdEstudiante(), articulosAdquiridosDTO.getIdArticulos());
        });
        assertEquals(exception.getMessage(), NO_SE_ENCONTRO_NINGUN_REGISTRO_CORRESPONDIENTE_EN_LA_BASE_DE_DATOS);
    }

}
