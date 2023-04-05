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
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@SpringBootTest
public class ManejadorActualizarProgramaTest {

    private static  final String SE_ACTUALIZO_EXITOSAMENTE_EL_PROGRAMA = "Se actualizo exitosamente el programa.";
    private static final String DEBE_INGRESAR_EL_ID_DEL_PROGRAMA_QUE_DESEA_ACTUALIZAR = "Debe ingresar el id del programa que desea actualizar.";
    private static final String NO_EXISTE_EL_PROGRAMA_CON_ESE_ID = "No existe el programa con ese id.";
    private static  final String SE_DEBE_INGRESAR_UN_NOMBRE_DEL_PROGRAMA = "Se debe ingresar un nombre del programa.";
    private static  final String DEBE_INGRESAR_UN_NOMBRE_DEL_PROGRAMA_NO_SUPERIOR_A_50_CARACTERES = "Debe ingresar un nombre del programa no superior a 50 caracteres.";
    private static final String DEBE_INGRESAR_UN_USUARIO_MODIFICADOR_VALIDO = "Debe ingresar un usuario modificador válido.";
    private static final String DEBE_INGRESAR_UN_USUARIO_MODIFICADOR_NO_SUPERIOR_A_50_CARACTERES = "Debe ingresar un usuario modificador no superior a 50 caracteres.";
    private static final String SE_DEBE_INGRESAR_UNA_FECHA_DE_MODIFICACION = "Se debe ingresar una fecha de modificación.";
    private static final String NO_PUEDE_INGRESAR_UNA_FECHA_QUE_AUN_NO_HA_SUCEDIDO = "No puede ingresar una fecha que aun no ha sucedido.";
    private static final String NOMBRE_PROGRAMA = "Base de datos I";
    private static final String NOMBRE_PROGRAMA_MALO = "MNBVCXZLÑKJHGFDSAQWERTYUIOPÚHBGTRESDCVLONGTIFAZBGTQW";
    private static final String USUARIO_MODIFICADOR = "Angela";
    private static final String USUARIO_MODIFICADOR_MALO = "MNBVCXZLÑKJH GFDSAQWERTYU IOPÚHBGTRESDCVLONGTIFAZBGTQW";

    @Autowired
    ProgramaService programaService;

    @MockBean
    private ProgramaDAO programaDAO;
    @Autowired
    ProgramaMapper programaMapper;

    @Test
    @DisplayName("Se debe actualizar exitosamente el programa")
    void deberiaActualizarProgramaExitosamente()throws Exception{
        ProgramaDTO programaDTO = new ProgramaTestDataBuilder()
                .conNombre(NOMBRE_PROGRAMA)
                .conUsuarioModificador(USUARIO_MODIFICADOR)
                .conFechaModificacion(new Date())
                .build();
        Programa programa = programaMapper.toEntity(programaDTO);
        Mockito.when(programaDAO.existsById(1348857854L)).thenReturn(true);
        Mockito.when(programaDAO.findById(programa.getIdPrograma())).thenReturn(Optional.of(programa));
        Mockito.when((programaDAO.save(Mockito.any(Programa.class)))).thenReturn(programa);
        String actualizarPrograma = programaService.actualizar(programaDTO);
        assertEquals(SE_ACTUALIZO_EXITOSAMENTE_EL_PROGRAMA, actualizarPrograma);
    }

