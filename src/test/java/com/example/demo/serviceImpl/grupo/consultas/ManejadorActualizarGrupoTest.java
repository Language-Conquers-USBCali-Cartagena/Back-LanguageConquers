package com.example.demo.serviceImpl.grupo.consultas;

import com.example.demo.dao.GrupoDAO;
import com.example.demo.mapper.GrupoMapper;
import com.example.demo.model.Grupo;
import com.example.demo.model.dto.GrupoDTO;
import com.example.demo.service.GrupoService;
import com.example.demo.serviceImpl.grupo.testDataBuilder.GrupoTestDataBuilder;
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
public class ManejadorActualizarGrupoTest {

    private static final String SE_ACTUALIZO_EXITOSAMENTE_EL_GRUPO = "Se actualizo exitosamente el grupo.";
    private static final String DEBE_INGRESAR_EL_ID_DEL_GRUPO_QUE_DESEA_ACTUALIZAR = "Debe ingresar el id del grupo que desea actualizar";
    private static final String NO_EXISTE_EL_GRUPO_CON_ESE_ID = "No exste el grupo con ese id.";
    private static final String  DEBE_INGRESAR_EL_NOMBRE_DEL_GRUPO = "Debe ingresar el nombre del grupo.";
    private static final String EL_NOMBRE_DEL_GRUPO_NO_DEBE_CONTENER_MAS_DE_50_CARACTERES = "El nombre del grupo no debe contener más de 50 caracteres.";
    private static final String DEBE_INGRESAR_UN_USUARIO_MODIFICADOR = "Debe ingresar un usuario modificador válido.";
    private static final String EL_NOMBRE_DEL_USUARIO_NO_DEBE_CONTENER_MAS_DE_50_CARACTERES = "El nombre del usuario modificador no debe contener más de 50 caracteres.";
    private static final String DEBE_INGRESAR_UNA_FECHA_DE_CREACION = "Debe ingresar una fecha de modificación.";
    private static final String NO_SE_PUEDE_INGRESAR_UNA_FECHA_QUE_AUN_NO_HA_SUCEDIDO = "No se puede ingresar una fecha que aun no ha sucedido.";
    private static final String NOMBRE_GRUPO = "Los supervivientes";
    private static final String NOMBRE_GRUPO_MALO = "Los supervivientes jhdsh dshkhds khd kdkudkshdsiekd";
    private static final String USUARIO_MODIFICADOR = "Angela Acosta";
    private static final String USUARIO_MODIFICADOR_MALO = "Angela Acosta hdhas dhka  adhkja ashdk hdynmakiwloaf";

    @Autowired
    GrupoService grupoService;

    @MockBean
    private GrupoDAO grupoDAO;

    @Autowired
    GrupoMapper grupoMapper;

