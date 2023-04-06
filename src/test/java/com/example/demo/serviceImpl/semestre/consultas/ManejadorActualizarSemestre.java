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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@SpringBootTest
public class ManejadorActualizarSemestre {
    private static final String SE_ACTUALIZO_EXITOSAMENTE_EL_SEMESTRE = "Se actualizo exitosamente el semestre.";
    private static final String SE_DEBE_INGRESAR_UN_ID_DEL_SEMESTRE_QUE_DESEA_ACTUALIZAR = "Debe ingresar un id del semestre que desea actualizar.";
    private static final String NO_SE_ENCONTRO_EL_SEMESTRE_CON_ESE_ID = "No se encontró el semestre con ese id.";
    private static final String SE_DEBE_INGRESAR_EL_NOMBRE_DEL_SEMESTRE = "Se debe ingresar el nombre del semestre.";
    private static final String EL_NOMBRE_DEL_SEMESTRE_NO_DEBE_SUPERAR_LOS_50_CARACTERES = "El nombre del semestre no debe superar los 50 caracteres.";
    private static final String SE_DEBE_INGRESAR_UN_USUARIO_MODIFICADOR = "Se debe ingresar un usuario modificador.";
    private static final String EL_USUARIO_MODIFICADOR_NO_DEBE_TENER_MAS_DE_50_CARACTERES = "El usuario modificador no debe de tener más de 50 caracteres.";
    private static final String NO_SE_PUEDE_INGRESAR_UNA_FECHA_QUE_AUN_NO_HA_SUCEDIDO = "No puede ingresar una fecha que aun no ha sucedido.";
    private static final String TEXTO_MAS_CINCUENTA_CARACTERES = "DGSDGSDSDAGSFDGDFGSDFFGDFSGDFGDFGDFGDFGDFGDFGDSGDFDVSDDSBDSBSDBSVDVSDVSDVSDVSDV";
    private static final String SE_DEBE_INGRESAR_UNA_FECHA_VALIDA = "Se debe ingresar una fecha válida.";
    private static final String VACIO = "";

    @Autowired
    SemestreService semestreService;
    @Autowired
    SemestreMapper semestreMapper;
    @MockBean
    private SemestreDAO semestreDAO;


