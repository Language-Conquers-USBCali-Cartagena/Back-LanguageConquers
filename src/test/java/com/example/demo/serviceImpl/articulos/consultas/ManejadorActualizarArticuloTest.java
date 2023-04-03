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

import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@SpringBootTest
public class ManejadorActualizarArticuloTest {
    public static final String SE_ACTUALIZO_CORRECTAMENTE_EL_ARTICULO = "Se actualizo correctamente el artículo.";
    public static final String DEBE_INGRESAR_EL_ID_DEL_ARTICULO_QUE_DESEA_ACTUALIZAR = "Debe ingresar el id del artículo que desea actualizar.";
    public static final String NO_SE_ENCONTRO_UN_ARTICULO_CON_ESE_ID = "No se encontró un artículo con ese id.";
    public static final String NO_EXISTE_UNA_CATEGORIA_CON_ESE_ID = "No existe una categoría con ese id.";
    public static final String NO_EXISTE_UN_ESTADO_CON_ESE_ID="No existe un estado con ese id.";
    public static final String DEBE_INGRESAR_UN_ID_ESTADO = "Debe ingresar un id estado.";
    public static final String DEBE_INGRESAR_UN_ID_DE_CATEGORIA= "Debe ingresar un id de categoría.";
    public static final String DEBE_INGRESAR_UN_ID_DE_ESTADO_VALIDO = "Debe ingresar un id de estado válido.";
    public static final String DEBE_INGRESAR_UN_ID_DE_CATEGORIA_VALIDO = "Debe ingresar un id de categoría válido.";
    public static final String DEBE_INGRESAR_UN_NOMBRE_PARA_EL_ARTICULO = "Debe ingresar un nombre para el artículo.";
    public static final String SE_DEBE_INGRESAR_EL_PRECIO_DEL_ARTICULO = "Se debe ingresar el precio del artículo.";
    public static final String DEBE_INGRESAR_UNA_DESCRIPCION_PARA_EL_ARTICULO = "Debe ingresar una descripción para el artículo.";
    public static final String SE_DEBE_INGRESAR_EL_NIVEL_VALIDO_DEL_ARTICULO = "Se debe ingresar el nivel válido del artículo.";
    public static final String DEBE_INGRESAR_UNA_IMAGEN_PARA_EL_ARTICULO = "Debe ingresar una imagen para el artículo.";
    public static final String LA_URL_DE_LA_IMAGEN_ES_MUY_LARGA_MAXIMO_250_CARACTERES = "La url de la imagen es muy larga, máximo 250 caracteres.";
    public static final String SE_DEBE_INGRESAR_UN_USUARIO_MODIFICADOR_DEL_ARTICULO_VALIDO ="Debe ingresar el usuario modificador.";
    public static final String EL_NOMBRE_DEL_USUARIO_MODIFICADOR_ES_MUY_LARGO_SOLO_PUEDE_CONTENER_50_CARACTERES = "El nombre del usuario modificador es muy largo, solo puede contener 50 caracteres.";
    public static final String SE_DEBE_INGRESAR_UNA_FECHA_DE_MODIFICACION = "Debe ingresar una fecha de modificación.";
    public static final String NO_SE_PUEDE_INGRESAR_UNA_FECHA_QUE_AUN_NO_HA_SUCEDIDO = "No puede ingresar una fecha que aun no ha sucedido.";
    public static final String NOMBRE_ARTICULO_MALO = "SDHCKHDCH DCHKDC CDKHDK EWDCHKHDWH CWEHK IUIEUDWH EWDGHJ";
    public static final String DESCRIPCION_MALA = "SDHCKHDCH DCHKDC CDKHDK EWDCHKHDWH CWEHK IUIEUDWH EWDGHJ DCKNJ " +
            "DCJ DCJDCJ SDCD JLSCJ  CDJDSK L JDCSLK SDC JDCLKJ DC LJC DSLJ JDC LKJDSLK KLDJ DS CLJLC CJ JC JDCL JLKDCJ " +
            "JCDLKJLKDSJJDC J JI W JDWJ LKDSJ";
    public static final String IMAGEN_MALA = "DKDFJSJFJVLUGF/firebasee.comSDHCKHDCHDC/DFGDDHKDCDKHDKEWD/CHKHDWHCWEHKIUIEUDWHEWDGHJDCKNJDCJDCJDCJSDCJLSCJC/DJDSKLJDCSLKSDCDCL/KJDCLJCDSLJJDCLKJD/SLKKLDJDSDCJDCJDCJSDCJLS/SDJHDKHKH/DSVSVJHHN/FDVDKCJCDJDSKLJDCSLKSDCDCLKJDCLJCDSLJJDCLKJDSLKKLDJDSdedeui.png";
    public static final String USUARIO_MODIFICADOR_MALO = "SDHCKHDCH DCHKDC CDKHDK EWDCHKHDWH CWEHK IUIEUDWH EWDGHJ";

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
    @DisplayName("Debería actualizar de manera exitosa un artículo")
    void deberiaActualizarArticuloExitosamente()throws Exception{

        ArticulosDTO articulosDTO = new ArticuloTestDataBuilder().build();
        articulosDTO.setNombre("Martillo");
        articulosDTO.setDescripcion("Este martillo te permite reparar más rápido");
        articulosDTO.setImagen("martillo.png");
        articulosDTO.setPrecio(120);
        articulosDTO.setIdCategoria(1L);
        articulosDTO.setNivelValido(1);
        articulosDTO.setIdEstado(2L);
        articulosDTO.setUsuarioModificador("Angela");
        articulosDTO.setFechaModificacion(new Date());
        Articulos articulos = articulosMapper.toEntity(articulosDTO);
        Mockito.when(articulosDAO.existsById(articulosDTO.getIdArticulo())).thenReturn(true);
        Mockito.when(articulosDAO.findById(articulosDTO.getIdArticulo())).thenReturn(Optional.of(articulos));
        Mockito.when(categoriaDAO.existsById(articulosDTO.getIdCategoria())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(articulosDTO.getIdEstado())).thenReturn(true);
        Mockito.when(articulosDAO.save(Mockito.any())).thenReturn(articulos);
        String actualizarArticulo = articulosService.actualizar(articulos);
        assertEquals(SE_ACTUALIZO_CORRECTAMENTE_EL_ARTICULO, actualizarArticulo);

    }
    @Test
    @DisplayName("Debería fallar por idArticulo nulo")
    void deberiaFallarPorIdArticuloNulo()throws Exception{

        ArticulosDTO articulosDTO = new ArticuloTestDataBuilder().conIdArticulo(null).build();
        Articulos articulos = articulosMapper.toEntity(articulosDTO);
        Mockito.when(articulosDAO.save(Mockito.any())).thenReturn(articulos);
        Exception exception = assertThrows(Exception.class, ()->{
            articulosService.actualizar(articulos);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_EL_ID_DEL_ARTICULO_QUE_DESEA_ACTUALIZAR);

    }
    @Test
    @DisplayName("Debería fallar por idArticulo no existente")
    void deberiaFallarPorIdArticuloNoExistente()throws Exception{

        ArticulosDTO articulosDTO = new ArticuloTestDataBuilder().conIdArticulo(5556L).build();
        articulosDTO.setNombre("Martillo");
        articulosDTO.setDescripcion("Este martillo te permite reparar más rápido");
        articulosDTO.setImagen("martillo.png");
        articulosDTO.setPrecio(120);
        articulosDTO.setIdCategoria(1L);
        articulosDTO.setNivelValido(1);
        articulosDTO.setIdEstado(2L);
        articulosDTO.setUsuarioModificador("Angela");
        articulosDTO.setFechaModificacion(new Date());
        Articulos articulos = articulosMapper.toEntity(articulosDTO);
        Mockito.when(articulosDAO.existsById(articulosDTO.getIdArticulo())).thenReturn(false);
        Mockito.when(articulosDAO.save(Mockito.any())).thenReturn(articulos);
        Exception exception = assertThrows(Exception.class, ()->{
            articulosService.actualizar(articulos);
        });
        assertEquals(exception.getMessage(), NO_SE_ENCONTRO_UN_ARTICULO_CON_ESE_ID);

    }
    @Test
    @DisplayName("Debería fallar por nombre nulo")
    void deberiaFallarPorNombreNulo()throws Exception{

        ArticulosDTO articulosDTO = new ArticuloTestDataBuilder().conIdArticulo(5556L).build();
        articulosDTO.setNombre(null);
        articulosDTO.setDescripcion("Este martillo te permite reparar más rápido");
        articulosDTO.setImagen("martillo.png");
        articulosDTO.setPrecio(120);
        articulosDTO.setIdCategoria(1L);
        articulosDTO.setNivelValido(1);
        articulosDTO.setIdEstado(2L);
        articulosDTO.setUsuarioModificador("Angela");
        articulosDTO.setFechaModificacion(new Date());
        Articulos articulos = articulosMapper.toEntity(articulosDTO);
        Mockito.when(articulosDAO.existsById(articulosDTO.getIdArticulo())).thenReturn(true);
        Mockito.when(articulosDAO.save(Mockito.any())).thenReturn(articulos);
        Exception exception = assertThrows(Exception.class, ()->{
            articulosService.actualizar(articulos);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UN_NOMBRE_PARA_EL_ARTICULO);

    }

    @Test
    @DisplayName("Debería fallar por nombre vacio")
    void deberiaFallarPorNombreVacio()throws Exception{

        ArticulosDTO articulosDTO = new ArticuloTestDataBuilder().conIdArticulo(5556L).build();
        articulosDTO.setNombre("");
        articulosDTO.setDescripcion("Este martillo te permite reparar más rápido");
        articulosDTO.setImagen("martillo.png");
        articulosDTO.setPrecio(120);
        articulosDTO.setIdCategoria(1L);
        articulosDTO.setNivelValido(1);
        articulosDTO.setIdEstado(2L);
        articulosDTO.setUsuarioModificador("Angela");
        articulosDTO.setFechaModificacion(new Date());
        Articulos articulos = articulosMapper.toEntity(articulosDTO);
        Mockito.when(articulosDAO.existsById(articulosDTO.getIdArticulo())).thenReturn(true);
        Mockito.when(articulosDAO.save(Mockito.any())).thenReturn(articulos);
        Exception exception = assertThrows(Exception.class, ()->{
            articulosService.actualizar(articulos);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UN_NOMBRE_PARA_EL_ARTICULO);

    }
    @Test
    @DisplayName("Debería fallar por nombre largo")
    void deberiaFallarPorNombreLargo()throws Exception{

        ArticulosDTO articulosDTO = new ArticuloTestDataBuilder().conIdArticulo(5556L).build();
        articulosDTO.setNombre(NOMBRE_ARTICULO_MALO);
        articulosDTO.setDescripcion("Este martillo te permite reparar más rápido");
        articulosDTO.setImagen("martillo.png");
        articulosDTO.setPrecio(120);
        articulosDTO.setIdCategoria(1L);
        articulosDTO.setNivelValido(1);
        articulosDTO.setIdEstado(2L);
        articulosDTO.setUsuarioModificador("Angela");
        articulosDTO.setFechaModificacion(new Date());
        Articulos articulos = articulosMapper.toEntity(articulosDTO);
        Mockito.when(articulosDAO.existsById(articulosDTO.getIdArticulo())).thenReturn(true);
        Mockito.when(articulosDAO.save(Mockito.any())).thenReturn(articulos);
        Exception exception = assertThrows(Exception.class, ()->{
            articulosService.actualizar(articulos);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UN_NOMBRE_PARA_EL_ARTICULO);

    }

    @Test
    @DisplayName("Debería fallar por descripción null")
    void deberiaFallarPorDescripcionNull()throws Exception{

        ArticulosDTO articulosDTO = new ArticuloTestDataBuilder().conIdArticulo(5556L).build();
        articulosDTO.setNombre("Martillo");
        articulosDTO.setDescripcion(null);
        articulosDTO.setImagen("martillo.png");
        articulosDTO.setPrecio(120);
        articulosDTO.setIdCategoria(1L);
        articulosDTO.setNivelValido(1);
        articulosDTO.setIdEstado(2L);
        articulosDTO.setUsuarioModificador("Angela");
        articulosDTO.setFechaModificacion(new Date());
        Articulos articulos = articulosMapper.toEntity(articulosDTO);
        Mockito.when(articulosDAO.existsById(articulosDTO.getIdArticulo())).thenReturn(true);
        Mockito.when(articulosDAO.save(Mockito.any())).thenReturn(articulos);
        Exception exception = assertThrows(Exception.class, ()->{
            articulosService.actualizar(articulos);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UNA_DESCRIPCION_PARA_EL_ARTICULO);

    }

    @Test
    @DisplayName("Debería fallar por descripción vacio")
    void deberiaFallarPorDescripcionVacio()throws Exception{

        ArticulosDTO articulosDTO = new ArticuloTestDataBuilder().conIdArticulo(5556L).build();
        articulosDTO.setNombre("Martillo");
        articulosDTO.setDescripcion("");
        articulosDTO.setImagen("martillo.png");
        articulosDTO.setPrecio(120);
        articulosDTO.setIdCategoria(1L);
        articulosDTO.setNivelValido(1);
        articulosDTO.setIdEstado(2L);
        articulosDTO.setUsuarioModificador("Angela");
        articulosDTO.setFechaModificacion(new Date());
        Articulos articulos = articulosMapper.toEntity(articulosDTO);
        Mockito.when(articulosDAO.existsById(articulosDTO.getIdArticulo())).thenReturn(true);
        Mockito.when(articulosDAO.save(Mockito.any())).thenReturn(articulos);
        Exception exception = assertThrows(Exception.class, ()->{
            articulosService.actualizar(articulos);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UNA_DESCRIPCION_PARA_EL_ARTICULO);

    }

    @Test
    @DisplayName("Debería fallar por descripción largo")
    void deberiaFallarPorDescripcionLargo()throws Exception{

        ArticulosDTO articulosDTO = new ArticuloTestDataBuilder().conIdArticulo(5556L).build();
        articulosDTO.setNombre("Martillo");
        articulosDTO.setDescripcion(DESCRIPCION_MALA);
        articulosDTO.setImagen("martillo.png");
        articulosDTO.setPrecio(120);
        articulosDTO.setIdCategoria(1L);
        articulosDTO.setNivelValido(1);
        articulosDTO.setIdEstado(2L);
        articulosDTO.setUsuarioModificador("Angela");
        articulosDTO.setFechaModificacion(new Date());
        Articulos articulos = articulosMapper.toEntity(articulosDTO);
        Mockito.when(articulosDAO.existsById(articulosDTO.getIdArticulo())).thenReturn(true);
        Mockito.when(articulosDAO.save(Mockito.any())).thenReturn(articulos);
        Exception exception = assertThrows(Exception.class, ()->{
            articulosService.actualizar(articulos);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UNA_DESCRIPCION_PARA_EL_ARTICULO);

    }

    @Test
    @DisplayName("Debería fallar por imagen null")
    void deberiaFallarPorImagenNull()throws Exception{

        ArticulosDTO articulosDTO = new ArticuloTestDataBuilder().conIdArticulo(5556L).build();
        articulosDTO.setNombre("Martillo");
        articulosDTO.setDescripcion("Este martillo te permite reparar más rápido");
        articulosDTO.setImagen(null);
        articulosDTO.setPrecio(120);
        articulosDTO.setIdCategoria(1L);
        articulosDTO.setNivelValido(1);
        articulosDTO.setIdEstado(2L);
        articulosDTO.setUsuarioModificador("Angela");
        articulosDTO.setFechaModificacion(new Date());
        Articulos articulos = articulosMapper.toEntity(articulosDTO);
        Mockito.when(articulosDAO.existsById(articulosDTO.getIdArticulo())).thenReturn(true);
        Mockito.when(articulosDAO.save(Mockito.any())).thenReturn(articulos);
        Exception exception = assertThrows(Exception.class, ()->{
            articulosService.actualizar(articulos);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UNA_IMAGEN_PARA_EL_ARTICULO);

    }
    @Test
    @DisplayName("Debería fallar por imagen vacío")
    void deberiaFallarPorImagenVacio()throws Exception{

        ArticulosDTO articulosDTO = new ArticuloTestDataBuilder().conIdArticulo(5556L).build();
        articulosDTO.setNombre("Martillo");
        articulosDTO.setDescripcion("Este martillo te permite reparar más rápido");
        articulosDTO.setImagen("");
        articulosDTO.setPrecio(120);
        articulosDTO.setIdCategoria(1L);
        articulosDTO.setNivelValido(1);
        articulosDTO.setIdEstado(2L);
        articulosDTO.setUsuarioModificador("Angela");
        articulosDTO.setFechaModificacion(new Date());
        Articulos articulos = articulosMapper.toEntity(articulosDTO);
        Mockito.when(articulosDAO.existsById(articulosDTO.getIdArticulo())).thenReturn(true);
        Mockito.when(articulosDAO.save(Mockito.any())).thenReturn(articulos);
        Exception exception = assertThrows(Exception.class, ()->{
            articulosService.actualizar(articulos);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UNA_IMAGEN_PARA_EL_ARTICULO);

    }

    @Test
    @DisplayName("Debería fallar por nombre imagen largo")
    void deberiaFallarPorImagenLargo()throws Exception{

        ArticulosDTO articulosDTO = new ArticuloTestDataBuilder().conIdArticulo(5556L).build();
        articulosDTO.setNombre("Martillo");
        articulosDTO.setDescripcion("Este martillo te permite reparar más rápido");
        articulosDTO.setImagen(IMAGEN_MALA);
        articulosDTO.setPrecio(120);
        articulosDTO.setIdCategoria(1L);
        articulosDTO.setNivelValido(1);
        articulosDTO.setIdEstado(2L);
        articulosDTO.setUsuarioModificador("Angela");
        articulosDTO.setFechaModificacion(new Date());
        Articulos articulos = articulosMapper.toEntity(articulosDTO);
        Mockito.when(articulosDAO.existsById(articulosDTO.getIdArticulo())).thenReturn(true);
        Mockito.when(articulosDAO.save(Mockito.any())).thenReturn(articulos);
        Exception exception = assertThrows(Exception.class, ()->{
            articulosService.actualizar(articulos);
        });
        assertEquals(exception.getMessage(), LA_URL_DE_LA_IMAGEN_ES_MUY_LARGA_MAXIMO_250_CARACTERES);

    }

    @Test
    @DisplayName("Debería fallar por precio negativo")
    void deberiaFallarPorPrecioNegativo()throws Exception{

        ArticulosDTO articulosDTO = new ArticuloTestDataBuilder().conIdArticulo(5556L).build();
        articulosDTO.setNombre("Martillo");
        articulosDTO.setDescripcion("Este martillo te permite reparar más rápido");
        articulosDTO.setImagen("martillo.png");
        articulosDTO.setPrecio(-120);
        articulosDTO.setIdCategoria(1L);
        articulosDTO.setNivelValido(1);
        articulosDTO.setIdEstado(2L);
        articulosDTO.setUsuarioModificador("Angela");
        articulosDTO.setFechaModificacion(new Date());
        Articulos articulos = articulosMapper.toEntity(articulosDTO);
        Mockito.when(articulosDAO.existsById(articulosDTO.getIdArticulo())).thenReturn(true);
        Mockito.when(articulosDAO.save(Mockito.any())).thenReturn(articulos);
        Exception exception = assertThrows(Exception.class, ()->{
            articulosService.actualizar(articulos);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_EL_PRECIO_DEL_ARTICULO);

    }

    @Test
    @DisplayName("Debería fallar por idCategoria null")
    void deberiaFallarPorIdCategoriaNull()throws Exception{

        ArticulosDTO articulosDTO = new ArticuloTestDataBuilder().conIdArticulo(5556L).build();
        articulosDTO.setNombre("Martillo");
        articulosDTO.setDescripcion("Este martillo te permite reparar más rápido");
        articulosDTO.setImagen("martillo.png");
        articulosDTO.setPrecio(120);
        articulosDTO.setIdCategoria(null);
        articulosDTO.setNivelValido(1);
        articulosDTO.setIdEstado(2L);
        articulosDTO.setUsuarioModificador("Angela");
        articulosDTO.setFechaModificacion(new Date());
        Articulos articulos = articulosMapper.toEntity(articulosDTO);
        Mockito.when(articulosDAO.existsById(articulosDTO.getIdArticulo())).thenReturn(true);
        Mockito.when(articulosDAO.save(Mockito.any())).thenReturn(articulos);
        Exception exception = assertThrows(Exception.class, ()->{
            articulosService.actualizar(articulos);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UN_ID_DE_CATEGORIA);

    }

    @Test
    @DisplayName("Debería fallar por idCategoria negativo")
    void deberiaFallarPorIdCategoriaNegativo()throws Exception{

        ArticulosDTO articulosDTO = new ArticuloTestDataBuilder().conIdArticulo(5556L).build();
        articulosDTO.setNombre("Martillo");
        articulosDTO.setDescripcion("Este martillo te permite reparar más rápido");
        articulosDTO.setImagen("martillo.png");
        articulosDTO.setPrecio(120);
        articulosDTO.setIdCategoria(-482L);
        articulosDTO.setNivelValido(1);
        articulosDTO.setIdEstado(2L);
        articulosDTO.setUsuarioModificador("Angela");
        articulosDTO.setFechaModificacion(new Date());
        Articulos articulos = articulosMapper.toEntity(articulosDTO);
        Mockito.when(articulosDAO.existsById(articulosDTO.getIdArticulo())).thenReturn(true);
        Mockito.when(articulosDAO.save(Mockito.any())).thenReturn(articulos);
        Exception exception = assertThrows(Exception.class, ()->{
            articulosService.actualizar(articulos);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UN_ID_DE_CATEGORIA_VALIDO);

    }
    @Test
    @DisplayName("Debería fallar por idCategoria que no existe")
    void deberiaFallarPorIdCategoriaNoExiste()throws Exception{

        ArticulosDTO articulosDTO = new ArticuloTestDataBuilder().conIdArticulo(5556L).build();
        articulosDTO.setNombre("Martillo");
        articulosDTO.setDescripcion("Este martillo te permite reparar más rápido");
        articulosDTO.setImagen("martillo.png");
        articulosDTO.setPrecio(120);
        articulosDTO.setIdCategoria(482L);
        articulosDTO.setNivelValido(1);
        articulosDTO.setIdEstado(2L);
        articulosDTO.setUsuarioModificador("Angela");
        articulosDTO.setFechaModificacion(new Date());
        Articulos articulos = articulosMapper.toEntity(articulosDTO);
        Mockito.when(articulosDAO.existsById(articulosDTO.getIdArticulo())).thenReturn(true);
        Mockito.when(categoriaDAO.existsById(articulosDTO.getIdCategoria())).thenReturn(false);
        Mockito.when(articulosDAO.save(Mockito.any())).thenReturn(articulos);
        Exception exception = assertThrows(Exception.class, ()->{
            articulosService.actualizar(articulos);
        });
        assertEquals(exception.getMessage(), NO_EXISTE_UNA_CATEGORIA_CON_ESE_ID);

    }

    @Test
    @DisplayName("Debería fallar por nivel válido negativo")
    void deberiaFallarPorNivelValidoNegativo()throws Exception{

        ArticulosDTO articulosDTO = new ArticuloTestDataBuilder().conIdArticulo(5556L).build();
        articulosDTO.setNombre("Martillo");
        articulosDTO.setDescripcion("Este martillo te permite reparar más rápido");
        articulosDTO.setImagen("martillo.png");
        articulosDTO.setPrecio(120);
        articulosDTO.setIdCategoria(482L);
        articulosDTO.setNivelValido(-541);
        articulosDTO.setIdEstado(2L);
        articulosDTO.setUsuarioModificador("Angela");
        articulosDTO.setFechaModificacion(new Date());
        Articulos articulos = articulosMapper.toEntity(articulosDTO);
        Mockito.when(articulosDAO.existsById(articulosDTO.getIdArticulo())).thenReturn(true);
        Mockito.when(categoriaDAO.existsById(articulosDTO.getIdCategoria())).thenReturn(true);
        Mockito.when(articulosDAO.save(Mockito.any())).thenReturn(articulos);
        Exception exception = assertThrows(Exception.class, ()->{
            articulosService.actualizar(articulos);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_EL_NIVEL_VALIDO_DEL_ARTICULO);

    }

    @Test
    @DisplayName("Debería fallar por idEstado null")
    void deberiaFallarPorIdEstadoNulll()throws Exception{

        ArticulosDTO articulosDTO = new ArticuloTestDataBuilder().conIdArticulo(267367L).build();
        articulosDTO.setNombre("Martillo");
        articulosDTO.setDescripcion("Este martillo te permite reparar más rápido");
        articulosDTO.setImagen("martillo.png");
        articulosDTO.setPrecio(120);
        articulosDTO.setIdCategoria(1L);
        articulosDTO.setNivelValido(1);
        articulosDTO.setIdEstado(null);
        articulosDTO.setUsuarioModificador("Angela");
        articulosDTO.setFechaModificacion(new Date());
        Articulos articulos = articulosMapper.toEntity(articulosDTO);
        Mockito.when(articulosDAO.existsById(articulosDTO.getIdArticulo())).thenReturn(true);
        Mockito.when(categoriaDAO.existsById(articulosDTO.getIdCategoria())).thenReturn(true);
        Mockito.when(articulosDAO.save(Mockito.any())).thenReturn(articulos);
        Exception exception = assertThrows(Exception.class, ()->{
            articulosService.actualizar(articulos);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UN_ID_ESTADO);

    }

    @Test
    @DisplayName("Debería fallar por idEstado negativo")
    void deberiaFallarPorIdEstadoNegativo()throws Exception{

        ArticulosDTO articulosDTO = new ArticuloTestDataBuilder().conIdArticulo(267367L).build();
        articulosDTO.setNombre("Martillo");
        articulosDTO.setDescripcion("Este martillo te permite reparar más rápido");
        articulosDTO.setImagen("martillo.png");
        articulosDTO.setPrecio(120);
        articulosDTO.setIdCategoria(1L);
        articulosDTO.setNivelValido(1);
        articulosDTO.setIdEstado(-8524L);
        articulosDTO.setUsuarioModificador("Angela");
        articulosDTO.setFechaModificacion(new Date());
        Articulos articulos = articulosMapper.toEntity(articulosDTO);
        Mockito.when(articulosDAO.existsById(articulosDTO.getIdArticulo())).thenReturn(true);
        Mockito.when(categoriaDAO.existsById(articulosDTO.getIdCategoria())).thenReturn(true);
        Mockito.when(articulosDAO.save(Mockito.any())).thenReturn(articulos);
        Exception exception = assertThrows(Exception.class, ()->{
            articulosService.actualizar(articulos);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UN_ID_DE_ESTADO_VALIDO);

    }
    @Test
    @DisplayName("Debería fallar por idEstado no existe")
    void deberiaFallarPorIdEstadoNoExiste()throws Exception{

        ArticulosDTO articulosDTO = new ArticuloTestDataBuilder().conIdArticulo(267367L).build();
        articulosDTO.setNombre("Martillo");
        articulosDTO.setDescripcion("Este martillo te permite reparar más rápido");
        articulosDTO.setImagen("martillo.png");
        articulosDTO.setPrecio(120);
        articulosDTO.setIdCategoria(1L);
        articulosDTO.setNivelValido(1);
        articulosDTO.setIdEstado(8524L);
        articulosDTO.setUsuarioModificador("Angela");
        articulosDTO.setFechaModificacion(new Date());
        Articulos articulos = articulosMapper.toEntity(articulosDTO);
        Mockito.when(articulosDAO.existsById(articulosDTO.getIdArticulo())).thenReturn(true);
        Mockito.when(categoriaDAO.existsById(articulosDTO.getIdCategoria())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(articulosDTO.getIdEstado())).thenReturn(false);
        Mockito.when(articulosDAO.save(Mockito.any())).thenReturn(articulos);
        Exception exception = assertThrows(Exception.class, ()->{
            articulosService.actualizar(articulos);
        });
        assertEquals(exception.getMessage(), NO_EXISTE_UN_ESTADO_CON_ESE_ID);

    }
    @Test
    @DisplayName("Debería fallar por usuario modificador nulo")
    void deberiaFallarPorUsuarioModificadorNulo()throws Exception{

        ArticulosDTO articulosDTO = new ArticuloTestDataBuilder().conIdArticulo(267367L).build();
        articulosDTO.setNombre("Martillo");
        articulosDTO.setDescripcion("Este martillo te permite reparar más rápido");
        articulosDTO.setImagen("martillo.png");
        articulosDTO.setPrecio(120);
        articulosDTO.setIdCategoria(1L);
        articulosDTO.setNivelValido(1);
        articulosDTO.setIdEstado(8524L);
        articulosDTO.setUsuarioModificador(null);
        articulosDTO.setFechaModificacion(new Date());
        Articulos articulos = articulosMapper.toEntity(articulosDTO);
        Mockito.when(articulosDAO.existsById(articulosDTO.getIdArticulo())).thenReturn(true);
        Mockito.when(categoriaDAO.existsById(articulosDTO.getIdCategoria())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(articulosDTO.getIdEstado())).thenReturn(true);
        Mockito.when(articulosDAO.save(Mockito.any())).thenReturn(articulos);
        Exception exception = assertThrows(Exception.class, ()->{
            articulosService.actualizar(articulos);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UN_USUARIO_MODIFICADOR_DEL_ARTICULO_VALIDO);

    }

    @Test
    @DisplayName("Debería fallar por usuario modificador vacio")
    void deberiaFallarPorUsuarioModificadorVacio()throws Exception{

        ArticulosDTO articulosDTO = new ArticuloTestDataBuilder().conIdArticulo(267367L).build();
        articulosDTO.setNombre("Martillo");
        articulosDTO.setDescripcion("Este martillo te permite reparar más rápido");
        articulosDTO.setImagen("martillo.png");
        articulosDTO.setPrecio(120);
        articulosDTO.setIdCategoria(1L);
        articulosDTO.setNivelValido(1);
        articulosDTO.setIdEstado(8524L);
        articulosDTO.setUsuarioModificador("");
        articulosDTO.setFechaModificacion(new Date());
        Articulos articulos = articulosMapper.toEntity(articulosDTO);
        Mockito.when(articulosDAO.existsById(articulosDTO.getIdArticulo())).thenReturn(true);
        Mockito.when(categoriaDAO.existsById(articulosDTO.getIdCategoria())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(articulosDTO.getIdEstado())).thenReturn(true);
        Mockito.when(articulosDAO.save(Mockito.any())).thenReturn(articulos);
        Exception exception = assertThrows(Exception.class, ()->{
            articulosService.actualizar(articulos);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UN_USUARIO_MODIFICADOR_DEL_ARTICULO_VALIDO);

    }

    @Test
    @DisplayName("Debería fallar por usuario modificador largo")
    void deberiaFallarPorUsuarioModificadorLargo()throws Exception{

        ArticulosDTO articulosDTO = new ArticuloTestDataBuilder().conIdArticulo(267367L).build();
        articulosDTO.setNombre("Martillo");
        articulosDTO.setDescripcion("Este martillo te permite reparar más rápido");
        articulosDTO.setImagen("martillo.png");
        articulosDTO.setPrecio(120);
        articulosDTO.setIdCategoria(1L);
        articulosDTO.setNivelValido(1);
        articulosDTO.setIdEstado(8524L);
        articulosDTO.setUsuarioModificador(USUARIO_MODIFICADOR_MALO);
        articulosDTO.setFechaModificacion(new Date());
        Articulos articulos = articulosMapper.toEntity(articulosDTO);
        Mockito.when(articulosDAO.existsById(articulosDTO.getIdArticulo())).thenReturn(true);
        Mockito.when(categoriaDAO.existsById(articulosDTO.getIdCategoria())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(articulosDTO.getIdEstado())).thenReturn(true);
        Mockito.when(articulosDAO.save(Mockito.any())).thenReturn(articulos);
        Exception exception = assertThrows(Exception.class, ()->{
            articulosService.actualizar(articulos);
        });
        assertEquals(exception.getMessage(), EL_NOMBRE_DEL_USUARIO_MODIFICADOR_ES_MUY_LARGO_SOLO_PUEDE_CONTENER_50_CARACTERES);

    }

    @Test
    @DisplayName("Debería fallar por fecha modificación null")
    void deberiaFallarPorFechaModificacionNull()throws Exception{

        ArticulosDTO articulosDTO = new ArticuloTestDataBuilder().conIdArticulo(267367L).build();
        articulosDTO.setNombre("Martillo");
        articulosDTO.setDescripcion("Este martillo te permite reparar más rápido");
        articulosDTO.setImagen("martillo.png");
        articulosDTO.setPrecio(120);
        articulosDTO.setIdCategoria(1L);
        articulosDTO.setNivelValido(1);
        articulosDTO.setIdEstado(8524L);
        articulosDTO.setUsuarioModificador("Angela");
        articulosDTO.setFechaModificacion(null);
        Articulos articulos = articulosMapper.toEntity(articulosDTO);
        Mockito.when(articulosDAO.existsById(articulosDTO.getIdArticulo())).thenReturn(true);
        Mockito.when(categoriaDAO.existsById(articulosDTO.getIdCategoria())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(articulosDTO.getIdEstado())).thenReturn(true);
        Mockito.when(articulosDAO.save(Mockito.any())).thenReturn(articulos);
        Exception exception = assertThrows(Exception.class, ()->{
            articulosService.actualizar(articulos);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UNA_FECHA_DE_MODIFICACION);

    }
    @Test
    @DisplayName("Debería fallar por fecha modificación futura")
    void deberiaFallarPorFechaModificacionFutura()throws Exception{

        ArticulosDTO articulosDTO = new ArticuloTestDataBuilder().conIdArticulo(267367L).build();
        articulosDTO.setNombre("Martillo");
        articulosDTO.setDescripcion("Este martillo te permite reparar más rápido");
        articulosDTO.setImagen("martillo.png");
        articulosDTO.setPrecio(120);
        articulosDTO.setIdCategoria(1L);
        articulosDTO.setNivelValido(1);
        articulosDTO.setIdEstado(8524L);
        articulosDTO.setUsuarioModificador("Angela");
        articulosDTO.setFechaModificacion(new Date(2023,4,12));
        Articulos articulos = articulosMapper.toEntity(articulosDTO);
        Mockito.when(articulosDAO.existsById(articulosDTO.getIdArticulo())).thenReturn(true);
        Mockito.when(categoriaDAO.existsById(articulosDTO.getIdCategoria())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(articulosDTO.getIdEstado())).thenReturn(true);
        Mockito.when(articulosDAO.save(Mockito.any())).thenReturn(articulos);
        Exception exception = assertThrows(Exception.class, ()->{
            articulosService.actualizar(articulos);
        });
        assertEquals(exception.getMessage(), NO_SE_PUEDE_INGRESAR_UNA_FECHA_QUE_AUN_NO_HA_SUCEDIDO);

    }


}
