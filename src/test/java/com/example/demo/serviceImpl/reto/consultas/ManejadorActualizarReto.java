package com.example.demo.serviceImpl.reto.consultas;

import com.example.demo.dao.CursoDAO;
import com.example.demo.dao.EstadoDAO;
import com.example.demo.dao.MisionDAO;
import com.example.demo.dao.RetoDAO;
import com.example.demo.mapper.RetoMapper;
import com.example.demo.model.Curso;
import com.example.demo.model.Estado;
import com.example.demo.model.Mision;
import com.example.demo.model.Reto;
import com.example.demo.model.dto.RetoDTO;
import com.example.demo.service.RetoService;
import com.example.demo.serviceImpl.reto.testDataBuilder.RetoTestDataBuilder;
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
public class ManejadorActualizarReto {
    public static final String RETO_ACTUALIZADO = "Se actualizo el reto.";
    public static final String INGRESO_NOMBRE_RETO = "Se debe ingresar el nombre del reto.";
    public static final String RETO_NO_EXISTE_CON_ID = "No existe un reto con ese id.";

    public static final String ERROR_LIMITE_CARACTERES_NOMBRE_RETO = "El nombre del reto no debe contener más de 50 caracteres.";
    public static final String INGRESO_DESCRIPCION_RETO = "Se debe ingresar una descripción para el reto.";
    public static final String DESCRIPCION_LIMITE_CARACTERES_RETO = "La descripción del reto no debe superar los 300 caracteres.";
    public static final String INGRSO_DESCRIPCION_TEORIA = "Se debe ingresar una descripción de la teoría referente al reto.";
    public static final String LIMITE_CARACTERES_DESCRIPCION_TEORIA = "La descripción de la teoría no debe superar los 1000 caracteres.";
    public static final String REQUERIMIENTO_MINIMO_INTENTOS_RETO = "El reto debe tener como mínimo 1 intento.";
    public static final String ASIGNACION_MONEDAS_RETO = "Debe asignarle una cantidad de monedas al reto.";
    public static final String INGRESO_SOLUCION_RETO = "Debe ingresar una solucion al reto.";
    public static final String LIMITE_CARACTERES_SOLUCION_RETO = "La solución no debe superar los 800 caracteres.";
    public static final String URL_IMAGEN_1_LARGA = "La Url de la imagen 1 es muy larga.";
    public static final String URL_IMAGEN_2_LARGA = "La Url de la imagen 2 es muy larga.";
    public static final String URL_VIDEO_1_LARGA = "La Url del video 1 es muy largo.";
    public static final String URL_VIDEO_2_LARGA = "La Url del video 2 es muy largo.";
    public static final String INGRESO_ID_ESTADO = "Se debe ingresar un id estado.";
    public static final String INGRESO_ID_ESTADO_VALIDO = "Se debe ingresar un id estado válido.";
    public static final String INGRESO_ID_CURSO = "Se debe ingresar un id curso.";
    public static final String INGRESO_ID_CURSO_VALIDO = "Se debe ingresar un id curso válido.";
    public static final String INGRESO_ID_MISION = "Se debe ingresar un id misión.";
    public static final String INGRESO_ID_MISION_VALIDO = "Se debe ingresar un id misión válido.";
    public static final String INGRESO_USUARIO_MODIFICADOR = "Debe ingresar el usuario modificador.";
    public static final String LIMITE_CARACTERES_USUARIO_MODIFICADOR = "El nombre del usuario creador es muy largo, solo puede contener 50 caracteres.";
    public static final String INGRESO_FECHA_MODIFICACION = "Debe ingresar una fecha de modificación.";
    public static final String FECHA_LIMITE_INVALIDA = "La fecha límite no puede ser menor que la fecha de inicio del reto.";
    public static final String INGRESO_ID_RETO_ACTUALIZAR = "Se debe ingresar el id del reto que se va a actualizar.";


