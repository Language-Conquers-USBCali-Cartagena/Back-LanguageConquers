package com.example.demo.serviceImpl.credenciales.consultas;

import com.example.demo.dao.CredencialesDAO;
import com.example.demo.mapper.CredencialesMapper;
import com.example.demo.model.Credenciales;
import com.example.demo.model.dto.CredencialesDTO;
import com.example.demo.service.CredencialesService;
import com.example.demo.serviceImpl.credenciales.testDataBuilder.CredencialesTestDataBuilder;
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
public class ManejadorActualizarCredencialesTest {

    public static final String ACTUALIZACION_EXITOSA = "Se actualizo la credencial.";
    public static final String INGRESAR_ID_ACTUALIZAR_CREDENCIAL = "Debe ingresar el id de la credencial para actualizarla.";
    public static final String NO_SE_ENCONTRO_CREDENCIALES_ASOCIADAS_AL_ID = "No se encontró ninguna credencial asociada a ese id.";

    private static final String DEBE_INGRESAR_EL_NOMBRE_O_CORREO_ASOCIADO_A_LA_CUENTA = "Debe ingresar el nombre o correo asociado a la cuenta.";
    private static final String LA_CUENTA_O_CORREO_ES_LARGO_SE_ACEPTAN_SOLO_80_CARACTERES = "La cuenta o correo es larga, se aceptan solo 80 caracteres.";
    private static final String DEBE_INGRESAR_LA_CONTRASENNA="Debe ingresar la contraseña.";
    private static final String LA_CONTRASEÑA_ES_LARGA_SE_ACEPTAN_SOLO_80_CARACTERES = "La contraseña es larga, se aceptan solo 80 caracteres.";
    private static final String DEBE_INGRESAR_LA_URL = "Debe ingresar la url.";
    private static final String LA_URL_ESTA_MUY_LARGA_SE_ACEPTAN_SOLO_150_CARACTERES = "La url esta muy larga, se aceptan solo 150 caracteres.";
    private static final String DEBE_INGRESAR_EL_NOMBRE_DE_LA_PLATAFORMA_A_LA_CUAL_PERTENECE_LA_CUENTA = "Debe ingresar la plataforma a la cual pertenece la cuenta.";
    private static final String EL_NOMBRE_DE_LA_PLATAFORMA_ES_MUY_LARGO_SE_ACEPTAN_SOLO_80_CARACTERES = "El nombre de la plataforma es muy largo, se aceptan solo 80 caracteres.";
    private static final String DEBE_INGRESAR_EL_NOMBRE_DEL_USUARIO_MODIFICADOR = "Debe ingresar el nombre del usuario modificador.";
    private static final String EL_NOMBRE_DEL_USUARIO_MODIFICADOR_SOLO_PUEDE_CONTENER_50_CARACTERES = "El nombre del usuario modificador solo puede contener 50 caracteres.";
    private static final String DEBE_INGRESAR_LA_FECHA_DE_MODIFICACION = "Debe ingresar la fecha de modificación.";
    private static final String NO_PUEDE_INGRESAR_UNA_FECHA_QUE_AUN_NO_HA_SUCEDIDO = "No puede ingresar una fecha que aun no ha sucedido.";
    private static final String CUENTA_MALA = "JHDFJHSFJHSDFH FDHSDFHJSDKH FHDFH JHFDGJFGBFRG ERGT TR RHDFJ DJHFGDGD TRHR RTDG RTGR DGHJDH";
    private static final String CUENTA = "Angela56";
    private static final String PASSWORD = "3t7deds";
    private static final String PASSWORD_MALA = "SDHCFSDFH FHJHFG RHF ERJHE EH FTHEHJ EHR RE HGER HG G HERH HGERHKHG HGEJHGTRJH GHHFRGVEJKH GERHR EH";
    private static final String URL = "https://www.youtube.com/";
    private static final String URL_LARGA = "https://www.youtube.com/WLFEHKWEHFKHWEFKHWEKJF/WFKJEHFVHEJKRHVGFER/FJLEHSVHERJKVGE/REGERJHGJER/ERGHERVGER/GJKHBVKHBJKHBSJK/TRJKBKRHJKBHDJKCHSDKC/CFKJSDHV/DFVGDF";
    private static final String PLATAFORMA = "Google";
    private static final String PLATAFORMA_LARGA = "SFSD SF HSFKHFSD SDHFJKHSDKF SDHKSDFHSDF HSDFKJS FSDHKHSDFKFHSDK SDFHKFSDHKSDSDFH KSDHFKSDHFKDSJKH";
    private static final String USUARIO_MODIFICADOR = "FJEDKHF EKFHR REHGJK HEGK HGK  HGRTHGKTR GR TGHTRKGH TRKG RTKHGKJTRHGH RTHR JKRHHG HTRJKERHHSE";

