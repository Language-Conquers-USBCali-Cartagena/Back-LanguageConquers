package com.example.demo.serviceImpl.mision.consultas;

import com.example.demo.dao.CursoDAO;
import com.example.demo.dao.MisionDAO;
import com.example.demo.mapper.MisionMapper;
import com.example.demo.model.Curso;
import com.example.demo.model.Mision;
import com.example.demo.model.dto.MisionDTO;
import com.example.demo.service.MisionService;
import com.example.demo.serviceImpl.mision.testDataBuilder.MisionTestDataBuilder;
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
public class ManejadorActualizarMisionTest {
    private static final String SE_ACTUALIZO_EXITOSAMENTE_MISION = "Se actualizo la misión correctamente.";
    private static final String SE_DEBE_INGRESAR_EL_NOMBRE_MISION ="Se debe ingresar el nombre de la misión.";
    private static final String NOMBRE_MISION_MUY_LARGO = "El nombre de la misión es muy largo, solo se aceptan 50 caracteres.";
    private static final String SE_DEBE_INGRESAR_UN_ID_CURSO = "Se debe ingresar un id del curso.";
    private static final String SE_DEBE_INGRSAR_UN_ID_CURSO_EXISTENTE = "Se debe ingresar un id del curso que exista.";
    private static final String DEBE_INGRESAR_USUARIO_CREADOR = "Debe ingresar el usuario creador.";
    private static final String NOMBRE_USUARIO_CREADOR_MUY_LARGO ="El nombre del usuario creador es muy largo, solo puede contener 50 caracteres.";
    private static final String DEBE_INGRESAR_FECHA_CREACION = "Debe ingresar una fecha de creación.";
    private static final String DEBE_INGRESAR_ID_MISION = "Debe ingresar un id misión que desea actualizar.";
    private static final String NO_SE_ENCONTRO_MISION_CON_ID ="No se encontró la misión con ese id.";
    private static final String NO_EXISTE_UN_CURSO_ID ="No existe un curso con ese id.";
    private static final String SE_DEBE_INGRESAR_UN_ID_CURSO_VALIDO ="Se debe ingresar un id del curso válido.";
    private static final String NO_FECHA_QUE_NO_HA_SUCEDIDO = "No puede ingresar una fecha que aun no ha sucedido.";
    private static final Date FECHA_FUTURA= new Date(3500, 12, 12);
    private static final String VACIO = "";
    private static final String TEXTO_MAS_CINCUENTA_CARACTERES = "DGSDGSDSDAGSFDGDFGSDFFGDFSGDFGDFGDFGDFGDFGDFGDSGDFDVSDDSBDSBSDBSVDVSDVSDVSDVSDV";
    private static final String TEXTO_MAS_TRECIOENTOS_CARACTERES = "DGSDGSDSDAGSFGEWRHERHTRHRTJJRHERGWGLFGHEÑGHJERÑHEJR{HJERÑLHKERLKGEJFÑLDSAJFLÑKSDJGDKÑLFGJDFLÑGJSAFDGJAEPRGJEROIGJEOIRHREJGOERJGÑLKERWJGÑLEWRJGÑLEGJÑELRGJLERWGJLERJGLWERJGLEGJELJGÑLEJGLERGGFLKGJASFDGLKJSKLGDJFGLSDJHGKJFHGKDASSKJDLGSAOFGIAOGSAFHASÑFHÑSFLGLÑHWEHEHRHERHERHERHERHEWRHERHEWHERHEWRHERHERHERHRETYKTYKDGDFGSDFFGDFSGDFGDFGDFGDFGDFGDFGDSGDFDVSDDSBDSBSDBSVDVSDVSDVSDVSDV";
    private static final String URL_IMAGEN_MUY_LARGA = "La URL de la imagen es muy largo, solo se aceptan 250 caracteres.";
    @Autowired
    MisionMapper misionMapper;
    @Autowired
    MisionService misionService;
    @MockBean
    MisionDAO misionDAO;
    @MockBean
    CursoDAO cursoDAO;

