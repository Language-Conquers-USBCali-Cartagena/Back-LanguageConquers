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

import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@SpringBootTest
public class ManejadorActualizarArticulosAdquiridosTest {

    public static final String SE_ACTUALIZO_CORRECTAMENTE_EL_ARTICULO_ADQUIRIDO = "Se actualizo correctamente el artículo adquirido.";
    public static final String DEBE_INGRESAR_EL_ID_DEL_ARTICULO_QUE_DESEA_ACTUALIZAR = "Debe ingresar el id del artículo que desea actualizar.";
    public static final String DEBE_INGRESAR_EL_ID_DEL_ARTICULO_ADQUIRIDO_VALIDO = "Debe ingresar el id del artículo adquirido válido.";
    public static final String NO_SE_ENCONTRO_UN_ARTICULO_ADQUIRIDO_CON_ESE_ID = "No se encontró un artículo adquirido con ese id.";
    public static final String DEBE_INGRESAR_UN_ARTICULO = "Debe ingresar un id de artículo.";
    public static final String DEBE_INGRESAR_UN_ESTUDIANTE = "Debe ingresar un id de un estudiante.";
    public static final String DEBE_INGRESAR_UN_ARTICULO_VALIDO = "Debe ingresar un id de artículo válido.";
    public static final String DEBE_INGRESAR_UN_ESTUDIANTE_VALIDO = "Debe ingresar un id de estudiante válido.";
    public static final String NO_SE_ENCONTRO_UN_ARTICULO_CON_ESE_ID = "No se encontró un artículo con ese id.";
    public static final String NO_SE_ENCONTRO_UN_ESTUDIANTE_CON_ESE_ID = "No se encontró un estudiante con ese id.";
    public static final String DEBE_INGRESAR_UN_USUARIO_MODIFICADOR = "Debe ingresar el usuario modificador.";
    public static final String EL_NOMBRE_DE_USUARIO_MODIFICADOR_ES_MUY_LARGO_SOLO_PUEDE_CONTENER_5O_CARACTERES = "El nombre de usuario modifcador es muy largo, solo puede contener 50 caracteres.";
    public static final String DEBE_INGRESAR_UNA_FECHA_DE_MODIFICACION = "Debe ingresar una fecha de modificación.";
    public static final String NO_PUEDE_INGRESAR_UNA_FECHA_QUE_AUN_NO_HA_SUCEDIDO = "No puede ingresar una fecha que aun no ha sucedido.";
    public static final Long ID_ARTICULO_ADQUIRIDO_NEGATIVO = -734984L;
    public static final Long ID_ARTICULO_ADQUIRIDO_MALO = 233445L;
    public static final Long ID_ARTICULO = 2344984L;
    public static final Long ID_ARTICULO_MALO = 2345L;
    public static final Long ID_ARTICULO_NEGATIVO = -2345L;
    public static final Long ID_ESTUDIANTE = 238734L;
    public static final Long ID_ESTUDIANTE_MALO = 2334L;
    public static final Long ID_ESTUDIANTE_NEGATIVO = -2874L;
    public static final String USUARIO_MODIFICADOR = "Angela";
    public static final String USUARIO_MODIFICADOR_MALO = "Se debe ingresar un usuario creador del artículo válido.";

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
    @DisplayName("Debería actualizar el articulo adquirido exitosamente")
    void deberiaActualizarArticuloExitosamente()throws Exception{

        ArticulosAdquiridosDTO articulosAdquiridosDTO = new ArticulosAdquiridosTestDataBuilder()
                .conIdArticulos(ID_ARTICULO)
                .conIdEstudiante(ID_ESTUDIANTE)
                .conUsuarioModificador(USUARIO_MODIFICADOR)
                .conFechaModificacion(new Date())
                .build();
        ArticulosAdquiridos articulosAdquiridos = articulosAdquiridosMapper.toEntity(articulosAdquiridosDTO);

        Mockito.when(articulosAdquiridosDAO.existsById(articulosAdquiridosDTO.getIdArticuloAdquirido())).thenReturn(true);
        Mockito.when(articulosAdquiridosDAO.findById(articulosAdquiridosDTO.getIdArticuloAdquirido())).thenReturn(Optional.of(articulosAdquiridos));
        Mockito.when(estudianteDAO.existsById(articulosAdquiridosDTO.getIdEstudiante())).thenReturn(true);
        Mockito.when(articulosDAO.existsById(articulosAdquiridosDTO.getIdArticulos())).thenReturn(true);
        Mockito.when(articulosAdquiridosDAO.save(Mockito.any())).thenReturn(articulosAdquiridos);

        String actualizarArticuloAdquirido = articulosAdquiridosService.actualizar(articulosAdquiridos);
        assertEquals(SE_ACTUALIZO_CORRECTAMENTE_EL_ARTICULO_ADQUIRIDO, actualizarArticuloAdquirido);

    }

