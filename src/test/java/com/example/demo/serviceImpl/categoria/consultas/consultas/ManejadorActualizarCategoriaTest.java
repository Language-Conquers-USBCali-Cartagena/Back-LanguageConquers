package com.example.demo.serviceImpl.categoria.consultas.consultas;

import com.example.demo.dao.CategoriaDAO;
import com.example.demo.dao.EstadoDAO;
import com.example.demo.mapper.CategoriaMapper;
import com.example.demo.model.Categoria;
import com.example.demo.model.Estado;
import com.example.demo.model.dto.CategoriaDTO;
import com.example.demo.service.CategoriaService;
import com.example.demo.serviceImpl.categoria.testDataBuilder.CategoriaTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@SpringBootTest
public class ManejadorActualizarCategoriaTest {

    public static final String SE_ACTUALIZO_EXITOSAMENTE_LA_CATEGORIA = "Se actualizo exitosamente la categoría.";
    public static final String DEBE_INGRESAR_EL_ID_DE_LA_CATEGORIA_QUE_DESEA_ACTUALIZAR = "Debe ingresar el id de la categoría que desea actualizar.";
    public static final String NO_EXISTE_UNA_CATEGORIA_CON_ESE_ID = "No existe una categoría con ese id.";
    public static final String SE_DEBE_INGRESAR_EL_NOMBRE_DE_LA_CATEGORIA = "Se debe ingresar el nombre de la categoría.";
    public static final String EL_NOMBRE_DE_LA_CATEGORIA_NO_DEBE_CONTENER_MAS_DE_50_CARACTERES = "El nombre de la categoría no debe contener más de 50 caracteres.";
    public static final String SE_DEBE_INGRESAR_UNA_DESCRIPCION_DE_LA_CATEGORIA = "Se debe ingresar una descripción de la categoría.";
    public static final String LA_DESCRIPCION_NO_DEBE_CONTENER_MAS_DE_200_CARACTERES = "La descripción no debe contener más de 200 caracteres.";
    public static final String SE_DEBE_INGRESAR_EL_ID_DEL_ESTADO = "Se debe ingresar el id del estado.";
    public static final String SE_DEBE_INGRESAR_UN_ID_DE_ESTADO_VALIDO = "Se debe ingresar un id de estado válido.";
    public static final String NO_EXISTE_UN_ESTADO_CON_EL_ID_INGRESADO_INGRESE_UNO_VALIDO = "No existe un estado con el id ingresado, ingrese un id válido.";
    public static final String SE_DEBE_INGRESAR_EL_USUARIO_MODIFICADOR = "Se debe ingresar el usuario modificador.";
    public static final String EL_USUARIO_MODIFICADOR_NO_PUEDE_CONTENER_MAS_DE_50_CARACTERES = "El usuario modificador no debe contener más de 50 caracteres.";
    public static final String SE_DEBE_INGRESAR_LA_FECHA_DE_MODIFICACION = "Se debe ingresar la fecha de modificación.";
    public static final String NOMBRE_CATEGORIA_MALO = "FH DFHJF JHFJHJF GHFRF F RYETUFTUTFREWYUB CTUERFTTCB F" ;
    public static final String NOMBRE_CATEGORIA = "Hogar";
    public static final String DESCRIPCION_MALA = "RFUIR FEURYERUUYF R EFRUEY REF REUIY REYUIY EY UIREY IU UYGERUIYUERIERYUIG ERUGHEUHUITGUIERIUGHERJGHEGR  YG EUIYERUITH JTREHI UTJHYGTDJJFJH G FERGUFHJV JHFDSUF UREJHFJHFJH DFHSD JRHDUUYDUY YDEGDHUSGD DGSDGHS SDFYSGFF";
    public static final String USUARIO_MODIFICADOR_MALO = "SDHS GDFJHSDGFJ FJHJDH DGFHGHJD DGHJDG GHG JDGHDGSJH JHDJH";
    public static final String USUARIO_MODIFICADOR = "Angela";
    public static final String DESCRIPCION = "Esta categoria es para adquirir elementos de hogar";

    @Autowired
    CategoriaMapper categoriaMapper;

    @Autowired
    CategoriaService categoriaService;

    @MockBean
    private CategoriaDAO categoriaDAO;

    @MockBean
    private EstadoDAO estadoDAO;