    @Test
    @DisplayName("Deberia actualizar la mision")
    void testDeberiaCrearMision() throws Exception{
        MisionDTO misionDTO = new MisionTestDataBuilder().build();
        Mision mision = misionMapper.toEntity(misionDTO);
        Mockito.when(cursoDAO.findById(misionDTO.getIdCurso())).thenReturn(Optional.of(new Curso()));
        Mockito.when(misionDAO.save(Mockito.any())).thenReturn(mision);
        Mockito.when((misionDAO.existsById(mision.getIdMision()))).thenReturn(true);
        Mockito.when(cursoDAO.existsById(misionDTO.getIdCurso())).thenReturn(true);
        String resp = misionService.actualizar(mision);
        assertEquals(resp, SE_ACTUALIZO_EXITOSAMENTE_MISION);
    }
    @Test
    @DisplayName("Deberia fallar nombre nulo")
    void testDeberiaFallarNombreNulo() throws Exception{
        MisionDTO misionDTO = new MisionTestDataBuilder().conNombre(null).build();
        Mision mision = misionMapper.toEntity(misionDTO);
        Mockito.when(cursoDAO.findById(misionDTO.getIdCurso())).thenReturn(Optional.of(new Curso()));
        Mockito.when(misionDAO.save(Mockito.any())).thenReturn(mision);
        Mockito.when((misionDAO.existsById(mision.getIdMision()))).thenReturn(true);
        Mockito.when(cursoDAO.existsById(misionDTO.getIdCurso())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, ()->{
            misionService.actualizar(mision);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_EL_NOMBRE_MISION);
    }
    @Test
    @DisplayName("Deberia fallar nombre vacio")
    void testDeberiaFallarNombreVacio() throws Exception{
        MisionDTO misionDTO = new MisionTestDataBuilder().conNombre(VACIO).build();
        Mision mision = misionMapper.toEntity(misionDTO);
        Mockito.when(cursoDAO.findById(misionDTO.getIdCurso())).thenReturn(Optional.of(new Curso()));
        Mockito.when(misionDAO.save(Mockito.any())).thenReturn(mision);
        Mockito.when((misionDAO.existsById(mision.getIdMision()))).thenReturn(true);
        Mockito.when(cursoDAO.existsById(misionDTO.getIdCurso())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, ()->{
            misionService.actualizar(mision);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_EL_NOMBRE_MISION);
    }
    @Test
    @DisplayName("Deberia fallar nombre muy largo")
    void testDeberiaFallarNombreMuyLargo() throws Exception{
        MisionDTO misionDTO = new MisionTestDataBuilder().conNombre(TEXTO_MAS_CINCUENTA_CARACTERES).build();
        Mision mision = misionMapper.toEntity(misionDTO);
        Mockito.when(cursoDAO.findById(misionDTO.getIdCurso())).thenReturn(Optional.of(new Curso()));
        Mockito.when(misionDAO.save(Mockito.any())).thenReturn(mision);
        Mockito.when((misionDAO.existsById(mision.getIdMision()))).thenReturn(true);
        Mockito.when(cursoDAO.existsById(misionDTO.getIdCurso())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, ()->{
            misionService.actualizar(mision);
        });
        assertEquals(exception.getMessage(), NOMBRE_MISION_MUY_LARGO);
    }
    @Test
    @DisplayName("Deberia fallar por imagen con muchos caracteres")
    void testDeberiaFallarImagenCaracteres() throws Exception{
        MisionDTO misionDTO = new MisionTestDataBuilder().conImagen(TEXTO_MAS_TRECIOENTOS_CARACTERES).build();
        Mision mision = misionMapper.toEntity(misionDTO);
        Mockito.when(cursoDAO.findById(misionDTO.getIdCurso())).thenReturn(Optional.of(new Curso()));
        Mockito.when(misionDAO.save(Mockito.any())).thenReturn(mision);
        Mockito.when((misionDAO.existsById(mision.getIdMision()))).thenReturn(true);
        Mockito.when(cursoDAO.existsById(misionDTO.getIdCurso())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, ()->{
            misionService.actualizar(mision);
        });
        assertEquals(exception.getMessage(), URL_IMAGEN_MUY_LARGA);
    }

    @Test
    @DisplayName("Deberia fallar por id curso nulo")
    void testDeberiaFallarIdCursoNulo() throws Exception{
        MisionDTO misionDTO = new MisionTestDataBuilder().conIdCurso(null).build();
        Mision mision = misionMapper.toEntity(misionDTO);
        Mockito.when(cursoDAO.findById(misionDTO.getIdCurso())).thenReturn(Optional.of(new Curso()));
        Mockito.when(misionDAO.save(Mockito.any())).thenReturn(mision);
        Mockito.when((misionDAO.existsById(mision.getIdMision()))).thenReturn(true);
        Mockito.when(cursoDAO.existsById(misionDTO.getIdCurso())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, ()->{
            misionService.actualizar(mision);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UN_ID_CURSO);
    }
    @Test
    @DisplayName("Deberia fallar por id curso inexistente")
    void testDeberiaFallarPotIdCursoInexistente() throws Exception{
        MisionDTO misionDTO = new MisionTestDataBuilder().build();
        Mision mision = misionMapper.toEntity(misionDTO);
        Mockito.when(cursoDAO.findById(misionDTO.getIdCurso())).thenReturn(Optional.empty());
        Mockito.when(misionDAO.save(Mockito.any())).thenReturn(mision);
        Mockito.when((misionDAO.existsById(mision.getIdMision()))).thenReturn(true);
        Mockito.when(cursoDAO.existsById(misionDTO.getIdCurso())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, ()->{
            misionService.actualizar(mision);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRSAR_UN_ID_CURSO_EXISTENTE);
    }
    @Test
    @DisplayName("Deberia fallar por ususario modificador nulo")
    void testDeberiaFallarPorUsusarioModificadorNulo() throws Exception{
        MisionDTO misionDTO = new MisionTestDataBuilder().conUsuarioModificador(null).build();
        Mision mision = misionMapper.toEntity(misionDTO);
        Mockito.when(cursoDAO.findById(misionDTO.getIdCurso())).thenReturn(Optional.of(new Curso()));
        Mockito.when(misionDAO.save(Mockito.any())).thenReturn(mision);
        Mockito.when((misionDAO.existsById(mision.getIdMision()))).thenReturn(true);
        Mockito.when(cursoDAO.existsById(misionDTO.getIdCurso())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, ()->{
            misionService.actualizar(mision);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_USUARIO_CREADOR);
    }
    @Test
    @DisplayName("Deberia fallar por ususario modificador vacio")
    void testDeberiaFallarPorUsusarioModificadorVacio() throws Exception{
        MisionDTO misionDTO = new MisionTestDataBuilder().conUsuarioModificador(VACIO).build();
        Mision mision = misionMapper.toEntity(misionDTO);
        Mockito.when(cursoDAO.findById(misionDTO.getIdCurso())).thenReturn(Optional.of(new Curso()));
        Mockito.when(misionDAO.save(Mockito.any())).thenReturn(mision);
        Mockito.when((misionDAO.existsById(mision.getIdMision()))).thenReturn(true);
        Mockito.when(cursoDAO.existsById(misionDTO.getIdCurso())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, ()->{
            misionService.actualizar(mision);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_USUARIO_CREADOR);
    }
    @Test
    @DisplayName("Deberia fallar por ususario modificador caracteres")
    void testDeberiaFallarPorUsusarioModificadorCaracteres() throws Exception{
        MisionDTO misionDTO = new MisionTestDataBuilder().conUsuarioModificador(TEXTO_MAS_CINCUENTA_CARACTERES).build();
        Mision mision = misionMapper.toEntity(misionDTO);
        Mockito.when(cursoDAO.findById(misionDTO.getIdCurso())).thenReturn(Optional.of(new Curso()));
        Mockito.when(misionDAO.save(Mockito.any())).thenReturn(mision);
        Mockito.when((misionDAO.existsById(mision.getIdMision()))).thenReturn(true);
        Mockito.when(cursoDAO.existsById(misionDTO.getIdCurso())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, ()->{
            misionService.actualizar(mision);
        });
        assertEquals(exception.getMessage(), NOMBRE_USUARIO_CREADOR_MUY_LARGO);
    }
    @Test
    @DisplayName("Deberia fallar por fechaModificacion nula")
    void testDeberiaFallarPorFEchaModificacionNula() throws Exception{
        MisionDTO misionDTO = new MisionTestDataBuilder().conFechaModificacion(null).build();
        Mision mision = misionMapper.toEntity(misionDTO);
        Mockito.when(cursoDAO.findById(misionDTO.getIdCurso())).thenReturn(Optional.of(new Curso()));
        Mockito.when(misionDAO.save(Mockito.any())).thenReturn(mision);
        Mockito.when((misionDAO.existsById(mision.getIdMision()))).thenReturn(true);
        Mockito.when(cursoDAO.existsById(misionDTO.getIdCurso())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, ()->{
            misionService.actualizar(mision);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_FECHA_CREACION);
    }
    @Test
    @DisplayName("Deberia fallar id mision nulo")
    void testDeberiaFallarPorIdMisionNulo() throws Exception{
        MisionDTO misionDTO = new MisionTestDataBuilder().conIdMision(null).build();
        Mision mision = misionMapper.toEntity(misionDTO);
        Mockito.when(cursoDAO.findById(misionDTO.getIdCurso())).thenReturn(Optional.of(new Curso()));
        Mockito.when(misionDAO.save(Mockito.any())).thenReturn(mision);
        Mockito.when((misionDAO.existsById(mision.getIdMision()))).thenReturn(true);
        Mockito.when(cursoDAO.existsById(misionDTO.getIdCurso())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, ()->{
            misionService.actualizar(mision);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_ID_MISION);
    }
    @Test
    @DisplayName("Deberia fallar id mision no valido")
    void testDeberiaFallarPorIdMisionNoValido() throws Exception{
        MisionDTO misionDTO = new MisionTestDataBuilder().build();
        Mision mision = misionMapper.toEntity(misionDTO);
        Mockito.when(cursoDAO.findById(misionDTO.getIdCurso())).thenReturn(Optional.of(new Curso()));
        Mockito.when(misionDAO.save(Mockito.any())).thenReturn(mision);
        Mockito.when((misionDAO.existsById(mision.getIdMision()))).thenReturn(false);
        Mockito.when(cursoDAO.existsById(misionDTO.getIdCurso())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, ()->{
            misionService.actualizar(mision);
        });
        assertEquals(exception.getMessage(), NO_SE_ENCONTRO_MISION_CON_ID);
    }
    @Test
    @DisplayName("Deberia fallar curso no existe")
    void testDeberiaFallarPorCursoNoExiste() throws Exception{
        MisionDTO misionDTO = new MisionTestDataBuilder().build();
        Mision mision = misionMapper.toEntity(misionDTO);
        Mockito.when(cursoDAO.findById(misionDTO.getIdCurso())).thenReturn(Optional.of(new Curso()));
        Mockito.when(misionDAO.save(Mockito.any())).thenReturn(mision);
        Mockito.when((misionDAO.existsById(mision.getIdMision()))).thenReturn(true);
        Mockito.when(cursoDAO.existsById(misionDTO.getIdCurso())).thenReturn(false);
        Exception exception = assertThrows(Exception.class, ()->{
            misionService.actualizar(mision);
        });
        assertEquals(exception.getMessage(), NO_EXISTE_UN_CURSO_ID);
    }
    @Test
    @DisplayName("Deberia fallar curso no valido")
    void testDeberiaFallarPorCursoNoValido() throws Exception{
        MisionDTO misionDTO = new MisionTestDataBuilder().conIdCurso(-1L).build();
        Mision mision = misionMapper.toEntity(misionDTO);
        Mockito.when(cursoDAO.findById(misionDTO.getIdCurso())).thenReturn(Optional.of(new Curso()));
        Mockito.when(misionDAO.save(Mockito.any())).thenReturn(mision);
        Mockito.when((misionDAO.existsById(mision.getIdMision()))).thenReturn(true);
        Mockito.when(cursoDAO.existsById(misionDTO.getIdCurso())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, ()->{
            misionService.actualizar(mision);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UN_ID_CURSO_VALIDO);
    }
    @Test
    @DisplayName("Deberia fallar fecha futura")
    void testDeberiaFallarPorFechaFutura() throws Exception{
        MisionDTO misionDTO = new MisionTestDataBuilder().conFechaModificacion(FECHA_FUTURA).build();
        Mision mision = misionMapper.toEntity(misionDTO);
        Mockito.when(cursoDAO.findById(misionDTO.getIdCurso())).thenReturn(Optional.of(new Curso()));
        Mockito.when(misionDAO.save(Mockito.any())).thenReturn(mision);
        Mockito.when((misionDAO.existsById(mision.getIdMision()))).thenReturn(true);
        Mockito.when(cursoDAO.existsById(misionDTO.getIdCurso())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, ()->{
            misionService.actualizar(mision);
        });
        assertEquals(exception.getMessage(), NO_FECHA_QUE_NO_HA_SUCEDIDO);
    }
}
