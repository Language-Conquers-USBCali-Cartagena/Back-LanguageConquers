package com.example.demo.serviceImpl.rol.consultas;

import com.example.demo.dao.RetoDAO;
import com.example.demo.dao.RolDAO;
import com.example.demo.mapper.RolMapper;
import com.example.demo.model.Reto;
import com.example.demo.model.Rol;
import com.example.demo.model.dto.RolDTO;
import com.example.demo.service.RolService;
import com.example.demo.serviceImpl.rol.testdataBuilder.RolTestDataBuilder;
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
public class ManejadorCrearRol {
    private static final String SE_CREO_EXITOSAMENTE_EL_ROL = "Se creo exitosamente el rol.";
    private static final String SE_DEBE_INGRESAR_EL_NOMBRE_DEL_ROL = "Se debe ingresar el nombre del rol.";
    private static final String EL_NOMBRE_DEL_ROL_NO_PUEDE_SUPERAR_LOS_50_CARACTERES = "El nombre del rol no puede superar los 50 caracteres.";
    private static final String DEBE_INGRESAR_UN_ID_RETO_VALIDO = "Debe ingresar un id reto válido.";
    private static final String SE_DEBE_INGRESAR_EL_USUARIO_CREADOR = "Se debe ingresar el usuario creador.";
    private static final String EL_NOMBRE_DEL_USUARIO_CREADOR_NO_DEBE_SUPERAR_LOS_50_CARACTERES = "El nombre del usuario creador no debe superar los 50 caracteres.";
    private static final String SE_DEBE_INGRESAR_UNA_FECHA_DE_CREACION = "Se debe ingresar una fecha de creación.";
    private static final String NO_SE_PUEDE_INGRESAR_UNA_FECHA_QUE_AUN_NO_HA_SUCEDIDO = "No se puede ingresar una fecha que aun no ha sucedido.";
    private static final String TEXTO_MAS_CINCUENTA_CARACTERES = "DGSDGSDSDAGSFDGDFGSDFFGDFSGDFGDFGDFGDFGDFGDFGDSGDFDVSDDSBDSBSDBSVDVSDVSDVSDVSDV";
    private static final String VACIO = "";
    private static final Date FECHA_FUTURA= new Date(3500, 12, 12);
    @Autowired
    RolService rolService;
    @Autowired
    RolMapper rolMapper;
    @MockBean
    RolDAO rolDAO;
    @MockBean
    RetoDAO retoDAO;

