package com.example.demo.serviceImpl.articulos.consultas;

import com.example.demo.dao.ArticulosDAO;
import com.example.demo.dao.CategoriaDAO;
import com.example.demo.dao.EstadoDAO;
import com.example.demo.mapper.ArticulosMapper;
import com.example.demo.model.Articulos;
import com.example.demo.model.Categoria;
import com.example.demo.model.Estado;
import com.example.demo.model.dto.ArticulosDTO;
import com.example.demo.service.ArticulosService;
import com.example.demo.serviceImpl.articulos.testDataBuilder.ArticuloTestDataBuilder;
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
public class ManejadorCrearArticuloTest {
    public static final String SE_CREO_EXITOSAMENTE_EL_ARTICULO = "Se creo correctamente el artículo.";
    public static final String DEBE_INGRESAR_UN_NOMBRE_PARA_EL_ARTICULO = "Debe ingresar un nombre para el artículo.";
    public static final String SE_DEBE_INGRESAR_EL_PRECIO_DEL_ARTICULO = "Se debe ingresar el precio del artículo.";
    public static final String DEBE_INGRESAR_UNA_DESCRIPCION_PARA_EL_ARTICULO = "Debe ingresar una descripción para el artículo.";
    public static final String SE_DEBE_INGRESAR_EL_NIVEL_VALIDO_DEL_ARTICULO = "Se debe ingresar el nivel válido del artículo.";
    public static final String DEBE_INGRESAR_UNA_IMAGEN_PARA_EL_ARTICULO = "Debe ingresar una imagen para el artículo.";
    public static final String LA_URL_DE_LA_IMAGEN_ES_MUY_LARGA_MAXIMO_250_CARACTERES = "La url de la imagen es muy larga, máximo 250 caracteres.";
    public static final String DEBE_INGRESAR_UN_IDCATEGORIA_QUE_ESTE_REGISTRADA = "Debe ingresar un idCategoría que este registrada.";
    public static final String DEBE_INGRESAR_UN_IDESTADO_QUE_ESTE_REGISTRADO = "Debe ingresar un idEstado que este registrado.";
    public static final String DEBE_INGRESAR_UN_ID_CATEGORIA_VALIDO = "Debe ingresar un idCategoría válido.";
    public static final String DEBE_INGRESAR_UN_ID_ESTADO_VALIDO = "Debe ingresar un idEstado válido.";
    public static final String SE_DEBE_INGRESAR_UN_USUARIO_CREADOR_DEL_ARTICULO_VALIDO ="Se debe ingresar un usuario creador del artículo válido.";
    public static final String SE_DEBE_INGRESAR_UNA_FECHA_VALIDA = "Se debe ingresar una fecha válida.";
    public static final String NO_SE_PUEDE_INGRESAR_UNA_FECHA_QUE_AUN_NO_HA_SUCEDIDO = "No puede ingresar una fecha que aun no ha sucedido.";
    public static final String NOMBRE_ARTICULO_MALO = "SDHCKHDCH DCHKDC CDKHDK EWDCHKHDWH CWEHK IUIEUDWH EWDGHJ";
    public static final String DESCRIPCION_MALA = "SDHCKHDCH DCHKDC CDKHDK EWDCHKHDWH CWEHK IUIEUDWH EWDGHJ DCKNJ " +
            "DCJ DCJDCJ SDCD JLSCJ  CDJDSK L JDCSLK SDC JDCLKJ DC LJC DSLJ JDC LKJDSLK KLDJ DS CLJLC CJ JC JDCL JLKDCJ " +
            "JCDLKJLKDSJJDC J JI W JDWJ LKDSJ";
    public static final String IMAGEN_MALA = "DKDFJSJFJVLUGF/firebasee.comSDHCKHDCHDC/DFGDDHKDCDKHDKEWD/CHKHDWHCWEHKIUIEUDWHEWDGHJDCKNJDCJDCJDCJSDCJLSCJC/DJDSKLJDCSLKSDCDCL/KJDCLJCDSLJJDCLKJD/SLKKLDJDSDCJDCJDCJSDCJLS/SDJHDKHKH/DSVSVJHHN/FDVDKCJCDJDSKLJDCSLKSDCDCLKJDCLJCDSLJJDCLKJDSLKKLDJDSdedeui.png";
    public static final String USUARIO_CREADOR_MALO = "SDHCKHDCH DCHKDC CDKHDK EWDCHKHDWH CWEHK IUIEUDWH EWDGHJ";

