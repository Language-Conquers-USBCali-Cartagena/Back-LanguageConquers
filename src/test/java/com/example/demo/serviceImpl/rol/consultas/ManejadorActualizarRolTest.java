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
public class ManejadorActualizarRolTest {
    private static final String SE_ACTUALIZO_EXITOSAMENTE_EL_ROL = "Se actualizo exitosamente el rol.";
    private static final String DEBE_INGRESAR_EL_ID_DEL_ROL_QUE_DESEA_MODIFICAR = "Debe ingresar el id del rol que desea modificar.";
    private static final String NO_EXISTE_EL_ROL_CON_ESE_ID = "No existe el rol con ese id.";
    private static final String SE_DEBE_INGRESAR_EL_NOMBRE_DEL_ROL = "Se debe ingresar el nombre del rol.";
    private static final String EL_NOMBRE_DEL_ROL_NO_PUEDE_SUPERAR_LOS_50_CARACTERES = "El nombre del rol no puede superar los 50 caracteres.";
    private static final String DEBE_INGRESAR_UN_ID_RETO_VALIDO = "Debe ingresar un id reto válido.";
    private static final String SE_DEBE_INGRESAR_EL_USUARIO_MODIFICADOR = "Se debe ingresar el usuario modificador.";
    private static final String EL_NOMBRE_DEL_USUARIO_MODIFICADOR_NO_DEBE_SUPERAR_LOS_50_CARACTERES = "El nombre del usuario modificador no debe superar los 50 caracteres.";
    private static final String SE_DEBE_INGRESAR_UNA_FECHA_DE_MODIFICACION = "Se debe ingresar una fecha de modificación.";
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
    @DisplayName("Deberia actualizar un rol")
    void deberiaActualizarUnRol() throws Exception{
        RolDTO rolDTO = new RolTestDataBuilder().build();
        Rol rol = rolMapper.toEntity(rolDTO);
        Mockito.when(rolDAO.existsById(rol.getIdRol())).thenReturn(true);
        Mockito.when(rolDAO.save(Mockito.any())).thenReturn(rol);
        Mockito.when(retoDAO.findById(rolDTO.getIdReto())).thenReturn(Optional.of(new Reto()));
        String crearSemestre = rolService.actualizar(rol);
        assertEquals(SE_ACTUALIZO_EXITOSAMENTE_EL_ROL, crearSemestre);
    }
    @Test
    @DisplayName("Deberia fallar por rol id nulo")
    void deberiaFallarPotIdNulo() throws Exception{
        RolDTO rolDTO = new RolTestDataBuilder().conIdRol(null).build();
        Rol rol = rolMapper.toEntity(rolDTO);
        Mockito.when(rolDAO.save(Mockito.any())).thenReturn(rol);
        Mockito.when(retoDAO.findById(rolDTO.getIdReto())).thenReturn(Optional.of(new Reto()));

        Exception exception = assertThrows(Exception.class, () ->{
            rolService.actualizar(rol);
        });
        assertEquals(DEBE_INGRESAR_EL_ID_DEL_ROL_QUE_DESEA_MODIFICAR, exception.getMessage());
    }
    @Test
    @DisplayName("Deberia fallar por rol id no existe")
    void deberiaFallarPorRolIdNoExiste() throws Exception{
        RolDTO rolDTO = new RolTestDataBuilder().build();
        Rol rol = rolMapper.toEntity(rolDTO);
        Mockito.when(rolDAO.save(Mockito.any())).thenReturn(rol);
        Mockito.when(retoDAO.findById(rolDTO.getIdReto())).thenReturn(Optional.of(new Reto()));
        Mockito.when(rolDAO.existsById(rol.getIdRol())).thenReturn(false);
        Exception exception = assertThrows(Exception.class, () ->{
            rolService.actualizar(rol);
        });
        assertEquals(NO_EXISTE_EL_ROL_CON_ESE_ID, exception.getMessage());
    }
    @Test
    @DisplayName("Deberia fallar por nombre rol nulo")
    void deberiaFallarPorNombreRolNulo() throws Exception{
        RolDTO rolDTO = new RolTestDataBuilder().conNombre(null).build();
        Rol rol = rolMapper.toEntity(rolDTO);
        Mockito.when(rolDAO.save(Mockito.any())).thenReturn(rol);
        Mockito.when(retoDAO.findById(rolDTO.getIdReto())).thenReturn(Optional.of(new Reto()));
        Mockito.when(rolDAO.existsById(rol.getIdRol())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, () ->{
            rolService.actualizar(rol);
        });
        assertEquals(SE_DEBE_INGRESAR_EL_NOMBRE_DEL_ROL, exception.getMessage());
    }
    @Test
    @DisplayName("Deberia fallar por nombre rol vacio")
    void deberiaFallarPorNombreRolVacio() throws Exception{
        RolDTO rolDTO = new RolTestDataBuilder().conNombre(VACIO).build();
        Rol rol = rolMapper.toEntity(rolDTO);
        Mockito.when(rolDAO.save(Mockito.any())).thenReturn(rol);
        Mockito.when(retoDAO.findById(rolDTO.getIdReto())).thenReturn(Optional.of(new Reto()));
        Mockito.when(rolDAO.existsById(rol.getIdRol())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, () ->{
            rolService.actualizar(rol);
        });
        assertEquals(SE_DEBE_INGRESAR_EL_NOMBRE_DEL_ROL, exception.getMessage());
    }
    @Test
    @DisplayName("Deberia fallar por nombre rol supera caracteres")
    void deberiaFallarPorNombreSuperaCaracteres() throws Exception{
        RolDTO rolDTO = new RolTestDataBuilder().conNombre(TEXTO_MAS_CINCUENTA_CARACTERES).build();
        Rol rol = rolMapper.toEntity(rolDTO);
        Mockito.when(rolDAO.save(Mockito.any())).thenReturn(rol);
        Mockito.when(retoDAO.findById(rolDTO.getIdReto())).thenReturn(Optional.of(new Reto()));
        Mockito.when(rolDAO.existsById(rol.getIdRol())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, () ->{
            rolService.actualizar(rol);
        });
        assertEquals(EL_NOMBRE_DEL_ROL_NO_PUEDE_SUPERAR_LOS_50_CARACTERES, exception.getMessage());
    }
    @Test
    @DisplayName("Deberia fallar por id reto no valido")
    void deberiaFallarPorIdRetoNoValido() throws Exception{
        RolDTO rolDTO = new RolTestDataBuilder().conIdReto(-1L).build();
        Rol rol = rolMapper.toEntity(rolDTO);
        Mockito.when(rolDAO.save(Mockito.any())).thenReturn(rol);
        Mockito.when(retoDAO.findById(rolDTO.getIdReto())).thenReturn(Optional.of(new Reto()));
        Mockito.when(rolDAO.existsById(rol.getIdRol())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, () ->{
            rolService.actualizar(rol);
        });
        assertEquals(DEBE_INGRESAR_UN_ID_RETO_VALIDO, exception.getMessage());
    }
    @Test
    @DisplayName("Deberia fallar por id reto no existente")
    void deberiaFallarPorIdRetoNoExistente() throws Exception{
        RolDTO rolDTO = new RolTestDataBuilder().build();
        Rol rol = rolMapper.toEntity(rolDTO);
        Mockito.when(rolDAO.save(Mockito.any())).thenReturn(rol);
        Mockito.when(retoDAO.findById(rolDTO.getIdReto())).thenReturn(Optional.empty());
        Mockito.when(rolDAO.existsById(rol.getIdRol())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, () ->{
            rolService.actualizar(rol);
        });
        assertEquals(DEBE_INGRESAR_UN_ID_RETO_VALIDO, exception.getMessage());
    }
    @Test
    @DisplayName("Deberia fallar usuario modificador nulo")
    void deberiaFallarPorUsuarioModificadorNulo() throws Exception{
        RolDTO rolDTO = new RolTestDataBuilder().conUsuarioModificador(null).build();
        Rol rol = rolMapper.toEntity(rolDTO);
        Mockito.when(rolDAO.save(Mockito.any())).thenReturn(rol);
        Mockito.when(retoDAO.findById(rolDTO.getIdReto())).thenReturn(Optional.of(new Reto()));
        Mockito.when(rolDAO.existsById(rol.getIdRol())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, () ->{
            rolService.actualizar(rol);
        });
        assertEquals(SE_DEBE_INGRESAR_EL_USUARIO_MODIFICADOR, exception.getMessage());
    }
    @Test
    @DisplayName("Deberia fallar usuario modificador vacio")
    void deberiaFallarPorUsuarioModificadorVacio() throws Exception{
        RolDTO rolDTO = new RolTestDataBuilder().conUsuarioModificador(VACIO).build();
        Rol rol = rolMapper.toEntity(rolDTO);
        Mockito.when(rolDAO.save(Mockito.any())).thenReturn(rol);
        Mockito.when(retoDAO.findById(rolDTO.getIdReto())).thenReturn(Optional.of(new Reto()));
        Mockito.when(rolDAO.existsById(rol.getIdRol())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, () ->{
            rolService.actualizar(rol);
        });
        assertEquals(SE_DEBE_INGRESAR_EL_USUARIO_MODIFICADOR, exception.getMessage());
    }
    @Test
    @DisplayName("Deberia fallar usuario modificador supera caracteres")
    void deberiaFallarPorUsuarioModificadorSuperaCaracteres() throws Exception{
        RolDTO rolDTO = new RolTestDataBuilder().conUsuarioModificador(TEXTO_MAS_CINCUENTA_CARACTERES).build();
        Rol rol = rolMapper.toEntity(rolDTO);
        Mockito.when(rolDAO.save(Mockito.any())).thenReturn(rol);
        Mockito.when(retoDAO.findById(rolDTO.getIdReto())).thenReturn(Optional.of(new Reto()));
        Mockito.when(rolDAO.existsById(rol.getIdRol())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, () ->{
            rolService.actualizar(rol);
        });
        assertEquals(EL_NOMBRE_DEL_USUARIO_MODIFICADOR_NO_DEBE_SUPERAR_LOS_50_CARACTERES, exception.getMessage());
    }
    @Test
    @DisplayName("Deberia fallar si fecha modificacion es nula")
    void deberiaFallarPorFechaModificacionNula() throws Exception{
        RolDTO rolDTO = new RolTestDataBuilder().conFechaModificacion(null).build();
        Rol rol = rolMapper.toEntity(rolDTO);
        Mockito.when(rolDAO.save(Mockito.any())).thenReturn(rol);
        Mockito.when(retoDAO.findById(rolDTO.getIdReto())).thenReturn(Optional.of(new Reto()));
        Mockito.when(rolDAO.existsById(rol.getIdRol())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, () ->{
            rolService.actualizar(rol);
        });
        assertEquals(SE_DEBE_INGRESAR_UNA_FECHA_DE_MODIFICACION, exception.getMessage());
    }
    @Test
    @DisplayName("Deberia fallar si fecha futura")
    void deberiaFallarPorFechaModificacionFutura() throws Exception{
        RolDTO rolDTO = new RolTestDataBuilder().conFechaModificacion(FECHA_FUTURA).build();
        Rol rol = rolMapper.toEntity(rolDTO);
        Mockito.when(rolDAO.save(Mockito.any())).thenReturn(rol);
        Mockito.when(retoDAO.findById(rolDTO.getIdReto())).thenReturn(Optional.of(new Reto()));
        Mockito.when(rolDAO.existsById(rol.getIdRol())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, () ->{
            rolService.actualizar(rol);
        });
        assertEquals(NO_SE_PUEDE_INGRESAR_UNA_FECHA_QUE_AUN_NO_HA_SUCEDIDO, exception.getMessage());
    }
}