    @Test
    @DisplayName("Deberia crear un rol")
    void deberiaCrearUnRol() throws Exception{
        RolDTO rolDTO = new RolTestDataBuilder().build();
        Rol rol = rolMapper.toEntity(rolDTO);
        Mockito.when(rolDAO.save(Mockito.any())).thenReturn(rol);
        Mockito.when(retoDAO.findById(rolDTO.getIdReto())).thenReturn(Optional.of(new Reto()));
        String crearSemestre = rolService.registrar(rol);
        assertEquals(SE_CREO_EXITOSAMENTE_EL_ROL, crearSemestre);
    }
    @Test
    @DisplayName("Debe fallar por nombre nulo")
    void deberiaFallarPorNombreNulo() throws Exception{
        RolDTO rolDTO = new RolTestDataBuilder().conNombre(null).build();
        Rol rol = rolMapper.toEntity(rolDTO);
        Mockito.when(rolDAO.save(Mockito.any())).thenReturn(rol);
        Mockito.when(retoDAO.findById(rolDTO.getIdReto())).thenReturn(Optional.of(new Reto()));
        Exception exception = assertThrows(Exception.class, () ->{
           rolService.registrar(rol);
        });
        assertEquals(SE_DEBE_INGRESAR_EL_NOMBRE_DEL_ROL, exception.getMessage());
    }
    @Test
    @DisplayName("Debe fallar por nombre vacio")
    void deberiaFallarPorNombreVacio() throws Exception{
        RolDTO rolDTO = new RolTestDataBuilder().conNombre(VACIO).build();
        Rol rol = rolMapper.toEntity(rolDTO);
        Mockito.when(rolDAO.save(Mockito.any())).thenReturn(rol);
        Mockito.when(retoDAO.findById(rolDTO.getIdReto())).thenReturn(Optional.of(new Reto()));
        Exception exception = assertThrows(Exception.class, () ->{
            rolService.registrar(rol);
        });
        assertEquals(SE_DEBE_INGRESAR_EL_NOMBRE_DEL_ROL, exception.getMessage());
    }
    @Test
    @DisplayName("deberia fallar por superar caracteres del nombre")
    void deberiaFallarPorSuperarCaracteresnombre() throws Exception{
        RolDTO rolDTO = new RolTestDataBuilder().conNombre(TEXTO_MAS_CINCUENTA_CARACTERES).build();
        Rol rol = rolMapper.toEntity(rolDTO);
        Mockito.when(rolDAO.save(Mockito.any())).thenReturn(rol);
        Mockito.when(retoDAO.findById(rolDTO.getIdReto())).thenReturn(Optional.of(new Reto()));
        Exception exception = assertThrows(Exception.class, () ->{
            rolService.registrar(rol);
        });
        assertEquals(EL_NOMBRE_DEL_ROL_NO_PUEDE_SUPERAR_LOS_50_CARACTERES, exception.getMessage());
    }
    @Test
    @DisplayName("deberia fallar por idReto menor a 0")
    void deberiaFallarPorIdRetoMenorCero() throws Exception{
        RolDTO rolDTO = new RolTestDataBuilder().conIdReto(-1L).build();
        Rol rol = rolMapper.toEntity(rolDTO);
        Mockito.when(rolDAO.save(Mockito.any())).thenReturn(rol);
        Mockito.when(retoDAO.findById(rolDTO.getIdReto())).thenReturn(Optional.of(new Reto()));
        Exception exception = assertThrows(Exception.class, () ->{
            rolService.registrar(rol);
        });
        assertEquals(DEBE_INGRESAR_UN_ID_RETO_VALIDO, exception.getMessage());
    }
    @Test
    @DisplayName("deberia fallar por idReto no existente")
    void deberiaFallarPorIdRetoNoExistente() throws Exception{
        RolDTO rolDTO = new RolTestDataBuilder().build();
        Rol rol = rolMapper.toEntity(rolDTO);
        Mockito.when(rolDAO.save(Mockito.any())).thenReturn(rol);
        Mockito.when(retoDAO.findById(rolDTO.getIdReto())).thenReturn(Optional.empty());
        Exception exception = assertThrows(Exception.class, () ->{
            rolService.registrar(rol);
        });
        assertEquals(DEBE_INGRESAR_UN_ID_RETO_VALIDO, exception.getMessage());
    }
    @Test
    @DisplayName("deberia fallar por usuario creador nulo")
    void deberiaFallarPorUsuarioCreadorNulo() throws Exception{
        RolDTO rolDTO = new RolTestDataBuilder().conUsuarioCreador(null).build();
        Rol rol = rolMapper.toEntity(rolDTO);
        Mockito.when(rolDAO.save(Mockito.any())).thenReturn(rol);
        Mockito.when(retoDAO.findById(rolDTO.getIdReto())).thenReturn(Optional.of(new Reto()));
        Exception exception = assertThrows(Exception.class, () ->{
            rolService.registrar(rol);
        });
        assertEquals(SE_DEBE_INGRESAR_EL_USUARIO_CREADOR, exception.getMessage());
    }
    @Test
    @DisplayName("deberia fallar por usuario creador vacio")
    void deberiaFallarPorUsuarioCreadorVacio() throws Exception{
        RolDTO rolDTO = new RolTestDataBuilder().conUsuarioCreador(VACIO).build();
        Rol rol = rolMapper.toEntity(rolDTO);
        Mockito.when(rolDAO.save(Mockito.any())).thenReturn(rol);
        Mockito.when(retoDAO.findById(rolDTO.getIdReto())).thenReturn(Optional.of(new Reto()));
        Exception exception = assertThrows(Exception.class, () ->{
            rolService.registrar(rol);
        });
        assertEquals(SE_DEBE_INGRESAR_EL_USUARIO_CREADOR, exception.getMessage());
    }
    @Test
    @DisplayName("deberia fallar por superar caracteres usuario creador")
    void deberiaFallarPorSuperarCaracteresUsusarioCreador() throws Exception{
        RolDTO rolDTO = new RolTestDataBuilder().conUsuarioCreador(TEXTO_MAS_CINCUENTA_CARACTERES).build();
        Rol rol = rolMapper.toEntity(rolDTO);
        Mockito.when(rolDAO.save(Mockito.any())).thenReturn(rol);
        Mockito.when(retoDAO.findById(rolDTO.getIdReto())).thenReturn(Optional.of(new Reto()));
        Exception exception = assertThrows(Exception.class, () ->{
            rolService.registrar(rol);
        });
        assertEquals(EL_NOMBRE_DEL_USUARIO_CREADOR_NO_DEBE_SUPERAR_LOS_50_CARACTERES, exception.getMessage());
    }
    @Test
    @DisplayName("deberia fallar fecha creacion nula")
    void deberiaFallarPorFechaCreacionNulla() throws Exception{
        RolDTO rolDTO = new RolTestDataBuilder().conFechaCreacion(null).build();
        Rol rol = rolMapper.toEntity(rolDTO);
        Mockito.when(rolDAO.save(Mockito.any())).thenReturn(rol);
        Mockito.when(retoDAO.findById(rolDTO.getIdReto())).thenReturn(Optional.of(new Reto()));
        Exception exception = assertThrows(Exception.class, () ->{
            rolService.registrar(rol);
        });
        assertEquals(SE_DEBE_INGRESAR_UNA_FECHA_DE_CREACION, exception.getMessage());
    }
    @Test
    @DisplayName("deberia fallar fecha creacion futura")
    void deberiaFallarPorFechaCreacionFutura() throws Exception{
        RolDTO rolDTO = new RolTestDataBuilder().conFechaCreacion(FECHA_FUTURA).build();
        Rol rol = rolMapper.toEntity(rolDTO);
        Mockito.when(rolDAO.save(Mockito.any())).thenReturn(rol);
        Mockito.when(retoDAO.findById(rolDTO.getIdReto())).thenReturn(Optional.of(new Reto()));
        Exception exception = assertThrows(Exception.class, () ->{
            rolService.registrar(rol);
        });
        assertEquals(NO_SE_PUEDE_INGRESAR_UNA_FECHA_QUE_AUN_NO_HA_SUCEDIDO, exception.getMessage());
    }
}