    @Autowired
    CredencialesService credencialesService;

    @Autowired
    CredencialesMapper credencialesMapper;

    @MockBean
    private CredencialesDAO credencialesDAO;

    @Test
    @DisplayName("Deberia actualizar exitosamente la credencial")
    void deberiaCrearExitosamenteCredecial()throws Exception{
        CredencialesDTO credencialesDTO = new CredencialesTestDataBuilder()
                .conCuenta(CUENTA)
                .conPassword(PASSWORD)
                .conPlataforma(PLATAFORMA)
                .conUsuarioCreador("Angela")
                .conFechaCreacion(new Date())
                .build();
        Credenciales credenciales = credencialesMapper.toEntity(credencialesDTO);
        Mockito.when(credencialesDAO.existsById(credenciales.getIdCredencial())).thenReturn(true);
        Mockito.when(credencialesDAO.findById(credenciales.getIdCredencial())).thenReturn(Optional.of(credenciales));
        Mockito.when(credencialesDAO.save(Mockito.any(Credenciales.class))).thenReturn(credenciales);
        String actualizarCredencial = credencialesService.actualizar(credencialesDTO);
        assertEquals(ACTUALIZACION_EXITOSA, actualizarCredencial);
    }
    @Test
    @DisplayName("Deberia fallar por idCredencial null")
    void deberiaFallarPorIdCredencialNull()throws Exception{
        CredencialesDTO credencialesDTO = new CredencialesTestDataBuilder()
                .conIdCredencial(null)
                .build();
        Credenciales credenciales = credencialesMapper.toEntity(credencialesDTO);
        Mockito.when(credencialesDAO.save(Mockito.any(Credenciales.class))).thenReturn(credenciales);
        Exception exception = assertThrows(Exception.class, ()->{
            credencialesService.actualizar(credencialesDTO);
        });
        assertEquals(exception.getMessage(),INGRESAR_ID_ACTUALIZAR_CREDENCIAL);
    }
    @Test
    @DisplayName("Deberia fallar por idCredencial no existe")
    void deberiaFallarPorIdCredencialNoExiste()throws Exception{
        CredencialesDTO credencialesDTO = new CredencialesTestDataBuilder()
                .conIdCredencial(581L)
                .build();
        Credenciales credenciales = credencialesMapper.toEntity(credencialesDTO);
        Mockito.when(credencialesDAO.save(Mockito.any(Credenciales.class))).thenReturn(credenciales);
        Exception exception = assertThrows(Exception.class, ()->{
            credencialesService.actualizar(credencialesDTO);
        });
        assertEquals(exception.getMessage(),NO_SE_ENCONTRO_CREDENCIALES_ASOCIADAS_AL_ID);
    }
    @Test
    @DisplayName("Deberia fallar por cuenta null")
    void deberiaFallarPorCuentaNull()throws Exception{
        CredencialesDTO credencialesDTO = new CredencialesTestDataBuilder()
                .conCuenta(null)
                .build();
        Credenciales credenciales = credencialesMapper.toEntity(credencialesDTO);
        Mockito.when(credencialesDAO.existsById(credencialesDTO.getIdCredencial())).thenReturn(true);
        Mockito.when(credencialesDAO.save(Mockito.any(Credenciales.class))).thenReturn(credenciales);
        Exception exception = assertThrows(Exception.class, ()->{
            credencialesService.actualizar(credencialesDTO);
        });
        assertEquals(exception.getMessage(),DEBE_INGRESAR_EL_NOMBRE_O_CORREO_ASOCIADO_A_LA_CUENTA);
    }

