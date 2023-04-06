package com.example.demo.serviceImpl.genero.consultas;

import com.example.demo.dao.GeneroDAO;
import com.example.demo.mapper.GeneroMapper;
import com.example.demo.model.Genero;
import com.example.demo.model.dto.GeneroDTO;
import com.example.demo.service.GeneroService;
import com.example.demo.serviceImpl.genero.testDataBuilder.GeneroTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
public class ManejadorActualizarGeneroTest {

    public static final String DEBE_INGRESAR_EL_ID_DEL_GENERO_QUE_DESEA_ACTUALIZAR = "Debe ingresar el id del genero que desea actualizar.";
    public static final String NO_EXISTE_EL_GENERO_CON_ESE_ID = "No existe el genero con ese id.";
    public static final String SE_ACTUALIZO_EXITOSAMENTE_EL_GENERO = "Se actualizo exitosamente el genero.";
    public static final String DEBE_INGRESAR_UN_NOMBRE_DE_GENERO = "Debe ingresar un nombre de genero.";
    public static final String EL_NOMBRE_DEL_GENERO_ES_MUY_LARGO = "El nombre del genero es muy largo.";
    public static final String SE_DEBE_INGRESAR_UN_USUARIO_MODIFICADOR_DEL_GENERO_VALIDO = "Se debe ingresar un usuario modificador del genero válido.";
    public static final String SE_DEBE_INGRESAR_UNA_FECHA_VALIDA = "Se debe ingresar una fecha de modificación válida.";
    public static final String NO_PUEDE_INGRESAR_UNA_FECHA_QUE_AUN_NO_HA_SUCEDIDO = "No puede ingresar una fecha que aun no ha sucedido.";
    public static final String NOMBRE_GENERO_MALO = "masculinowekdhdhcfhckhfsvkh ytwnjlshkfdsjlunnusdmsvd";
    public static final String USUARIO_MODIFICADOR_MALO = "angelaiewdueduacostaewdhedehrf eufie wcdcjkhh kjwdhkkwe";
    @Autowired
    GeneroService generoService;
    @MockBean
    private GeneroDAO generoDAO;
    @Autowired
    GeneroMapper generoMapper;

    @Test
    @DisplayName("Deberia actualizar un genero exitosamente")
    void deberiaActualizarUnGenero() throws Exception {
        GeneroDTO generoDTO = new GeneroTestDataBuilder()
                .conGenero("no binario")
                .conUsuarioModificador("Angela")
                .conFechaModificacion(new Date())
                .build();
        Genero genero = generoMapper.toEntity(generoDTO);
        Mockito.when(generoDAO.existsById(4343858L)).thenReturn(true);
        Mockito.when(generoDAO.findById(generoDTO.getIdGenero())).thenReturn(Optional.of(genero));
        genero.setGenero(generoDTO.getGenero());
        genero.setUsuarioModificador(generoDTO.getUsuarioModificador());
        genero.setFechaModificacion(generoDTO.getFechaModificacion());
        Mockito.when(generoDAO.save(Mockito.any(Genero.class))).thenReturn(genero);
        String actualizarGenero = generoService.actualizar(generoDTO);
        assertEquals(SE_ACTUALIZO_EXITOSAMENTE_EL_GENERO, actualizarGenero);
    }

