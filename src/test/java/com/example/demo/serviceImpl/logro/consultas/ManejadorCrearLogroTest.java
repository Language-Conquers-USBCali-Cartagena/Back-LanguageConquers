package com.example.demo.serviceImpl.logro.consultas;

import com.example.demo.dao.EstudianteDAO;
import com.example.demo.dao.LogroDAO;
import com.example.demo.mapper.LogroMapper;
import com.example.demo.model.Logro;
import com.example.demo.model.dto.LogroDTO;
import com.example.demo.service.LogroService;
import com.example.demo.serviceImpl.logro.testDataBuilder.LogroTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;@SpringBootTest
public class ManejadorCrearLogroTest {
    private static final String SE_GUARDO_EXITOSAMENTE = "Se guardo exitosamente el logro.";
    private static final String DEBE_INGRESAR_EL_NOMBRE_LOGRO = "Debe ingresar el nombre del logro.";
    private static final String TEXTO_MAS_CINCUENTA_CARACTERES = "DGSDGSDSDAGSFDGDFGSDFFGDFSGDFGDFGDFGDFGDFGDFGDSGDFDVSDDSBDSBSDBSVDVSDVSDVSDVSDV";
    private static final String TEXTO_MAS_TRECIOENTOS_CARACTERES = "DGSDGSDSDAGSFGEWRHERHTRHRTJJRHERGWGLFGHEÑGHJERÑHEJR{HJERÑLHKERLKGEJFÑLDSAJFLÑKSDJGDKÑLFGJDFLÑGJSAFDGJAEPRGJEROIGJEOIRHREJGOERJGÑLKERWJGÑLEWRJGÑLEGJÑELRGJLERWGJLERJGLWERJGLEGJELJGÑLEJGLERGGFLKGJASFDGLKJSKLGDJFGLSDJHGKJFHGKDASSKJDLGSAOFGIAOGSAFHASÑFHÑSFLGLÑHWEHEHRHERHERHERHERHEWRHERHEWHERHEWRHERHERHERHRETYKTYKDGDFGSDFFGDFSGDFGDFGDFGDFGDFGDFGDSGDFDVSDDSBDSBSDBSVDVSDVSDVSDVSDV";
    @Autowired
    LogroMapper logroMapper;
    @Autowired
    LogroService logroService;
    @MockBean
    LogroDAO logroDAO;
    @MockBean
    EstudianteDAO estudianteDAO;