    @Test
    @DisplayName("Deberia fallar por cuenta vacia")
    void deberiaFallarPorCuentaVacia()throws Exception{
        CredencialesDTO credencialesDTO = new CredencialesTestDataBuilder()
                .conCuenta("")
                .build();
        Credenciales credenciales = credencialesMapper.toEntity(credencialesDTO);
        Mockito.when(credencialesDAO.existsById(credencialesDTO.getIdCredencial())).thenReturn(true);
        Mockito.when(credencialesDAO.save(Mockito.any(Credenciales.class))).thenReturn(credenciales);
        Exception exception = assertThrows(Exception.class, ()->{
            credencialesService.actualizar(credencialesDTO);
        });
        assertEquals(exception.getMessage(),DEBE_INGRESAR_EL_NOMBRE_O_CORREO_ASOCIADO_A_LA_CUENTA);
    }
    @Test
    @DisplayName("Deberia fallar por cuenta Larga")
    void deberiaFallarPorCuentaLarga()throws Exception{
        CredencialesDTO credencialesDTO = new CredencialesTestDataBuilder()
                .conCuenta(CUENTA_MALA)
                .build();
        Credenciales credenciales = credencialesMapper.toEntity(credencialesDTO);
        Mockito.when(credencialesDAO.existsById(credencialesDTO.getIdCredencial())).thenReturn(true);
        Mockito.when(credencialesDAO.save(Mockito.any(Credenciales.class))).thenReturn(credenciales);
        Exception exception = assertThrows(Exception.class, ()->{
            credencialesService.actualizar(credencialesDTO);
        });
        assertEquals(exception.getMessage(),LA_CUENTA_O_CORREO_ES_LARGO_SE_ACEPTAN_SOLO_80_CARACTERES);
    }
    @Test
    @DisplayName("Deberia fallar por password null")
    void deberiaFallarPorPasswordNull()throws Exception{
        CredencialesDTO credencialesDTO = new CredencialesTestDataBuilder()
                .conPassword(null)
                .build();
        Credenciales credenciales = credencialesMapper.toEntity(credencialesDTO);
        Mockito.when(credencialesDAO.existsById(credencialesDTO.getIdCredencial())).thenReturn(true);
        Mockito.when(credencialesDAO.save(Mockito.any(Credenciales.class))).thenReturn(credenciales);
        Exception exception = assertThrows(Exception.class, ()->{
            credencialesService.actualizar(credencialesDTO);
        });
        assertEquals(exception.getMessage(),DEBE_INGRESAR_LA_CONTRASENNA);
    }
    @Test
    @DisplayName("Deberia fallar por password larga")
    void deberiaFallarPorPasswordLarga()throws Exception{
        CredencialesDTO credencialesDTO = new CredencialesTestDataBuilder()
                .conPassword(PASSWORD_MALA)
                .build();
        Credenciales credenciales = credencialesMapper.toEntity(credencialesDTO);
        Mockito.when(credencialesDAO.existsById(credencialesDTO.getIdCredencial())).thenReturn(true);
        Mockito.when(credencialesDAO.save(Mockito.any(Credenciales.class))).thenReturn(credenciales);
        Exception exception = assertThrows(Exception.class, ()->{
            credencialesService.actualizar(credencialesDTO);
        });
        assertEquals(exception.getMessage(),LA_CONTRASEÑA_ES_LARGA_SE_ACEPTAN_SOLO_80_CARACTERES);
    }
    @Test
    @DisplayName("Deberia fallar por url null")
    void deberiaFallarPorUrlNull()throws Exception{
        CredencialesDTO credencialesDTO = new CredencialesTestDataBuilder()
                .conUrl(null)
                .build();
        Credenciales credenciales = credencialesMapper.toEntity(credencialesDTO);
        Mockito.when(credencialesDAO.existsById(credencialesDTO.getIdCredencial())).thenReturn(true);
        Mockito.when(credencialesDAO.save(Mockito.any(Credenciales.class))).thenReturn(credenciales);
        Exception exception = assertThrows(Exception.class, ()->{
            credencialesService.actualizar(credencialesDTO);
        });
        assertEquals(exception.getMessage(),DEBE_INGRESAR_LA_URL);
    }
    @Test
    @DisplayName("Deberia fallar por url vacio")
    void deberiaFallarPorUrlVacio()throws Exception{
        CredencialesDTO credencialesDTO = new CredencialesTestDataBuilder()
                .conUrl("")
                .build();
        Credenciales credenciales = credencialesMapper.toEntity(credencialesDTO);
        Mockito.when(credencialesDAO.existsById(credencialesDTO.getIdCredencial())).thenReturn(true);
        Mockito.when(credencialesDAO.save(Mockito.any(Credenciales.class))).thenReturn(credenciales);
        Exception exception = assertThrows(Exception.class, ()->{
            credencialesService.actualizar(credencialesDTO);
        });
        assertEquals(exception.getMessage(),DEBE_INGRESAR_LA_URL);
    }
    @Test
    @DisplayName("Deberia fallar por url larga")
    void deberiaFallarPorUrlLarga()throws Exception{
        CredencialesDTO credencialesDTO = new CredencialesTestDataBuilder()
                .conUrl(URL_LARGA)
                .build();
        Credenciales credenciales = credencialesMapper.toEntity(credencialesDTO);
        Mockito.when(credencialesDAO.existsById(credencialesDTO.getIdCredencial())).thenReturn(true);
        Mockito.when(credencialesDAO.save(Mockito.any(Credenciales.class))).thenReturn(credenciales);
        Exception exception = assertThrows(Exception.class, ()->{
            credencialesService.actualizar(credencialesDTO);
        });
        assertEquals(exception.getMessage(),LA_URL_ESTA_MUY_LARGA_SE_ACEPTAN_SOLO_150_CARACTERES);
    }
    @Test
    @DisplayName("Deberia fallar por plataforma null")
    void deberiaFallarPorPlataformaNull()throws Exception{
        CredencialesDTO credencialesDTO = new CredencialesTestDataBuilder()
                .conPlataforma(null)
                .build();
        Credenciales credenciales = credencialesMapper.toEntity(credencialesDTO);
        Mockito.when(credencialesDAO.existsById(credencialesDTO.getIdCredencial())).thenReturn(true);
        Mockito.when(credencialesDAO.save(Mockito.any(Credenciales.class))).thenReturn(credenciales);
        Exception exception = assertThrows(Exception.class, ()->{
            credencialesService.actualizar(credencialesDTO);
        });
        assertEquals(exception.getMessage(),DEBE_INGRESAR_EL_NOMBRE_DE_LA_PLATAFORMA_A_LA_CUAL_PERTENECE_LA_CUENTA);
    }
    @Test
    @DisplayName("Deberia fallar por plataforma vacio")
    void deberiaFallarPorPlataformaVacio()throws Exception{
        CredencialesDTO credencialesDTO = new CredencialesTestDataBuilder()
                .conPlataforma("")
                .build();
        Credenciales credenciales = credencialesMapper.toEntity(credencialesDTO);
        Mockito.when(credencialesDAO.existsById(credencialesDTO.getIdCredencial())).thenReturn(true);
        Mockito.when(credencialesDAO.save(Mockito.any(Credenciales.class))).thenReturn(credenciales);
        Exception exception = assertThrows(Exception.class, ()->{
            credencialesService.actualizar(credencialesDTO);
        });
        assertEquals(exception.getMessage(),DEBE_INGRESAR_EL_NOMBRE_DE_LA_PLATAFORMA_A_LA_CUAL_PERTENECE_LA_CUENTA);
    }
    @Test
    @DisplayName("Deberia fallar por plataforma largo")
    void deberiaFallarPorPlataformaLargo()throws Exception{
        CredencialesDTO credencialesDTO = new CredencialesTestDataBuilder()
                .conPlataforma(PLATAFORMA_LARGA)
                .build();
        Credenciales credenciales = credencialesMapper.toEntity(credencialesDTO);
        Mockito.when(credencialesDAO.existsById(credencialesDTO.getIdCredencial())).thenReturn(true);
        Mockito.when(credencialesDAO.save(Mockito.any(Credenciales.class))).thenReturn(credenciales);
        Exception exception = assertThrows(Exception.class, ()->{
            credencialesService.actualizar(credencialesDTO);
        });
        assertEquals(exception.getMessage(),EL_NOMBRE_DE_LA_PLATAFORMA_ES_MUY_LARGO_SE_ACEPTAN_SOLO_80_CARACTERES);
    }

