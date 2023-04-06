package com.example.demo.serviceImpl.curso.consultas;

import com.example.demo.dao.CursoDAO;
import com.example.demo.dao.EstadoDAO;
import com.example.demo.dao.ProfesorDAO;
import com.example.demo.mapper.CursoMapper;
import com.example.demo.model.Curso;
import com.example.demo.model.Estado;
import com.example.demo.model.Profesor;
import com.example.demo.model.dto.CursoDTO;
import com.example.demo.service.CursoService;
import com.example.demo.serviceImpl.curso.testDataBuilder.CursoTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@SpringBootTest
public class ManejadorCrearCursoTest {

    private static final String CREACION_EXITOSA_CURSO= "Se creo exitosamente el curso.";
    private static final String DEBE_INGRESAR_EL_NOMBRE_DEL_CURSO = "Debe ingresar el nombre del curso.";
    private static final String EL_NOMBRE_DEL_CURSO_ES_MUY_LARGO_SOLO_SE_ACEPTA_50_CARACTERES = "El nombre del curso es muy largo, solo se aceptan 50 caracteres.";
    private static final String SE_DEBE_INGRESAR_UNA_CONTRASENNA_PARA_EL_CURSO = "Se debe ingresar una contraseña para el curso.";
    private static final String LA_CONTRASENNA_ES_MUY_LARGA_SOLO_SE_ACEPTAN_50_CARACTERES = "La contraseña es muy larga, solo se aceptan 50 caracteres.";
    private static final String SE_DEBE_INGRESAR_LA_CANTIDAD_DE_ESTUDIANTES_QUE_TENDRAN_ACCESO_AL_CURSO = "Se debe ingresar la cantidad de estudiantes que tendrán acceso al curso.";
    private static final String AL_CREAR_EL_CURSO_SE_CREA_CON_EL_PROGRESO_EN_EL0_PORCENTAJE = "Al crear el curso se crea con el progreso en el 0%.";
    private static final String DEBE_INGRESAR_UNA_FECHA_DE_INICI_DEL_CURSO = "Debe ingresar una fecha de inicio del curso.";
    private static final String DIGITE_UNA_FECHA_DE_INICIO_DEL_CURSO_VALIDA = "Digite una fecha de inicio del curso válida.";
    private static final String DEBE_INGRESAR_UN_ID_ESTADO = "Debe ingresar un id estado.";
    private static final String DEBE_INGRESAR_UN_ID_PROFESOR = "Debe ingresar un id del profesor.";
    private static final String DEBE_INGRESAR_UN_ID_ESTADO_VALIDO = "Debe ingresar un id estado válido.";
    private static final String DEBE_INGRESAR_UN_ID_DEL_PROFESOR_VALIDO = "Debe ingresar un id del profesor válido.";
    private static final String EL_ESTADO_QUE_INGRESO_NO_ES_VALIDO = "El estado que ingreso no es válido.";
    private static final String EL_PROFESOR_QUE_INGRESO_NO_ES_VALIDO = "El profesor que ingreso no es válido.";
    private static final String DEBE_INGRESAR_EL_USUARIO_CREADOR = "Debe ingresar el usuario creador.";
    private static final String EL_NOMBRE_DEL_USUARIO_CREADOR_ES_MUY_LARGO_SOLO_SE_PUEDE_CONTENER_50_CARACTERES = "El nombre del usuario creador es muy largo, solo puede contener 50 caracteres.";
    private static final String DEBE_INGRESAR_UNA_FECHA_DE_CREACION = "Debe ingresar una fecha de creación.";
    private static final String NOMBRE_MALO = "QWKJD DJK CKCJWK WC CKJEWK C ECJ WJC JJEWC K WJ WW KW ";
    private static final String PASSWORD = "JEWFUWE DC WC WC WCHWK CHWKCWH  HWK HWHJ WCH63862E2Y ED";
    private static final Integer CANTIDAD_ESTUDIANTES_MALO = -20;
    private static final String USUARIO_CREADOR = "EWGDWJEFG WEGFJ FJW VCWJEGVJGW FCWGJKHJDH KWHEEWH8763";