    private static final String TEXTO_MAS_CINCUENTA_CARACTERES = "DGSDGSDSDAGSFDGDFGSDFFGDFSGDFGDFGDFGDFGDFGDFGDSGDFDVSDDSBDSBSDBSVDVSDVSDVSDVSDV";
    private static final String TEXTO_MAS_TRECIOENTOS_CARACTERES = "DGSDGSDSDAGSFGEWRHERHTRHRTJJRHERGWGLFGHEÑGHJERÑHEJR{HJERÑLHKERLKGEJFÑLDSAJFLÑKSDJGDKÑLFGJDFLÑGJSAFDGJAEPRGJEROIGJEOIRHREJGOERJGÑLKERWJGÑLEWRJGÑLEGJÑELRGJLERWGJLERJGLWERJGLEGJELJGÑLEJGLERGGFLKGJASFDGLKJSKLGDJFGLSDJHGKJFHGKDASSKJDLGSAOFGIAOGSAFHASÑFHÑSFLGLÑHWEHEHRHERHERHERHERHEWRHERHEWHERHEWRHERHERHERHRETYKTYKDGDFGSDFFGDFSGDFGDFGDFGDFGDFGDFGDSGDFDVSDDSBDSBSDBSVDVSDVSDVSDVSDV";
    private static final String TEXTO_MAS_MIL_CARACTERES = "LaigualdadderesultadodeunasumadeesfuerzosindividualescontinuosysostenidosdemanerapersistenteeneltiempoLapersistenciaesclavedelexitoyelexitoessumadetalentoytrabajoLadisciplinaeselbrilloqueconviertelostalentosenjoyasLamotivacionesloqueempezarlahabitoesloquecompletalatrayectoriaSiquierescambiaralgunacosaempiezaporcambiartusactitudesyconviccionesCambiatumentalidadyelmundoseabriranuevas" +
            "puertasCadaunotienedentrodesisuficientecapacidadyfortalezaparalograrcualquiermetaquetepropongasLacreatividadyelconocimientosonarmasinfaliblesenlaluchaporloquequiereslograrPonfocoyconstanciaynuncaperdasesaesperanzaquemuevetusuenoshacialaaccionLomejorestaporvenirsiempreycuandopongastodoennuestrasmetasynofrenesalprimerobstaculoquesetepresente.Laigualdadderesultadodeunasumadeesfuerzosindividualescontinuosysostenidosdemanerapersisten" +
            "teeneltiempoLapersistenciaesclavedelexitoyelexitoessumadetalentoytrabajoLadisciplinaeselbrilloqueconviertelostalentosenjoyasLamotivacionesloqueempezarlahabitoesloquecompletalatrayectoriaSiquierescambiaralgunacosaempiezaporcambiartusactitudesyconviccionesCambiatumentalidadyelmundoseabriranuevaspuertasCadaunotienedentrodesisuficientecapacidadyfortalezaparalograrcualquiermetaquetepropongasLacreatividadyelconocimientosonarmasinfa" +
            "liblesenlaluchaporloquequiereslograrPonfocoyconstanciaynuncaperdasesaesperanzaquemuevetusuenoshacialaaccionLomejorestaporvenirsiempreycuandopongastodoennuestrasmetasynofrenesalprimerobstaculoquesetepresente.";
    private static final String VACIO = "";
    @Autowired
    RetoService retoService;
    @Autowired
    RetoMapper retoMapper;
    @MockBean
    RetoDAO retoDAO;
    @MockBean
    EstadoDAO estadoDAO;
    @MockBean
    CursoDAO cursoDAO;
    @MockBean
    MisionDAO misionDAO;