    @Test
    @DisplayName("Debería fallar por IdArticuloAdquirido null ")
    void deberiaFallarPorIdArticuloAdquiridoNull()throws Exception{

        ArticulosAdquiridosDTO articulosAdquiridosDTO = new ArticulosAdquiridosTestDataBuilder()
                .conIdArticuloAdquirido(null)
                .build();
        ArticulosAdquiridos articulosAdquiridos = articulosAdquiridosMapper.toEntity(articulosAdquiridosDTO);

        Mockito.when(articulosAdquiridosDAO.save(Mockito.any())).thenReturn(articulosAdquiridos);

        Exception exception = assertThrows(Exception.class, ()->{
            articulosAdquiridosService.actualizar(articulosAdquiridos);
        });
        assertEquals(exception.getMessage(),DEBE_INGRESAR_EL_ID_DEL_ARTICULO_QUE_DESEA_ACTUALIZAR);

    }
    @Test
    @DisplayName("Debería fallar por IdArticuloAdquirido no existente ")
    void deberiaFallarPorIdArticuloAdquiridoNoExistente()throws Exception{

        ArticulosAdquiridosDTO articulosAdquiridosDTO = new ArticulosAdquiridosTestDataBuilder()
                .conIdArticuloAdquirido(ID_ARTICULO_ADQUIRIDO_MALO)
                .build();
        ArticulosAdquiridos articulosAdquiridos = articulosAdquiridosMapper.toEntity(articulosAdquiridosDTO);

        Mockito.when(articulosAdquiridosDAO.existsById(articulosAdquiridosDTO.getIdArticuloAdquirido())).thenReturn(false);
        Mockito.when(articulosAdquiridosDAO.save(Mockito.any())).thenReturn(articulosAdquiridos);

        Exception exception = assertThrows(Exception.class, ()->{
            articulosAdquiridosService.actualizar(articulosAdquiridos);
        });
        assertEquals(exception.getMessage(),NO_SE_ENCONTRO_UN_ARTICULO_ADQUIRIDO_CON_ESE_ID);

    }

    @Test
    @DisplayName("Debería fallar por IdArticuloAdquirido negativo ")
    void deberiaFallarPorIdArticuloAdquiridoNegativo()throws Exception{

        ArticulosAdquiridosDTO articulosAdquiridosDTO = new ArticulosAdquiridosTestDataBuilder()
                .conIdArticuloAdquirido(ID_ARTICULO_ADQUIRIDO_NEGATIVO)
                .build();
        ArticulosAdquiridos articulosAdquiridos = articulosAdquiridosMapper.toEntity(articulosAdquiridosDTO);

        Mockito.when(articulosAdquiridosDAO.save(Mockito.any())).thenReturn(articulosAdquiridos);

        Exception exception = assertThrows(Exception.class, ()->{
            articulosAdquiridosService.actualizar(articulosAdquiridos);
        });
        assertEquals(exception.getMessage(),DEBE_INGRESAR_EL_ID_DEL_ARTICULO_ADQUIRIDO_VALIDO);

    }

    @Test
    @DisplayName("Debería fallar por IdArticulo nulo")
    void deberiaFallarPorIdArticuloNull()throws Exception{

        ArticulosAdquiridosDTO articulosAdquiridosDTO = new ArticulosAdquiridosTestDataBuilder()
                .conIdArticulos(null)
                .build();
        ArticulosAdquiridos articulosAdquiridos = articulosAdquiridosMapper.toEntity(articulosAdquiridosDTO);

        Mockito.when(articulosAdquiridosDAO.existsById(articulosAdquiridosDTO.getIdArticuloAdquirido())).thenReturn(true);
        Mockito.when(articulosAdquiridosDAO.save(Mockito.any())).thenReturn(articulosAdquiridos);

        Exception exception = assertThrows(Exception.class, ()->{
            articulosAdquiridosService.actualizar(articulosAdquiridos);
        });
        assertEquals(exception.getMessage(),DEBE_INGRESAR_UN_ARTICULO);

    }