    @Autowired
    CursoService cursoService;
    @Autowired
    CursoMapper cursoMapper;
    @MockBean
    private CursoDAO cursoDAO;
    @MockBean
    private ProfesorDAO profesorDAO;
    @MockBean
    private EstadoDAO estadoDAO;

    @Test
    @DisplayName("Deberia crear un curso correctamente")
    void deberiaCrearExitosamenteCurso()throws Exception{
        CursoDTO cursoDTO = new CursoTestDataBuilder().build();
        Curso curso = cursoMapper.toEntity(cursoDTO);
        Mockito.when(estadoDAO.existsById(cursoDTO.getIdEstado())).thenReturn(true);
        Mockito.when(profesorDAO.existsById(cursoDTO.getIdProfesor())).thenReturn(true);
        String crearCurso = cursoService.registrar(curso);
        Mockito.when(cursoDAO.save(Mockito.any())).thenReturn(curso);
        assertEquals(CREACION_EXITOSA_CURSO, crearCurso);
    }
    @Test
    @DisplayName("Deberia fallar por nombre null")
    void deberiaFallarPorNombreNull()throws Exception{
        CursoDTO cursoDTO = new CursoTestDataBuilder().conNombre(null).build();
        Curso curso = cursoMapper.toEntity(cursoDTO);
        Mockito.when(cursoDAO.save(Mockito.any())).thenReturn(curso);
        Exception exception = assertThrows(Exception.class, ()->{
            cursoService.registrar(curso);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_EL_NOMBRE_DEL_CURSO);
    }
    @Test
    @DisplayName("Deberia fallar por nombre vacio")
    void deberiaFallarPorNombreVacio()throws Exception{
        CursoDTO cursoDTO = new CursoTestDataBuilder().conNombre("").build();
        Curso curso = cursoMapper.toEntity(cursoDTO);
        Mockito.when(cursoDAO.save(Mockito.any())).thenReturn(curso);
        Exception exception = assertThrows(Exception.class, ()->{
            cursoService.registrar(curso);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_EL_NOMBRE_DEL_CURSO);
    }
    @Test
    @DisplayName("Deberia fallar por nombre largo")
    void deberiaFallarPorNombreLargo()throws Exception{
        CursoDTO cursoDTO = new CursoTestDataBuilder().conNombre(NOMBRE_MALO).build();
        Curso curso = cursoMapper.toEntity(cursoDTO);
        Mockito.when(cursoDAO.save(Mockito.any())).thenReturn(curso);
        Exception exception = assertThrows(Exception.class, ()->{
            cursoService.registrar(curso);
        });
        assertEquals(exception.getMessage(), EL_NOMBRE_DEL_CURSO_ES_MUY_LARGO_SOLO_SE_ACEPTA_50_CARACTERES);
    }

    @Test
    @DisplayName("Deberia fallar por password null")
    void deberiaFallarPorPasswordNull()throws Exception{
        CursoDTO cursoDTO = new CursoTestDataBuilder().conPassword(null).build();
        Curso curso = cursoMapper.toEntity(cursoDTO);
        Mockito.when(cursoDAO.save(Mockito.any())).thenReturn(curso);
        Exception exception = assertThrows(Exception.class, ()->{
            cursoService.registrar(curso);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UNA_CONTRASENNA_PARA_EL_CURSO);
    }

    @Test
    @DisplayName("Deberia fallar por password vacio")
    void deberiaFallarPorPasswordVacio()throws Exception{
        CursoDTO cursoDTO = new CursoTestDataBuilder().conPassword("").build();
        Curso curso = cursoMapper.toEntity(cursoDTO);
        Mockito.when(cursoDAO.save(Mockito.any())).thenReturn(curso);
        Exception exception = assertThrows(Exception.class, ()->{
            cursoService.registrar(curso);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UNA_CONTRASENNA_PARA_EL_CURSO);
    }

    @Test
    @DisplayName("Deberia fallar por password largo")
    void deberiaFallarPorPasswordLargo()throws Exception{
        CursoDTO cursoDTO = new CursoTestDataBuilder().conPassword(PASSWORD).build();
        Curso curso = cursoMapper.toEntity(cursoDTO);
        Mockito.when(cursoDAO.save(Mockito.any())).thenReturn(curso);
        Exception exception = assertThrows(Exception.class, ()->{
            cursoService.registrar(curso);
        });
        assertEquals(exception.getMessage(), LA_CONTRASENNA_ES_MUY_LARGA_SOLO_SE_ACEPTAN_50_CARACTERES);
    }

    @Test
    @DisplayName("Deberia fallar por cantidad estudiantes negativo")
    void deberiaFallarPorCantidadEstudiantesNegativo()throws Exception{
        CursoDTO cursoDTO = new CursoTestDataBuilder().conCantidadEstudiantes(CANTIDAD_ESTUDIANTES_MALO).build();
        Curso curso = cursoMapper.toEntity(cursoDTO);
        Mockito.when(cursoDAO.save(Mockito.any())).thenReturn(curso);
        Exception exception = assertThrows(Exception.class, ()->{
            cursoService.registrar(curso);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_LA_CANTIDAD_DE_ESTUDIANTES_QUE_TENDRAN_ACCESO_AL_CURSO);
    }

    @Test
    @DisplayName("Deberia fallar por progreso diferennte de 0")
    void deberiaFallarPorProgresoDiferenteCero()throws Exception{
        CursoDTO cursoDTO = new CursoTestDataBuilder().conProgreso(12).build();
        Curso curso = cursoMapper.toEntity(cursoDTO);
        Mockito.when(profesorDAO.existsById(cursoDTO.getIdProfesor())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(cursoDTO.getIdEstado())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, ()->{
            cursoService.registrar(curso);
        });
        assertEquals(exception.getMessage(), AL_CREAR_EL_CURSO_SE_CREA_CON_EL_PROGRESO_EN_EL0_PORCENTAJE);
    }

    @Test
    @DisplayName("Deberia fallar por inicio curso null")
    void deberiaFallarPorInicioCursoNull()throws Exception{
        CursoDTO cursoDTO = new CursoTestDataBuilder().conInicioCurso(null).build();
        Curso curso = cursoMapper.toEntity(cursoDTO);
        Mockito.when(cursoDAO.save(Mockito.any())).thenReturn(curso);
        Exception exception = assertThrows(Exception.class, ()->{
            cursoService.registrar(curso);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UNA_FECHA_DE_INICI_DEL_CURSO);
    }


    @Test
    @DisplayName("Deberia fallar por esatdo null")
    void deberiaFallarPorEstadoNull()throws Exception{
        CursoDTO cursoDTO = new CursoTestDataBuilder().conIdEstado(null).build();
        Curso curso = cursoMapper.toEntity(cursoDTO);
        Mockito.when(cursoDAO.save(Mockito.any())).thenReturn(curso);
        Exception exception = assertThrows(Exception.class, ()->{
            cursoService.registrar(curso);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UN_ID_ESTADO);
    }

    @Test
    @DisplayName("Deberia fallar por profesor null")
    void deberiaFallarPorProfesorNull()throws Exception{
        CursoDTO cursoDTO = new CursoTestDataBuilder().conIdProfesor(null).build();
        Curso curso = cursoMapper.toEntity(cursoDTO);
        Mockito.when(cursoDAO.save(Mockito.any())).thenReturn(curso);
        Exception exception = assertThrows(Exception.class, ()->{
            cursoService.registrar(curso);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UN_ID_PROFESOR);
    }

    @Test
    @DisplayName("Deberia fallar por profesor no existe")
    void deberiaFallarPorProfesorNoExiste()throws Exception{
        CursoDTO cursoDTO = new CursoTestDataBuilder().conIdProfesor(7L).build();
        Curso curso = cursoMapper.toEntity(cursoDTO);
        Mockito.when(profesorDAO.existsById(cursoDTO.getIdProfesor())).thenReturn(false);
        Mockito.when(estadoDAO.existsById(cursoDTO.getIdEstado())).thenReturn(true);
        Mockito.when(cursoDAO.save(Mockito.any())).thenReturn(curso);
        Exception exception = assertThrows(Exception.class, ()->{
            cursoService.registrar(curso);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UN_ID_DEL_PROFESOR_VALIDO);
    }

    @Test
    @DisplayName("Deberia fallar por estado no existe")
    void deberiaFallarPorEstadoNoExiste()throws Exception{
        CursoDTO cursoDTO = new CursoTestDataBuilder().conIdEstado(7L).build();
        Curso curso = cursoMapper.toEntity(cursoDTO);
        Mockito.when(estadoDAO.existsById(cursoDTO.getIdEstado())).thenReturn(false);
        Mockito.when(profesorDAO.existsById(cursoDTO.getIdProfesor())).thenReturn(true);
        Mockito.when(cursoDAO.save(Mockito.any())).thenReturn(curso);
        Exception exception = assertThrows(Exception.class, ()->{
            cursoService.registrar(curso);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UN_ID_ESTADO_VALIDO);
    }
    @Test
    @DisplayName("Deberia fallar por usuario creador null")
    void deberiaFallarPorUusarioCreadorNull()throws Exception{
        CursoDTO cursoDTO = new CursoTestDataBuilder().conUsuarioCreador(null).build();
        Curso curso = cursoMapper.toEntity(cursoDTO);
        Mockito.when(estadoDAO.existsById(cursoDTO.getIdEstado())).thenReturn(true);
        Mockito.when(profesorDAO.existsById(cursoDTO.getIdProfesor())).thenReturn(true);
        Mockito.when(cursoDAO.save(Mockito.any())).thenReturn(curso);
        Exception exception = assertThrows(Exception.class, ()->{
            cursoService.registrar(curso);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_EL_USUARIO_CREADOR);
    }
    @Test
    @DisplayName("Deberia fallar por usuario creador vacio")
    void deberiaFallarPorUusarioCreadorVacio()throws Exception{
        CursoDTO cursoDTO = new CursoTestDataBuilder().conUsuarioCreador("").build();
        Curso curso = cursoMapper.toEntity(cursoDTO);
        Mockito.when(estadoDAO.existsById(cursoDTO.getIdEstado())).thenReturn(true);
        Mockito.when(profesorDAO.existsById(cursoDTO.getIdProfesor())).thenReturn(true);
        Mockito.when(cursoDAO.save(Mockito.any())).thenReturn(curso);
        Exception exception = assertThrows(Exception.class, ()->{
            cursoService.registrar(curso);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_EL_USUARIO_CREADOR);
    }

    @Test
    @DisplayName("Deberia fallar por usuario creador largo")
    void deberiaFallarPorUusarioCreadorLargo()throws Exception{
        CursoDTO cursoDTO = new CursoTestDataBuilder().conUsuarioCreador(USUARIO_CREADOR).build();
        Curso curso = cursoMapper.toEntity(cursoDTO);
        Mockito.when(estadoDAO.existsById(cursoDTO.getIdEstado())).thenReturn(true);
        Mockito.when(profesorDAO.existsById(cursoDTO.getIdProfesor())).thenReturn(true);
        Mockito.when(cursoDAO.save(Mockito.any())).thenReturn(curso);
        Exception exception = assertThrows(Exception.class, ()->{
            cursoService.registrar(curso);
        });
        assertEquals(exception.getMessage(), EL_NOMBRE_DEL_USUARIO_CREADOR_ES_MUY_LARGO_SOLO_SE_PUEDE_CONTENER_50_CARACTERES);
    }

    @Test
    @DisplayName("Deberia fallar por fecha creacion null")
    void deberiaFallarPorFechaCreacionNull()throws Exception{
        CursoDTO cursoDTO = new CursoTestDataBuilder().conFechaCreacion(null).build();
        Curso curso = cursoMapper.toEntity(cursoDTO);
        Mockito.when(estadoDAO.existsById(cursoDTO.getIdEstado())).thenReturn(true);
        Mockito.when(profesorDAO.existsById(cursoDTO.getIdProfesor())).thenReturn(true);
        Mockito.when(cursoDAO.save(Mockito.any())).thenReturn(curso);
        Exception exception = assertThrows(Exception.class, ()->{
            cursoService.registrar(curso);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UNA_FECHA_DE_CREACION);
    }


}
