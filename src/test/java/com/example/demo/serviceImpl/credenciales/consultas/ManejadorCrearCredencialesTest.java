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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@SpringBootTest
public class ManejadorCrearCredencialesTest {

    private static final String SE_AGREGO_LA_CREDENCIAL = "Se agrego la credencial.";
    private static final String DEBE_INGRESAR_EL_NOMBRE_O_CORREO_ASOCIADO_A_LA_CUENTA = "Debe ingresar el nombre o correo asociado a la cuenta.";
    private static final String LA_CUENTA_O_CORREO_ES_LARGO_SE_ACEPTAN_SOLO_80_CARACTERES = "La cuenta o correo es largo, se aceptan solo 80 caracteres.";
    private static final String DEBE_INGRESAR_LA_CONTRASENNA="Debe ingresar la contraseña.";
    private static final String LA_CONTRASEÑA_ES_LARGA_SE_ACEPTAN_SOLO_80_CARACTERES = "La contraseña es larga, se aceptan solo 80 caracteres.";
    private static final String DEBE_INGRESAR_LA_URL = "Debe ingresar la url.";
    private static final String LA_URL_ESTA_MUY_LARGA_SE_ACEPTAN_SOLO_150_CARACTERES = "La url esta muy larga, se aceptan solo 150 caracteres.";
    private static final String DEBE_INGRESAR_EL_NOMBRE_DE_LA_PLATAFORMA_A_LA_CUAL_PERTENECE_LA_CUENTA = "Debe ingresar el nombre de la plataforma a la cual pertenece la cuenta.";
    private static final String EL_NOMBRE_DE_LA_PLATAFORMA_ES_MUY_LARGO_SE_ACEPTAN_SOLO_80_CARACTERES = "El nombre de la plataforma es muy largo, se aceptan solo 80 caracteres.";
    private static final String DEBE_INGRESAR_EL_NOMBRE_DEL_USUARIO_CREADOR = "Debe ingresar el nombre del usuario creador.";
    private static final String EL_NOMBRE_DEL_USUARIO_CREADOR_SOLO_PUEDE_CONTENER_50_CARACTERES = "El nombre del usuario creador solo puede contener 50 caracteres.";
    private static final String DEBE_INGRESAR_LA_FECHA_DE_CREACION = "Debe ingresar la fecha de creación.";
    private static final String NO_PUEDE_INGRESAR_UNA_FECHA_QUE_AUN_NO_HA_SUCEDIDO = "No puede ingresar una fecha que aun no ha sucedido.";
    private static final String CUENTA_MALA = "JHDFJHSFJHSDFH FDHSDFHJSDKH FHDFH JHFDGJFGBFRG ERGT TR RHDFJ DJHFGDGD TRHR RTDG RTGR DGHJDH";
    private static final String PASSWORD = "SDHCFSDFH FHJHFG RHF ERJHE EH FTHEHJ EHR RE HGER HG G HERH HGERHKHG HGEJHGTRJH GHHFRGVEJKH GERHR EH";

    private static final String URL_LARGA = "https://www.youtube.com/WLFEHKWEHFKHWEFKHWEKJF/WFKJEHFVHEJKRHVGFER/FJLEHSVHERJKVGE/REGERJHGJER/ERGHERVGER/GJKHBVKHBJKHBSJK/TRJKBKRHJKBHDJKCHSDKC/CFKJSDHV/DFVGDF";
    private static final String PLATAFORMA_LARGA = "SFSD SF HSFKHFSD SDHFJKHSDKF SDHKSDFHSDF HSDFKJS FSDHKHSDFKFHSDK SDFHKFSDHKSDSDFH KSDHFKSDHFKDSJKH";
    private static final String USUARIO_CREADOR = "FJEDKHF EKFHR REHGJK HEGK HGK  HGRTHGKTR GR TGHTRKGH TRKG RTKHGKJTRHGH RTHR JKRHHG HTRJKERHHSE";

    @Autowired
    CredencialesService credencialesService;

    @Autowired
    CredencialesMapper credencialesMapper;

    @MockBean
    private CredencialesDAO credencialesDAO;

