package com.example.demo.serviceImpl.programa.consultas;

import com.example.demo.dao.ProgramaDAO;
import com.example.demo.mapper.ProgramaMapper;
import com.example.demo.model.Programa;
import com.example.demo.model.dto.ProgramaDTO;
import com.example.demo.service.ProgramaService;
import com.example.demo.serviceImpl.programa.testDataBuilder.ProgramaTestDataBuilder;
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
public class ManejadorCrearProgramaTest  {

    private static  final String SE_CREO_EXITOSAMENTE_EL_PROGRAMA = "Se creo exitosamente el programa.";
    private static  final String DEBE_INGRESAR_UN_NOMBRE_DEL_PROGRAMA_NO_SUPERIOR_A_50_CARACTERES = "Debe ingresar un nombre del programa no superior a 50 caracteres.";
    private static  final String SE_DEBE_INGRESAR_UN_NOMBRE_DEL_PROGRAMA = "Se debe ingresar un nombre del programa.";
    private static final String DEBE_INGRESAR_UN_USUARIO_CREADOR_VALIDO = "Debe ingresar un usuario creador válido.";
    private static final String DEBE_INGRESAR_UN_USUARIO_CREADOR_NO_SUPERIOR_A_50_CARACTERES = "Debe ingresar un usuario creador no superior a 50 caracteres.";
    private static final String SE_DEBE_INGRESAR_UNA_FECHA_DE_CREACION = "Se debe ingresar una fecha de creación.";
    private static final String NO_PUEDE_INGRESAR_UNA_FECHA_QUE_AUN_NO_HA_SUCEDIDO = "No puede ingresar una fecha que aun no ha sucedido.";
    private static final String NOMBRE_PROGRAMA = "MNBVCXZLÑKJHGFDSAQWERTYUIOPÚHBGTRESDCVLONGTIFAZBGTQW";
    private static final String USUARIO_CREADOR_MALO = "MNBVCXZLÑKJH GFDSAQWERTYU IOPÚHBGTRESDCVLONGTIFAZBGTQW";

    @Autowired
    ProgramaService programaService;

    @MockBean
    private ProgramaDAO programaDAO;
    @Autowired
    ProgramaMapper programaMapper;

    @Test
    @DisplayName("Deberia crear un programa")
    void deberiaCrearUnPrograma() throws Exception {

        ProgramaDTO programaDTO = new ProgramaTestDataBuilder().build();
        Programa programa = programaMapper.toEntity(programaDTO);
        String crearPrograma = programaService.registrar(programa);
        Mockito.when(programaDAO.save(Mockito.any())).thenReturn(programa);
        assertEquals(SE_CREO_EXITOSAMENTE_EL_PROGRAMA, crearPrograma);

    }

    @Test
    @DisplayName("Deberia lanzar un error nombre null")
    void deberiaFallarPorNombreNull() throws Exception {

        ProgramaDTO programaDTO = new ProgramaTestDataBuilder().conNombre(null).build();
        Programa programa = programaMapper.toEntity(programaDTO);
        Mockito.when(programaDAO.save(Mockito.any())).thenReturn(programa);
        Exception exception = assertThrows(Exception.class, ()->{
            programaService.registrar(programa);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UN_NOMBRE_DEL_PROGRAMA);

    }

    @Test
    @DisplayName("Deberia lanzar un error por nombre vacio")
    void deberiaFallarPorNombreVacio() throws Exception {

        ProgramaDTO programaDTO = new ProgramaTestDataBuilder().conNombre("").build();
        Programa programa = programaMapper.toEntity(programaDTO);
        Mockito.when(programaDAO.save(Mockito.any())).thenReturn(programa);
        Exception exception = assertThrows(Exception.class, ()->{
            programaService.registrar(programa);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UN_NOMBRE_DEL_PROGRAMA);

    }

    @Test
    @DisplayName("Deberia lanzar un error por longitud del atributo nombre")
    void deberiaFallarPorNombreProgramaLargo() throws Exception {

        ProgramaDTO programaDTO = new ProgramaTestDataBuilder().conNombre(NOMBRE_PROGRAMA).build();
        Programa programa = programaMapper.toEntity(programaDTO);
        Mockito.when(programaDAO.save(Mockito.any())).thenReturn(programa);
        Exception exception = assertThrows(Exception.class, ()->{
            programaService.registrar(programa);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UN_NOMBRE_DEL_PROGRAMA_NO_SUPERIOR_A_50_CARACTERES);

    }

    @Test
    @DisplayName("Deberia lanzar un error por usuario creador null")
    void deberiaFallarPorNombreUsuarioCreadorNull() throws Exception {

        ProgramaDTO programaDTO = new ProgramaTestDataBuilder().conUsuarioCreador(null).build();
        Programa programa = programaMapper.toEntity(programaDTO);
        Mockito.when(programaDAO.save(Mockito.any())).thenReturn(programa);
        Exception exception = assertThrows(Exception.class, ()->{
            programaService.registrar(programa);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UN_USUARIO_CREADOR_VALIDO);

    }

    @Test
    @DisplayName("Deberia lanzar un error por usuario creador vacio")
    void deberiaFallarPorNombreUsuarioCreadorVacio() throws Exception {

        ProgramaDTO programaDTO = new ProgramaTestDataBuilder().conUsuarioCreador("").build();
        Programa programa = programaMapper.toEntity(programaDTO);
        Mockito.when(programaDAO.save(Mockito.any())).thenReturn(programa);
        Exception exception = assertThrows(Exception.class, ()->{
            programaService.registrar(programa);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UN_USUARIO_CREADOR_VALIDO);

    }
    @Test
    @DisplayName("Deberia lanzar un error por usuario creador largo")
    void deberiaFallarPorNombreUsuarioCreadorLargo() throws Exception {

        ProgramaDTO programaDTO = new ProgramaTestDataBuilder().conUsuarioCreador(USUARIO_CREADOR_MALO).build();
        Programa programa = programaMapper.toEntity(programaDTO);
        Mockito.when(programaDAO.save(Mockito.any())).thenReturn(programa);
        Exception exception = assertThrows(Exception.class, ()->{
            programaService.registrar(programa);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UN_USUARIO_CREADOR_NO_SUPERIOR_A_50_CARACTERES);

    }

    @Test
    @DisplayName("Deberia lanzar un error por fecha creacion null")
    void deberiaFallarPorFechaCreacionNull() throws Exception {

        ProgramaDTO programaDTO = new ProgramaTestDataBuilder().conFechaCreacion(null).build();
        Programa programa = programaMapper.toEntity(programaDTO);
        Mockito.when(programaDAO.save(Mockito.any())).thenReturn(programa);
        Exception exception = assertThrows(Exception.class, ()->{
            programaService.registrar(programa);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UNA_FECHA_DE_CREACION);

    }

    @Test
    @DisplayName("Deberia lanzar un error por fecha creacion futura")
    void deberiaFallarPorFechaCreacionFutura() throws Exception {

        ProgramaDTO programaDTO = new ProgramaTestDataBuilder().conFechaCreacion(new Date(2023,8,12)).build();
        Programa programa = programaMapper.toEntity(programaDTO);
        Mockito.when(programaDAO.save(Mockito.any())).thenReturn(programa);
        Exception exception = assertThrows(Exception.class, ()->{
            programaService.registrar(programa);
        });
        assertEquals(exception.getMessage(), NO_PUEDE_INGRESAR_UNA_FECHA_QUE_AUN_NO_HA_SUCEDIDO);

    }



}