    @Test
    @DisplayName("Deberia actualizar el reto")
    void deberiaCrearElReto() throws Exception{
        RetoDTO retoDTO = new RetoTestDataBuilder().build();
        Reto reto = retoMapper.toEntity(retoDTO);
        Mockito.when(estadoDAO.findById(retoDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(cursoDAO.findById(retoDTO.getIdCurso())).thenReturn(Optional.of(new Curso()));
        Mockito.when(misionDAO.findById(retoDTO.getIdMision())).thenReturn(Optional.of(new Mision()));
        Mockito.when(retoDAO.existsById(reto.getIdReto())).thenReturn(true);
        Mockito.when(retoDAO.save(Mockito.any())).thenReturn(reto);
        String crearReto = retoService.actualizar(reto);
        assertEquals(RETO_ACTUALIZADO , crearReto);
    }
    @Test
    @DisplayName("Debe fallar idReto nulo")
    void deberiaFallarPorIdRetoNulo() throws Exception{
        RetoDTO retoDTO = new RetoTestDataBuilder().conIdReto(null).build();
        Reto reto = retoMapper.toEntity(retoDTO);
        Mockito.when(estadoDAO.findById(retoDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(cursoDAO.findById(retoDTO.getIdCurso())).thenReturn(Optional.of(new Curso()));
        Mockito.when(misionDAO.findById(retoDTO.getIdMision())).thenReturn(Optional.of(new Mision()));
        Mockito.when(retoDAO.existsById(reto.getIdReto())).thenReturn(true);
        Mockito.when(retoDAO.save(Mockito.any())).thenReturn(reto);
        Exception exception = assertThrows(Exception.class, () ->{
            retoService.actualizar(reto);
        });
        assertEquals(INGRESO_ID_RETO_ACTUALIZAR, exception.getMessage());
    }
    @Test
    @DisplayName("Debe fallar idReto no existe")
    void deberiaFallarPorIdRetoNoExiste() throws Exception{
        RetoDTO retoDTO = new RetoTestDataBuilder().build();
        Reto reto = retoMapper.toEntity(retoDTO);
        Mockito.when(estadoDAO.findById(retoDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(cursoDAO.findById(retoDTO.getIdCurso())).thenReturn(Optional.of(new Curso()));
        Mockito.when(misionDAO.findById(retoDTO.getIdMision())).thenReturn(Optional.of(new Mision()));
        Mockito.when(retoDAO.existsById(reto.getIdReto())).thenReturn(false);
        Mockito.when(retoDAO.save(Mockito.any())).thenReturn(reto);
        Exception exception = assertThrows(Exception.class, () ->{
            retoService.actualizar(reto);
        });
        assertEquals(RETO_NO_EXISTE_CON_ID, exception.getMessage());
    }
    @Test
    @DisplayName("Debe fallar por nombre nulo")
    void deberiaFallarPorNombreNulo() throws Exception{
        RetoDTO retoDTO = new RetoTestDataBuilder().conNombreReto(null).build();
        Reto reto = retoMapper.toEntity(retoDTO);
        Mockito.when(estadoDAO.findById(retoDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(cursoDAO.findById(retoDTO.getIdCurso())).thenReturn(Optional.of(new Curso()));
        Mockito.when(misionDAO.findById(retoDTO.getIdMision())).thenReturn(Optional.of(new Mision()));
        Mockito.when(retoDAO.existsById(reto.getIdReto())).thenReturn(true);
        Mockito.when(retoDAO.save(Mockito.any())).thenReturn(reto);
        Exception exception = assertThrows(Exception.class, () ->{
            retoService.actualizar(reto);
        });
        assertEquals(INGRESO_NOMBRE_RETO, exception.getMessage());
    }
    @Test
    @DisplayName("Debe fallar por nombre vacio")
    void deberiaFallarPorNombreVacio() throws Exception{
        RetoDTO retoDTO = new RetoTestDataBuilder().conNombreReto(VACIO).build();
        Reto reto = retoMapper.toEntity(retoDTO);
        Mockito.when(estadoDAO.findById(retoDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(cursoDAO.findById(retoDTO.getIdCurso())).thenReturn(Optional.of(new Curso()));
        Mockito.when(misionDAO.findById(retoDTO.getIdMision())).thenReturn(Optional.of(new Mision()));
        Mockito.when(retoDAO.existsById(reto.getIdReto())).thenReturn(true);
        Mockito.when(retoDAO.save(Mockito.any())).thenReturn(reto);
        Exception exception = assertThrows(Exception.class, () ->{
            retoService.actualizar(reto);
        });
        assertEquals(INGRESO_NOMBRE_RETO, exception.getMessage());
    }
    @Test
    @DisplayName("Debe fallar por limite de caracteres nombre")
    void deberiaFallarPorLimiteDeCaracteresNombre() throws Exception{
        RetoDTO retoDTO = new RetoTestDataBuilder().conNombreReto(TEXTO_MAS_CINCUENTA_CARACTERES).build();
        Reto reto = retoMapper.toEntity(retoDTO);
        Mockito.when(estadoDAO.findById(retoDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(cursoDAO.findById(retoDTO.getIdCurso())).thenReturn(Optional.of(new Curso()));
        Mockito.when(misionDAO.findById(retoDTO.getIdMision())).thenReturn(Optional.of(new Mision()));
        Mockito.when(retoDAO.existsById(reto.getIdReto())).thenReturn(true);
        Mockito.when(retoDAO.save(Mockito.any())).thenReturn(reto);
        Exception exception = assertThrows(Exception.class, () ->{
            retoService.actualizar(reto);
        });
        assertEquals(ERROR_LIMITE_CARACTERES_NOMBRE_RETO, exception.getMessage());
    }
    @Test
    @DisplayName("Debe fallar por descripcion del reto nula")
    void deberiaFallarPorDescripcionDelRetoNula() throws Exception{
        RetoDTO retoDTO = new RetoTestDataBuilder().conDescripcion(null).build();
        Reto reto = retoMapper.toEntity(retoDTO);
        Mockito.when(estadoDAO.findById(retoDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(cursoDAO.findById(retoDTO.getIdCurso())).thenReturn(Optional.of(new Curso()));
        Mockito.when(misionDAO.findById(retoDTO.getIdMision())).thenReturn(Optional.of(new Mision()));
        Mockito.when(retoDAO.existsById(reto.getIdReto())).thenReturn(true);
        Mockito.when(retoDAO.save(Mockito.any())).thenReturn(reto);
        Exception exception = assertThrows(Exception.class, () ->{
            retoService.actualizar(reto);
        });
        assertEquals(INGRESO_DESCRIPCION_RETO, exception.getMessage());
    }
    @Test
    @DisplayName("Debe fallar por descripcion del reto vacia")
    void deberiaFallarPorDescripcionDelRetoVacia() throws Exception{
        RetoDTO retoDTO = new RetoTestDataBuilder().conDescripcion(VACIO).build();
        Reto reto = retoMapper.toEntity(retoDTO);
        Mockito.when(estadoDAO.findById(retoDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(cursoDAO.findById(retoDTO.getIdCurso())).thenReturn(Optional.of(new Curso()));
        Mockito.when(misionDAO.findById(retoDTO.getIdMision())).thenReturn(Optional.of(new Mision()));
        Mockito.when(retoDAO.existsById(reto.getIdReto())).thenReturn(true);
        Mockito.when(retoDAO.save(Mockito.any())).thenReturn(reto);
        Exception exception = assertThrows(Exception.class, () ->{
            retoService.actualizar(reto);
        });
        assertEquals(INGRESO_DESCRIPCION_RETO, exception.getMessage());
    }
    @Test
    @DisplayName("Debe fallar descripcion mayor a 300 caracteres")
    void deberiaFallarPorDescripcionMuchosCaracteres() throws Exception{
        RetoDTO retoDTO = new RetoTestDataBuilder().conDescripcion(TEXTO_MAS_TRECIOENTOS_CARACTERES).build();
        Reto reto = retoMapper.toEntity(retoDTO);
        Mockito.when(estadoDAO.findById(retoDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(cursoDAO.findById(retoDTO.getIdCurso())).thenReturn(Optional.of(new Curso()));
        Mockito.when(misionDAO.findById(retoDTO.getIdMision())).thenReturn(Optional.of(new Mision()));
        Mockito.when(retoDAO.existsById(reto.getIdReto())).thenReturn(true);
        Mockito.when(retoDAO.save(Mockito.any())).thenReturn(reto);
        Exception exception = assertThrows(Exception.class, () ->{
            retoService.actualizar(reto);
        });
        assertEquals(DESCRIPCION_LIMITE_CARACTERES_RETO , exception.getMessage());
    }
    @Test
    @DisplayName("Debe fallar descripcion teoria nula")
    void deberiaFallarPorDescripcionTeoriaNula() throws Exception{
        RetoDTO retoDTO = new RetoTestDataBuilder().conDescripcionTeoria(null).build();
        Reto reto = retoMapper.toEntity(retoDTO);
        Mockito.when(estadoDAO.findById(retoDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(cursoDAO.findById(retoDTO.getIdCurso())).thenReturn(Optional.of(new Curso()));
        Mockito.when(misionDAO.findById(retoDTO.getIdMision())).thenReturn(Optional.of(new Mision()));
        Mockito.when(retoDAO.existsById(reto.getIdReto())).thenReturn(true);
        Mockito.when(retoDAO.save(Mockito.any())).thenReturn(reto);
        Exception exception = assertThrows(Exception.class, () ->{
            retoService.actualizar(reto);
        });
        assertEquals(INGRSO_DESCRIPCION_TEORIA , exception.getMessage());
    }
    @Test
    @DisplayName("Debe fallar descripcion teoria vacia")
    void deberiaFallarPorDescripcionTeoriaVacia() throws Exception{
        RetoDTO retoDTO = new RetoTestDataBuilder().conDescripcionTeoria(VACIO).build();
        Reto reto = retoMapper.toEntity(retoDTO);
        Mockito.when(estadoDAO.findById(retoDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(cursoDAO.findById(retoDTO.getIdCurso())).thenReturn(Optional.of(new Curso()));
        Mockito.when(misionDAO.findById(retoDTO.getIdMision())).thenReturn(Optional.of(new Mision()));
        Mockito.when(retoDAO.existsById(reto.getIdReto())).thenReturn(true);
        Mockito.when(retoDAO.save(Mockito.any())).thenReturn(reto);
        Exception exception = assertThrows(Exception.class, () ->{
            retoService.actualizar(reto);
        });
        assertEquals(INGRSO_DESCRIPCION_TEORIA , exception.getMessage());
    }
    @Test
    @DisplayName("Debe fallar descripcion mayor a mil caracteres")
    void deberiaFallarPorDescripcionTeoriaMuyGrande() throws Exception{
        RetoDTO retoDTO = new RetoTestDataBuilder().conDescripcionTeoria(TEXTO_MAS_MIL_CARACTERES).build();
        Reto reto = retoMapper.toEntity(retoDTO);
        Mockito.when(estadoDAO.findById(retoDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(cursoDAO.findById(retoDTO.getIdCurso())).thenReturn(Optional.of(new Curso()));
        Mockito.when(misionDAO.findById(retoDTO.getIdMision())).thenReturn(Optional.of(new Mision()));
        Mockito.when(retoDAO.existsById(reto.getIdReto())).thenReturn(true);
        Mockito.when(retoDAO.save(Mockito.any())).thenReturn(reto);
        Exception exception = assertThrows(Exception.class, () ->{
            retoService.actualizar(reto);
        });
        assertEquals(LIMITE_CARACTERES_DESCRIPCION_TEORIA , exception.getMessage());
    }
    @Test
    @DisplayName("Debe fallar minimo de intentos")
    void deberiaFallarPorMinimoIntentos() throws Exception{
        RetoDTO retoDTO = new RetoTestDataBuilder().conMaximoIntentos(0).build();
        Reto reto = retoMapper.toEntity(retoDTO);
        Mockito.when(estadoDAO.findById(retoDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(cursoDAO.findById(retoDTO.getIdCurso())).thenReturn(Optional.of(new Curso()));
        Mockito.when(misionDAO.findById(retoDTO.getIdMision())).thenReturn(Optional.of(new Mision()));
        Mockito.when(retoDAO.existsById(reto.getIdReto())).thenReturn(true);
        Mockito.when(retoDAO.save(Mockito.any())).thenReturn(reto);
        Exception exception = assertThrows(Exception.class, () ->{
            retoService.actualizar(reto);
        });
        assertEquals(REQUERIMIENTO_MINIMO_INTENTOS_RETO , exception.getMessage());
    }
    @Test
    @DisplayName("Debe fallar minimo de monedas")
    void deberiaFallarPorMinimoMonedas() throws Exception{
        RetoDTO retoDTO = new RetoTestDataBuilder().conMoneda(-1).build();
        Reto reto = retoMapper.toEntity(retoDTO);
        Mockito.when(estadoDAO.findById(retoDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(cursoDAO.findById(retoDTO.getIdCurso())).thenReturn(Optional.of(new Curso()));
        Mockito.when(misionDAO.findById(retoDTO.getIdMision())).thenReturn(Optional.of(new Mision()));
        Mockito.when(retoDAO.existsById(reto.getIdReto())).thenReturn(true);
        Mockito.when(retoDAO.save(Mockito.any())).thenReturn(reto);
        Exception exception = assertThrows(Exception.class, () ->{
            retoService.actualizar(reto);
        });
        assertEquals(ASIGNACION_MONEDAS_RETO , exception.getMessage());
    }
    @Test
    @DisplayName("Debe fallar sin solucion reto")
    void deberiaFallarPorNoSolucion() throws Exception{
        RetoDTO retoDTO = new RetoTestDataBuilder().conSolucion(null).build();
        Reto reto = retoMapper.toEntity(retoDTO);
        Mockito.when(estadoDAO.findById(retoDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(cursoDAO.findById(retoDTO.getIdCurso())).thenReturn(Optional.of(new Curso()));
        Mockito.when(misionDAO.findById(retoDTO.getIdMision())).thenReturn(Optional.of(new Mision()));
        Mockito.when(retoDAO.existsById(reto.getIdReto())).thenReturn(true);
        Mockito.when(retoDAO.save(Mockito.any())).thenReturn(reto);
        Exception exception = assertThrows(Exception.class, () ->{
            retoService.actualizar(reto);
        });
        assertEquals(INGRESO_SOLUCION_RETO , exception.getMessage());
    }
    @Test
    @DisplayName("Debe fallar solucion reto muchos caracteres")
    void deberiaFallarPorSolucionMuchosCaracteres() throws Exception{
        RetoDTO retoDTO = new RetoTestDataBuilder().conSolucion(TEXTO_MAS_MIL_CARACTERES).build();
        Reto reto = retoMapper.toEntity(retoDTO);
        Mockito.when(estadoDAO.findById(retoDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(cursoDAO.findById(retoDTO.getIdCurso())).thenReturn(Optional.of(new Curso()));
        Mockito.when(misionDAO.findById(retoDTO.getIdMision())).thenReturn(Optional.of(new Mision()));
        Mockito.when(retoDAO.existsById(reto.getIdReto())).thenReturn(true);
        Mockito.when(retoDAO.save(Mockito.any())).thenReturn(reto);
        Exception exception = assertThrows(Exception.class, () ->{
            retoService.actualizar(reto);
        });
        assertEquals(LIMITE_CARACTERES_SOLUCION_RETO , exception.getMessage());
    }
    @Test
    @DisplayName("Debe fallar imagen1 muy larga")
    void deberiaFallarPorImageUnoMuyLarga() throws Exception{
        RetoDTO retoDTO = new RetoTestDataBuilder().conImagenTema1(TEXTO_MAS_TRECIOENTOS_CARACTERES).build();
        Reto reto = retoMapper.toEntity(retoDTO);
        Mockito.when(estadoDAO.findById(retoDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(cursoDAO.findById(retoDTO.getIdCurso())).thenReturn(Optional.of(new Curso()));
        Mockito.when(misionDAO.findById(retoDTO.getIdMision())).thenReturn(Optional.of(new Mision()));
        Mockito.when(retoDAO.existsById(reto.getIdReto())).thenReturn(true);
        Mockito.when(retoDAO.save(Mockito.any())).thenReturn(reto);
        Exception exception = assertThrows(Exception.class, () ->{
            retoService.actualizar(reto);
        });
        assertEquals(URL_IMAGEN_1_LARGA  , exception.getMessage());
    }
    @Test
    @DisplayName("Debe fallar imagen2 muy larga")
    void deberiaFallarPorImageDosMuyLarga() throws Exception{
        RetoDTO retoDTO = new RetoTestDataBuilder().conImagenTema2(TEXTO_MAS_TRECIOENTOS_CARACTERES).build();
        Reto reto = retoMapper.toEntity(retoDTO);
        Mockito.when(estadoDAO.findById(retoDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(cursoDAO.findById(retoDTO.getIdCurso())).thenReturn(Optional.of(new Curso()));
        Mockito.when(misionDAO.findById(retoDTO.getIdMision())).thenReturn(Optional.of(new Mision()));
        Mockito.when(retoDAO.existsById(reto.getIdReto())).thenReturn(true);
        Mockito.when(retoDAO.save(Mockito.any())).thenReturn(reto);
        Exception exception = assertThrows(Exception.class, () ->{
            retoService.actualizar(reto);
        });
        assertEquals(URL_IMAGEN_2_LARGA  , exception.getMessage());
    }
    @Test
    @DisplayName("Debe fallar video1 muy larga")
    void deberiaFallarPorVideoUnoMuyLarga() throws Exception{
        RetoDTO retoDTO = new RetoTestDataBuilder().conUrlVideo1(TEXTO_MAS_TRECIOENTOS_CARACTERES).build();
        Reto reto = retoMapper.toEntity(retoDTO);
        Mockito.when(estadoDAO.findById(retoDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(cursoDAO.findById(retoDTO.getIdCurso())).thenReturn(Optional.of(new Curso()));
        Mockito.when(misionDAO.findById(retoDTO.getIdMision())).thenReturn(Optional.of(new Mision()));
        Mockito.when(retoDAO.existsById(reto.getIdReto())).thenReturn(true);
        Mockito.when(retoDAO.save(Mockito.any())).thenReturn(reto);
        Exception exception = assertThrows(Exception.class, () ->{
            retoService.actualizar(reto);
        });
        assertEquals(URL_VIDEO_1_LARGA , exception.getMessage());
    }
    @Test
    @DisplayName("Debe fallar video2 muy larga")
    void deberiaFallarPorVideoDosMuyLarga() throws Exception{
        RetoDTO retoDTO = new RetoTestDataBuilder().conUrlVideo2(TEXTO_MAS_TRECIOENTOS_CARACTERES).build();
        Reto reto = retoMapper.toEntity(retoDTO);
        Mockito.when(estadoDAO.findById(retoDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(cursoDAO.findById(retoDTO.getIdCurso())).thenReturn(Optional.of(new Curso()));
        Mockito.when(misionDAO.findById(retoDTO.getIdMision())).thenReturn(Optional.of(new Mision()));
        Mockito.when(retoDAO.existsById(reto.getIdReto())).thenReturn(true);
        Mockito.when(retoDAO.save(Mockito.any())).thenReturn(reto);
        Exception exception = assertThrows(Exception.class, () ->{
            retoService.actualizar(reto);
        });
        assertEquals(URL_VIDEO_2_LARGA , exception.getMessage());
    }
    @Test
    @DisplayName("Debe fallar id estado nulo")
    void deberiaFallarPorIdEstadoNulo() throws Exception{
        RetoDTO retoDTO = new RetoTestDataBuilder().conIdEstado(null).build();
        Reto reto = retoMapper.toEntity(retoDTO);
        Mockito.when(estadoDAO.findById(retoDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(cursoDAO.findById(retoDTO.getIdCurso())).thenReturn(Optional.of(new Curso()));
        Mockito.when(misionDAO.findById(retoDTO.getIdMision())).thenReturn(Optional.of(new Mision()));
        Mockito.when(retoDAO.existsById(reto.getIdReto())).thenReturn(true);
        Mockito.when(retoDAO.save(Mockito.any())).thenReturn(reto);
        Exception exception = assertThrows(Exception.class, () ->{
            retoService.actualizar(reto);
        });
        assertEquals(INGRESO_ID_ESTADO , exception.getMessage());
    }
    @Test
    @DisplayName("Debe fallar id estado menor a cero")
    void deberiaFallarPorIdEstadoMenorACero() throws Exception{
        RetoDTO retoDTO = new RetoTestDataBuilder().conIdEstado(-1L).build();
        Reto reto = retoMapper.toEntity(retoDTO);
        Mockito.when(estadoDAO.findById(retoDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(cursoDAO.findById(retoDTO.getIdCurso())).thenReturn(Optional.of(new Curso()));
        Mockito.when(misionDAO.findById(retoDTO.getIdMision())).thenReturn(Optional.of(new Mision()));
        Mockito.when(retoDAO.existsById(reto.getIdReto())).thenReturn(true);
        Mockito.when(retoDAO.save(Mockito.any())).thenReturn(reto);
        Exception exception = assertThrows(Exception.class, () ->{
            retoService.actualizar(reto);
        });
        assertEquals(INGRESO_ID_ESTADO_VALIDO , exception.getMessage());
    }
    @Test
    @DisplayName("Debe fallar id curso nulo")
    void deberiaFallarPorIdCursoNulo() throws Exception{
        RetoDTO retoDTO = new RetoTestDataBuilder().conIdCurso(null).build();
        Reto reto = retoMapper.toEntity(retoDTO);
        Mockito.when(estadoDAO.findById(retoDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(cursoDAO.findById(retoDTO.getIdCurso())).thenReturn(Optional.of(new Curso()));
        Mockito.when(misionDAO.findById(retoDTO.getIdMision())).thenReturn(Optional.of(new Mision()));
        Mockito.when(retoDAO.existsById(reto.getIdReto())).thenReturn(true);
        Mockito.when(retoDAO.save(Mockito.any())).thenReturn(reto);
        Exception exception = assertThrows(Exception.class, () ->{
            retoService.actualizar(reto);
        });
        assertEquals(INGRESO_ID_CURSO , exception.getMessage());
    }


    @Test
    @DisplayName("Debe fallar id curso menor no valido")
    void deberiaFallarPorIdCursoNoValidoNoExiste() throws Exception{
        RetoDTO retoDTO = new RetoTestDataBuilder().conIdCurso(-1L).build();
        Reto reto = retoMapper.toEntity(retoDTO);
        Mockito.when(estadoDAO.findById(retoDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(cursoDAO.findById(retoDTO.getIdCurso())).thenReturn(Optional.of(new Curso()));
        Mockito.when(misionDAO.findById(retoDTO.getIdMision())).thenReturn(Optional.of(new Mision()));
        Mockito.when(retoDAO.existsById(reto.getIdReto())).thenReturn(true);
        Mockito.when(retoDAO.save(Mockito.any())).thenReturn(reto);
        Exception exception = assertThrows(Exception.class, () ->{
            retoService.actualizar(reto);
        });
        assertEquals(INGRESO_ID_CURSO_VALIDO , exception.getMessage());
    }

    @Test
    @DisplayName("Debe fallar id mision es nulo")
    void deberiaFallarPorIdMisionNulo() throws Exception{
        RetoDTO retoDTO = new RetoTestDataBuilder().conIdMision(null).build();
        Reto reto = retoMapper.toEntity(retoDTO);
        Mockito.when(estadoDAO.findById(retoDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(cursoDAO.findById(retoDTO.getIdCurso())).thenReturn(Optional.of(new Curso()));
        Mockito.when(misionDAO.findById(retoDTO.getIdMision())).thenReturn(Optional.of(new Mision()));
        Mockito.when(retoDAO.existsById(reto.getIdReto())).thenReturn(true);
        Mockito.when(retoDAO.save(Mockito.any())).thenReturn(reto);
        Exception exception = assertThrows(Exception.class, () ->{
            retoService.actualizar(reto);
        });
        assertEquals(INGRESO_ID_MISION , exception.getMessage());
    }
    @Test
    @DisplayName("Debe fallar id mision es menor a 0")
    void deberiaFallarPorIdMisionMenorACero() throws Exception{
        RetoDTO retoDTO = new RetoTestDataBuilder().conIdMision(-1L).build();
        Reto reto = retoMapper.toEntity(retoDTO);
        Mockito.when(estadoDAO.findById(retoDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(cursoDAO.findById(retoDTO.getIdCurso())).thenReturn(Optional.of(new Curso()));
        Mockito.when(misionDAO.findById(retoDTO.getIdMision())).thenReturn(Optional.of(new Mision()));
        Mockito.when(retoDAO.existsById(reto.getIdReto())).thenReturn(true);
        Mockito.when(retoDAO.save(Mockito.any())).thenReturn(reto);
        Exception exception = assertThrows(Exception.class, () ->{
            retoService.actualizar(reto);
        });
        assertEquals(INGRESO_ID_MISION_VALIDO , exception.getMessage());
    }
    @Test
    @DisplayName("Debe fallar usuario modificador nulo")
    void deberiaFallarPorUsuarioModificadorNulo() throws Exception{
        RetoDTO retoDTO = new RetoTestDataBuilder().conUsuarioModificador(null).build();
        Reto reto = retoMapper.toEntity(retoDTO);
        Mockito.when(estadoDAO.findById(retoDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(cursoDAO.findById(retoDTO.getIdCurso())).thenReturn(Optional.of(new Curso()));
        Mockito.when(misionDAO.findById(retoDTO.getIdMision())).thenReturn(Optional.of(new Mision()));
        Mockito.when(retoDAO.existsById(reto.getIdReto())).thenReturn(true);
        Mockito.when(retoDAO.save(Mockito.any())).thenReturn(reto);
        Exception exception = assertThrows(Exception.class, () ->{
            retoService.actualizar(reto);
        });
        assertEquals(INGRESO_USUARIO_MODIFICADOR  , exception.getMessage());
    }
    @Test
    @DisplayName("Debe fallar usuario modificador vacio")
    void deberiaFallarPorUsuarioModificadorVacio() throws Exception{
        RetoDTO retoDTO = new RetoTestDataBuilder().conUsuarioModificador(VACIO).build();
        Reto reto = retoMapper.toEntity(retoDTO);
        Mockito.when(estadoDAO.findById(retoDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(cursoDAO.findById(retoDTO.getIdCurso())).thenReturn(Optional.of(new Curso()));
        Mockito.when(misionDAO.findById(retoDTO.getIdMision())).thenReturn(Optional.of(new Mision()));
        Mockito.when(retoDAO.existsById(reto.getIdReto())).thenReturn(true);
        Mockito.when(retoDAO.save(Mockito.any())).thenReturn(reto);
        Exception exception = assertThrows(Exception.class, () ->{
            retoService.actualizar(reto);
        });
        assertEquals(INGRESO_USUARIO_MODIFICADOR  , exception.getMessage());
    }
    @Test
    @DisplayName("Debe fallar usuario modificador supera caracteres")
    void deberiaFallarPorUsuarioModificadorSuperaCaracteres() throws Exception{
        RetoDTO retoDTO = new RetoTestDataBuilder().conUsuarioModificador(TEXTO_MAS_CINCUENTA_CARACTERES).build();
        Reto reto = retoMapper.toEntity(retoDTO);
        Mockito.when(estadoDAO.findById(retoDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(cursoDAO.findById(retoDTO.getIdCurso())).thenReturn(Optional.of(new Curso()));
        Mockito.when(misionDAO.findById(retoDTO.getIdMision())).thenReturn(Optional.of(new Mision()));
        Mockito.when(retoDAO.existsById(reto.getIdReto())).thenReturn(true);
        Mockito.when(retoDAO.save(Mockito.any())).thenReturn(reto);
        Exception exception = assertThrows(Exception.class, () ->{
            retoService.actualizar(reto);
        });
        assertEquals(LIMITE_CARACTERES_USUARIO_MODIFICADOR  , exception.getMessage());
    }
    @Test
    @DisplayName("Debe fallar fechacreacion nula")
    void deberiaFallarPorFechaCreacionNula() throws Exception{
        RetoDTO retoDTO = new RetoTestDataBuilder().conFechaModificacion(null).build();
        Reto reto = retoMapper.toEntity(retoDTO);
        Mockito.when(estadoDAO.findById(retoDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(cursoDAO.findById(retoDTO.getIdCurso())).thenReturn(Optional.of(new Curso()));
        Mockito.when(misionDAO.findById(retoDTO.getIdMision())).thenReturn(Optional.of(new Mision()));
        Mockito.when(retoDAO.existsById(reto.getIdReto())).thenReturn(true);
        Mockito.when(retoDAO.save(Mockito.any())).thenReturn(reto);
        Exception exception = assertThrows(Exception.class, () ->{
            retoService.actualizar(reto);
        });
        assertEquals(INGRESO_FECHA_MODIFICACION  , exception.getMessage());
    }
}