    @Test
    @DisplayName("Deberia fallar por usuario modificador null")
    void deberiaFallarPorUsuarioModificadorNull()throws Exception{
        CredencialesDTO credencialesDTO = new CredencialesTestDataBuilder()
                .consuarioModificador(null)
                .build();
        Credenciales credenciales = credencialesMapper.toEntity(credencialesDTO);
        Mockito.when(credencialesDAO.existsById(credencialesDTO.getIdCredencial())).thenReturn(true);
        Mockito.when(credencialesDAO.save(Mockito.any(Credenciales.class))).thenReturn(credenciales);
        Exception exception = assertThrows(Exception.class, ()->{
            credencialesService.actualizar(credencialesDTO);
        });
        assertEquals(exception.getMessage(),DEBE_INGRESAR_EL_NOMBRE_DEL_USUARIO_MODIFICADOR);
    }
    @Test
    @DisplayName("Deberia fallar por usuario modificador vacio")
    void deberiaFallarPorUsuarioModificadorVacio()throws Exception{
        CredencialesDTO credencialesDTO = new CredencialesTestDataBuilder()
                .consuarioModificador("")
                .build();
        Credenciales credenciales = credencialesMapper.toEntity(credencialesDTO);
        Mockito.when(credencialesDAO.existsById(credencialesDTO.getIdCredencial())).thenReturn(true);
        Mockito.when(credencialesDAO.save(Mockito.any(Credenciales.class))).thenReturn(credenciales);
        Exception exception = assertThrows(Exception.class, ()->{
            credencialesService.actualizar(credencialesDTO);
        });
        assertEquals(exception.getMessage(),DEBE_INGRESAR_EL_NOMBRE_DEL_USUARIO_MODIFICADOR);
    }

