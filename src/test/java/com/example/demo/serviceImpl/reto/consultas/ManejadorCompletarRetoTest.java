package com.example.demo.serviceImpl.reto.consultas;

import com.example.demo.dao.*;
import com.example.demo.mapper.*;
import com.example.demo.model.*;
import com.example.demo.model.dto.*;
import com.example.demo.service.RetoService;
import com.example.demo.serviceImpl.estado.testDataBuilder.EstadoTestDataBuilder;
import com.example.demo.serviceImpl.estudiante.testDataBuilder.EstudianteTestDataBuilder;
import com.example.demo.serviceImpl.palabrasReservadas.testDataBuilder.PalabrasReservadasTestDataBuilder;
import com.example.demo.serviceImpl.reto.testDataBuilder.RetoTestDataBuilder;
import com.example.demo.serviceImpl.retoEstudiante.testDataBuilder.RetoEstudianteTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
public class ManejadorCompletarRetoTest {

    public static final String GANANCIA_Y_PUNTAJE = "Has ganado 1500 monedas.\n" +
            "Tu puntaje para este reto es 1000.\n" +
            " La respuesta fue: arbol.";
    public static final String MAXIMO_INTENTOS_SUPERADO = "Has superado el máximo de intentos.";
    public static final String RESPUESTA_INCORRECTA = "¡Revisa tu código! Esto es lo que está respondiendo tu código: madera. Lo que se espera es: arbol";
    private static final Long ID_ESTUDIANTE = 15L;
    private static final Long ID_RETO = 2L;
    private static final String RESPUESTA = "arbol";
    private static final Long ID_ESTADO = 3L;
    @Autowired
    RetoService retoService;
    @Autowired
    RetoMapper retoMapper;
    @Autowired
    RetoEstudianteMapper retoEstudianteMapper;
    @Autowired
    EstudianteMapper estudianteMapper;
    @Autowired
    PalabrasReservadasMapper palabrasReservadasMapper;
    @Autowired
    EstadoMapper estadoMapper;
    @MockBean
    RetoDAO retoDAO;
    @MockBean
    EstadoDAO estadoDAO;
    @MockBean
    CursoDAO cursoDAO;
    @MockBean
    MisionDAO misionDAO;
    @MockBean
    EstudianteDAO estudianteDAO;
    @MockBean
    RetoEstudianteDAO retoEstudianteDAO;
    @MockBean
    AvatarDAO avatarDAO;
    @MockBean
    GeneroDAO generoDAO;
    @MockBean
    ProgramaDAO programaDAO;
    @MockBean
    SemestreDAO semestreDAO;