    @Autowired
    ArticulosService articulosService;

    @MockBean
    private ArticulosDAO articulosDAO;

    @MockBean
    private CategoriaDAO categoriaDAO;

    @MockBean
    private EstadoDAO estadoDAO;

    @Autowired
    ArticulosMapper articulosMapper;


    @Test
    @DisplayName("Debería crear de manera exitosa un artículo")
    void deberiaCrearArticuloExitosamente()throws Exception{

        ArticulosDTO articulosDTO = new ArticuloTestDataBuilder().build();
        Articulos articulos = articulosMapper.toEntity(articulosDTO);

        Mockito.when(categoriaDAO.findById(articulosDTO.getIdCategoria())).thenReturn(Optional.of(new Categoria()));
        Mockito.when(estadoDAO.findById(articulosDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(articulosDAO.save(Mockito.any())).thenReturn(articulos);
        String crearArticulo = articulosService.registrar(articulos);

        assertEquals(SE_CREO_EXITOSAMENTE_EL_ARTICULO, crearArticulo);

    }

    @Test
    @DisplayName("Debería fallar la creación del artículo por el nombre nulo")
    void deberiaFallarPorNombreNulo()throws Exception{
        ArticulosDTO articulosDTO = new ArticuloTestDataBuilder().conNombre(null).build();
        Articulos articulos = articulosMapper.toEntity(articulosDTO);
        Mockito.when(articulosDAO.save(Mockito.any())).thenReturn(articulos);
        Exception exception = assertThrows(Exception.class, ()->{
            articulosService.registrar(articulos);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UN_NOMBRE_PARA_EL_ARTICULO);
    }

    @Test
    @DisplayName("Debería fallar la creación del artículo por el nombre vacío")
    void deberiaFallarPorNombreVacio()throws Exception{
        ArticulosDTO articulosDTO = new ArticuloTestDataBuilder().conNombre("").build();
        Articulos articulos = articulosMapper.toEntity(articulosDTO);
        Mockito.when(articulosDAO.save(Mockito.any())).thenReturn(articulos);
        Exception exception = assertThrows(Exception.class, ()->{
            articulosService.registrar(articulos);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UN_NOMBRE_PARA_EL_ARTICULO);
    }

    @Test
    @DisplayName("Debería fallar la creación del artículo por el nombre largo")
    void deberiaFallarPorNombreLargo()throws Exception{
        ArticulosDTO articulosDTO = new ArticuloTestDataBuilder().conNombre(NOMBRE_ARTICULO_MALO).build();
        Articulos articulos = articulosMapper.toEntity(articulosDTO);
        Mockito.when(articulosDAO.save(Mockito.any())).thenReturn(articulos);
        Exception exception = assertThrows(Exception.class, ()->{
            articulosService.registrar(articulos);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UN_NOMBRE_PARA_EL_ARTICULO);
    }

    @Test
    @DisplayName("Debería fallar la creación del artículo por precio negativo")
    void deberiaFallarPorPrecioNegativo()throws Exception{
        ArticulosDTO articulosDTO = new ArticuloTestDataBuilder().conPrecio(-12.0).build();
        Articulos articulos = articulosMapper.toEntity(articulosDTO);
        Mockito.when(articulosDAO.save(Mockito.any())).thenReturn(articulos);
        Exception exception = assertThrows(Exception.class, ()->{
            articulosService.registrar(articulos);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_EL_PRECIO_DEL_ARTICULO);
    }
    @Test
    @DisplayName("Debería fallar la creación del artículo por descripción null")
    void deberiaFallarPorDescripcionNulo()throws Exception{
        ArticulosDTO articulosDTO = new ArticuloTestDataBuilder().conDescripcion(null).build();
        Articulos articulos = articulosMapper.toEntity(articulosDTO);
        Mockito.when(articulosDAO.save(Mockito.any())).thenReturn(articulos);
        Exception exception = assertThrows(Exception.class, ()->{
            articulosService.registrar(articulos);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UNA_DESCRIPCION_PARA_EL_ARTICULO);
    }

    @Test
    @DisplayName("Debería fallar la creación del artículo por descripción vacío")
    void deberiaFallarPorDescripcionVacio()throws Exception{
        ArticulosDTO articulosDTO = new ArticuloTestDataBuilder().conDescripcion("").build();
        Articulos articulos = articulosMapper.toEntity(articulosDTO);
        Mockito.when(articulosDAO.save(Mockito.any())).thenReturn(articulos);
        Exception exception = assertThrows(Exception.class, ()->{
            articulosService.registrar(articulos);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UNA_DESCRIPCION_PARA_EL_ARTICULO);
    }

    @Test
    @DisplayName("Debería fallar la creación del artículo por descripción largo")
    void deberiaFallarPorDescripcionLargo()throws Exception{
        ArticulosDTO articulosDTO = new ArticuloTestDataBuilder().conDescripcion(DESCRIPCION_MALA).build();
        Articulos articulos = articulosMapper.toEntity(articulosDTO);
        Mockito.when(articulosDAO.save(Mockito.any())).thenReturn(articulos);
        Exception exception = assertThrows(Exception.class, ()->{
            articulosService.registrar(articulos);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UNA_DESCRIPCION_PARA_EL_ARTICULO);
    }

    @Test
    @DisplayName("Debería fallar la creación del artículo por nivel válido menor o igual a cero")
    void deberiaFallarPorNivelValidoNegativo()throws Exception{
        ArticulosDTO articulosDTO = new ArticuloTestDataBuilder().conNivelValido(0).build();
        Articulos articulos = articulosMapper.toEntity(articulosDTO);
        Mockito.when(articulosDAO.save(Mockito.any())).thenReturn(articulos);
        Exception exception = assertThrows(Exception.class, ()->{
            articulosService.registrar(articulos);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_EL_NIVEL_VALIDO_DEL_ARTICULO);
    }

    @Test
    @DisplayName("Debería fallar la creación del artículo por imagen null")
    void deberiaFallarPorImagenNulo()throws Exception{
        ArticulosDTO articulosDTO = new ArticuloTestDataBuilder().conImagen(null).build();
        Articulos articulos = articulosMapper.toEntity(articulosDTO);
        Mockito.when(articulosDAO.save(Mockito.any())).thenReturn(articulos);
        Exception exception = assertThrows(Exception.class, ()->{
            articulosService.registrar(articulos);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UNA_IMAGEN_PARA_EL_ARTICULO);
    }

    @Test
    @DisplayName("Debería fallar la creación del artículo por imagen vacío")
    void deberiaFallarPorImagenVacio()throws Exception{
        ArticulosDTO articulosDTO = new ArticuloTestDataBuilder().conImagen("").build();
        Articulos articulos = articulosMapper.toEntity(articulosDTO);
        Mockito.when(articulosDAO.save(Mockito.any())).thenReturn(articulos);
        Exception exception = assertThrows(Exception.class, ()->{
            articulosService.registrar(articulos);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UNA_IMAGEN_PARA_EL_ARTICULO);
    }

    @Test
    @DisplayName("Debería fallar la creación del artículo por imagen larga")
    void deberiaFallarPorImagenLargo()throws Exception{
        ArticulosDTO articulosDTO = new ArticuloTestDataBuilder().conImagen(IMAGEN_MALA).build();
        Articulos articulos = articulosMapper.toEntity(articulosDTO);
        Mockito.when(articulosDAO.save(Mockito.any())).thenReturn(articulos);
        Exception exception = assertThrows(Exception.class, ()->{
            articulosService.registrar(articulos);
        });
        assertEquals(exception.getMessage(), LA_URL_DE_LA_IMAGEN_ES_MUY_LARGA_MAXIMO_250_CARACTERES);
    }

    @Test
    @DisplayName("Debería fallar la creación del artículo por idCategoria null")
    void deberiaFallarPorIdCategoriaNull()throws Exception{
        ArticulosDTO articulosDTO = new ArticuloTestDataBuilder().conIdCategoria(null).build();
        Articulos articulos = articulosMapper.toEntity(articulosDTO);
        Mockito.when(articulosDAO.save(Mockito.any())).thenReturn(articulos);
        Exception exception = assertThrows(Exception.class, ()->{
            articulosService.registrar(articulos);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UN_ID_CATEGORIA_VALIDO);
    }

    @Test
    @DisplayName("Debería fallar la creación del artículo por idCategoria menor o igual a cero")
    void deberiaFallarPorIdCategoriaNegativo()throws Exception{
        ArticulosDTO articulosDTO = new ArticuloTestDataBuilder().conIdCategoria(-544L).build();
        Articulos articulos = articulosMapper.toEntity(articulosDTO);
        Mockito.when(articulosDAO.save(Mockito.any())).thenReturn(articulos);
        Exception exception = assertThrows(Exception.class, ()->{
            articulosService.registrar(articulos);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UN_ID_CATEGORIA_VALIDO);

    }
    @Test
    @DisplayName("Debería fallar la creación del artículo por idCategoria que no existe")
    void deberiaFallarPorIdCategoriaNoExistente()throws Exception{
        ArticulosDTO articulosDTO = new ArticuloTestDataBuilder().conIdCategoria(5344L).build();
        Articulos articulos = articulosMapper.toEntity(articulosDTO);
        Mockito.when(categoriaDAO.findById(articulosDTO.getIdCategoria())).thenReturn(Optional.empty());
        Exception exception = assertThrows(Exception.class, ()->{
            articulosService.registrar(articulos);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UN_IDCATEGORIA_QUE_ESTE_REGISTRADA);
    }

    //TODO: Esta Fallando revisar
    @Test
    @DisplayName("Debería fallar la creación del artículo por idEstado nulo")
    void deberiaFallarPorIdEstadoNulo()throws Exception{

        ArticulosDTO articulosDTO = new ArticuloTestDataBuilder().conIdEstado(null).build();
        Articulos articulos = articulosMapper.toEntity(articulosDTO);
        Mockito.when(categoriaDAO.findById(articulosDTO.getIdCategoria())).thenReturn(Optional.of(new Categoria()));
        Mockito.when(articulosDAO.save(Mockito.any())).thenReturn(articulos);
        Exception exception = assertThrows(Exception.class, ()->{
            articulosService.registrar(articulos);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UN_ID_ESTADO_VALIDO);

    }
    //TODO:ESTA FALLANDO
    @Test
    @DisplayName("Debería fallar la creación del artículo por idEstado negativo")
    void deberiaFallarPorIdEstadoNegativo()throws Exception{
        ArticulosDTO articulosDTO = new ArticuloTestDataBuilder().conIdEstado(-435L).build();
        Articulos articulos = articulosMapper.toEntity(articulosDTO);
        Mockito.when(categoriaDAO.findById(articulosDTO.getIdCategoria())).thenReturn(Optional.of(new Categoria()));
        Mockito.when(articulosDAO.save(Mockito.any())).thenReturn(articulos);
        Exception exception = assertThrows(Exception.class, ()->{
            articulosService.registrar(articulos);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UN_ID_ESTADO_VALIDO);
    }
    //TODO:ESTA FALLANDO
    @Test
    @DisplayName("Debería fallar la creación del artículo por idEstado que no existe")
    void deberiaFallarPorIdEstadoNoExistente()throws Exception{
        ArticulosDTO articulosDTO = new ArticuloTestDataBuilder().conIdEstado(85435L).build();
        Articulos articulos = articulosMapper.toEntity(articulosDTO);
        Mockito.when(categoriaDAO.findById(articulosDTO.getIdCategoria())).thenReturn(Optional.of(new Categoria()));
        Mockito.when(articulosDAO.save(Mockito.any())).thenReturn(articulos);
        Exception exception = assertThrows(Exception.class, ()->{
            articulosService.registrar(articulos);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UN_IDESTADO_QUE_ESTE_REGISTRADO);
    }

    @Test
    @DisplayName("Debería fallar la creación del artículo por usuario creador nulo")
    void deberiaFallarPorUsuarioCreadorNulo()throws Exception{
        ArticulosDTO articulosDTO = new ArticuloTestDataBuilder().conUsuarioCreador(null).build();
        Articulos articulos = articulosMapper.toEntity(articulosDTO);
        Mockito.when(categoriaDAO.findById(articulosDTO.getIdCategoria())).thenReturn(Optional.of(new Categoria()));
        Mockito.when(estadoDAO.findById(articulosDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(articulosDAO.save(Mockito.any())).thenReturn(articulos);
        Exception exception = assertThrows(Exception.class, ()->{
            articulosService.registrar(articulos);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UN_USUARIO_CREADOR_DEL_ARTICULO_VALIDO);
    }

    @Test
    @DisplayName("Debería fallar la creación del artículo por usuario creador vacío")
    void deberiaFallarPorUsuarioCreadorVacio()throws Exception{
        ArticulosDTO articulosDTO = new ArticuloTestDataBuilder().conUsuarioCreador("").build();
        Articulos articulos = articulosMapper.toEntity(articulosDTO);
        Mockito.when(categoriaDAO.findById(articulosDTO.getIdCategoria())).thenReturn(Optional.of(new Categoria()));
        Mockito.when(estadoDAO.findById(articulosDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(articulosDAO.save(Mockito.any())).thenReturn(articulos);
        Exception exception = assertThrows(Exception.class, ()->{
            articulosService.registrar(articulos);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UN_USUARIO_CREADOR_DEL_ARTICULO_VALIDO);
    }

    @Test
    @DisplayName("Debería fallar la creación del artículo por usuario creador largo")
    void deberiaFallarPorUsuarioCreadorLargo()throws Exception{
        ArticulosDTO articulosDTO = new ArticuloTestDataBuilder().conUsuarioCreador(USUARIO_CREADOR_MALO).build();
        Articulos articulos = articulosMapper.toEntity(articulosDTO);
        Mockito.when(categoriaDAO.findById(articulosDTO.getIdCategoria())).thenReturn(Optional.of(new Categoria()));
        Mockito.when(estadoDAO.findById(articulosDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(articulosDAO.save(Mockito.any())).thenReturn(articulos);
        Exception exception = assertThrows(Exception.class, ()->{
            articulosService.registrar(articulos);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UN_USUARIO_CREADOR_DEL_ARTICULO_VALIDO);
    }

    @Test
    @DisplayName("Debería fallar la creación del artículo por fecha creación nula")
    void deberiaFallarPorFechaCreacionNull()throws Exception{
        ArticulosDTO articulosDTO = new ArticuloTestDataBuilder().conFechaCreacion(null).build();
        Articulos articulos = articulosMapper.toEntity(articulosDTO);
        Mockito.when(categoriaDAO.findById(articulosDTO.getIdCategoria())).thenReturn(Optional.of(new Categoria()));
        Mockito.when(estadoDAO.findById(articulosDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(articulosDAO.save(Mockito.any())).thenReturn(articulos);
        Exception exception = assertThrows(Exception.class, ()->{
            articulosService.registrar(articulos);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UNA_FECHA_VALIDA);
    }

    @Test
    @DisplayName("Debería fallar la creación del artículo por fecha creación que aun no ha sucedido")
    void deberiaFallarPorFechaCreacionFutura()throws Exception{
        ArticulosDTO articulosDTO = new ArticuloTestDataBuilder().conFechaCreacion(new Date(2023,4,12)).build();
        Articulos articulos = articulosMapper.toEntity(articulosDTO);
        Mockito.when(categoriaDAO.findById(articulosDTO.getIdCategoria())).thenReturn(Optional.of(new Categoria()));
        Mockito.when(estadoDAO.findById(articulosDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(articulosDAO.save(Mockito.any())).thenReturn(articulos);
        Exception exception = assertThrows(Exception.class, ()->{
            articulosService.registrar(articulos);
        });
        assertEquals(exception.getMessage(), NO_SE_PUEDE_INGRESAR_UNA_FECHA_QUE_AUN_NO_HA_SUCEDIDO);
    }

}