    @Test
    @DisplayName("Deberia actualizar un semestre")
    void deberiaActualizarUnSemestre() throws Exception{
        SemestreDTO semestreDTO = new SemestreTestDataBuilder().conIdSemestre(1L).build();
        Semestre semestre = semestreMapper.toEntity(semestreDTO);
        Mockito.when(semestreDAO.existsById(semestreDTO.getIdSemestre())).thenReturn(true);
        Mockito.when(semestreDAO.save(Mockito.any())).thenReturn(semestre);
        String crearSemestre = semestreService.actualizar(semestre);
        assertEquals(SE_ACTUALIZO_EXITOSAMENTE_EL_SEMESTRE, crearSemestre);

    }
    @Test
    @DisplayName("Debería fallar por idArticulo nulo")
    void deberiaFallarPorIdArticuloNulo()throws Exception{

        SemestreDTO semestreDTO = new SemestreTestDataBuilder().conIdSemestre(null).build();
        Semestre semestre = semestreMapper.toEntity(semestreDTO);
        Mockito.when(semestreDAO.existsById(semestreDTO.getIdSemestre())).thenReturn(true);
        Mockito.when(semestreDAO.save(Mockito.any())).thenReturn(semestre);
        Exception exception = assertThrows(Exception.class, ()->{
            semestreService.actualizar(semestre);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UN_ID_DEL_SEMESTRE_QUE_DESEA_ACTUALIZAR);
    }
    @Test
    @DisplayName("Deberia fallar por id no encontrado")
    void deberiaFallarPorIdSemestreNoEncontrado()throws Exception{

        SemestreDTO semestreDTO = new SemestreTestDataBuilder().build();
        Semestre semestre = semestreMapper.toEntity(semestreDTO);
        Mockito.when(semestreDAO.existsById(semestreDTO.getIdSemestre())).thenReturn(false);
        Mockito.when(semestreDAO.save(Mockito.any())).thenReturn(semestre);
        Exception exception = assertThrows(Exception.class, ()->{
            semestreService.actualizar(semestre);
        });
        assertEquals(exception.getMessage(), NO_SE_ENCONTRO_EL_SEMESTRE_CON_ESE_ID);
    }
    @Test
    @DisplayName("Deberia lanzar excepcion de nombre de semestre")
    void deberiaLanzarExcepcionDeNombreSemestre() throws Exception{
        SemestreDTO semestreDTO = new SemestreTestDataBuilder().conNombre(VACIO).build();
        Semestre semestre = semestreMapper.toEntity(semestreDTO);
        Mockito.when(semestreDAO.existsById(semestreDTO.getIdSemestre())).thenReturn(true);
        Mockito.when(semestreDAO.save(Mockito.any())).thenReturn(semestre);
        Exception exception = assertThrows(Exception.class, ()->{
            semestreService.actualizar(semestre);
        });
        assertEquals(SE_DEBE_INGRESAR_EL_NOMBRE_DEL_SEMESTRE, exception.getMessage());
    }
    @Test
    @DisplayName("Deberia lanzar una excepcion cuando el nombre es nulo")
    void deberiaLanzarUnaExcepcionNombreNull() throws Exception{
        SemestreDTO semestreDTO = new SemestreTestDataBuilder().conNombre(null).build();
        Semestre semestre = semestreMapper.toEntity(semestreDTO);
        Mockito.when(semestreDAO.existsById(semestreDTO.getIdSemestre())).thenReturn(true);
        Mockito.when(semestreDAO.save(Mockito.any())).thenReturn(semestre);
        Exception exception = assertThrows(Exception.class, ()->{
            semestreService.actualizar(semestre);
        });
        assertEquals(SE_DEBE_INGRESAR_EL_NOMBRE_DEL_SEMESTRE, exception.getMessage());
    }
    @Test
    @DisplayName("Deberia lanzar una excepcion nombre supera caracteres nombre")
    void deberiaLanzarExcepcionDeNombreCaracteres() throws Exception{
        SemestreDTO semestreDTO = new SemestreTestDataBuilder().conNombre(TEXTO_MAS_CINCUENTA_CARACTERES).build();
        Semestre semestre = semestreMapper.toEntity(semestreDTO);
        Mockito.when(semestreDAO.existsById(semestreDTO.getIdSemestre())).thenReturn(true);
        Mockito.when(semestreDAO.save(Mockito.any())).thenReturn(semestre);
        Exception exception = assertThrows(Exception.class, ()->{
            semestreService.actualizar(semestre);
        });
        assertEquals(EL_NOMBRE_DEL_SEMESTRE_NO_DEBE_SUPERAR_LOS_50_CARACTERES, exception.getMessage());
    }
    @Test
    @DisplayName("Deberia fallar por nombre modificador nulo")
    void deberiaFallarPorNombreModificadorNulo () throws Exception{
        SemestreDTO semestreDTO = new SemestreTestDataBuilder().conUsuarioModificador(null).build();
        Semestre semestre = semestreMapper.toEntity(semestreDTO);
        Mockito.when(semestreDAO.existsById(semestreDTO.getIdSemestre())).thenReturn(true);
        Mockito.when(semestreDAO.save(Mockito.any())).thenReturn(semestre);
        Exception exception = assertThrows(Exception.class, ()->{
            semestreService.actualizar(semestre);
        });
        assertEquals(SE_DEBE_INGRESAR_UN_USUARIO_MODIFICADOR, exception.getMessage());
    }
    @Test
    @DisplayName("Deberia fallar por nombre modificador vacio")
    void deberiaFallarPorNombreCreadorVacio () throws Exception{
        SemestreDTO semestreDTO = new SemestreTestDataBuilder().conUsuarioModificador(VACIO).build();
        Semestre semestre = semestreMapper.toEntity(semestreDTO);
        Mockito.when(semestreDAO.existsById(semestreDTO.getIdSemestre())).thenReturn(true);
        Mockito.when(semestreDAO.save(Mockito.any())).thenReturn(semestre);
        Exception exception = assertThrows(Exception.class, ()->{
            semestreService.actualizar(semestre);
        });
        assertEquals(SE_DEBE_INGRESAR_UN_USUARIO_MODIFICADOR, exception.getMessage());
    }
    @Test
    @DisplayName("Deberia lanzar una excepcion nombre supera caracteres usuario modificador")
    void deberiaLanzarExcepcionDeUsuarioCreadorCaracteres()throws Exception{
        SemestreDTO semestreDTO = new SemestreTestDataBuilder().conUsuarioModificador(TEXTO_MAS_CINCUENTA_CARACTERES).build();
        Semestre semestre = semestreMapper.toEntity(semestreDTO);
        Mockito.when(semestreDAO.existsById(semestreDTO.getIdSemestre())).thenReturn(true);
        Mockito.when(semestreDAO.save(Mockito.any())).thenReturn(semestre);
        Exception exception = assertThrows(Exception.class, ()->{
            semestreService.actualizar(semestre);
        });
        assertEquals(EL_USUARIO_MODIFICADOR_NO_DEBE_TENER_MAS_DE_50_CARACTERES, exception.getMessage());
    }
    @Test
    @DisplayName("Deberia fallar por fecha modificacion nulo")
    void deberiaFallarPorFechaCreacionNula()throws Exception{
        SemestreDTO semestreDTO = new SemestreTestDataBuilder().conFechaModificacion(null).build();
        Semestre semestre = semestreMapper.toEntity(semestreDTO);
        Mockito.when(semestreDAO.existsById(semestreDTO.getIdSemestre())).thenReturn(true);
        Mockito.when(semestreDAO.save(Mockito.any())).thenReturn(semestre);
        Exception exception = assertThrows(Exception.class, ()->{
            semestreService.actualizar(semestre);
        });
        assertEquals(SE_DEBE_INGRESAR_UNA_FECHA_VALIDA, exception.getMessage());
    }
    @Test
    @DisplayName("Deberia fallar por fecha futura")
    void deberiaFallarPorFechaCreacionFutura()throws Exception{
        SemestreDTO semestreDTO = new SemestreTestDataBuilder().conFechaModificacion(new Date(3500, 12, 12)).build();
        Semestre semestre = semestreMapper.toEntity(semestreDTO);
        Mockito.when(semestreDAO.existsById(semestreDTO.getIdSemestre())).thenReturn(true);
        Mockito.when(semestreDAO.save(Mockito.any())).thenReturn(semestre);
        Exception exception = assertThrows(Exception.class, ()->{
            semestreService.actualizar(semestre);
        });
        assertEquals(NO_SE_PUEDE_INGRESAR_UNA_FECHA_QUE_AUN_NO_HA_SUCEDIDO, exception.getMessage());
    }

}