    @Test
    @DisplayName("Deberia actualizar correctamente el grupo")
    void deberiaActualizarGrupoExitosamente()throws Exception{

        GrupoDTO grupoDTO = new GrupoTestDataBuilder()
                .conNombre(NOMBRE_GRUPO)
                .conUsuarioCreador(USUARIO_MODIFICADOR)
                .conFechaCreacion(new Date())
                .build();
        Grupo grupo = grupoMapper.toEntity(grupoDTO);
        Mockito.when(grupoDAO.existsById(grupo.getIdGrupo())).thenReturn(true);
        Mockito.when(grupoDAO.findById(grupo.getIdGrupo())).thenReturn(Optional.of(grupo));
        Mockito.when(grupoDAO.save(Mockito.any())).thenReturn(grupo);
        String actualizacionGrupo = grupoService.actualizar(grupoDTO);
        assertEquals(SE_ACTUALIZO_EXITOSAMENTE_EL_GRUPO, actualizacionGrupo);

    }
    @Test
    @DisplayName("Deberia fallar por idGrupo null")
    void deberiaFallarPorIdGrupoNull()throws Exception{

        GrupoDTO grupoDTO = new GrupoTestDataBuilder()
                .conIdGrupo(null)
                .build();
        Exception exception = assertThrows(Exception.class, ()->{
            grupoService.actualizar(grupoDTO);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_EL_ID_DEL_GRUPO_QUE_DESEA_ACTUALIZAR);

    }
    @Test
    @DisplayName("Deberia fallar por idGrupo no existente")
    void deberiaFallarPorIdGrupoNoExistente()throws Exception{

        GrupoDTO grupoDTO = new GrupoTestDataBuilder()
                .conIdGrupo(68l)
                .build();
        Mockito.when(grupoDAO.existsById(grupoDTO.getIdGrupo())).thenReturn(false);
        Exception exception = assertThrows(Exception.class, ()->{
            grupoService.actualizar(grupoDTO);
        });
        assertEquals(exception.getMessage(), NO_EXISTE_EL_GRUPO_CON_ESE_ID);

    }

    @Test
    @DisplayName("Deberia fallar por nombre grupo null")
    void deberiaFallarPorNombreGrupoNull()throws Exception{

        GrupoDTO grupoDTO = new GrupoTestDataBuilder()
                .conIdGrupo(783787L)
                .conNombre(null)
                .build();
        Mockito.when(grupoDAO.existsById(grupoDTO.getIdGrupo())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, ()->{
            grupoService.actualizar(grupoDTO);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_EL_NOMBRE_DEL_GRUPO);

    }

    @Test
    @DisplayName("Deberia fallar por nombre grupo vacio")
    void deberiaFallarPorNombreGrupoVacio()throws Exception{

        GrupoDTO grupoDTO = new GrupoTestDataBuilder()
                .conIdGrupo(783787L)
                .conNombre("")
                .build();
        Mockito.when(grupoDAO.existsById(grupoDTO.getIdGrupo())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, ()->{
            grupoService.actualizar(grupoDTO);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_EL_NOMBRE_DEL_GRUPO);

    }

    @Test
    @DisplayName("Deberia fallar por nombre grupo largo")
    void deberiaFallarPorNombreGrupoLargo()throws Exception{

        GrupoDTO grupoDTO = new GrupoTestDataBuilder()
                .conIdGrupo(783787L)
                .conNombre(NOMBRE_GRUPO_MALO)
                .build();
        Mockito.when(grupoDAO.existsById(grupoDTO.getIdGrupo())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, ()->{
            grupoService.actualizar(grupoDTO);
        });
        assertEquals(exception.getMessage(), EL_NOMBRE_DEL_GRUPO_NO_DEBE_CONTENER_MAS_DE_50_CARACTERES);

    }
    @Test
    @DisplayName("Deberia fallar por usuario modificador null")
    void deberiaFallarPorUsuarioModificadorNull()throws Exception{

        GrupoDTO grupoDTO = new GrupoTestDataBuilder()
                .conIdGrupo(783787L)
                .conNombre(NOMBRE_GRUPO)
                .conUsuarioModificador(null)
                .build();
        Mockito.when(grupoDAO.existsById(grupoDTO.getIdGrupo())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, ()->{
            grupoService.actualizar(grupoDTO);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UN_USUARIO_MODIFICADOR);

    }
    @Test
    @DisplayName("Deberia fallar por usuario modificador vacio")
    void deberiaFallarPorUsuarioModificadorVacio()throws Exception{

        GrupoDTO grupoDTO = new GrupoTestDataBuilder()
                .conIdGrupo(783787L)
                .conNombre(NOMBRE_GRUPO)
                .conUsuarioModificador("")
                .build();
        Mockito.when(grupoDAO.existsById(grupoDTO.getIdGrupo())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, ()->{
            grupoService.actualizar(grupoDTO);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UN_USUARIO_MODIFICADOR);

    }

    @Test
    @DisplayName("Deberia fallar por usuario modificador largo")
    void deberiaFallarPorUsuarioModificadorLargo()throws Exception{

        GrupoDTO grupoDTO = new GrupoTestDataBuilder()
                .conIdGrupo(783787L)
                .conNombre(NOMBRE_GRUPO)
                .conUsuarioModificador(USUARIO_MODIFICADOR_MALO)
                .build();
        Mockito.when(grupoDAO.existsById(grupoDTO.getIdGrupo())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, ()->{
            grupoService.actualizar(grupoDTO);
        });
        assertEquals(exception.getMessage(), EL_NOMBRE_DEL_USUARIO_NO_DEBE_CONTENER_MAS_DE_50_CARACTERES);

    }
    @Test
    @DisplayName("Deberia fallar por fecha modificación null")
    void deberiaFallarPorFechaModificacion()throws Exception{

        GrupoDTO grupoDTO = new GrupoTestDataBuilder()
                .conIdGrupo(783787L)
                .conNombre(NOMBRE_GRUPO)
                .conUsuarioModificador(USUARIO_MODIFICADOR)
                .conFechaModificacion(null)
                .build();
        Mockito.when(grupoDAO.existsById(grupoDTO.getIdGrupo())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, ()->{
            grupoService.actualizar(grupoDTO);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UNA_FECHA_DE_CREACION);

    }
    @Test
    @DisplayName("Deberia fallar por fecha modificación futura")
    void deberiaFallarPorFechaModificacionFutura()throws Exception{

        GrupoDTO grupoDTO = new GrupoTestDataBuilder()
                .conIdGrupo(783787L)
                .conNombre(NOMBRE_GRUPO)
                .conUsuarioModificador(USUARIO_MODIFICADOR)
                .conFechaModificacion(new Date(2023,7,12))
                .build();
        Mockito.when(grupoDAO.existsById(grupoDTO.getIdGrupo())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, ()->{
            grupoService.actualizar(grupoDTO);
        });
        assertEquals(exception.getMessage(),NO_SE_PUEDE_INGRESAR_UNA_FECHA_QUE_AUN_NO_HA_SUCEDIDO);

    }
}
