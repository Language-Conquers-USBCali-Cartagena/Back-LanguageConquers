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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@SpringBootTest
public class ManejadorCrearGeneroTest {

    public static final String SE_CREO_EL_GENERO_EXITOSAMENTE = "Se creo el genero exitosamente.";
    public static final String DEBE_INGRESAR_UN_NOMBRE_DE_GENERO = "Debe ingresar un nombre de genero.";
    public static final String EL_NOMBRE_DEL_GENERO_ES_MUY_LARGO = "El nombre del genero es muy largo.";
    public static final String SE_DEBE_INGRESAR_UN_USUARIO_CREADOR_DEL_GENERO_VALIDO = "Se debe ingresar un usuario creador del genero válido.";
    public static final String SE_DEBE_INGRESAR_UNA_FECHA_VALIDA = "Se debe ingresar una fecha de creación válida.";
    public static final String NO_PUEDE_INGRESAR_UNA_FECHA_QUE_AUN_NO_HA_SUCEDIDO = "No puede ingresar una fecha que aun no ha sucedido.";
    public static final String NOMBRE_GENERO_MALO = "masculinowekdhdhcfhckhfsvkh ytwnjlshkfdsjlunnusdmsvd";
    public static final String USUARIO_CREADOR_MALO = "angelaacostawdjueihckhfswwv ytwnjlshkfdsjlunnusdmsvd";

    @Autowired
    GeneroService generoService;

    @MockBean
    private GeneroDAO generoDAO;

    @Autowired
    GeneroMapper generoMapper;

    @Test
    @DisplayName("Deberia crear un genero exitosamente")
    void deberiaCrearUnGenero()throws Exception{
        GeneroDTO generoDTO = new GeneroTestDataBuilder().build();
        Genero genero = generoMapper.toEntity(generoDTO);
        String crearGenero = generoService.registrar(genero);
        Mockito.when(generoDAO.save(Mockito.any())).thenReturn(genero);
        assertEquals(SE_CREO_EL_GENERO_EXITOSAMENTE, crearGenero);
    }

    @Test
    @DisplayName("Deberia lanzar un error por genero null")
    void deberiaFallarPorGeneroNulo()throws Exception{

        GeneroDTO generoDTO = new GeneroTestDataBuilder().conGenero(null).build();
        Genero genero = generoMapper.toEntity(generoDTO);
        Mockito.when(generoDAO.save(Mockito.any())).thenReturn(genero);
        Exception exception = assertThrows(Exception.class, ()->{
            generoService.registrar(genero);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UN_NOMBRE_DE_GENERO);
    }

    @Test
    @DisplayName("Deberia lanzar un error por genero vacio")
    void deberiaFallarPorGeneroVacio()throws Exception{

        GeneroDTO generoDTO = new GeneroTestDataBuilder().conGenero("").build();
        Genero genero = generoMapper.toEntity(generoDTO);
        Mockito.when(generoDAO.save(Mockito.any())).thenReturn(genero);
        Exception exception = assertThrows(Exception.class, ()->{
            generoService.registrar(genero);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UN_NOMBRE_DE_GENERO);
    }

    @Test
    @DisplayName("Deberia lanzar un error por nombre del genero muy largo")
    void deberiaFallarPorGeneroLargo()throws Exception{

        GeneroDTO generoDTO = new GeneroTestDataBuilder().conGenero(NOMBRE_GENERO_MALO).build();
        Genero genero = generoMapper.toEntity(generoDTO);
        Mockito.when(generoDAO.save(Mockito.any())).thenReturn(genero);
        Exception exception = assertThrows(Exception.class, ()->{
            generoService.registrar(genero);
        });
        assertEquals(exception.getMessage(), EL_NOMBRE_DEL_GENERO_ES_MUY_LARGO);

    }

    @Test
    @DisplayName("Deberia lanzar un error por nombre del usuario creador nulo")
    void deberiaFallarPorUsuarioCreadorNulo()throws Exception{

        GeneroDTO generoDTO = new GeneroTestDataBuilder().conUsuarioCreador(null).build();
        Genero genero = generoMapper.toEntity(generoDTO);
        Mockito.when(generoDAO.save(Mockito.any())).thenReturn(genero);
        Exception exception = assertThrows(Exception.class, ()->{
            generoService.registrar(genero);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UN_USUARIO_CREADOR_DEL_GENERO_VALIDO);

    }

    @Test
    @DisplayName("Deberia lanzar un error por el usuario creador vacio")
    void deberiaFallarPorUsuarioCreadorVacio()throws Exception{

        GeneroDTO generoDTO = new GeneroTestDataBuilder().conUsuarioCreador("").build();
        Genero genero = generoMapper.toEntity(generoDTO);
        Mockito.when(generoDAO.save(Mockito.any())).thenReturn(genero);
        Exception exception = assertThrows(Exception.class, ()->{
            generoService.registrar(genero);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UN_USUARIO_CREADOR_DEL_GENERO_VALIDO);

    }

    @Test
    @DisplayName("Deberia lanzar un error por el usuario creador largo")
    void deberiaFallarPorUsuarioCreadorLargo()throws Exception{

        GeneroDTO generoDTO = new GeneroTestDataBuilder().conUsuarioCreador(USUARIO_CREADOR_MALO).build();
        Genero genero = generoMapper.toEntity(generoDTO);
        Mockito.when(generoDAO.save(Mockito.any())).thenReturn(genero);
        Exception exception = assertThrows(Exception.class, ()->{
            generoService.registrar(genero);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UN_USUARIO_CREADOR_DEL_GENERO_VALIDO);

    }
    @Test
    @DisplayName("Deberia lanzar un error por una fecha creacion nula")
    void deberiaFallarPorFechaCreacionNulo()throws Exception{

        GeneroDTO generoDTO = new GeneroTestDataBuilder().conFechaCreacion(null).build();
        Genero genero = generoMapper.toEntity(generoDTO);
        Mockito.when(generoDAO.save(Mockito.any())).thenReturn(genero);
        Exception exception = assertThrows(Exception.class, ()->{
            generoService.registrar(genero);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UNA_FECHA_VALIDA);

    }
    @Test
    @DisplayName("Deberia lanzar un error por una fecha creacion que aun no ha sucedido")
    void deberiaFallarPorFechaCreacionFutura()throws Exception{

        GeneroDTO generoDTO = new GeneroTestDataBuilder().conFechaCreacion(new Date(2023,04,20)).build();
        Genero genero = generoMapper.toEntity(generoDTO);
        Mockito.when(generoDAO.save(Mockito.any())).thenReturn(genero);
        Exception exception = assertThrows(Exception.class, ()->{
            generoService.registrar(genero);
        });
        assertEquals(exception.getMessage(), NO_PUEDE_INGRESAR_UNA_FECHA_QUE_AUN_NO_HA_SUCEDIDO);

    }
}
