package com.example.demo.serviceImpl.curso.consultas;

import com.example.demo.dao.CursoDAO;
import com.example.demo.dao.EstadoDAO;
import com.example.demo.dao.ProfesorDAO;
import com.example.demo.mapper.CursoMapper;
import com.example.demo.model.Curso;
import com.example.demo.model.dto.CursoDTO;
import com.example.demo.service.CursoService;
import com.example.demo.serviceImpl.curso.testDataBuilder.CursoTestDataBuilder;
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
public class ManejadorActualizarCursoTest {
    private static final String SE_ACTUALIZO_CURSO= "Se actualizo el curso.";
    private static final String DEBE_INGRESAR_EL_ID_DEL_CURSO_QUE_DESEA_ACTUALIZAR = "Se debe ingresar el id del curso que desea actualizar.";
    private static final String NO_SE_ENCONTRO_EL_CURSO_CON_ESE_ID = "No se encontró el curso con ese id.";
    private static final String NO_SE_ENCONTRO_EL_ESTADO_CON_ESE_ID = "No se encontró el estado con ese id.";
    private static final String NO_SE_ENCONTRO_EL_PROFESOR_CON_ESE_ID = "No se encontró el profesor con ese id.";
    private static final String DEBE_INGRESAR_EL_NOMBRE_DEL_CURSO = "Debe ingresar el nombre del curso.";
    private static final String EL_NOMBRE_DEL_CURSO_ES_MUY_LARGO_SOLO_SE_ACEPTA_50_CARACTERES = "El nombre del curso es muy largo, solo se aceptan 50 caracteres.";
    private static final String SE_DEBE_INGRESAR_UNA_CONTRASENNA_PARA_EL_CURSO = "Se debe ingresar una contraseña para el curso.";
    private static final String LA_CONTRASENNA_ES_MUY_LARGA_SOLO_SE_ACEPTAN_50_CARACTERES = "La contraseña es muy larga, solo se aceptan 50 caracteres.";
    private static final String SE_DEBE_INGRESAR_LA_CANTIDAD_DE_ESTUDIANTES_QUE_TENDRAN_ACCESO_AL_CURSO = "Se debe ingresar la cantidad de estudiantes que tendrán acceso al curso.";
    private static final String AL_CREAR_EL_CURSO_SE_CREA_CON_EL_PROGRESO_EN_EL0_PORCENTAJE = "Al crear el curso se crea con el progreso en el 0%.";
    private static final String DEBE_INGRESAR_UNA_FECHA_DE_INICI_DEL_CURSO = "Debe ingresar una fecha de inicio del curso.";
    private static final String DEBE_INGRESAR_UN_ID_ESTADO = "Debe ingresar un id estado.";
    private static final String DEBE_INGRESAR_UN_ID_PROFESOR = "Debe ingresar un id del profesor.";
    private static final String DEBE_INGRESAR_EL_USUARIO_MODIFICADOR = "Debe ingresar el usuario modificador.";
    private static final String EL_NOMBRE_DEL_USUARIO_MODIFICADOR_ES_MUY_LARGO_SOLO_SE_PUEDE_CONTENER_50_CARACTERES = "El nombre del usuario modificador es muy largo, solo puede contener 50 caracteres.";
    private static final String DEBE_INGRESAR_UNA_FECHA_DE_MODIFICACION = "Debe ingresar una fecha de modificación.";
    private static final String NOMBRE_MALO = "QWKJD DJK CKCJWK WC CKJEWK C ECJ WJC JJEWC K WJ WW KW ";
    private static final String NOMBRE = "Redes";
    private static final String PASSWORD = "JEWFUWE DC WC WC WCHWK CHWKCWH  HWK HWHJ WCH63862E2Y ED";
    private static final Integer CANTIDAD_ESTUDIANTES_MALO = -20;
    private static final String USUARIO_MODIFICADOR = "EWGDWJEFG WEGFJ FJW VCWJEGVJGW FCWGJKHJDH KWHEEWH8763";

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
    @DisplayName("Deberia actualizar un curso correctamente")
    void deberiaActualizarExitosamenteCurso()throws Exception{
        CursoDTO cursoDTO = new CursoTestDataBuilder()
                .conNombre(NOMBRE)
                .conIdProfesor(23l)
                .conCantidadEstudiantes(20)
                .conInicioCurso(new Date())
                .conPassword("1234")
                .conProgreso(0)
                .conFinCurso(new Date(2023,9,12))
                .conUsuarioCreador("Angela")
                .conFechaCreacion(new Date())
                .build();
        Curso curso = cursoMapper.toEntity(cursoDTO);
        Mockito.when(cursoDAO.existsById(curso.getIdCurso())).thenReturn(true);
        Mockito.when(cursoDAO.findById(cursoDTO.getIdCurso())).thenReturn(Optional.of(curso));
        Mockito.when(estadoDAO.existsById(cursoDTO.getIdEstado())).thenReturn(true);
        Mockito.when(profesorDAO.existsById(cursoDTO.getIdProfesor())).thenReturn(true);
        Mockito.when(cursoDAO.save(Mockito.any())).thenReturn(curso);
        String crearCurso = cursoService.actualizar(cursoDTO);
        assertEquals(SE_ACTUALIZO_CURSO, crearCurso);
    }
    @Test
    @DisplayName("Deberia fallar por curso null")
    void deberiaFallarPorCursoNull()throws Exception{
        CursoDTO cursoDTO = new CursoTestDataBuilder().conIdCurso(null).build();
        Curso curso = cursoMapper.toEntity(cursoDTO);
        Mockito.when(cursoDAO.existsById(curso.getIdCurso())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(curso.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(profesorDAO.existsById(curso.getProfesor().getIdProfesor())).thenReturn(true);
        Mockito.when(cursoDAO.save(Mockito.any())).thenReturn(curso);
        Exception exception = assertThrows(Exception.class, ()->{
            cursoService.actualizar(cursoDTO);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_EL_ID_DEL_CURSO_QUE_DESEA_ACTUALIZAR);
    }

    @Test
    @DisplayName("Deberia fallar por curso no existe")
    void deberiaFallarPorCursoNoExiste()throws Exception{
        CursoDTO cursoDTO = new CursoTestDataBuilder().conIdCurso(852L).build();
        Curso curso = cursoMapper.toEntity(cursoDTO);
        Mockito.when(cursoDAO.existsById(curso.getIdCurso())).thenReturn(false);
        Mockito.when(estadoDAO.existsById(curso.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(profesorDAO.existsById(curso.getProfesor().getIdProfesor())).thenReturn(true);
        Mockito.when(cursoDAO.save(Mockito.any())).thenReturn(curso);
        Exception exception = assertThrows(Exception.class, ()->{
            cursoService.actualizar(cursoDTO);
        });
        assertEquals(exception.getMessage(), NO_SE_ENCONTRO_EL_CURSO_CON_ESE_ID);
    }


    @Test
    @DisplayName("Deberia fallar por nombre null")
    void deberiaFallarPorNombreNull()throws Exception{
        CursoDTO cursoDTO = new CursoTestDataBuilder().conNombre(null).build();
        Curso curso = cursoMapper.toEntity(cursoDTO);
        Mockito.when(cursoDAO.existsById(curso.getIdCurso())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(curso.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(profesorDAO.existsById(curso.getProfesor().getIdProfesor())).thenReturn(true);
        Mockito.when(cursoDAO.save(Mockito.any())).thenReturn(curso);
        Exception exception = assertThrows(Exception.class, ()->{
            cursoService.actualizar(cursoDTO);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_EL_NOMBRE_DEL_CURSO);
    }
    @Test
    @DisplayName("Deberia fallar por nombre vacio")
    void deberiaFallarPorNombreVacio()throws Exception{
        CursoDTO cursoDTO = new CursoTestDataBuilder().conNombre("").build();
        Curso curso = cursoMapper.toEntity(cursoDTO);
        Mockito.when(cursoDAO.existsById(curso.getIdCurso())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(curso.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(profesorDAO.existsById(curso.getProfesor().getIdProfesor())).thenReturn(true);
        Mockito.when(cursoDAO.save(Mockito.any())).thenReturn(curso);
        Exception exception = assertThrows(Exception.class, ()->{
            cursoService.actualizar(cursoDTO);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_EL_NOMBRE_DEL_CURSO);
    }
    @Test
    @DisplayName("Deberia fallar por nombre largo")
    void deberiaFallarPorNombreLargo()throws Exception{
        CursoDTO cursoDTO = new CursoTestDataBuilder().conNombre(NOMBRE_MALO).build();
        Curso curso = cursoMapper.toEntity(cursoDTO);
        Mockito.when(cursoDAO.existsById(curso.getIdCurso())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(curso.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(profesorDAO.existsById(curso.getProfesor().getIdProfesor())).thenReturn(true);
        Mockito.when(cursoDAO.save(Mockito.any())).thenReturn(curso);
        Exception exception = assertThrows(Exception.class, ()->{
            cursoService.actualizar(cursoDTO);
        });
        assertEquals(exception.getMessage(), EL_NOMBRE_DEL_CURSO_ES_MUY_LARGO_SOLO_SE_ACEPTA_50_CARACTERES);
    }

    @Test
    @DisplayName("Deberia fallar por password null")
    void deberiaFallarPorPasswordNull()throws Exception{
        CursoDTO cursoDTO = new CursoTestDataBuilder().conPassword(null).build();
        Curso curso = cursoMapper.toEntity(cursoDTO);
        Mockito.when(cursoDAO.existsById(curso.getIdCurso())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(curso.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(profesorDAO.existsById(curso.getProfesor().getIdProfesor())).thenReturn(true);
        Mockito.when(cursoDAO.save(Mockito.any())).thenReturn(curso);
        Exception exception = assertThrows(Exception.class, ()->{
            cursoService.actualizar(cursoDTO);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UNA_CONTRASENNA_PARA_EL_CURSO);
    }

    @Test
    @DisplayName("Deberia fallar por password vacio")
    void deberiaFallarPorPasswordVacio()throws Exception{
        CursoDTO cursoDTO = new CursoTestDataBuilder().conPassword("").build();
        Curso curso = cursoMapper.toEntity(cursoDTO);
        Mockito.when(cursoDAO.existsById(curso.getIdCurso())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(curso.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(profesorDAO.existsById(curso.getProfesor().getIdProfesor())).thenReturn(true);
        Mockito.when(cursoDAO.save(Mockito.any())).thenReturn(curso);
        Exception exception = assertThrows(Exception.class, ()->{
            cursoService.actualizar(cursoDTO);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UNA_CONTRASENNA_PARA_EL_CURSO);
    }

    @Test
    @DisplayName("Deberia fallar por password largo")
    void deberiaFallarPorPasswordLargo()throws Exception{
        CursoDTO cursoDTO = new CursoTestDataBuilder().conPassword(PASSWORD).build();
        Curso curso = cursoMapper.toEntity(cursoDTO);
        Mockito.when(cursoDAO.existsById(curso.getIdCurso())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(curso.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(profesorDAO.existsById(curso.getProfesor().getIdProfesor())).thenReturn(true);
        Mockito.when(cursoDAO.save(Mockito.any())).thenReturn(curso);
        Exception exception = assertThrows(Exception.class, ()->{
            cursoService.actualizar(cursoDTO);
        });
        assertEquals(exception.getMessage(), LA_CONTRASENNA_ES_MUY_LARGA_SOLO_SE_ACEPTAN_50_CARACTERES);
    }

    @Test
    @DisplayName("Deberia fallar por cantidad estudiantes negativo")
    void deberiaFallarPorCantidadEstudiantesNegativo()throws Exception{
        CursoDTO cursoDTO = new CursoTestDataBuilder().conCantidadEstudiantes(CANTIDAD_ESTUDIANTES_MALO).build();
        Curso curso = cursoMapper.toEntity(cursoDTO);
        Mockito.when(cursoDAO.existsById(curso.getIdCurso())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(curso.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(profesorDAO.existsById(curso.getProfesor().getIdProfesor())).thenReturn(true);
        Mockito.when(cursoDAO.save(Mockito.any())).thenReturn(curso);
        Exception exception = assertThrows(Exception.class, ()->{
            cursoService.actualizar(cursoDTO);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_LA_CANTIDAD_DE_ESTUDIANTES_QUE_TENDRAN_ACCESO_AL_CURSO);
    }

    @Test
    @DisplayName("Deberia fallar por progreso diferennte de 0")
    void deberiaFallarPorProgresoDiferenteCero()throws Exception{
        CursoDTO cursoDTO = new CursoTestDataBuilder().conProgreso(12).build();
        Curso curso = cursoMapper.toEntity(cursoDTO);
        Mockito.when(cursoDAO.existsById(curso.getIdCurso())).thenReturn(true);
        Mockito.when(profesorDAO.existsById(cursoDTO.getIdProfesor())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(cursoDTO.getIdEstado())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, ()->{
            cursoService.actualizar(cursoDTO);
        });
        assertEquals(exception.getMessage(), AL_CREAR_EL_CURSO_SE_CREA_CON_EL_PROGRESO_EN_EL0_PORCENTAJE);
    }

    @Test
    @DisplayName("Deberia fallar por inicio curso null")
    void deberiaFallarPorInicioCursoNull()throws Exception{
        CursoDTO cursoDTO = new CursoTestDataBuilder().conInicioCurso(null).build();
        Curso curso = cursoMapper.toEntity(cursoDTO);
        Mockito.when(cursoDAO.existsById(curso.getIdCurso())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(curso.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(profesorDAO.existsById(curso.getProfesor().getIdProfesor())).thenReturn(true);
        Mockito.when(cursoDAO.save(Mockito.any())).thenReturn(curso);
        Exception exception = assertThrows(Exception.class, ()->{
            cursoService.actualizar(cursoDTO);
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
            cursoService.actualizar(cursoDTO);
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
            cursoService.actualizar(cursoDTO);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UN_ID_PROFESOR);
    }

    @Test
    @DisplayName("Deberia fallar por profesor no existe")
    void deberiaFallarPorProfesorNoExiste()throws Exception{
        CursoDTO cursoDTO = new CursoTestDataBuilder().conIdProfesor(7L).build();
        Curso curso = cursoMapper.toEntity(cursoDTO);
        Mockito.when(cursoDAO.existsById(curso.getIdCurso())).thenReturn(true);
        Mockito.when(profesorDAO.existsById(cursoDTO.getIdProfesor())).thenReturn(false);
        Mockito.when(estadoDAO.existsById(cursoDTO.getIdEstado())).thenReturn(true);
        Mockito.when(cursoDAO.save(Mockito.any())).thenReturn(curso);
        Exception exception = assertThrows(Exception.class, ()->{
            cursoService.actualizar(cursoDTO);
        });
        assertEquals(exception.getMessage(), NO_SE_ENCONTRO_EL_PROFESOR_CON_ESE_ID);
    }

    @Test
    @DisplayName("Deberia fallar por estado no existe")
    void deberiaFallarPorEstadoNoExiste()throws Exception{
        CursoDTO cursoDTO = new CursoTestDataBuilder().conIdEstado(7L).build();
        Curso curso = cursoMapper.toEntity(cursoDTO);
        Mockito.when(cursoDAO.existsById(curso.getIdCurso())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(cursoDTO.getIdEstado())).thenReturn(false);
        Mockito.when(profesorDAO.existsById(cursoDTO.getIdProfesor())).thenReturn(true);
        Mockito.when(cursoDAO.save(Mockito.any())).thenReturn(curso);
        Exception exception = assertThrows(Exception.class, ()->{
            cursoService.actualizar(cursoDTO);
        });
        assertEquals(exception.getMessage(), NO_SE_ENCONTRO_EL_ESTADO_CON_ESE_ID);
    }
    @Test
    @DisplayName("Deberia fallar por usuario modificador null")
    void deberiaFallarPorUsuarioModificadorNull()throws Exception{
        CursoDTO cursoDTO = new CursoTestDataBuilder().conUsuarioModificador(null).build();
        Curso curso = cursoMapper.toEntity(cursoDTO);
        Mockito.when(cursoDAO.existsById(curso.getIdCurso())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(cursoDTO.getIdEstado())).thenReturn(true);
        Mockito.when(profesorDAO.existsById(cursoDTO.getIdProfesor())).thenReturn(true);
        Mockito.when(cursoDAO.save(Mockito.any())).thenReturn(curso);
        Exception exception = assertThrows(Exception.class, ()->{
            cursoService.actualizar(cursoDTO);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_EL_USUARIO_MODIFICADOR);
    }
    @Test
    @DisplayName("Deberia fallar por usuario modificador vacio")
    void deberiaFallarPorUusarioModificadorVacio()throws Exception{
        CursoDTO cursoDTO = new CursoTestDataBuilder().conUsuarioModificador("").build();
        Curso curso = cursoMapper.toEntity(cursoDTO);
        Mockito.when(cursoDAO.existsById(curso.getIdCurso())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(cursoDTO.getIdEstado())).thenReturn(true);
        Mockito.when(profesorDAO.existsById(cursoDTO.getIdProfesor())).thenReturn(true);
        Mockito.when(cursoDAO.save(Mockito.any())).thenReturn(curso);
        Exception exception = assertThrows(Exception.class, ()->{
            cursoService.actualizar(cursoDTO);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_EL_USUARIO_MODIFICADOR);
    }

    @Test
    @DisplayName("Deberia fallar por usuario modificador largo")
    void deberiaFallarPorUusarioCreadorLargo()throws Exception{
        CursoDTO cursoDTO = new CursoTestDataBuilder().conUsuarioModificador(USUARIO_MODIFICADOR).build();
        Curso curso = cursoMapper.toEntity(cursoDTO);
        Mockito.when(cursoDAO.existsById(curso.getIdCurso())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(cursoDTO.getIdEstado())).thenReturn(true);
        Mockito.when(profesorDAO.existsById(cursoDTO.getIdProfesor())).thenReturn(true);
        Mockito.when(cursoDAO.save(Mockito.any())).thenReturn(curso);
        Exception exception = assertThrows(Exception.class, ()->{
            cursoService.actualizar(cursoDTO);
        });
        assertEquals(exception.getMessage(), EL_NOMBRE_DEL_USUARIO_MODIFICADOR_ES_MUY_LARGO_SOLO_SE_PUEDE_CONTENER_50_CARACTERES);
    }

    @Test
    @DisplayName("Deberia fallar por fecha modificacion null")
    void deberiaFallarPorFechaModificacionNull()throws Exception{
        CursoDTO cursoDTO = new CursoTestDataBuilder().conFechaModificacion(null).build();
        Curso curso = cursoMapper.toEntity(cursoDTO);
        Mockito.when(cursoDAO.existsById(curso.getIdCurso())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(cursoDTO.getIdEstado())).thenReturn(true);
        Mockito.when(profesorDAO.existsById(cursoDTO.getIdProfesor())).thenReturn(true);
        Mockito.when(cursoDAO.save(Mockito.any())).thenReturn(curso);
        Exception exception = assertThrows(Exception.class, ()->{
            cursoService.actualizar(cursoDTO);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UNA_FECHA_DE_MODIFICACION);
    }
}
