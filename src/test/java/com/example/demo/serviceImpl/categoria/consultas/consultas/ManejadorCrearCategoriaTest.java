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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@SpringBootTest
public class ManejadorCrearCategoriaTest {

    public static final String SE_REGISTRO_EXITOSAMENTE_LA_CATEGORIA ="Se registro exitosamente la categoría.";
    public static final String SE_DEBE_INGRESAR_EL_NOMBRE_DE_LA_CATEGORIA = "Se debe ingresar el nombre de la categoría.";
    public static final String EL_NOMBRE_DE_LA_CATEGORIA_NO_DEBE_CONTENER_MAS_DE_50_CARACTERES = "El nombre de la categoría no debe contener más de 50 caracteres.";
    public static final String SE_DEBE_INGRESAR_UNA_DESCRIPCION_DE_LA_CATEGORIA = "Se debe ingresar una descripción de la categoría.";
    public static final String LA_DESCRIPCION_NO_DEBE_CONTENER_MAS_DE_200_CARACTERES = "La descripción no debe contener más de 200 caracteres.";
    public static final String SE_DEBE_INGRESAR_EL_ID_DEL_ESTADO = "Se debe ingresar el id del estado.";
    public static final String SE_DEBE_INGRESAR_UN_ID_DE_ESTADO_VALIDO = "Se debe ingresar un id de estado válido.";
    public static final String NO_EXISTE_UN_ESTADO_CON_EL_ID_INGRESADO_INGRESE_UNO_VALIDO = "No existe un estado con el id ingresado, ingrese un id válido.";
    public static final String SE_DEBE_INGRESAR_EL_USUARIO_CREADOR = "Se debe ingresar el usuario creador.";
    public static final String EL_USUARIO_CREADOR_NO_PUEDE_CONTENER_MAS_DE_50_CARACTERES = "El usuario creador no debe contener más de 50 caracteres.";
    public static final String SE_DEBE_INGRESAR_LA_FECHA_DE_CREACION = "Se debe ingresar la fecha de creación.";
    public static final String NOMBRE_CATEGORIA_MALO = "FH DFHJF JHFJHJF GHFRF F RYETUFTUTFREWYUB CTUERFTTCB F" ;
    public static final String DESCRIPCION = "RFUIR FEURYERUUYF R EFRUEY REF REUIY REYUIY EY UIREY IU UYGERUIYUERIERYUIG ERUGHEUHUITGUIERIUGHERJGHEGR  YG EUIYERUITH JTREHI UTJHYGTDJJFJH G FERGUFHJV JHFDSUF UREJHFJHFJH DFHSD JRHDUUYDUY YDEGDHUSGD DGSDGHS SDFYSGFF";
    public static final String USUARIO_CREADOR_MALO = "SDHS GDFJHSDGFJ FJHJDH DGFHGHJD DGHJDG GHG JDGHDGSJH JHDJH";

    @Autowired
    CategoriaMapper categoriaMapper;

    @Autowired
    CategoriaService categoriaService;

    @MockBean
    private CategoriaDAO categoriaDAO;

    @MockBean
    private EstadoDAO estadoDAO;

    @Test
    @DisplayName("Deberia crear la categoria exitosamente")
    void deberiaCrearCategoriaExitosamente()throws Exception{

        CategoriaDTO categoriaDTO = new CategoriaTestDataBuilder().build();
        Categoria categoria = categoriaMapper.toEntity(categoriaDTO);
        Mockito.when(estadoDAO.existsById(categoria.getEstado().getIdEstado())).thenReturn(true);
        String creacionCategoria = categoriaService.registrar(categoria);
        Mockito.when(categoriaDAO.save(Mockito.any())).thenReturn(categoria);
        assertEquals(SE_REGISTRO_EXITOSAMENTE_LA_CATEGORIA, creacionCategoria);
    }