    @Test
    @DisplayName("Deberia fallar por usuario modificador largo")
    void deberiaFallarPorUsuarioModificadorLargo()throws Exception{
        CredencialesDTO credencialesDTO = new CredencialesTestDataBuilder()
                .consuarioModificador(USUARIO_MODIFICADOR)
                .build();
        Credenciales credenciales = credencialesMapper.toEntity(credencialesDTO);
        Mockito.when(credencialesDAO.existsById(credencialesDTO.getIdCredencial())).thenReturn(true);
        Mockito.when(credencialesDAO.save(Mockito.any(Credenciales.class))).thenReturn(credenciales);
        Exception exception = assertThrows(Exception.class, ()->{
            credencialesService.actualizar(credencialesDTO);
        });
        assertEquals(exception.getMessage(),EL_NOMBRE_DEL_USUARIO_MODIFICADOR_SOLO_PUEDE_CONTENER_50_CARACTERES);
    }

    @Test
    @DisplayName("Deberia fallar por fecha modificación null")
    void deberiaFallarPorFechaModificacionNull()throws Exception{
        CredencialesDTO credencialesDTO = new CredencialesTestDataBuilder()
                .conFechaModificacion(null)
                .build();
        Credenciales credenciales = credencialesMapper.toEntity(credencialesDTO);
        Mockito.when(credencialesDAO.existsById(credencialesDTO.getIdCredencial())).thenReturn(true);
        Mockito.when(credencialesDAO.save(Mockito.any(Credenciales.class))).thenReturn(credenciales);
        Exception exception = assertThrows(Exception.class, ()->{
            credencialesService.actualizar(credencialesDTO);
        });
        assertEquals(exception.getMessage(),DEBE_INGRESAR_LA_FECHA_DE_MODIFICACION);
    }

    @Test
    @DisplayName("Deberia fallar por fecha modificación futura")
    void deberiaFallarPorFechaModificacionFutura()throws Exception{
        CredencialesDTO credencialesDTO = new CredencialesTestDataBuilder()
                .conFechaModificacion(new Date(2023,8,12))
                .build();
        Credenciales credenciales = credencialesMapper.toEntity(credencialesDTO);
        Mockito.when(credencialesDAO.existsById(credencialesDTO.getIdCredencial())).thenReturn(true);
        Mockito.when(credencialesDAO.save(Mockito.any(Credenciales.class))).thenReturn(credenciales);
        Exception exception = assertThrows(Exception.class, ()->{
            credencialesService.actualizar(credencialesDTO);
        });
        assertEquals(exception.getMessage(),NO_PUEDE_INGRESAR_UNA_FECHA_QUE_AUN_NO_HA_SUCEDIDO);
    }

}