    @Test
    @DisplayName("Deberia crear una credencias exitosamente")
    void deberiaCrearCredencialExitosamente()throws Exception{
        CredencialesDTO credencialesDTO = new CredencialesTestDataBuilder().build();
        Credenciales credenciales = credencialesMapper.toEntity(credencialesDTO);
        String crearCredencial = credencialesService.crear(credenciales);
        Mockito.when(credencialesDAO.save(Mockito.any())).thenReturn(credenciales);
        assertEquals(SE_AGREGO_LA_CREDENCIAL, crearCredencial);
    }
    @Test
    @DisplayName("Deberia fallar por cuenta null ")
    void deberiaFallarPorCuentaNull()throws Exception{
        CredencialesDTO credencialesDTO = new CredencialesTestDataBuilder().conCuenta(null).build();
        Credenciales credenciales = credencialesMapper.toEntity(credencialesDTO);
        Mockito.when(credencialesDAO.save(Mockito.any())).thenReturn(credenciales);
        Exception exception = assertThrows(Exception.class, ()->{
            credencialesService.crear(credenciales);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_EL_NOMBRE_O_CORREO_ASOCIADO_A_LA_CUENTA);
    }

    @Test
    @DisplayName("Deberia fallar por cuenta vacio ")
    void deberiaFallarPorCuentaVacio()throws Exception{
        CredencialesDTO credencialesDTO = new CredencialesTestDataBuilder().conCuenta("").build();
        Credenciales credenciales = credencialesMapper.toEntity(credencialesDTO);
        Mockito.when(credencialesDAO.save(Mockito.any())).thenReturn(credenciales);
        Exception exception = assertThrows(Exception.class, ()->{
            credencialesService.crear(credenciales);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_EL_NOMBRE_O_CORREO_ASOCIADO_A_LA_CUENTA);
    }
    @Test
    @DisplayName("Deberia fallar por cuenta vacio ")
        void deberiaFallarPorCuentaLargo()throws Exception{
        CredencialesDTO credencialesDTO = new CredencialesTestDataBuilder().conCuenta(CUENTA_MALA).build();
        Credenciales credenciales = credencialesMapper.toEntity(credencialesDTO);
        Mockito.when(credencialesDAO.save(Mockito.any())).thenReturn(credenciales);
        Exception exception = assertThrows(Exception.class, ()->{
            credencialesService.crear(credenciales);
        });
        assertEquals(exception.getMessage(),LA_CUENTA_O_CORREO_ES_LARGO_SE_ACEPTAN_SOLO_80_CARACTERES);
    }

    @Test
    @DisplayName("Deberia fallar por contraseña null ")
    void deberiaFallarPorPasswordNull()throws Exception{
        CredencialesDTO credencialesDTO = new CredencialesTestDataBuilder().conPassword(null).build();
        Credenciales credenciales = credencialesMapper.toEntity(credencialesDTO);
        Mockito.when(credencialesDAO.save(Mockito.any())).thenReturn(credenciales);
        Exception exception = assertThrows(Exception.class, ()->{
            credencialesService.crear(credenciales);
        });
        assertEquals(exception.getMessage(),DEBE_INGRESAR_LA_CONTRASENNA);
    }

    @Test
    @DisplayName("Deberia fallar por contraseña vacio ")
    void deberiaFallarPorPasswordVacio()throws Exception{
        CredencialesDTO credencialesDTO = new CredencialesTestDataBuilder().conPassword("").build();
        Credenciales credenciales = credencialesMapper.toEntity(credencialesDTO);
        Mockito.when(credencialesDAO.save(Mockito.any())).thenReturn(credenciales);
        Exception exception = assertThrows(Exception.class, ()->{
            credencialesService.crear(credenciales);
        });
        assertEquals(exception.getMessage(),DEBE_INGRESAR_LA_CONTRASENNA);
    }
    @Test
    @DisplayName("Deberia fallar por contraseña largo ")
    void deberiaFallarPorPasswordLargo()throws Exception{
        CredencialesDTO credencialesDTO = new CredencialesTestDataBuilder().conPassword(PASSWORD).build();
        Credenciales credenciales = credencialesMapper.toEntity(credencialesDTO);
        Mockito.when(credencialesDAO.save(Mockito.any())).thenReturn(credenciales);
        Exception exception = assertThrows(Exception.class, ()->{
            credencialesService.crear(credenciales);
        });
        assertEquals(exception.getMessage(),LA_CONTRASEÑA_ES_LARGA_SE_ACEPTAN_SOLO_80_CARACTERES);
    }
    @Test
    @DisplayName("Deberia fallar por url null ")
    void deberiaFallarPorUrlNull()throws Exception{
        CredencialesDTO credencialesDTO = new CredencialesTestDataBuilder().conUrl(null).build();
        Credenciales credenciales = credencialesMapper.toEntity(credencialesDTO);
        Mockito.when(credencialesDAO.save(Mockito.any())).thenReturn(credenciales);
        Exception exception = assertThrows(Exception.class, ()->{
            credencialesService.crear(credenciales);
        });
        assertEquals(exception.getMessage(),DEBE_INGRESAR_LA_URL);
    }
    @Test
    @DisplayName("Deberia fallar por url vacio ")
    void deberiaFallarPorUrlVacio()throws Exception{
        CredencialesDTO credencialesDTO = new CredencialesTestDataBuilder().conUrl("").build();
        Credenciales credenciales = credencialesMapper.toEntity(credencialesDTO);
        Mockito.when(credencialesDAO.save(Mockito.any())).thenReturn(credenciales);
        Exception exception = assertThrows(Exception.class, ()->{
            credencialesService.crear(credenciales);
        });
        assertEquals(exception.getMessage(),DEBE_INGRESAR_LA_URL);
    }
    @Test
    @DisplayName("Deberia fallar por url largo")
    void deberiaFallarPorUrlLargo()throws Exception{
        CredencialesDTO credencialesDTO = new CredencialesTestDataBuilder().conUrl(URL_LARGA).build();
        Credenciales credenciales = credencialesMapper.toEntity(credencialesDTO);
        Mockito.when(credencialesDAO.save(Mockito.any())).thenReturn(credenciales);
        Exception exception = assertThrows(Exception.class, ()->{
            credencialesService.crear(credenciales);
        });
        assertEquals(exception.getMessage(),LA_URL_ESTA_MUY_LARGA_SE_ACEPTAN_SOLO_150_CARACTERES);
    }
    @Test
    @DisplayName("Deberia fallar por plataforma null")
    void deberiaFallarPorPlataformaNull()throws Exception{
        CredencialesDTO credencialesDTO = new CredencialesTestDataBuilder().conPlataforma(null).build();
        Credenciales credenciales = credencialesMapper.toEntity(credencialesDTO);
        Mockito.when(credencialesDAO.save(Mockito.any())).thenReturn(credenciales);
        Exception exception = assertThrows(Exception.class, ()->{
            credencialesService.crear(credenciales);
        });
        assertEquals(exception.getMessage(),DEBE_INGRESAR_EL_NOMBRE_DE_LA_PLATAFORMA_A_LA_CUAL_PERTENECE_LA_CUENTA);
    }

    @Test
    @DisplayName("Deberia fallar por plataforma vacio")
    void deberiaFallarPorPlataformaVacio()throws Exception{
        CredencialesDTO credencialesDTO = new CredencialesTestDataBuilder().conPlataforma("").build();
        Credenciales credenciales = credencialesMapper.toEntity(credencialesDTO);
        Mockito.when(credencialesDAO.save(Mockito.any())).thenReturn(credenciales);
        Exception exception = assertThrows(Exception.class, ()->{
            credencialesService.crear(credenciales);
        });
        assertEquals(exception.getMessage(),DEBE_INGRESAR_EL_NOMBRE_DE_LA_PLATAFORMA_A_LA_CUAL_PERTENECE_LA_CUENTA);
    }
    @Test
    @DisplayName("Deberia fallar por plataforma largo")
    void deberiaFallarPorPlataformaLargo()throws Exception{
        CredencialesDTO credencialesDTO = new CredencialesTestDataBuilder().conPlataforma(PLATAFORMA_LARGA).build();
        Credenciales credenciales = credencialesMapper.toEntity(credencialesDTO);
        Mockito.when(credencialesDAO.save(Mockito.any())).thenReturn(credenciales);
        Exception exception = assertThrows(Exception.class, ()->{
            credencialesService.crear(credenciales);
        });
        assertEquals(exception.getMessage(),EL_NOMBRE_DE_LA_PLATAFORMA_ES_MUY_LARGO_SE_ACEPTAN_SOLO_80_CARACTERES);
    }
    @Test
    @DisplayName("Deberia fallar por usuario creador null")
    void deberiaFallarPorUsuarioCreadorNull()throws Exception{
        CredencialesDTO credencialesDTO = new CredencialesTestDataBuilder().conUsuarioCreador(null).build();
        Credenciales credenciales = credencialesMapper.toEntity(credencialesDTO);
        Mockito.when(credencialesDAO.save(Mockito.any())).thenReturn(credenciales);
        Exception exception = assertThrows(Exception.class, ()->{
            credencialesService.crear(credenciales);
        });
        assertEquals(exception.getMessage(),DEBE_INGRESAR_EL_NOMBRE_DEL_USUARIO_CREADOR);
    }

    @Test
    @DisplayName("Deberia fallar por usuario creador vacio")
    void deberiaFallarPorUsuarioCreadorVacio()throws Exception{
        CredencialesDTO credencialesDTO = new CredencialesTestDataBuilder().conUsuarioCreador("").build();
        Credenciales credenciales = credencialesMapper.toEntity(credencialesDTO);
        Mockito.when(credencialesDAO.save(Mockito.any())).thenReturn(credenciales);
        Exception exception = assertThrows(Exception.class, ()->{
            credencialesService.crear(credenciales);
        });
        assertEquals(exception.getMessage(),DEBE_INGRESAR_EL_NOMBRE_DEL_USUARIO_CREADOR);
    }
    @Test
    @DisplayName("Deberia fallar por usuario creador largo")
    void deberiaFallarPorUsuarioCreadorLargo()throws Exception{
        CredencialesDTO credencialesDTO = new CredencialesTestDataBuilder().conUsuarioCreador(USUARIO_CREADOR).build();
        Credenciales credenciales = credencialesMapper.toEntity(credencialesDTO);
        Mockito.when(credencialesDAO.save(Mockito.any())).thenReturn(credenciales);
        Exception exception = assertThrows(Exception.class, ()->{
            credencialesService.crear(credenciales);
        });
        assertEquals(exception.getMessage(),EL_NOMBRE_DEL_USUARIO_CREADOR_SOLO_PUEDE_CONTENER_50_CARACTERES);
    }
    @Test
    @DisplayName("Deberia fallar por fecha creación null")
    void deberiaFallarPorFechaCreacionNull()throws Exception{
        CredencialesDTO credencialesDTO = new CredencialesTestDataBuilder().conFechaCreacion(null).build();
        Credenciales credenciales = credencialesMapper.toEntity(credencialesDTO);
        Mockito.when(credencialesDAO.save(Mockito.any())).thenReturn(credenciales);
        Exception exception = assertThrows(Exception.class, ()->{
            credencialesService.crear(credenciales);
        });
        assertEquals(exception.getMessage(),DEBE_INGRESAR_LA_FECHA_DE_CREACION);
    }
    @Test
    @DisplayName("Deberia fallar por fecha creación futura")
    void deberiaFallarPorFechaCreacionFutura()throws Exception{
        CredencialesDTO credencialesDTO = new CredencialesTestDataBuilder().conFechaCreacion(new Date(2023,6,29)).build();
        Credenciales credenciales = credencialesMapper.toEntity(credencialesDTO);
        Mockito.when(credencialesDAO.save(Mockito.any())).thenReturn(credenciales);
        Exception exception = assertThrows(Exception.class, ()->{
            credencialesService.crear(credenciales);
        });
        assertEquals(exception.getMessage(),NO_PUEDE_INGRESAR_UNA_FECHA_QUE_AUN_NO_HA_SUCEDIDO);
    }



}