    @Test
    @DisplayName("Debería fallar por IdEstudiante nulo")
    void deberiaFallarPorIdEstudiante()throws Exception{

        ArticulosAdquiridosDTO articulosAdquiridosDTO = new ArticulosAdquiridosTestDataBuilder()
                .conIdEstudiante(null)
                .build();
        ArticulosAdquiridos articulosAdquiridos = articulosAdquiridosMapper.toEntity(articulosAdquiridosDTO);

        Mockito.when(articulosAdquiridosDAO.existsById(articulosAdquiridosDTO.getIdArticuloAdquirido())).thenReturn(true);
        Mockito.when(articulosAdquiridosDAO.save(Mockito.any())).thenReturn(articulosAdquiridos);

        Exception exception = assertThrows(Exception.class, ()->{
            articulosAdquiridosService.actualizar(articulosAdquiridos);
        });
        assertEquals(exception.getMessage(),DEBE_INGRESAR_UN_ESTUDIANTE);

    }

    @Test
    @DisplayName("Debería fallar por IdArticulo negativo")
    void deberiaFallarPorIdArticuloNegativo()throws Exception{

        ArticulosAdquiridosDTO articulosAdquiridosDTO = new ArticulosAdquiridosTestDataBuilder()
                .conIdArticulos(ID_ARTICULO_NEGATIVO)
                .build();
        ArticulosAdquiridos articulosAdquiridos = articulosAdquiridosMapper.toEntity(articulosAdquiridosDTO);

        Mockito.when(articulosAdquiridosDAO.existsById(articulosAdquiridosDTO.getIdArticuloAdquirido())).thenReturn(true);
        Mockito.when(articulosAdquiridosDAO.save(Mockito.any())).thenReturn(articulosAdquiridos);

        Exception exception = assertThrows(Exception.class, ()->{
            articulosAdquiridosService.actualizar(articulosAdquiridos);
        });
        assertEquals(exception.getMessage(),DEBE_INGRESAR_UN_ARTICULO_VALIDO);

    }
    @Test
    @DisplayName("Debería fallar por IdEstudiante negativo")
    void deberiaFallarPorIdEstudianteNegativo()throws Exception{

        ArticulosAdquiridosDTO articulosAdquiridosDTO = new ArticulosAdquiridosTestDataBuilder()
                .conIdEstudiante(ID_ESTUDIANTE_NEGATIVO)
                .build();
        ArticulosAdquiridos articulosAdquiridos = articulosAdquiridosMapper.toEntity(articulosAdquiridosDTO);

        Mockito.when(articulosAdquiridosDAO.existsById(articulosAdquiridosDTO.getIdArticuloAdquirido())).thenReturn(true);
        Mockito.when(articulosAdquiridosDAO.save(Mockito.any())).thenReturn(articulosAdquiridos);

        Exception exception = assertThrows(Exception.class, ()->{
            articulosAdquiridosService.actualizar(articulosAdquiridos);
        });
        assertEquals(exception.getMessage(),DEBE_INGRESAR_UN_ESTUDIANTE_VALIDO);

    }

    @Test
    @DisplayName("Debería fallar por IdEstudiante no existe")
    void deberiaFallarPorIdEstudianteNoExiste()throws Exception{

        ArticulosAdquiridosDTO articulosAdquiridosDTO = new ArticulosAdquiridosTestDataBuilder()
                .conIdEstudiante(ID_ESTUDIANTE_MALO)
                .build();
        ArticulosAdquiridos articulosAdquiridos = articulosAdquiridosMapper.toEntity(articulosAdquiridosDTO);

        Mockito.when(articulosAdquiridosDAO.existsById(articulosAdquiridosDTO.getIdArticuloAdquirido())).thenReturn(true);
        Mockito.when(articulosDAO.existsById(articulosAdquiridosDTO.getIdArticulos())).thenReturn(true);
        Mockito.when(estudianteDAO.existsById(articulosAdquiridosDTO.getIdEstudiante())).thenReturn(false);
        Mockito.when(articulosAdquiridosDAO.save(Mockito.any())).thenReturn(articulosAdquiridos);

        Exception exception = assertThrows(Exception.class, ()->{
            articulosAdquiridosService.actualizar(articulosAdquiridos);
        });
        assertEquals(exception.getMessage(),NO_SE_ENCONTRO_UN_ESTUDIANTE_CON_ESE_ID);

    }