    @Test
    @DisplayName("Deberia crear un logro")
    void testDeberiaCrearUnLogro() throws Exception{
        LogroDTO logroDTO = new LogroTestDataBuilder().build();
        Logro logro = logroMapper.toEntity(logroDTO);
        Mockito.when(logroDAO.save(Mockito.any())).thenReturn(logro);
        String respuesta = logroService.registrar(logro);
        assertEquals(respuesta, SE_GUARDO_EXITOSAMENTE);
    }
    @Test
    @DisplayName("Deberia fallar por nombre  nulo")
    void testDeberiaFallarPorNombreNulo() throws Exception{
        LogroDTO logroDTO = new LogroTestDataBuilder().conNombre(null).build();
        Logro logro = logroMapper.toEntity(logroDTO);
        Mockito.when(logroDAO.save(Mockito.any())).thenReturn(logro);
        Exception exception = assertThrows(Exception.class, () ->{
            logroService.registrar(logro);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_EL_NOMBRE_LOGRO);
    }
    @Test
    @DisplayName("Deberia fallar por nombre  vacio")
    void testDeberiaFallarPorNombreVacio() throws Exception{
        LogroDTO logroDTO = new LogroTestDataBuilder().conNombre("").build();
        Logro logro = logroMapper.toEntity(logroDTO);
        Mockito.when(logroDAO.save(Mockito.any())).thenReturn(logro);
        Exception exception = assertThrows(Exception.class, () ->{
            logroService.registrar(logro);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_EL_NOMBRE_LOGRO);
    }
    @Test
    @DisplayName("Deberia fallar por nombre  muy largo")
    void testDeberiaFallarPorNombreMuyLargo() throws Exception{
        LogroDTO logroDTO = new LogroTestDataBuilder().conNombre(TEXTO_MAS_CINCUENTA_CARACTERES).build();
        Logro logro = logroMapper.toEntity(logroDTO);
        Mockito.when(logroDAO.save(Mockito.any())).thenReturn(logro);
        Exception exception = assertThrows(Exception.class, () ->{
            logroService.registrar(logro);
        });
        assertEquals(exception.getMessage(), "El nombre del logro es muy largo.");
    }
    @Test
    @DisplayName("Deberia fallar por descripcion nulo")
    void testDeberiaFallarPorDescripcionNulo() throws Exception{
        LogroDTO logroDTO = new LogroTestDataBuilder().conDescripcion(null).build();
        Logro logro = logroMapper.toEntity(logroDTO);
        Mockito.when(logroDAO.save(Mockito.any())).thenReturn(logro);
        Exception exception = assertThrows(Exception.class, () ->{
            logroService.registrar(logro);
        });
        assertEquals(exception.getMessage(), "Debe ingresar una descripción del logro.");
    }
    @Test
    @DisplayName("Deberia fallar por descripcion vacio")
    void testDeberiaFallarPorDescripcionVacio() throws Exception{
        LogroDTO logroDTO = new LogroTestDataBuilder().conDescripcion("").build();
        Logro logro = logroMapper.toEntity(logroDTO);
        Mockito.when(logroDAO.save(Mockito.any())).thenReturn(logro);
        Exception exception = assertThrows(Exception.class, () ->{
            logroService.registrar(logro);
        });
        assertEquals(exception.getMessage(), "Debe ingresar una descripción del logro.");
    }
    @Test
    @DisplayName("Deberia fallar por descripcion larga")
    void testDeberiaFallarPorDescripcionLarga() throws Exception{
        LogroDTO logroDTO = new LogroTestDataBuilder().conDescripcion(TEXTO_MAS_TRECIOENTOS_CARACTERES).build();
        Logro logro = logroMapper.toEntity(logroDTO);
        Mockito.when(logroDAO.save(Mockito.any())).thenReturn(logro);
        Exception exception = assertThrows(Exception.class, () ->{
            logroService.registrar(logro);
        });
        assertEquals(exception.getMessage(), "La descripción es muy larga.");
    }
    @Test
    @DisplayName("Deberia fallar imagen nula")
    void testDeberiaFallarImagenNula() throws Exception{
        LogroDTO logroDTO = new LogroTestDataBuilder().conImagen(null).build();
        Logro logro = logroMapper.toEntity(logroDTO);
        Mockito.when(logroDAO.save(Mockito.any())).thenReturn(logro);
        Exception exception = assertThrows(Exception.class, () ->{
            logroService.registrar(logro);
        });
        assertEquals(exception.getMessage(), "Debe ingresar una imagen.");
    }
    @Test
    @DisplayName("Deberia fallar imagen vacia")
    void testDeberiaFallarImagenVacia() throws Exception{
        LogroDTO logroDTO = new LogroTestDataBuilder().conImagen("").build();
        Logro logro = logroMapper.toEntity(logroDTO);
        Mockito.when(logroDAO.save(Mockito.any())).thenReturn(logro);
        Exception exception = assertThrows(Exception.class, () ->{
            logroService.registrar(logro);
        });
        assertEquals(exception.getMessage(), "Debe ingresar una imagen.");
    }
    @Test
    @DisplayName("Deberia fallar imagen larga")
    void testDeberiaFallarImagenLarga() throws Exception{
        LogroDTO logroDTO = new LogroTestDataBuilder().conImagen(TEXTO_MAS_TRECIOENTOS_CARACTERES).build();
        Logro logro = logroMapper.toEntity(logroDTO);
        Mockito.when(logroDAO.save(Mockito.any())).thenReturn(logro);
        Exception exception = assertThrows(Exception.class, () ->{
            logroService.registrar(logro);
        });
        assertEquals(exception.getMessage(), "El nombre de la imagen o url es muy larga.");
    }
    @Test
    @DisplayName("Deberia fallar usuario creador nulo")
    void testDeberiaFallarUsuarioCreadorNulo() throws Exception{
        LogroDTO logroDTO = new LogroTestDataBuilder().conUsuarioCreador(null).build();
        Logro logro = logroMapper.toEntity(logroDTO);
        Mockito.when(logroDAO.save(Mockito.any())).thenReturn(logro);
        Exception exception = assertThrows(Exception.class, () ->{
            logroService.registrar(logro);
        });
        assertEquals(exception.getMessage(), "Debe ingresar el usuario creador.");
    }
    @Test
    @DisplayName("Deberia fallar usuario creador vacio")
    void testDeberiaFallarUsuarioCreadorVacio() throws Exception{
        LogroDTO logroDTO = new LogroTestDataBuilder().conUsuarioCreador("").build();
        Logro logro = logroMapper.toEntity(logroDTO);
        Mockito.when(logroDAO.save(Mockito.any())).thenReturn(logro);
        Exception exception = assertThrows(Exception.class, () ->{
            logroService.registrar(logro);
        });
        assertEquals(exception.getMessage(), "Debe ingresar el usuario creador.");
    }
    @Test
    @DisplayName("Deberia fallar usuario creador muy largo")
    void testDeberiaFallarUsuarioCreadorMuyLargo() throws Exception{
        LogroDTO logroDTO = new LogroTestDataBuilder().conUsuarioCreador(TEXTO_MAS_CINCUENTA_CARACTERES).build();
        Logro logro = logroMapper.toEntity(logroDTO);
        Mockito.when(logroDAO.save(Mockito.any())).thenReturn(logro);
        Exception exception = assertThrows(Exception.class, () ->{
            logroService.registrar(logro);
        });
        assertEquals(exception.getMessage(), "El nombre del usuario creador es muy largo, solo puede contener 50 caracteres.");
    }
    @Test
    @DisplayName("Deberia fallar fecha creacion nulo")
    void testDeberiaFallarFechaCreacionNula() throws Exception{
        LogroDTO logroDTO = new LogroTestDataBuilder().conFechaCreacion(null).build();
        Logro logro = logroMapper.toEntity(logroDTO);
        Mockito.when(logroDAO.save(Mockito.any())).thenReturn(logro);
        Exception exception = assertThrows(Exception.class, () ->{
            logroService.registrar(logro);
        });
        assertEquals(exception.getMessage(), "Debe ingresar una fecha de creación.");
    }
}