    @Test
    @DisplayName("Deberia fallar por nombre null")
    void deberiaFallarPorNombreNull()throws Exception{

        CategoriaDTO categoriaDTO = new CategoriaTestDataBuilder().conNombre(null).build();
        Categoria categoria = categoriaMapper.toEntity(categoriaDTO);
        Mockito.when(estadoDAO.existsById(categoria.getEstado().getIdEstado())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, ()->{
            categoriaService.registrar(categoria);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_EL_NOMBRE_DE_LA_CATEGORIA);
    }

    @Test
    @DisplayName("Deberia fallar por nombre vacio")
    void deberiaFallarPorNombreVacio()throws Exception{

        CategoriaDTO categoriaDTO = new CategoriaTestDataBuilder().conNombre("").build();
        Categoria categoria = categoriaMapper.toEntity(categoriaDTO);
        Mockito.when(estadoDAO.existsById(categoria.getEstado().getIdEstado())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, ()->{
            categoriaService.registrar(categoria);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_EL_NOMBRE_DE_LA_CATEGORIA);
    }
    @Test
    @DisplayName("Deberia fallar por nombre largo")
    void deberiaFallarPorNombreLargo()throws Exception{

        CategoriaDTO categoriaDTO = new CategoriaTestDataBuilder().conNombre(NOMBRE_CATEGORIA_MALO).build();
        Categoria categoria = categoriaMapper.toEntity(categoriaDTO);
        Mockito.when(estadoDAO.existsById(categoria.getEstado().getIdEstado())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, ()->{
            categoriaService.registrar(categoria);
        });
        assertEquals(exception.getMessage(), EL_NOMBRE_DE_LA_CATEGORIA_NO_DEBE_CONTENER_MAS_DE_50_CARACTERES);
    }
    @Test
    @DisplayName("Deberia fallar por descripcion null")
    void deberiaFallarPorDescripcionNull()throws Exception{

        CategoriaDTO categoriaDTO = new CategoriaTestDataBuilder().conDescripcion(null).build();
        Categoria categoria = categoriaMapper.toEntity(categoriaDTO);
        Mockito.when(estadoDAO.existsById(categoria.getEstado().getIdEstado())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, ()->{
            categoriaService.registrar(categoria);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UNA_DESCRIPCION_DE_LA_CATEGORIA);
    }

    @Test
    @DisplayName("Deberia fallar por descripcion vacia")
    void deberiaFallarPorDescripcionVacia()throws Exception{

        CategoriaDTO categoriaDTO = new CategoriaTestDataBuilder().conDescripcion("").build();
        Categoria categoria = categoriaMapper.toEntity(categoriaDTO);
        Mockito.when(estadoDAO.existsById(categoria.getEstado().getIdEstado())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, ()->{
            categoriaService.registrar(categoria);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UNA_DESCRIPCION_DE_LA_CATEGORIA);
    }
    @Test
    @DisplayName("Deberia fallar por descripcion larga")
    void deberiaFallarPorDescripcionLargo()throws Exception{

        CategoriaDTO categoriaDTO = new CategoriaTestDataBuilder().conDescripcion(DESCRIPCION).build();
        Categoria categoria = categoriaMapper.toEntity(categoriaDTO);
        Mockito.when(estadoDAO.existsById(categoria.getEstado().getIdEstado())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, ()->{
            categoriaService.registrar(categoria);
        });
        assertEquals(exception.getMessage(), LA_DESCRIPCION_NO_DEBE_CONTENER_MAS_DE_200_CARACTERES);
    }
    @Test
    @DisplayName("Deberia fallar por estado null")
    void deberiaFallarPorEstadoNull()throws Exception{

        CategoriaDTO categoriaDTO = new CategoriaTestDataBuilder().conIdEstado(null).build();
        Categoria categoria = categoriaMapper.toEntity(categoriaDTO);
        Exception exception = assertThrows(Exception.class, ()->{
            categoriaService.registrar(categoria);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_EL_ID_DEL_ESTADO);
    }
    @Test
    @DisplayName("Deberia fallar por estado negativo")
    void deberiaFallarPorEstadoNegativo()throws Exception{

        CategoriaDTO categoriaDTO = new CategoriaTestDataBuilder().conIdEstado(-3476L).build();
        Categoria categoria = categoriaMapper.toEntity(categoriaDTO);
        Exception exception = assertThrows(Exception.class, ()->{
            categoriaService.registrar(categoria);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UN_ID_DE_ESTADO_VALIDO);
    }

    @Test
    @DisplayName("Deberia fallar por estado no existe")
    void deberiaFallarPorEstadoNoExiste()throws Exception{

        CategoriaDTO categoriaDTO = new CategoriaTestDataBuilder().conIdEstado(3476L).build();
        Categoria categoria = categoriaMapper.toEntity(categoriaDTO);
        Mockito.when(estadoDAO.existsById(categoria.getEstado().getIdEstado())).thenReturn(false);
        Exception exception = assertThrows(Exception.class, ()->{
            categoriaService.registrar(categoria);
        });
        assertEquals(exception.getMessage(), NO_EXISTE_UN_ESTADO_CON_EL_ID_INGRESADO_INGRESE_UNO_VALIDO);
    }
    @Test
    @DisplayName("Deberia fallar por usuario creador null")
    void deberiaFallarPorUsuarioCreadorNull()throws Exception{

        CategoriaDTO categoriaDTO = new CategoriaTestDataBuilder().conUsuarioCreador(null).build();
        Categoria categoria = categoriaMapper.toEntity(categoriaDTO);
        Mockito.when(estadoDAO.existsById(categoria.getEstado().getIdEstado())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, ()->{
            categoriaService.registrar(categoria);
        });
        assertEquals(exception.getMessage(),SE_DEBE_INGRESAR_EL_USUARIO_CREADOR);
    }
    @Test
    @DisplayName("Deberia fallar por usuario creador vacio")
    void deberiaFallarPorUsuarioCreadorVacio()throws Exception{

        CategoriaDTO categoriaDTO = new CategoriaTestDataBuilder().conUsuarioCreador("").build();
        Categoria categoria = categoriaMapper.toEntity(categoriaDTO);
        Mockito.when(estadoDAO.existsById(categoria.getEstado().getIdEstado())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, ()->{
            categoriaService.registrar(categoria);
        });
        assertEquals(exception.getMessage(),SE_DEBE_INGRESAR_EL_USUARIO_CREADOR);
    }
    @Test
    @DisplayName("Deberia fallar por usuario creador largo")
    void deberiaFallarPorUsuarioCreadorLargo()throws Exception{

        CategoriaDTO categoriaDTO = new CategoriaTestDataBuilder().conUsuarioCreador(USUARIO_CREADOR_MALO).build();
        Categoria categoria = categoriaMapper.toEntity(categoriaDTO);
        Mockito.when(estadoDAO.existsById(categoria.getEstado().getIdEstado())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, ()->{
            categoriaService.registrar(categoria);
        });
        assertEquals(exception.getMessage(),EL_USUARIO_CREADOR_NO_PUEDE_CONTENER_MAS_DE_50_CARACTERES);
    }

    @Test
    @DisplayName("Deberia fallar por fecha creacion null")
    void deberiaFallarPorFechaCreacionNull()throws Exception{

        CategoriaDTO categoriaDTO = new CategoriaTestDataBuilder().conFechaCreacion(null).build();
        Categoria categoria = categoriaMapper.toEntity(categoriaDTO);
        Mockito.when(estadoDAO.existsById(categoria.getEstado().getIdEstado())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, ()->{
            categoriaService.registrar(categoria);
        });
        assertEquals(exception.getMessage(),SE_DEBE_INGRESAR_LA_FECHA_DE_CREACION);
    }
}