    @Test
    @DisplayName("Debería fallar por IdArticulo no existe")
    void deberiaFallarPorIdArticuloNoExiste()throws Exception{

        ArticulosAdquiridosDTO articulosAdquiridosDTO = new ArticulosAdquiridosTestDataBuilder()
                .conIdArticulos(ID_ARTICULO_MALO)
                .build();
        ArticulosAdquiridos articulosAdquiridos = articulosAdquiridosMapper.toEntity(articulosAdquiridosDTO);

        Mockito.when(articulosAdquiridosDAO.existsById(articulosAdquiridosDTO.getIdArticuloAdquirido())).thenReturn(true);
        Mockito.when(estudianteDAO.existsById(articulosAdquiridosDTO.getIdEstudiante())).thenReturn(true);
        Mockito.when(articulosDAO.existsById(articulosAdquiridosDTO.getIdArticulos())).thenReturn(false);
        Mockito.when(articulosAdquiridosDAO.save(Mockito.any())).thenReturn(articulosAdquiridos);

        Exception exception = assertThrows(Exception.class, ()->{
            articulosAdquiridosService.actualizar(articulosAdquiridos);
        });
        assertEquals(exception.getMessage(),NO_SE_ENCONTRO_UN_ARTICULO_CON_ESE_ID);

    }