    @Test
    @DisplayName("Deberia actualizar exitosamente la categoria")
    void deberiaActualizarCategoriaExitosamente()throws Exception{

        CategoriaDTO categoriaDTO = new CategoriaTestDataBuilder()
                .conNombre(NOMBRE_CATEGORIA)
                .conDescripcion(DESCRIPCION)
                .conIdEstado(2L)
                .conUsuarioModificador(USUARIO_MODIFICADOR)
                .conFechaModificacion(new Date())
                .build();
        Categoria categoria = categoriaMapper.toEntity(categoriaDTO);
        Mockito.when(categoriaDAO.existsById(categoria.getIdCategoria())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(categoria.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(estadoDAO.findById(categoria.getEstado().getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(categoriaDAO.findById(categoria.getIdCategoria())).thenReturn(Optional.of(categoria));
        Mockito.when(categoriaDAO.save(Mockito.any(Categoria.class))).thenReturn(categoria);
        String actualizarCategoria = categoriaService.actualizar(categoriaDTO);
        assertEquals(SE_ACTUALIZO_EXITOSAMENTE_LA_CATEGORIA, actualizarCategoria);

    }
    @Test
    @DisplayName("Deberia fallar por IdCategoria null")
    void deberiaFallarPorIdCategoriaNull()throws Exception{

        CategoriaDTO categoriaDTO = new CategoriaTestDataBuilder()
                .conIdCategoria(null)
                .build();
        Categoria categoria = categoriaMapper.toEntity(categoriaDTO);
        Exception exception = assertThrows(Exception.class, ()->{
            categoriaService.actualizar(categoriaDTO);
        });
        assertEquals(exception.getMessage(),DEBE_INGRESAR_EL_ID_DE_LA_CATEGORIA_QUE_DESEA_ACTUALIZAR );

    }
    @Test
    @DisplayName("Deberia fallar por IdCategoria no existente")
    void deberiaFallarPorIdCategoriaNoExistente()throws Exception{

        CategoriaDTO categoriaDTO = new CategoriaTestDataBuilder()
                .conIdCategoria(475L)
                .build();
        Categoria categoria = categoriaMapper.toEntity(categoriaDTO);
        Mockito.when(categoriaDAO.existsById(categoria.getIdCategoria())).thenReturn(false);
        Exception exception = assertThrows(Exception.class, ()->{
            categoriaService.actualizar(categoriaDTO);
        });
        assertEquals(exception.getMessage(), NO_EXISTE_UNA_CATEGORIA_CON_ESE_ID );

    }

    @Test
    @DisplayName("Deberia fallar por nombre null")
    void deberiaFallarPorNombreNull()throws Exception{

        CategoriaDTO categoriaDTO = new CategoriaTestDataBuilder()
                .conNombre(null)
                .build();
        Categoria categoria = categoriaMapper.toEntity(categoriaDTO);
        Mockito.when(categoriaDAO.existsById(categoria.getIdCategoria())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, ()->{
            categoriaService.actualizar(categoriaDTO);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_EL_NOMBRE_DE_LA_CATEGORIA);

    }
    @Test
    @DisplayName("Deberia fallar por nombre vacio")
    void deberiaFallarPorNombreVacio()throws Exception{

        CategoriaDTO categoriaDTO = new CategoriaTestDataBuilder()
                .conNombre("")
                .build();
        Categoria categoria = categoriaMapper.toEntity(categoriaDTO);
        Mockito.when(categoriaDAO.existsById(categoria.getIdCategoria())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, ()->{
            categoriaService.actualizar(categoriaDTO);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_EL_NOMBRE_DE_LA_CATEGORIA);

    }
    @Test
    @DisplayName("Deberia fallar por nombre largo")
    void deberiaFallarPorNombreLargo()throws Exception{

        CategoriaDTO categoriaDTO = new CategoriaTestDataBuilder()
                .conNombre(NOMBRE_CATEGORIA_MALO)
                .build();
        Categoria categoria = categoriaMapper.toEntity(categoriaDTO);
        Mockito.when(categoriaDAO.existsById(categoria.getIdCategoria())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, ()->{
            categoriaService.actualizar(categoriaDTO);
        });
        assertEquals(exception.getMessage(), EL_NOMBRE_DE_LA_CATEGORIA_NO_DEBE_CONTENER_MAS_DE_50_CARACTERES);

    }
    @Test
    @DisplayName("Deberia fallar por descripcion null")
    void deberiaFallarPorDescripcionNull()throws Exception{

        CategoriaDTO categoriaDTO = new CategoriaTestDataBuilder()
                .conDescripcion(null)
                .build();
        Categoria categoria = categoriaMapper.toEntity(categoriaDTO);
        Mockito.when(categoriaDAO.existsById(categoria.getIdCategoria())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, ()->{
            categoriaService.actualizar(categoriaDTO);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UNA_DESCRIPCION_DE_LA_CATEGORIA);

    }
    @Test
    @DisplayName("Deberia fallar por descripcion vacio")
    void deberiaFallarPorDescripcionVacio()throws Exception{

        CategoriaDTO categoriaDTO = new CategoriaTestDataBuilder()
                .conDescripcion("")
                .build();
        Categoria categoria = categoriaMapper.toEntity(categoriaDTO);
        Mockito.when(categoriaDAO.existsById(categoria.getIdCategoria())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, ()->{
            categoriaService.actualizar(categoriaDTO);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UNA_DESCRIPCION_DE_LA_CATEGORIA);

    }

    @Test
    @DisplayName("Deberia fallar por descripcion largo")
    void deberiaFallarPorDescripcionLargo()throws Exception{

        CategoriaDTO categoriaDTO = new CategoriaTestDataBuilder()
                .conDescripcion(DESCRIPCION_MALA)
                .build();
        Categoria categoria = categoriaMapper.toEntity(categoriaDTO);
        Mockito.when(categoriaDAO.existsById(categoria.getIdCategoria())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, ()->{
            categoriaService.actualizar(categoriaDTO);
        });
        assertEquals(exception.getMessage(), LA_DESCRIPCION_NO_DEBE_CONTENER_MAS_DE_200_CARACTERES);

    }
    @Test
    @DisplayName("Deberia fallar por idEstado null")
    void deberiaFallarPorIdEstadoNull()throws Exception{

        CategoriaDTO categoriaDTO = new CategoriaTestDataBuilder()
                .conIdEstado(null)
                .build();
        Categoria categoria = categoriaMapper.toEntity(categoriaDTO);
        Mockito.when(categoriaDAO.existsById(categoria.getIdCategoria())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, ()->{
            categoriaService.actualizar(categoriaDTO);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_EL_ID_DEL_ESTADO);

    }
    @Test
    @DisplayName("Deberia fallar por idEstado negativo")
    void deberiaFallarPorIdEstadoNegativo()throws Exception{

        CategoriaDTO categoriaDTO = new CategoriaTestDataBuilder()
                .conIdEstado(-876L)
                .build();
        Categoria categoria = categoriaMapper.toEntity(categoriaDTO);
        Mockito.when(categoriaDAO.existsById(categoria.getIdCategoria())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, ()->{
            categoriaService.actualizar(categoriaDTO);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UN_ID_DE_ESTADO_VALIDO);

    }

    @Test
    @DisplayName("Deberia fallar por idEstado no existente")
    void deberiaFallarPorIdEstadoNoExistente()throws Exception{

        CategoriaDTO categoriaDTO = new CategoriaTestDataBuilder()
                .conIdEstado(876L)
                .build();
        Categoria categoria = categoriaMapper.toEntity(categoriaDTO);
        Mockito.when(categoriaDAO.existsById(categoria.getIdCategoria())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(categoria.getEstado().getIdEstado())).thenReturn(false);
        Exception exception = assertThrows(Exception.class, ()->{
            categoriaService.actualizar(categoriaDTO);
        });
        assertEquals(exception.getMessage(), NO_EXISTE_UN_ESTADO_CON_EL_ID_INGRESADO_INGRESE_UNO_VALIDO);

    }
    @Test
    @DisplayName("Deberia fallar por usuario modificador null")
    void deberiaFallarPorUsuarioModificadorNull()throws Exception{

        CategoriaDTO categoriaDTO = new CategoriaTestDataBuilder()
                .conUsuarioModificador(null)
                .build();
        Categoria categoria = categoriaMapper.toEntity(categoriaDTO);
        Mockito.when(categoriaDAO.existsById(categoria.getIdCategoria())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(categoria.getEstado().getIdEstado())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, ()->{
            categoriaService.actualizar(categoriaDTO);
        });
        assertEquals(exception.getMessage(),SE_DEBE_INGRESAR_EL_USUARIO_MODIFICADOR);

    }

    @Test
    @DisplayName("Deberia fallar por usuario modificador vacio")
    void deberiaFallarPorUsuarioModificadorVacio()throws Exception{

        CategoriaDTO categoriaDTO = new CategoriaTestDataBuilder()
                .conUsuarioModificador("")
                .build();
        Categoria categoria = categoriaMapper.toEntity(categoriaDTO);
        Mockito.when(categoriaDAO.existsById(categoria.getIdCategoria())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(categoria.getEstado().getIdEstado())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, ()->{
            categoriaService.actualizar(categoriaDTO);
        });
        assertEquals(exception.getMessage(),SE_DEBE_INGRESAR_EL_USUARIO_MODIFICADOR);

    }
    @Test
    @DisplayName("Deberia fallar por usuario modificador largo")
    void deberiaFallarPorUsuarioModificadorLargo()throws Exception{

        CategoriaDTO categoriaDTO = new CategoriaTestDataBuilder()
                .conUsuarioModificador(USUARIO_MODIFICADOR_MALO)
                .build();
        Categoria categoria = categoriaMapper.toEntity(categoriaDTO);
        Mockito.when(categoriaDAO.existsById(categoria.getIdCategoria())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(categoria.getEstado().getIdEstado())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, ()->{
            categoriaService.actualizar(categoriaDTO);
        });
        assertEquals(exception.getMessage(),EL_USUARIO_MODIFICADOR_NO_PUEDE_CONTENER_MAS_DE_50_CARACTERES);

    }
    @Test
    @DisplayName("Deberia fallar por fecha modificacion null")
    void deberiaFallarPorFechaModificacionNull()throws Exception{

        CategoriaDTO categoriaDTO = new CategoriaTestDataBuilder()
                .conFechaModificacion(null)
                .build();
        Categoria categoria = categoriaMapper.toEntity(categoriaDTO);
        Mockito.when(categoriaDAO.existsById(categoria.getIdCategoria())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(categoria.getEstado().getIdEstado())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, ()->{
            categoriaService.actualizar(categoriaDTO);
        });
        assertEquals(exception.getMessage(),SE_DEBE_INGRESAR_LA_FECHA_DE_MODIFICACION);

    }
}
