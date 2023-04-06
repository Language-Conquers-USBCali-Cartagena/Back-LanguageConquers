package com.example.demo.serviceImpl.semestre.consultas;

import com.example.demo.dao.SemestreDAO;
import com.example.demo.mapper.SemestreMapper;
import com.example.demo.model.Semestre;
import com.example.demo.model.dto.SemestreDTO;
import com.example.demo.service.SemestreService;
import com.example.demo.serviceImpl.semestre.testDataBuilder.SemestreTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ManejadorCrearSemestreTest {


    private static final String SE_CREO_EXITOSAMENTE_EL_SEMESTRE = "Se creo exitosamente el semestre.";
    private static final String SE_DEBE_INGRESAR_EL_NOMBRE_DEL_SEMESTRE = "Se debe ingresar el nombre del semestre.";
    private static final String EL_NOMBRE_DEL_SEMESTRE_NO_DEBE_SUPERAR_LOS_50_CARACTERES = "El nombre del semestre no debe superar los 50 caracteres.";
    private static final String EL_USUARIO_CREADOR_NO_DEBE_TENER_MAS_DE_50_CARACTERES = "El usuario creador no debe de tener más de 50 caracteres.";
    private static final String SE_DEBE_INGRESAR_UN_USUARIO_CREADOR = "Se debe ingresar un usuario creador.";
    private static final String NO_SE_PUEDE_INGRESAR_UNA_FECHA_QUE_AUN_NO_HA_SUCEDIDO = "No puede ingresar una fecha que aun no ha sucedido.";
    private static final String TEXTO_MAS_CINCUENTA_CARACTERES = "DGSDGSDSDAGSFDGDFGSDFFGDFSGDFGDFGDFGDFGDFGDFGDSGDFDVSDDSBDSBSDBSVDVSDVSDVSDVSDV";
    private static final String SE_DEBE_INGRESAR_UNA_FECHA_VALIDA = "Se debe ingresar una fecha válida.";
    private static final Date FECHA_FUTURA= new Date(3500, 12, 12);
    private static final String VACIO = "";
    @Autowired
    SemestreService semestreService;
    @Autowired
    SemestreMapper semestreMapper;
    @MockBean
    private SemestreDAO semestreDAO;

    @Test
    @DisplayName("Deberia crear un semestre")
    void deberiaCrearUnSemestre() throws Exception{
        SemestreDTO semestreDTO = new SemestreTestDataBuilder().build();
        Semestre semestre = semestreMapper.toEntity(semestreDTO);
        String crearSemestre = semestreService.registrar(semestre);
        Mockito.when(semestreDAO.save(Mockito.any())).thenReturn(semestre);
        assertEquals(SE_CREO_EXITOSAMENTE_EL_SEMESTRE, crearSemestre);
    }
    @Test
    @DisplayName("Deberia lanzar excepcion de nombre de semestre")
    void deberiaLanzarExcepcionDeNombreSemestre() throws Exception{
        SemestreDTO semestreDTO = new SemestreTestDataBuilder().conNombre(VACIO).build();
        Semestre semestre = semestreMapper.toEntity(semestreDTO);
        Mockito.when(semestreDAO.save(Mockito.any())).thenReturn(semestre);
        Exception exception = assertThrows(Exception.class, ()->{
            semestreService.registrar(semestre);
        });
        assertEquals(SE_DEBE_INGRESAR_EL_NOMBRE_DEL_SEMESTRE, exception.getMessage());
    }

    @Test
    @DisplayName("Deberia lanzar una excepcion cuando el nombre es nulo")
    void deberiaLanzarUnaExcepcionNombreNull() throws Exception{
        SemestreDTO semestreDTO = new SemestreTestDataBuilder().conNombre(null).build();
        Semestre semestre = semestreMapper.toEntity(semestreDTO);
        Mockito.when(semestreDAO.save(Mockito.any())).thenReturn(semestre);
        Exception exception = assertThrows(Exception.class, ()->{
            semestreService.registrar(semestre);
        });
        assertEquals(SE_DEBE_INGRESAR_EL_NOMBRE_DEL_SEMESTRE, exception.getMessage());
    }
    @Test
    @DisplayName("Deberia lanzar una excepcion nombre supera caracteres nombre")
    void deberiaLanzarExcepcionDeNombreCaracteres() throws Exception{
        SemestreDTO semestreDTO = new SemestreTestDataBuilder().conNombre(TEXTO_MAS_CINCUENTA_CARACTERES).build();
        Semestre semestre = semestreMapper.toEntity(semestreDTO);
        Mockito.when(semestreDAO.save(Mockito.any())).thenReturn(semestre);
        Exception exception = assertThrows(Exception.class, ()->{
            semestreService.registrar(semestre);
        });
        assertEquals(EL_NOMBRE_DEL_SEMESTRE_NO_DEBE_SUPERAR_LOS_50_CARACTERES, exception.getMessage());
    }
    @Test
    @DisplayName("Deberia fallar por nombre creador nulo")
    void deberiaFallarPorNombreCreadorNulo () throws Exception{
        SemestreDTO semestreDTO = new SemestreTestDataBuilder().conUsuarioCreador(null).build();
        Semestre semestre = semestreMapper.toEntity(semestreDTO);
        Mockito.when(semestreDAO.save(Mockito.any())).thenReturn(semestre);
        Exception exception = assertThrows(Exception.class, ()->{
            semestreService.registrar(semestre);
        });
        assertEquals(SE_DEBE_INGRESAR_UN_USUARIO_CREADOR, exception.getMessage());
    }
    @Test
    @DisplayName("Deberia fallar por nombre creador vacio")
    void deberiaFallarPorNombreCreadorVacio () throws Exception{
        SemestreDTO semestreDTO = new SemestreTestDataBuilder().conUsuarioCreador(VACIO).build();
        Semestre semestre = semestreMapper.toEntity(semestreDTO);
        Mockito.when(semestreDAO.save(Mockito.any())).thenReturn(semestre);
        Exception exception = assertThrows(Exception.class, ()->{
            semestreService.registrar(semestre);
        });
        assertEquals(SE_DEBE_INGRESAR_UN_USUARIO_CREADOR, exception.getMessage());
    }
    @Test
    @DisplayName("Deberia lanzar una excepcion nombre supera caracteres usuario creador")
    void deberiaLanzarExcepcionDeUsuarioCreadorCaracteres()throws Exception{
        SemestreDTO semestreDTO = new SemestreTestDataBuilder().conUsuarioCreador(TEXTO_MAS_CINCUENTA_CARACTERES).build();
        Semestre semestre = semestreMapper.toEntity(semestreDTO);
        Mockito.when(semestreDAO.save(Mockito.any())).thenReturn(semestre);
        Exception exception = assertThrows(Exception.class, ()->{
            semestreService.registrar(semestre);
        });
        assertEquals(EL_USUARIO_CREADOR_NO_DEBE_TENER_MAS_DE_50_CARACTERES, exception.getMessage());
    }
    @Test
    @DisplayName("Deberia fallar por fecha creacion creador nulo")
    void deberiaFallarPorFechaCreacionNula()throws Exception{
        SemestreDTO semestreDTO = new SemestreTestDataBuilder().conFechaCreacion(null).build();
        Semestre semestre = semestreMapper.toEntity(semestreDTO);
        Mockito.when(semestreDAO.save(Mockito.any())).thenReturn(semestre);
        Exception exception = assertThrows(Exception.class, ()->{
            semestreService.registrar(semestre);
        });
        assertEquals(SE_DEBE_INGRESAR_UNA_FECHA_VALIDA, exception.getMessage());
    }
    @Test
    @DisplayName("Deberia fallar por fecha futura")
    void deberiaFallarPorFechaCreacionFutura()throws Exception{
        SemestreDTO semestreDTO = new SemestreTestDataBuilder().conFechaCreacion(FECHA_FUTURA).build();
        Semestre semestre = semestreMapper.toEntity(semestreDTO);
        Mockito.when(semestreDAO.save(Mockito.any())).thenReturn(semestre);
        Exception exception = assertThrows(Exception.class, ()->{
            semestreService.registrar(semestre);
        });
        assertEquals(NO_SE_PUEDE_INGRESAR_UNA_FECHA_QUE_AUN_NO_HA_SUCEDIDO, exception.getMessage());
    }



}