    @Test
    @DisplayName("Deberia fallar por idGenero null")
    void deberiaFallarPorIdGeneroNull() throws Exception {
        GeneroDTO generoDTO = new GeneroTestDataBuilder().conIdGenero(null).build();
        generoDTO.setGenero("no binario");
        generoDTO.setUsuarioModificador("Angela");
        generoDTO.setFechaModificacion(new Date());
        Genero genero = generoMapper.toEntity(generoDTO);
        Mockito.when(generoDAO.findById(generoDTO.getIdGenero())).thenReturn(Optional.of(genero));
        Exception exception = assertThrows(Exception.class, () -> {
            generoService.actualizar(generoDTO);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_EL_ID_DEL_GENERO_QUE_DESEA_ACTUALIZAR);
    }

    @Test
    @DisplayName("Deberia fallar por que no existe el idGenero ")
    void deberiaFallarNoExisteIdGenero() throws Exception {
        GeneroDTO generoDTO = new GeneroTestDataBuilder().conIdGenero(567L).build();
        generoDTO.setGenero("no binario");
        generoDTO.setUsuarioModificador("Angela");
        generoDTO.setFechaModificacion(new Date());
        Mockito.when(generoDAO.existsById(generoDTO.getIdGenero())).thenReturn(false);
        Exception exception = assertThrows(Exception.class, () -> {
            generoService.actualizar(generoDTO);
        });
        assertEquals(exception.getMessage(), NO_EXISTE_EL_GENERO_CON_ESE_ID);

    }

    @Test
    @DisplayName("Deberia fallar por genero null ")
    void deberiaFallarPorGeneroNull() throws Exception {
        GeneroDTO generoDTO = new GeneroTestDataBuilder()
                .conIdGenero(4343858L)
                .build();
        generoDTO.setGenero(null);
        generoDTO.setUsuarioModificador("Angela");
        generoDTO.setFechaModificacion(new Date());
        Genero genero = generoMapper.toEntity(generoDTO);
        Mockito.when(generoDAO.existsById(4343858L)).thenReturn(true);
        Mockito.when(generoDAO.findById(generoDTO.getIdGenero())).thenReturn(Optional.of(genero));
        genero.setGenero(generoDTO.getGenero());
        genero.setUsuarioModificador(generoDTO.getUsuarioModificador());
        genero.setFechaModificacion(generoDTO.getFechaModificacion());
        Mockito.when(generoDAO.save(Mockito.any(Genero.class))).thenReturn(genero);
        Exception exception = assertThrows(Exception.class, () -> {
            generoService.actualizar(generoDTO);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UN_NOMBRE_DE_GENERO);
    }

    @Test
    @DisplayName("Deberia fallar por genero vacio ")
    void deberiaFallarPorGeneroVacio() throws Exception {
        GeneroDTO generoDTO = new GeneroTestDataBuilder()
                .conIdGenero(4343858L)
                .build();
        generoDTO.setGenero("");
        generoDTO.setUsuarioModificador("Angela");
        generoDTO.setFechaModificacion(new Date());
        Genero genero = generoMapper.toEntity(generoDTO);
        Mockito.when(generoDAO.existsById(4343858L)).thenReturn(true);
        Mockito.when(generoDAO.findById(generoDTO.getIdGenero())).thenReturn(Optional.of(genero));
        genero.setGenero(generoDTO.getGenero());
        genero.setUsuarioModificador(generoDTO.getUsuarioModificador());
        genero.setFechaModificacion(generoDTO.getFechaModificacion());
        Mockito.when(generoDAO.save(Mockito.any(Genero.class))).thenReturn(genero);
        Exception exception = assertThrows(Exception.class, () -> {
            generoService.actualizar(generoDTO);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UN_NOMBRE_DE_GENERO);
    }

    @Test
    @DisplayName("Deberia fallar por nombre del genero muy largo ")
    void deberiaFallarPorGeneroLargo() throws Exception {
        GeneroDTO generoDTO = new GeneroTestDataBuilder()
                .conIdGenero(4343858L)
                .build();
        generoDTO.setGenero(NOMBRE_GENERO_MALO);
        generoDTO.setUsuarioModificador("Angela");
        generoDTO.setFechaModificacion(new Date());
        Genero genero = generoMapper.toEntity(generoDTO);
        Mockito.when(generoDAO.existsById(4343858L)).thenReturn(true);
        Mockito.when(generoDAO.findById(generoDTO.getIdGenero())).thenReturn(Optional.of(genero));
        genero.setGenero(generoDTO.getGenero());
        genero.setUsuarioModificador(generoDTO.getUsuarioModificador());
        genero.setFechaModificacion(generoDTO.getFechaModificacion());
        Mockito.when(generoDAO.save(Mockito.any(Genero.class))).thenReturn(genero);
        Exception exception = assertThrows(Exception.class, () -> {
            generoService.actualizar(generoDTO);
        });
        assertEquals(exception.getMessage(), EL_NOMBRE_DEL_GENERO_ES_MUY_LARGO);
    }

    @Test
    @DisplayName("Deberia fallar por usuario modificador nulo ")
    void deberiaFallarPorUsuarioModificadorNulo() throws Exception {
        GeneroDTO generoDTO = new GeneroTestDataBuilder()
                .conIdGenero(4343858L)
                .build();
        generoDTO.setGenero("no binario");
        generoDTO.setUsuarioModificador(null);
        generoDTO.setFechaModificacion(new Date());
        Genero genero = generoMapper.toEntity(generoDTO);
        Mockito.when(generoDAO.existsById(4343858L)).thenReturn(true);
        Mockito.when(generoDAO.findById(generoDTO.getIdGenero())).thenReturn(Optional.of(genero));
        genero.setGenero(generoDTO.getGenero());
        genero.setUsuarioModificador(generoDTO.getUsuarioModificador());
        genero.setFechaModificacion(generoDTO.getFechaModificacion());
        Mockito.when(generoDAO.save(Mockito.any(Genero.class))).thenReturn(genero);
        Exception exception = assertThrows(Exception.class, () -> {
            generoService.actualizar(generoDTO);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UN_USUARIO_MODIFICADOR_DEL_GENERO_VALIDO);
    }

    @Test
    @DisplayName("Deberia fallar por usuario modificador vacio")
    void deberiaFallarPorUsuarioModificadorVacio() throws Exception {
        GeneroDTO generoDTO = new GeneroTestDataBuilder()
                .conIdGenero(4343858L)
                .build();
        generoDTO.setGenero("no binario");
        generoDTO.setUsuarioModificador("");
        generoDTO.setFechaModificacion(new Date());
        Genero genero = generoMapper.toEntity(generoDTO);
        Mockito.when(generoDAO.existsById(4343858L)).thenReturn(true);
        Mockito.when(generoDAO.findById(generoDTO.getIdGenero())).thenReturn(Optional.of(genero));
        genero.setGenero(generoDTO.getGenero());
        genero.setUsuarioModificador(generoDTO.getUsuarioModificador());
        genero.setFechaModificacion(generoDTO.getFechaModificacion());
        Mockito.when(generoDAO.save(Mockito.any(Genero.class))).thenReturn(genero);
        Exception exception = assertThrows(Exception.class, () -> {
            generoService.actualizar(generoDTO);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UN_USUARIO_MODIFICADOR_DEL_GENERO_VALIDO);
    }

    @Test
    @DisplayName("Deberia fallar por usuario modificador con nombre largo")
    void deberiaFallarPorUsuarioModificadorLargo() throws Exception {
        GeneroDTO generoDTO = new GeneroTestDataBuilder()
                .conIdGenero(4343858L)
                .build();
        generoDTO.setGenero("no binario");
        generoDTO.setUsuarioModificador(USUARIO_MODIFICADOR_MALO);
        generoDTO.setFechaModificacion(new Date());
        Genero genero = generoMapper.toEntity(generoDTO);
        Mockito.when(generoDAO.existsById(4343858L)).thenReturn(true);
        Mockito.when(generoDAO.findById(generoDTO.getIdGenero())).thenReturn(Optional.of(genero));
        genero.setGenero(generoDTO.getGenero());
        genero.setUsuarioModificador(generoDTO.getUsuarioModificador());
        genero.setFechaModificacion(generoDTO.getFechaModificacion());
        Mockito.when(generoDAO.save(Mockito.any(Genero.class))).thenReturn(genero);
        Exception exception = assertThrows(Exception.class, () -> {
            generoService.actualizar(generoDTO);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UN_USUARIO_MODIFICADOR_DEL_GENERO_VALIDO);
    }

    @Test
    @DisplayName("Debería fallar por fecha modificación null")
    void deberiaFallarPorFechaModificacionNulo() throws Exception {
        GeneroDTO generoDTO = new GeneroTestDataBuilder()
                .conIdGenero(4343858L)
                .build();
        generoDTO.setGenero("no binario");
        generoDTO.setUsuarioModificador("Angela");
        generoDTO.setFechaModificacion(null);
        Genero genero = generoMapper.toEntity(generoDTO);
        Mockito.when(generoDAO.existsById(4343858L)).thenReturn(true);
        Mockito.when(generoDAO.findById(generoDTO.getIdGenero())).thenReturn(Optional.of(genero));
        genero.setGenero(generoDTO.getGenero());
        genero.setUsuarioModificador(generoDTO.getUsuarioModificador());
        genero.setFechaModificacion(generoDTO.getFechaModificacion());
        Mockito.when(generoDAO.save(Mockito.any(Genero.class))).thenReturn(genero);
        Exception exception = assertThrows(Exception.class, () -> {
            generoService.actualizar(generoDTO);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UNA_FECHA_VALIDA);
    }

    @Test
    @DisplayName("Debería fallar por fecha modificación que aun no ha sucedido")
    void deberiaFallarPorFechaModificacionFutura() throws Exception {
        GeneroDTO generoDTO = new GeneroTestDataBuilder()
                .conIdGenero(4343858L)
                .build();
        generoDTO.setGenero("no binario");
        generoDTO.setUsuarioModificador("Angela");
        generoDTO.setFechaModificacion(new Date(2023,8,23));
        Genero genero = generoMapper.toEntity(generoDTO);
        Mockito.when(generoDAO.existsById(4343858L)).thenReturn(true);
        Mockito.when(generoDAO.findById(generoDTO.getIdGenero())).thenReturn(Optional.of(genero));
        genero.setGenero(generoDTO.getGenero());
        genero.setUsuarioModificador(generoDTO.getUsuarioModificador());
        genero.setFechaModificacion(generoDTO.getFechaModificacion());
        Mockito.when(generoDAO.save(Mockito.any(Genero.class))).thenReturn(genero);
        Exception exception = assertThrows(Exception.class, () -> {
            generoService.actualizar(generoDTO);
        });
        assertEquals(exception.getMessage(), NO_PUEDE_INGRESAR_UNA_FECHA_QUE_AUN_NO_HA_SUCEDIDO);
    }
}