    @Test
    @DisplayName("deberia fallar por idPrograma null")
    void deberiaFallarPorIdProgramaNull()throws Exception{
        ProgramaDTO programaDTO = new ProgramaTestDataBuilder()
                .conIdPrograma(null)
                .conNombre(NOMBRE_PROGRAMA)
                .conUsuarioModificador(USUARIO_MODIFICADOR)
                .conFechaModificacion(new Date())
                .build();
        Programa programa = programaMapper.toEntity(programaDTO);
        Mockito.when(programaDAO.findById(programaDTO.getIdPrograma())).thenReturn(Optional.of(programa));
        Exception exception = assertThrows(Exception.class, () -> {
            programaService.actualizar(programaDTO);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_EL_ID_DEL_PROGRAMA_QUE_DESEA_ACTUALIZAR);
        ;
    }
    @Test
    @DisplayName("deberia fallar por idPrograma no existente")
    void deberiaFallarPorIdProgramaNoExiste()throws Exception{
        ProgramaDTO programaDTO = new ProgramaTestDataBuilder()
                .conIdPrograma(838L)
                .conNombre(NOMBRE_PROGRAMA)
                .conUsuarioModificador(USUARIO_MODIFICADOR)
                .conFechaModificacion(new Date())
                .build();
        Programa programa = programaMapper.toEntity(programaDTO);
        Mockito.when(programaDAO.existsById(programaDTO.getIdPrograma())).thenReturn(false);
        Exception exception = assertThrows(Exception.class, () -> {
            programaService.actualizar(programaDTO);
        });
        assertEquals(exception.getMessage(), NO_EXISTE_EL_PROGRAMA_CON_ESE_ID);
        ;
    }

    @Test
    @DisplayName("deberia fallar por nombre programa null")
    void deberiaFallarPorNombreProgramaNull()throws Exception{
        ProgramaDTO programaDTO = new ProgramaTestDataBuilder()
                .conNombre(null)
                .conUsuarioModificador(USUARIO_MODIFICADOR)
                .conFechaModificacion(new Date())
                .build();
        Programa programa = programaMapper.toEntity(programaDTO);
        Mockito.when(programaDAO.existsById(programaDTO.getIdPrograma())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, () -> {
            programaService.actualizar(programaDTO);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UN_NOMBRE_DEL_PROGRAMA);
        ;
    }

    @Test
    @DisplayName("deberia fallar por nombre programa vacio")
    void deberiaFallarPorNombreProgramaVacio()throws Exception{
        ProgramaDTO programaDTO = new ProgramaTestDataBuilder()
                .conNombre("")
                .conUsuarioModificador(USUARIO_MODIFICADOR)
                .conFechaModificacion(new Date())
                .build();
        Programa programa = programaMapper.toEntity(programaDTO);
        Mockito.when(programaDAO.existsById(programaDTO.getIdPrograma())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, () -> {
            programaService.actualizar(programaDTO);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UN_NOMBRE_DEL_PROGRAMA);
        ;
    }
    @Test
    @DisplayName("deberia fallar por nombre programa largo")
    void deberiaFallarPorNombreProgramaLargo()throws Exception{
        ProgramaDTO programaDTO = new ProgramaTestDataBuilder()
                .conNombre(NOMBRE_PROGRAMA_MALO)
                .conUsuarioModificador(USUARIO_MODIFICADOR)
                .conFechaModificacion(new Date())
                .build();
        Programa programa = programaMapper.toEntity(programaDTO);
        Mockito.when(programaDAO.existsById(programaDTO.getIdPrograma())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, () -> {
            programaService.actualizar(programaDTO);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UN_NOMBRE_DEL_PROGRAMA_NO_SUPERIOR_A_50_CARACTERES);
        ;
    }
    @Test
    @DisplayName("deberia fallar por usuario modificador null")
    void deberiaFallarPorUsuarioModificadorNull()throws Exception{
        ProgramaDTO programaDTO = new ProgramaTestDataBuilder()
                .conNombre(NOMBRE_PROGRAMA)
                .conUsuarioModificador(null)
                .conFechaModificacion(new Date())
                .build();
        Programa programa = programaMapper.toEntity(programaDTO);
        Mockito.when(programaDAO.existsById(programaDTO.getIdPrograma())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, () -> {
            programaService.actualizar(programaDTO);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UN_USUARIO_MODIFICADOR_VALIDO);
        ;
    }
    @Test
    @DisplayName("deberia fallar por usuario modificador vacio")
    void deberiaFallarPorUsuarioModificadorVacio()throws Exception{
        ProgramaDTO programaDTO = new ProgramaTestDataBuilder()
                .conNombre(NOMBRE_PROGRAMA)
                .conUsuarioModificador("")
                .conFechaModificacion(new Date())
                .build();
        Programa programa = programaMapper.toEntity(programaDTO);
        Mockito.when(programaDAO.existsById(programaDTO.getIdPrograma())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, () -> {
            programaService.actualizar(programaDTO);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UN_USUARIO_MODIFICADOR_VALIDO);
        ;
    }
    @Test
    @DisplayName("deberia fallar por usuario modificador largo")
    void deberiaFallarPorUsuarioModificadorLargo()throws Exception{
        ProgramaDTO programaDTO = new ProgramaTestDataBuilder()
                .conNombre(NOMBRE_PROGRAMA)
                .conUsuarioModificador(USUARIO_MODIFICADOR_MALO)
                .conFechaModificacion(new Date())
                .build();
        Programa programa = programaMapper.toEntity(programaDTO);
        Mockito.when(programaDAO.existsById(programaDTO.getIdPrograma())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, () -> {
            programaService.actualizar(programaDTO);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UN_USUARIO_MODIFICADOR_NO_SUPERIOR_A_50_CARACTERES);
        ;
    }
    @Test
    @DisplayName("deberia fallar por fecha modificacion null")
    void deberiaFallarPorFechaModificacionNull()throws Exception{
        ProgramaDTO programaDTO = new ProgramaTestDataBuilder()
                .conNombre(NOMBRE_PROGRAMA)
                .conUsuarioModificador(USUARIO_MODIFICADOR)
                .conFechaModificacion(null)
                .build();
        Programa programa = programaMapper.toEntity(programaDTO);
        Mockito.when(programaDAO.existsById(programaDTO.getIdPrograma())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, () -> {
            programaService.actualizar(programaDTO);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UNA_FECHA_DE_MODIFICACION);
        ;
    }
    @Test
    @DisplayName("deberia fallar por fecha modificacion que aun no ha sucedido")
    void deberiaFallarPorFechaModificacionFutura()throws Exception{
        ProgramaDTO programaDTO = new ProgramaTestDataBuilder()
                .conNombre(NOMBRE_PROGRAMA)
                .conUsuarioModificador(USUARIO_MODIFICADOR)
                .conFechaModificacion(new Date(2023,7,12))
                .build();
        Programa programa = programaMapper.toEntity(programaDTO);
        Mockito.when(programaDAO.existsById(programaDTO.getIdPrograma())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, () -> {
            programaService.actualizar(programaDTO);
        });
        assertEquals(exception.getMessage(), NO_PUEDE_INGRESAR_UNA_FECHA_QUE_AUN_NO_HA_SUCEDIDO);
        ;
    }
}
