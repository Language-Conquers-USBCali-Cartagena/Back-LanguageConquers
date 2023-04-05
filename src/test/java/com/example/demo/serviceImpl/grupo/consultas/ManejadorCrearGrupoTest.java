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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@SpringBootTest
public class ManejadorCrearGrupoTest {

    private static final String SE_REGISTRO_EXITOSAMENTE_EL_GRUPO = "Se registro exitosamente el grupo.";
    private static final String  DEBE_INGRESAR_EL_NOMBRE_DEL_GRUPO = "Debe ingresar el nombre del grupo.";
    private static final String EL_NOMBRE_DEL_GRUPO_NO_DEBE_CONTENER_MAS_DE_50_CARACTERES = "El nombre del grupo no debe contener más de 50 caracteres.";
    private static final String DEBE_INGRESAR_UN_USUARIO_CREADOR = "Debe ingresar un usuario creador válido.";
    private static final String EL_NOMBRE_DEL_USUARIO_NO_DEBE_CONTENER_MAS_DE_50_CARACTERES = "El nombre del usuario creador no debe contener más de 50 caracteres.";
    private static final String DEBE_INGRESAR_UNA_FECHA_DE_CREACION = "Debe ingresar una fecha de creación.";
    private static final String NO_SE_PUEDE_INGRESAR_UNA_FECHA_QUE_AUN_NO_HA_SUCEDIDO = "No se puede ingresar una fecha que aun no ha sucedido.";
     private static final String NOMBRE_GRUPO_MALO = "Los supervivientes jhdsh dshkhds khd kdkudkshdsiekd";
    private static final String USUARIO_CREADOR_MALO = "Angela Acosta hdhas dhka  adhkja ashdk hdynmakiwloaf";

    @Autowired
    GrupoService grupoService;

    @MockBean
    private GrupoDAO grupoDAO;

    @Autowired
    GrupoMapper grupoMapper;

    @Test
    @DisplayName("Deberia crear exitosamente el grupo")
    void deberiaCrearExitosamenteElGrupo()throws Exception{

        GrupoDTO grupoDTO = new GrupoTestDataBuilder().build();
        Grupo grupo = grupoMapper.toEntity(grupoDTO);
        String crearGrupo = grupoService.registrar(grupo);
        Mockito.when(grupoDAO.save(Mockito.any())).thenReturn(grupo);
        assertEquals(SE_REGISTRO_EXITOSAMENTE_EL_GRUPO, crearGrupo);
    }

    @Test
    @DisplayName("Deberia fallar por nombre grupo null")
    void deberiaFallarPorNombreGrupoNull()throws Exception{

        GrupoDTO grupoDTO = new GrupoTestDataBuilder().conNombre(null).build();
        Grupo grupo = grupoMapper.toEntity(grupoDTO);
        Mockito.when(grupoDAO.save(Mockito.any())).thenReturn(grupo);
        Exception exception = assertThrows(Exception.class, ()->{
            grupoService.registrar(grupo);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_EL_NOMBRE_DEL_GRUPO);
    }
    @Test
    @DisplayName("Deberia fallar por nombre grupo vacio")
    void deberiaFallarPorNombreGrupoVacio()throws Exception{

        GrupoDTO grupoDTO = new GrupoTestDataBuilder().conNombre("").build();
        Grupo grupo = grupoMapper.toEntity(grupoDTO);
        Mockito.when(grupoDAO.save(Mockito.any())).thenReturn(grupo);
        Exception exception = assertThrows(Exception.class, ()->{
            grupoService.registrar(grupo);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_EL_NOMBRE_DEL_GRUPO);
    }
    @Test
    @DisplayName("Deberia fallar por nombre grupo largo")
    void deberiaFallarPorNombreGrupoLargo()throws Exception{

        GrupoDTO grupoDTO = new GrupoTestDataBuilder().conNombre(NOMBRE_GRUPO_MALO).build();
        Grupo grupo = grupoMapper.toEntity(grupoDTO);
        Mockito.when(grupoDAO.save(Mockito.any())).thenReturn(grupo);
        Exception exception = assertThrows(Exception.class, ()->{
            grupoService.registrar(grupo);
        });
        assertEquals(exception.getMessage(), EL_NOMBRE_DEL_GRUPO_NO_DEBE_CONTENER_MAS_DE_50_CARACTERES);
    }

    @Test
    @DisplayName("Deberia fallar por usuario creador null")
    void deberiaFallarPorUsuarioCreadorNull()throws Exception{

        GrupoDTO grupoDTO = new GrupoTestDataBuilder().conUsuarioCreador(null).build();
        Grupo grupo = grupoMapper.toEntity(grupoDTO);
        Mockito.when(grupoDAO.save(Mockito.any())).thenReturn(grupo);
        Exception exception = assertThrows(Exception.class, ()->{
            grupoService.registrar(grupo);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UN_USUARIO_CREADOR);
    }

    @Test
    @DisplayName("Deberia fallar por usuario creador vacio")
    void deberiaFallarPorUsuarioCreadorVacio()throws Exception{

        GrupoDTO grupoDTO = new GrupoTestDataBuilder().conUsuarioCreador("").build();
        Grupo grupo = grupoMapper.toEntity(grupoDTO);
        Mockito.when(grupoDAO.save(Mockito.any())).thenReturn(grupo);
        Exception exception = assertThrows(Exception.class, ()->{
            grupoService.registrar(grupo);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UN_USUARIO_CREADOR);
    }
    @Test
    @DisplayName("Deberia fallar por usuario creador largo")
    void deberiaFallarPorUsuarioCreadorLargo()throws Exception{

        GrupoDTO grupoDTO = new GrupoTestDataBuilder().conUsuarioCreador(USUARIO_CREADOR_MALO).build();
        Grupo grupo = grupoMapper.toEntity(grupoDTO);
        Mockito.when(grupoDAO.save(Mockito.any())).thenReturn(grupo);
        Exception exception = assertThrows(Exception.class, ()->{
            grupoService.registrar(grupo);
        });
        assertEquals(exception.getMessage(), EL_NOMBRE_DEL_USUARIO_NO_DEBE_CONTENER_MAS_DE_50_CARACTERES);
    }
    @Test
    @DisplayName("Deberia fallar por fecha creación null")
    void deberiaFallarPorFechaCreacionNull()throws Exception{

        GrupoDTO grupoDTO = new GrupoTestDataBuilder().conFechaCreacion(null).build();
        Grupo grupo = grupoMapper.toEntity(grupoDTO);
        Mockito.when(grupoDAO.save(Mockito.any())).thenReturn(grupo);
        Exception exception = assertThrows(Exception.class, ()->{
            grupoService.registrar(grupo);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UNA_FECHA_DE_CREACION);
    }

    @Test
    @DisplayName("Deberia fallar por fecha creación futura")
    void deberiaFallarPorFechaCreacionFutura()throws Exception{

        GrupoDTO grupoDTO = new GrupoTestDataBuilder().conFechaCreacion(new Date(2023,7,12)).build();
        Grupo grupo = grupoMapper.toEntity(grupoDTO);
        Mockito.when(grupoDAO.save(Mockito.any())).thenReturn(grupo);
        Exception exception = assertThrows(Exception.class, ()->{
            grupoService.registrar(grupo);
        });
        assertEquals(exception.getMessage(), NO_SE_PUEDE_INGRESAR_UNA_FECHA_QUE_AUN_NO_HA_SUCEDIDO);
    }

}