    @Test
    @DisplayName("Debería fallar por usuario modificador null")
    void deberiaFallarPorUsuarioModificadorNull()throws Exception{

        ArticulosAdquiridosDTO articulosAdquiridosDTO = new ArticulosAdquiridosTestDataBuilder()
                .conUsuarioModificador(null)
                .build();
        ArticulosAdquiridos articulosAdquiridos = articulosAdquiridosMapper.toEntity(articulosAdquiridosDTO);

        Mockito.when(articulosAdquiridosDAO.existsById(articulosAdquiridosDTO.getIdArticuloAdquirido())).thenReturn(true);
        Mockito.when(estudianteDAO.existsById(articulosAdquiridosDTO.getIdEstudiante())).thenReturn(true);
        Mockito.when(articulosDAO.existsById(articulosAdquiridosDTO.getIdArticulos())).thenReturn(true);
        Mockito.when(articulosAdquiridosDAO.save(Mockito.any())).thenReturn(articulosAdquiridos);

        Exception exception = assertThrows(Exception.class, ()->{
            articulosAdquiridosService.actualizar(articulosAdquiridos);
        });
        assertEquals(exception.getMessage(),DEBE_INGRESAR_UN_USUARIO_MODIFICADOR);

    }
    @Test
    @DisplayName("Debería fallar por usuario modificador vacio")
    void deberiaFallarPorUsuarioModificadorVacio()throws Exception{

        ArticulosAdquiridosDTO articulosAdquiridosDTO = new ArticulosAdquiridosTestDataBuilder()
                .conUsuarioModificador("")
                .build();
        ArticulosAdquiridos articulosAdquiridos = articulosAdquiridosMapper.toEntity(articulosAdquiridosDTO);

        Mockito.when(articulosAdquiridosDAO.existsById(articulosAdquiridosDTO.getIdArticuloAdquirido())).thenReturn(true);
        Mockito.when(estudianteDAO.existsById(articulosAdquiridosDTO.getIdEstudiante())).thenReturn(true);
        Mockito.when(articulosDAO.existsById(articulosAdquiridosDTO.getIdArticulos())).thenReturn(true);
        Mockito.when(articulosAdquiridosDAO.save(Mockito.any())).thenReturn(articulosAdquiridos);

        Exception exception = assertThrows(Exception.class, ()->{
            articulosAdquiridosService.actualizar(articulosAdquiridos);
        });
        assertEquals(exception.getMessage(),DEBE_INGRESAR_UN_USUARIO_MODIFICADOR);

    }
    @Test
    @DisplayName("Debería fallar por usuario modificador largo")
    void deberiaFallarPorUsuarioModificadorLargo()throws Exception{

        ArticulosAdquiridosDTO articulosAdquiridosDTO = new ArticulosAdquiridosTestDataBuilder()
                .conUsuarioModificador(USUARIO_MODIFICADOR_MALO)
                .build();
        ArticulosAdquiridos articulosAdquiridos = articulosAdquiridosMapper.toEntity(articulosAdquiridosDTO);

        Mockito.when(articulosAdquiridosDAO.existsById(articulosAdquiridosDTO.getIdArticuloAdquirido())).thenReturn(true);
        Mockito.when(estudianteDAO.existsById(articulosAdquiridosDTO.getIdEstudiante())).thenReturn(true);
        Mockito.when(articulosDAO.existsById(articulosAdquiridosDTO.getIdArticulos())).thenReturn(true);
        Mockito.when(articulosAdquiridosDAO.save(Mockito.any())).thenReturn(articulosAdquiridos);

        Exception exception = assertThrows(Exception.class, ()->{
            articulosAdquiridosService.actualizar(articulosAdquiridos);
        });
        assertEquals(exception.getMessage(),EL_NOMBRE_DE_USUARIO_MODIFICADOR_ES_MUY_LARGO_SOLO_PUEDE_CONTENER_5O_CARACTERES);

    }
    @Test
    @DisplayName("Debería fallar por fecha modificación null")
    void deberiaFallarPorFechaModificacionNull()throws Exception{

        ArticulosAdquiridosDTO articulosAdquiridosDTO = new ArticulosAdquiridosTestDataBuilder()
                .conFechaModificacion(null)
                .build();
        ArticulosAdquiridos articulosAdquiridos = articulosAdquiridosMapper.toEntity(articulosAdquiridosDTO);

        Mockito.when(articulosAdquiridosDAO.existsById(articulosAdquiridosDTO.getIdArticuloAdquirido())).thenReturn(true);
        Mockito.when(estudianteDAO.existsById(articulosAdquiridosDTO.getIdEstudiante())).thenReturn(true);
        Mockito.when(articulosDAO.existsById(articulosAdquiridosDTO.getIdArticulos())).thenReturn(true);
        Mockito.when(articulosAdquiridosDAO.save(Mockito.any())).thenReturn(articulosAdquiridos);

        Exception exception = assertThrows(Exception.class, ()->{
            articulosAdquiridosService.actualizar(articulosAdquiridos);
        });
        assertEquals(exception.getMessage(),DEBE_INGRESAR_UNA_FECHA_DE_MODIFICACION);

    }

    @Test
    @DisplayName("Debería fallar por fecha modificación futura")
    void deberiaFallarPorFechaModificacionFutura()throws Exception{

        ArticulosAdquiridosDTO articulosAdquiridosDTO = new ArticulosAdquiridosTestDataBuilder()
                .conFechaModificacion(new Date(2023,8,20))
                .build();
        ArticulosAdquiridos articulosAdquiridos = articulosAdquiridosMapper.toEntity(articulosAdquiridosDTO);

        Mockito.when(articulosAdquiridosDAO.existsById(articulosAdquiridosDTO.getIdArticuloAdquirido())).thenReturn(true);
        Mockito.when(estudianteDAO.existsById(articulosAdquiridosDTO.getIdEstudiante())).thenReturn(true);
        Mockito.when(articulosDAO.existsById(articulosAdquiridosDTO.getIdArticulos())).thenReturn(true);
        Mockito.when(articulosAdquiridosDAO.save(Mockito.any())).thenReturn(articulosAdquiridos);

        Exception exception = assertThrows(Exception.class, ()->{
            articulosAdquiridosService.actualizar(articulosAdquiridos);
        });
        assertEquals(exception.getMessage(),NO_PUEDE_INGRESAR_UNA_FECHA_QUE_AUN_NO_HA_SUCEDIDO);

    }

}