    @Test
    @DisplayName("Deberia permitir completar un reto")
    void deberiaCompletarunReto() throws Exception{
        RetoDTO retoDTO = new RetoTestDataBuilder().conIdReto(ID_RETO).conSolucion(RESPUESTA).build();
        Reto reto = retoMapper.toEntity(retoDTO);
        RetoEstudianteDTO retoEstudianteDTO = new RetoEstudianteTestDataBuilder().conIdEstado(ID_ESTADO).conIdReto(ID_RETO).conIdEstudiante(ID_ESTUDIANTE).build();
        RetoEstudiante retoEstudiante = retoEstudianteMapper.toEntity(retoEstudianteDTO);
        EstudianteDTO estudianteDTO = new EstudianteTestDataBuilder().conIdEstado(ID_ESTADO).conIdEstudiante(ID_ESTUDIANTE).build();
        Estudiante estudiante = estudianteMapper.toEntity(estudianteDTO);
        EstadoDTO estadoDTO = new EstadoTestDataBuilder().conIdEstado(ID_ESTADO).build();
        Estado estado = estadoMapper.toEntity(estadoDTO);
        Mockito.when(retoDAO.existsById(ID_RETO)).thenReturn(true);
        Mockito.when(estudianteDAO.existsById(ID_ESTUDIANTE)).thenReturn(true);
        Mockito.when(retoEstudianteDAO.findByIdRetoAndIdEstuduante(ID_RETO, ID_ESTUDIANTE)).thenReturn(retoEstudiante);
        Mockito.when(estudianteDAO.findById(ID_ESTUDIANTE)).thenReturn(Optional.ofNullable(estudiante));
        Mockito.when(retoDAO.findById(ID_RETO)).thenReturn(Optional.ofNullable(reto));
        Mockito.when(avatarDAO.existsById(estudianteDTO.getIdAvatar())).thenReturn(true);
        Mockito.when(generoDAO.existsById(estudianteDTO.getIdGenero())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(estudianteDTO.getIdEstado())).thenReturn(true);
        Mockito.when(programaDAO.existsById(estudianteDTO.getIdPrograma())).thenReturn(true);
        Mockito.when(semestreDAO.existsById(estudianteDTO.getIdSemestre())).thenReturn(true);
        Mockito.when(avatarDAO.findById(estudianteDTO.getIdAvatar())).thenReturn(Optional.of(new Avatar()));
        Mockito.when(generoDAO.findById(estudianteDTO.getIdGenero())).thenReturn(Optional.of(new Genero()));
        Mockito.when(semestreDAO.findById(estudianteDTO.getIdSemestre())).thenReturn(Optional.of(new Semestre()));
        Mockito.when(estadoDAO.findById(estudianteDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(programaDAO.findById(estudianteDTO.getIdPrograma())).thenReturn(Optional.of(new Programa()));
        Mockito.when(estadoDAO.getById(ID_ESTADO)).thenReturn(estado);
        List<PalabrasReservadasDTO> palabrasReservadasDTOS = new ArrayList<>();
        palabrasReservadasDTOS.add(new PalabrasReservadasTestDataBuilder().conCategoria("Metodo").conNombre("Buscar").build());
        palabrasReservadasDTOS.add(new PalabrasReservadasTestDataBuilder().conCategoria("Objeto").conNombre("Arbol").conIdPalabraReservada(2L).build());
        String resp = retoService.completarReto(palabrasReservadasDTOS, true, ID_RETO, ID_ESTUDIANTE);
        assertEquals(GANANCIA_Y_PUNTAJE, resp);

    }

    @Test
    @DisplayName("Debe fallar por superar el maximo de intentos")
    void debeFallarPorSuperarElMaximoDeIntrentos() throws Exception{
        RetoDTO retoDTO = new RetoTestDataBuilder().conIdReto(ID_RETO).conSolucion(RESPUESTA).build();
        Reto reto = retoMapper.toEntity(retoDTO);
        RetoEstudianteDTO retoEstudianteDTO = new RetoEstudianteTestDataBuilder().conIdEstado(4L).conIdReto(ID_RETO).conIdEstudiante(ID_ESTUDIANTE).build();
        RetoEstudiante retoEstudiante = retoEstudianteMapper.toEntity(retoEstudianteDTO);
        EstudianteDTO estudianteDTO = new EstudianteTestDataBuilder().conIdEstado(4L).conIdEstudiante(ID_ESTUDIANTE).build();
        Estudiante estudiante = estudianteMapper.toEntity(estudianteDTO);
        EstadoDTO estadoDTO = new EstadoTestDataBuilder().conIdEstado(4L).build();
        Estado estado = estadoMapper.toEntity(estadoDTO);
        Mockito.when(retoDAO.existsById(ID_RETO)).thenReturn(true);
        Mockito.when(estudianteDAO.existsById(ID_ESTUDIANTE)).thenReturn(true);
        Mockito.when(retoEstudianteDAO.findByIdRetoAndIdEstuduante(ID_RETO, ID_ESTUDIANTE)).thenReturn(retoEstudiante);
        Mockito.when(estudianteDAO.findById(ID_ESTUDIANTE)).thenReturn(Optional.ofNullable(estudiante));
        Mockito.when(retoDAO.findById(ID_RETO)).thenReturn(Optional.ofNullable(reto));
        Mockito.when(avatarDAO.existsById(estudianteDTO.getIdAvatar())).thenReturn(true);
        Mockito.when(generoDAO.existsById(estudianteDTO.getIdGenero())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(estudianteDTO.getIdEstado())).thenReturn(true);
        Mockito.when(programaDAO.existsById(estudianteDTO.getIdPrograma())).thenReturn(true);
        Mockito.when(semestreDAO.existsById(estudianteDTO.getIdSemestre())).thenReturn(true);
        Mockito.when(avatarDAO.findById(estudianteDTO.getIdAvatar())).thenReturn(Optional.of(new Avatar()));
        Mockito.when(generoDAO.findById(estudianteDTO.getIdGenero())).thenReturn(Optional.of(new Genero()));
        Mockito.when(semestreDAO.findById(estudianteDTO.getIdSemestre())).thenReturn(Optional.of(new Semestre()));
        Mockito.when(estadoDAO.findById(estudianteDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(programaDAO.findById(estudianteDTO.getIdPrograma())).thenReturn(Optional.of(new Programa()));
        Mockito.when(estadoDAO.getById(4L)).thenReturn(estado);
        List<PalabrasReservadasDTO> palabrasReservadasDTOS = new ArrayList<>();
        palabrasReservadasDTOS.add(new PalabrasReservadasTestDataBuilder().conCategoria("Metodo").conNombre("Buscar").build());
        palabrasReservadasDTOS.add(new PalabrasReservadasTestDataBuilder().conCategoria("Objeto").conNombre("Madera").conIdPalabraReservada(2L).build());
        Exception exception = assertThrows(Exception.class, () -> {
            retoService.completarReto(palabrasReservadasDTOS, true, ID_RETO, ID_ESTUDIANTE);
        });
        assertEquals(MAXIMO_INTENTOS_SUPERADO, exception.getMessage());
    }
    @Test
    @DisplayName("Debe fallar por respuesta incorrecta")
    void debeFallarPorRespuestaIncorrecta() throws Exception{
        RetoDTO retoDTO = new RetoTestDataBuilder().conIdReto(ID_RETO).conSolucion(RESPUESTA).build();
        Reto reto = retoMapper.toEntity(retoDTO);
        RetoEstudianteDTO retoEstudianteDTO = new RetoEstudianteTestDataBuilder().conIdEstado(4L).conIntentos(1).conIdReto(ID_RETO).conIdEstudiante(ID_ESTUDIANTE).build();
        RetoEstudiante retoEstudiante = retoEstudianteMapper.toEntity(retoEstudianteDTO);
        EstudianteDTO estudianteDTO = new EstudianteTestDataBuilder().conIdEstado(4L).conIdEstudiante(ID_ESTUDIANTE).build();
        Estudiante estudiante = estudianteMapper.toEntity(estudianteDTO);
        EstadoDTO estadoDTO = new EstadoTestDataBuilder().conIdEstado(4L).build();
        Estado estado = estadoMapper.toEntity(estadoDTO);
        Mockito.when(retoDAO.existsById(ID_RETO)).thenReturn(true);
        Mockito.when(estudianteDAO.existsById(ID_ESTUDIANTE)).thenReturn(true);
        Mockito.when(retoEstudianteDAO.findByIdRetoAndIdEstuduante(ID_RETO, ID_ESTUDIANTE)).thenReturn(retoEstudiante);
        Mockito.when(estudianteDAO.findById(ID_ESTUDIANTE)).thenReturn(Optional.ofNullable(estudiante));
        Mockito.when(retoDAO.findById(ID_RETO)).thenReturn(Optional.ofNullable(reto));
        Mockito.when(avatarDAO.existsById(estudianteDTO.getIdAvatar())).thenReturn(true);
        Mockito.when(generoDAO.existsById(estudianteDTO.getIdGenero())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(estudianteDTO.getIdEstado())).thenReturn(true);
        Mockito.when(programaDAO.existsById(estudianteDTO.getIdPrograma())).thenReturn(true);
        Mockito.when(semestreDAO.existsById(estudianteDTO.getIdSemestre())).thenReturn(true);
        Mockito.when(avatarDAO.findById(estudianteDTO.getIdAvatar())).thenReturn(Optional.of(new Avatar()));
        Mockito.when(generoDAO.findById(estudianteDTO.getIdGenero())).thenReturn(Optional.of(new Genero()));
        Mockito.when(semestreDAO.findById(estudianteDTO.getIdSemestre())).thenReturn(Optional.of(new Semestre()));
        Mockito.when(estadoDAO.findById(estudianteDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(programaDAO.findById(estudianteDTO.getIdPrograma())).thenReturn(Optional.of(new Programa()));
        Mockito.when(estadoDAO.getById(4L)).thenReturn(estado);
        List<PalabrasReservadasDTO> palabrasReservadasDTOS = new ArrayList<>();
        palabrasReservadasDTOS.add(new PalabrasReservadasTestDataBuilder().conCategoria("Metodo").conNombre("Buscar").build());
        palabrasReservadasDTOS.add(new PalabrasReservadasTestDataBuilder().conCategoria("Objeto").conNombre("Madera").conIdPalabraReservada(2L).build());
        Exception exception = assertThrows(Exception.class, () -> {
            retoService.completarReto(palabrasReservadasDTOS, true, ID_RETO, ID_ESTUDIANTE);
        });
        assertEquals(RESPUESTA_INCORRECTA, exception.getMessage());
    }
    @Test
    @DisplayName("Debe fallar por respuesta incorrecta e intentos nulo")
    void debeFallarPorRespuestaIncorrectaEIntentosNulo() throws Exception{
        RetoDTO retoDTO = new RetoTestDataBuilder().conIdReto(ID_RETO).conSolucion(RESPUESTA).build();
        Reto reto = retoMapper.toEntity(retoDTO);
        RetoEstudianteDTO retoEstudianteDTO = new RetoEstudianteTestDataBuilder().conIdEstado(4L).conIntentos(null).conIdReto(ID_RETO).conIdEstudiante(ID_ESTUDIANTE).build();
        RetoEstudiante retoEstudiante = retoEstudianteMapper.toEntity(retoEstudianteDTO);
        EstudianteDTO estudianteDTO = new EstudianteTestDataBuilder().conIdEstado(4L).conIdEstudiante(ID_ESTUDIANTE).build();
        Estudiante estudiante = estudianteMapper.toEntity(estudianteDTO);
        EstadoDTO estadoDTO = new EstadoTestDataBuilder().conIdEstado(4L).build();
        Estado estado = estadoMapper.toEntity(estadoDTO);
        Mockito.when(retoDAO.existsById(ID_RETO)).thenReturn(true);
        Mockito.when(estudianteDAO.existsById(ID_ESTUDIANTE)).thenReturn(true);
        Mockito.when(retoEstudianteDAO.findByIdRetoAndIdEstuduante(ID_RETO, ID_ESTUDIANTE)).thenReturn(retoEstudiante);
        Mockito.when(estudianteDAO.findById(ID_ESTUDIANTE)).thenReturn(Optional.ofNullable(estudiante));
        Mockito.when(retoDAO.findById(ID_RETO)).thenReturn(Optional.ofNullable(reto));
        Mockito.when(avatarDAO.existsById(estudianteDTO.getIdAvatar())).thenReturn(true);
        Mockito.when(generoDAO.existsById(estudianteDTO.getIdGenero())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(estudianteDTO.getIdEstado())).thenReturn(true);
        Mockito.when(programaDAO.existsById(estudianteDTO.getIdPrograma())).thenReturn(true);
        Mockito.when(semestreDAO.existsById(estudianteDTO.getIdSemestre())).thenReturn(true);
        Mockito.when(avatarDAO.findById(estudianteDTO.getIdAvatar())).thenReturn(Optional.of(new Avatar()));
        Mockito.when(generoDAO.findById(estudianteDTO.getIdGenero())).thenReturn(Optional.of(new Genero()));
        Mockito.when(semestreDAO.findById(estudianteDTO.getIdSemestre())).thenReturn(Optional.of(new Semestre()));
        Mockito.when(estadoDAO.findById(estudianteDTO.getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(programaDAO.findById(estudianteDTO.getIdPrograma())).thenReturn(Optional.of(new Programa()));
        Mockito.when(estadoDAO.getById(4L)).thenReturn(estado);
        List<PalabrasReservadasDTO> palabrasReservadasDTOS = new ArrayList<>();
        palabrasReservadasDTOS.add(new PalabrasReservadasTestDataBuilder().conCategoria("Metodo").conNombre("Buscar").build());
        palabrasReservadasDTOS.add(new PalabrasReservadasTestDataBuilder().conCategoria("Objeto").conNombre("Madera").conIdPalabraReservada(2L).build());
        Exception exception = assertThrows(Exception.class, () -> {
            retoService.completarReto(palabrasReservadasDTOS, true, ID_RETO, ID_ESTUDIANTE);
        });
        assertEquals(RESPUESTA_INCORRECTA, exception.getMessage());
    }
}
