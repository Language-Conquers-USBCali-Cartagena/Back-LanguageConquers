package com.example.demo.serviceImpl.estudiante.consultas;

import com.example.demo.dao.*;
import com.example.demo.mapper.EstudianteMapper;
import com.example.demo.model.*;
import com.example.demo.model.dto.EstudianteDTO;
import com.example.demo.service.EstudianteService;
import com.example.demo.serviceImpl.estudiante.testDataBuilder.EstudianteTestDataBuilder;
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
public class ManejadorActualizarEstudianteTest {
    public static final String SE_ACTUALIZO_EXITOSAMENETE_ESTUDIANTE = "Se actualizo exitosamente el estudiante.";
    public static final String DEBE_INGRESAR_EL_ID_DEL_ESTUDIANTE_QUE_DESEA_ACTUALIZAR = "Debe ingresar el id del estudiante que desea actualizar.";
    public static final String NO_SE_ENCONTRO_EL_ESTUDIANTE_CON_ESE_ID = "No se encontró el estudiante con ese id.";
    public static final String NO_SE_ENCONTRO_EL_AVATAR_CON_ESE_ID = "No existe un avatar con ese id.";
    public static final String NO_SE_ENCONTRO_EL_GENERO_CON_ESE_ID = "No existe un genero con ese id.";
    public static final String NO_SE_ENCONTRO_EL_ESTADO_CON_ESE_ID = "No existe un estado con ese id.";
    public static final String NO_SE_ENCONTRO_EL_PROGRAMA_CON_ESE_ID = "No existe un programa con ese id.";
    public static final String NO_SE_ENCONTRO_EL_SEMESTRE_CON_ESE_ID = "No existe un semestre con ese id.";
    public static final String ID_AVATAR_FALTANTE = "Se actualizo exitosamente el estudiante.";
    public static final String ID_GENERO_FALTANTE = "Debe ingresar un id genero.";
    public static final String ID_SEMESTRE_FALTANTE = "Debe ingresar un id semestre.";
    public static final String ID_PROGRAMA_FALTANTE = "Debe ingresar un id programa.";
    public static final String ID_ESTADO_FALTANTE = "Debe ingresar un id estado.";
    public static final String ID_AVATAR_INVALIDO = "Debe ingresar un id avatar válido.";
    public static final String ID_PROGRAMA_INVALIDO = "Debe ingresar un id programa válido.";
    public static final String DEBE_INGRESAR_ID_ESTADO_VALIDO = "Debe ingresar un id estado válido.";
    public static final String DEBE_INGRESAR_ID_SEMESTRE_VALIDO = "Debe ingresar un id semestre válido.";
    public static final String DEBE_INGRESAR_ID_GENERO_VALIDO = "Debe ingresar un id genero válido.";
    public static final String DEBE_INGRESAR_ID_AVATAR_VALIDO = "Debe ingresar un id avatar válido.";
    public static final String DEBE_INGRESAR_ID_PROGRAMA_VALIDO = "Debe ingresar un id programa válido.";
    public static final String INGRESAR_NOMBRE_ESTUDIANTE = "Debe ingresar el nombre del estudiante.";
    public static final String NOMBRE_ESTUDIANTE_LARGO = "El nombre del estudiante es muy largo.";
    public static final String INGRESAR_APELLIDO_ESTUDIANTE = "Debe ingresar el apellido del estudiante.";
    public static final String APELLIDO_ESTUDIANTE_LARGO = "El apellido del estudiante es muy largo.";
    public static final String INGRESAR_NICKNAME_ESTUDIANTE = "Debe ingresar un nickname para el estudiante.";
    public static final String NICKNAME_ESTUDIANTE_LARGO = "Debe ingresar un nickname para el estudiante, no debe contener más de 80 caracteres.";
    public static final String PUNTAJE_NO_NEGATIVO = "El puntaje no debe ser negativo.";
    public static final String MONEDAS_NO_NEGATIVAS = "Las monedas obtenidas no deben ser negativas.";
    public static final String MONEDAS_INICIALES_CERO = "Las monedas obtenidas no debe ser negativo.";
    public static final String INGRESAR_CORREO_ESTUDIANTE = "Debe ingresar un correo del estudiante.";
    public static final String CORREO_VALIDO = "Debe ingresar un correo válido.";
    public static final String INGRESAR_FECHA_NACIMIENTO = "Debe ingresar una fecha de nacimiento.";
    public static final String FECHA_NACIMIENTO_INVALIDA = "Digite una fecha de nacimiento válida, debe ser mayor de 15 años para poder registrarse.";
    public static final String USUARIO_MODIFICADOR_FALTANTE = "Debe ingresar el usuario modificador.";
    public static final String USUARIO_MODIFICADOR_LARGO = "El nombre del usuario modificador es muy largo, solo puede contener 50 caracteres.";
    public static final String FECHA_MODIFICACION_FALTANTE = "Debe ingresar una fecha de modificación.";
    public static final String CORREO_EXISTE = "El correo ya existe.";
    public static final String NOMBRE_LARGO = "FKHFHJFKEFHEH FHKFHKHFK HFKDHSFKH FKHKHKH HFKHK HG KEHK KGH KHG SKSDGFERG REGERGERGERHD";
    public static final String APELLIDO_LARGO = "FKHFHJFKEFHEH FHKFHKHFK HFKDHSFKH FKHKHKH HFKHK HG KEHK KGH KHG SKSDGFERG REGERGERGERHD";
    public static final String NICKNAME_LARGO = "FKHFHJFKEFHEH FHKFHKHFK HFKDHSFKH FKHKHKH HFKHK HG KEHK KGH KHG SKSDGFERG REGERGERGERHD";
    public static final String USUARIO_MODIFICADOR = "FKHFHJFKEFHEH FHKFHKHFK HFKDHSFKH FKHKHKH HFKHK HG ";
    @Autowired
    EstudianteService estudianteService;
    @Autowired
    EstudianteMapper estudianteMapper;
    @MockBean
    private EstudianteDAO estudianteDAO;
    @MockBean
    private AvatarDAO avatarDAO;
    @MockBean
    private EstadoDAO estadoDAO;
    @MockBean
    private GeneroDAO generoDAO;
    @MockBean
    private ProgramaDAO programaDAO;
    @MockBean
    private SemestreDAO semestreDAO;

    @MockBean
    private ProfesorDAO profesorDAO;

    @Test
    @DisplayName("Deberia crear existosamente el estudiante")
    void deberiaCrearExistosamenteEstudiante() throws Exception {
        EstudianteDTO estudianteDTO = new EstudianteTestDataBuilder()
                .conNombre("Pepito")
                .conApellido("Perez")
                .conNickName("pepitoP33")
                .conCorreo("pepito@gmail.com")
                .conPuntaje(0)
                .conMonedasObtenidas(0)
                .conIdAvatar(1L)
                .conIdGenero(1L)
                .conIdEstado(1L)
                .conIdPrograma(1L)
                .conFechaNacimiento(new Date(2000,7,31))
                .conIdSemestre(1l)
                .conUsuarioCreador("Angela")
                .conFechaCreacion(new Date())
                .build();
        Estudiante estudiante = estudianteMapper.toEntity(estudianteDTO);
        Mockito.when(estudianteDAO.existsById(estudiante.getIdEstudiante())).thenReturn(true);
        Mockito.when(avatarDAO.existsById(estudiante.getAvatar().getIdAvatar())).thenReturn(true);
        Mockito.when(avatarDAO.findById(estudiante.getAvatar().getIdAvatar())).thenReturn(Optional.of(new Avatar()));
        Mockito.when(estadoDAO.existsById(estudiante.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(estadoDAO.findById(estudiante.getEstado().getIdEstado())).thenReturn(Optional.of(new Estado()));
        Mockito.when(generoDAO.existsById(estudiante.getGenero().getIdGenero())).thenReturn(true);
        Mockito.when(generoDAO.findById(estudiante.getGenero().getIdGenero())).thenReturn(Optional.of(new Genero()));
        Mockito.when(programaDAO.existsById(estudiante.getPrograma().getIdPrograma())).thenReturn(true);
        Mockito.when(programaDAO.findById(estudiante.getPrograma().getIdPrograma())).thenReturn(Optional.of(new Programa()));
        Mockito.when(semestreDAO.existsById(estudiante.getSemestre().getIdSemestre())).thenReturn(true);
        Mockito.when(semestreDAO.findById(estudiante.getSemestre().getIdSemestre())).thenReturn(Optional.of(new Semestre()));
        Mockito.when(estudianteDAO.save(Mockito.any())).thenReturn(estudiante);
        String actualizarEstudiante = estudianteService.actualizar(estudiante);
        assertEquals(SE_ACTUALIZO_EXITOSAMENETE_ESTUDIANTE, actualizarEstudiante);

    }

    @Test
    @DisplayName("Deberia fallar Por idEstudiante null")
    void deberiaFallarPorIdEstudianteNull() throws Exception {
        EstudianteDTO estudianteDTO = new EstudianteTestDataBuilder()
                .conIdEstudiante(null)
                .build();
        Estudiante estudiante = estudianteMapper.toEntity(estudianteDTO);
        Mockito.when(estudianteDAO.save(Mockito.any())).thenReturn(estudiante);
        Exception exception = assertThrows(Exception.class, ()->{
            estudianteService.actualizar(estudiante);
        });
        assertEquals(exception.getMessage(),DEBE_INGRESAR_EL_ID_DEL_ESTUDIANTE_QUE_DESEA_ACTUALIZAR );

    }

    @Test
    @DisplayName("Deberia fallar Por idEstudiante no existe")
    void deberiaFallarPorIdEstudianteNoExiste() throws Exception {
        EstudianteDTO estudianteDTO = new EstudianteTestDataBuilder()
                .conIdEstudiante(67L)
                .build();
        Estudiante estudiante = estudianteMapper.toEntity(estudianteDTO);
        Mockito.when(estudianteDAO.existsById(estudiante.getIdEstudiante())).thenReturn(false);
        Mockito.when(estudianteDAO.save(Mockito.any())).thenReturn(estudiante);
        Exception exception = assertThrows(Exception.class, ()->{
            estudianteService.actualizar(estudiante);
        });
        assertEquals(exception.getMessage(),NO_SE_ENCONTRO_EL_ESTUDIANTE_CON_ESE_ID );

    }

    @Test
    @DisplayName("Deberia fallar Por idAvatar null")
    void deberiaFallarPorIdAvatarNull() throws Exception {
        EstudianteDTO estudianteDTO = new EstudianteTestDataBuilder()
                .conIdAvatar(null)
                .build();
        Estudiante estudiante = estudianteMapper.toEntity(estudianteDTO);
        Mockito.when(estudianteDAO.existsById(estudiante.getIdEstudiante())).thenReturn(true);
        Mockito.when(avatarDAO.existsById(estudiante.getAvatar().getIdAvatar())).thenReturn(false);
        Exception exception = assertThrows(Exception.class, ()->{
            estudianteService.actualizar(estudiante);
        });
        assertEquals(exception.getMessage(),NO_SE_ENCONTRO_EL_AVATAR_CON_ESE_ID);

    }

    @Test
    @DisplayName("Deberia fallar Por idGenero null")
    void deberiaFallarPorIdGeneroNull() throws Exception {
        EstudianteDTO estudianteDTO = new EstudianteTestDataBuilder()
                .conIdGenero(null)
                .build();
        Estudiante estudiante = estudianteMapper.toEntity(estudianteDTO);
        Mockito.when(estudianteDAO.existsById(estudiante.getIdEstudiante())).thenReturn(true);
        Mockito.when(avatarDAO.existsById(estudiante.getAvatar().getIdAvatar())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(estudiante.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(programaDAO.existsById(estudiante.getPrograma().getIdPrograma())).thenReturn(true);
        Mockito.when(semestreDAO.existsById(estudiante.getSemestre().getIdSemestre())).thenReturn(true);
        Mockito.when(estudianteDAO.save(Mockito.any())).thenReturn(estudiante);
        Exception exception = assertThrows(Exception.class, ()->{
            estudianteService.actualizar(estudiante);
        });
        assertEquals(exception.getMessage(),NO_SE_ENCONTRO_EL_GENERO_CON_ESE_ID);

    }

    @Test
    @DisplayName("Deberia fallar Por idSemestre null")
    void deberiaFallarPorIdSemestreNull() throws Exception {
        EstudianteDTO estudianteDTO = new EstudianteTestDataBuilder()
                .conIdSemestre(null)
                .build();
        Estudiante estudiante = estudianteMapper.toEntity(estudianteDTO);
        Mockito.when(estudianteDAO.existsById(estudiante.getIdEstudiante())).thenReturn(true);Mockito.when(estudianteDAO.existsById(estudiante.getIdEstudiante())).thenReturn(true);
        Mockito.when(avatarDAO.existsById(estudiante.getAvatar().getIdAvatar())).thenReturn(true);
        Mockito.when(generoDAO.existsById(estudiante.getGenero().getIdGenero())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(estudiante.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(programaDAO.existsById(estudiante.getPrograma().getIdPrograma())).thenReturn(true);
        Mockito.when(estudianteDAO.save(Mockito.any())).thenReturn(estudiante);
        Exception exception = assertThrows(Exception.class, ()->{
            estudianteService.actualizar(estudiante);
        });
        assertEquals(exception.getMessage(), NO_SE_ENCONTRO_EL_SEMESTRE_CON_ESE_ID);

    }

    @Test
    @DisplayName("Deberia fallar Por idPrograma null")
    void deberiaFallarPorIdProgramaNull() throws Exception {
        EstudianteDTO estudianteDTO = new EstudianteTestDataBuilder()
                .conIdPrograma(null)
                .build();
        Estudiante estudiante = estudianteMapper.toEntity(estudianteDTO);
        Mockito.when(estudianteDAO.existsById(estudiante.getIdEstudiante())).thenReturn(true);
        Mockito.when(avatarDAO.existsById(estudiante.getAvatar().getIdAvatar())).thenReturn(true);
        Mockito.when(generoDAO.existsById(estudiante.getGenero().getIdGenero())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(estudiante.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(semestreDAO.existsById(estudiante.getSemestre().getIdSemestre())).thenReturn(true);
        Mockito.when(estudianteDAO.save(Mockito.any())).thenReturn(estudiante);
        Exception exception = assertThrows(Exception.class, ()->{
            estudianteService.actualizar(estudiante);
        });
        assertEquals(exception.getMessage(), NO_SE_ENCONTRO_EL_PROGRAMA_CON_ESE_ID);

    }

    @Test
    @DisplayName("Deberia fallar Por idEstado null")
    void deberiaFallarPorIdEstadoNull() throws Exception {
        EstudianteDTO estudianteDTO = new EstudianteTestDataBuilder()
                .conIdEstado(null)
                .build();
        Estudiante estudiante = estudianteMapper.toEntity(estudianteDTO);
        Mockito.when(estudianteDAO.existsById(estudiante.getIdEstudiante())).thenReturn(true);
        Mockito.when(semestreDAO.existsById(estudiante.getSemestre().getIdSemestre())).thenReturn(true);
        Mockito.when(avatarDAO.existsById(estudiante.getAvatar().getIdAvatar())).thenReturn(true);
        Mockito.when(generoDAO.existsById(estudiante.getGenero().getIdGenero())).thenReturn(true);

        Mockito.when(programaDAO.existsById(estudiante.getPrograma().getIdPrograma())).thenReturn(true);
        Mockito.when(estudianteDAO.save(Mockito.any())).thenReturn(estudiante);
        Exception exception = assertThrows(Exception.class, ()->{
            estudianteService.actualizar(estudiante);
        });
        assertEquals(exception.getMessage(), NO_SE_ENCONTRO_EL_ESTADO_CON_ESE_ID);

    }
    @Test
    @DisplayName("Deberia fallar Por idAvatar negativo")
    void deberiaFallarPorIdAvatarNegativo() throws Exception {
        EstudianteDTO estudianteDTO = new EstudianteTestDataBuilder()
                .conIdAvatar(-5L)
                .build();
        Estudiante estudiante = estudianteMapper.toEntity(estudianteDTO);
        Mockito.when(estudianteDAO.existsById(estudiante.getIdEstudiante())).thenReturn(true);
        Mockito.when(avatarDAO.existsById(estudiante.getAvatar().getIdAvatar())).thenReturn(true);
        Mockito.when(generoDAO.existsById(estudiante.getGenero().getIdGenero())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(estudiante.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(programaDAO.existsById(estudiante.getPrograma().getIdPrograma())).thenReturn(true);
        Mockito.when(semestreDAO.existsById(estudiante.getSemestre().getIdSemestre())).thenReturn(true);
        Mockito.when(estudianteDAO.save(Mockito.any())).thenReturn(estudiante);
        Exception exception = assertThrows(Exception.class, ()->{
            estudianteService.actualizar(estudiante);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_ID_AVATAR_VALIDO);

    }

    @Test
    @DisplayName("Deberia fallar Por idPrograma negativo")
    void deberiaFallarPorIdProgramaNegativo() throws Exception {
        EstudianteDTO estudianteDTO = new EstudianteTestDataBuilder()
                .conIdPrograma(-5L)
                .build();
        Estudiante estudiante = estudianteMapper.toEntity(estudianteDTO);

        Mockito.when(estudianteDAO.existsById(estudiante.getIdEstudiante())).thenReturn(true);
        Mockito.when(avatarDAO.existsById(estudiante.getAvatar().getIdAvatar())).thenReturn(true);
        Mockito.when(generoDAO.existsById(estudiante.getGenero().getIdGenero())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(estudiante.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(estudiante.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(estudianteDAO.save(Mockito.any())).thenReturn(estudiante);
        Exception exception = assertThrows(Exception.class, ()->{
            estudianteService.actualizar(estudiante);
        });
        assertEquals(exception.getMessage(), NO_SE_ENCONTRO_EL_PROGRAMA_CON_ESE_ID);

    }

    @Test
    @DisplayName("Deberia fallar Por idEstado negativo")
    void deberiaFallarPorIdEstadoNegativo() throws Exception {
        EstudianteDTO estudianteDTO = new EstudianteTestDataBuilder()
                .conIdEstado(-5L)
                .build();
        Estudiante estudiante = estudianteMapper.toEntity(estudianteDTO);
        Mockito.when(estudianteDAO.existsById(estudiante.getIdEstudiante())).thenReturn(true);
        Mockito.when(avatarDAO.existsById(estudiante.getAvatar().getIdAvatar())).thenReturn(true);
        Mockito.when(generoDAO.existsById(estudiante.getGenero().getIdGenero())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(estudiante.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(programaDAO.existsById(estudiante.getPrograma().getIdPrograma())).thenReturn(true);
        Mockito.when(semestreDAO.existsById(estudiante.getSemestre().getIdSemestre())).thenReturn(true);
        Mockito.when(estudianteDAO.save(Mockito.any())).thenReturn(estudiante);
        Exception exception = assertThrows(Exception.class, ()->{
            estudianteService.actualizar(estudiante);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_ID_ESTADO_VALIDO);

    }
    @Test
    @DisplayName("Deberia fallar Por idSemestre negativo")
    void deberiaFallarPorIdSemestreNegativo() throws Exception {
        EstudianteDTO estudianteDTO = new EstudianteTestDataBuilder()
                .conIdSemestre(-5L)
                .build();
        Estudiante estudiante = estudianteMapper.toEntity(estudianteDTO);
        Mockito.when(estudianteDAO.existsById(estudiante.getIdEstudiante())).thenReturn(true);
        Mockito.when(avatarDAO.existsById(estudiante.getAvatar().getIdAvatar())).thenReturn(true);
        Mockito.when(generoDAO.existsById(estudiante.getGenero().getIdGenero())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(estudiante.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(programaDAO.existsById(estudiante.getPrograma().getIdPrograma())).thenReturn(true);
        Mockito.when(semestreDAO.existsById(estudiante.getSemestre().getIdSemestre())).thenReturn(true);
        Mockito.when(estudianteDAO.save(Mockito.any())).thenReturn(estudiante);
        Exception exception = assertThrows(Exception.class, ()->{
            estudianteService.actualizar(estudiante);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_ID_SEMESTRE_VALIDO);

    }
    @Test
    @DisplayName("Deberia fallar Por idGenero negativo")
    void deberiaFallarPorIdGeneroNegativo() throws Exception {
        EstudianteDTO estudianteDTO = new EstudianteTestDataBuilder()
                .conIdGenero(-5L)
                .build();
        Estudiante estudiante = estudianteMapper.toEntity(estudianteDTO);
        Mockito.when(estudianteDAO.existsById(estudiante.getIdEstudiante())).thenReturn(true);
        Mockito.when(avatarDAO.existsById(estudiante.getAvatar().getIdAvatar())).thenReturn(true);
        Mockito.when(generoDAO.existsById(estudiante.getGenero().getIdGenero())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(estudiante.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(programaDAO.existsById(estudiante.getPrograma().getIdPrograma())).thenReturn(true);
        Mockito.when(semestreDAO.existsById(estudiante.getSemestre().getIdSemestre())).thenReturn(true);
        Mockito.when(estudianteDAO.save(Mockito.any())).thenReturn(estudiante);
        Exception exception = assertThrows(Exception.class, ()->{
            estudianteService.actualizar(estudiante);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_ID_GENERO_VALIDO);

    }

    @Test
    @DisplayName("Deberia fallar Por idAvatar no existe")
    void deberiaFallarPorIdAvatarNoExiste() throws Exception {
        EstudianteDTO estudianteDTO = new EstudianteTestDataBuilder()
                .conIdAvatar(5L)
                .build();
        Estudiante estudiante = estudianteMapper.toEntity(estudianteDTO);
        Mockito.when(estudianteDAO.existsById(estudiante.getIdEstudiante())).thenReturn(true);
        Mockito.when(avatarDAO.existsById(estudiante.getAvatar().getIdAvatar())).thenReturn(false);
        Mockito.when(estudianteDAO.save(Mockito.any())).thenReturn(estudiante);
        Exception exception = assertThrows(Exception.class, ()->{
            estudianteService.actualizar(estudiante);
        });
        assertEquals(exception.getMessage(), NO_SE_ENCONTRO_EL_AVATAR_CON_ESE_ID);

    }

    @Test
    @DisplayName("Deberia fallar Por idGenero no existe")
    void deberiaFallarPorIdGeneroNoExiste() throws Exception {
        EstudianteDTO estudianteDTO = new EstudianteTestDataBuilder()
                .conIdGenero(5L)
                .build();
        Estudiante estudiante = estudianteMapper.toEntity(estudianteDTO);
        Mockito.when(estudianteDAO.existsById(estudiante.getIdEstudiante())).thenReturn(true);
        Mockito.when(avatarDAO.existsById(estudiante.getAvatar().getIdAvatar())).thenReturn(true);
        Mockito.when(generoDAO.existsById(estudiante.getGenero().getIdGenero())).thenReturn(false);
        Mockito.when(estudianteDAO.save(Mockito.any())).thenReturn(estudiante);
        Exception exception = assertThrows(Exception.class, ()->{
            estudianteService.actualizar(estudiante);
        });
        assertEquals(exception.getMessage(), NO_SE_ENCONTRO_EL_GENERO_CON_ESE_ID);

    }

    @Test
    @DisplayName("Deberia fallar Por idSemestre no existe")
    void deberiaFallarPorIdSemestreNoExiste() throws Exception {
        EstudianteDTO estudianteDTO = new EstudianteTestDataBuilder()
                .conIdSemestre(5L)
                .build();
        Estudiante estudiante = estudianteMapper.toEntity(estudianteDTO);
        Mockito.when(estudianteDAO.existsById(estudiante.getIdEstudiante())).thenReturn(true);
        Mockito.when(avatarDAO.existsById(estudiante.getAvatar().getIdAvatar())).thenReturn(true);
        Mockito.when(generoDAO.existsById(estudiante.getGenero().getIdGenero())).thenReturn(true);
        Mockito.when(semestreDAO.existsById(estudiante.getSemestre().getIdSemestre())).thenReturn(false);
        Mockito.when(estadoDAO.existsById(estudiante.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(programaDAO.existsById(estudiante.getPrograma().getIdPrograma())).thenReturn(true);
        Mockito.when(estudianteDAO.save(Mockito.any())).thenReturn(estudiante);
        Exception exception = assertThrows(Exception.class, ()->{
            estudianteService.actualizar(estudiante);
        });
        assertEquals(exception.getMessage(), NO_SE_ENCONTRO_EL_SEMESTRE_CON_ESE_ID);

    }

    @Test
    @DisplayName("Deberia fallar Por idEstado no existe")
    void deberiaFallarPorIdEstadoNoExiste() throws Exception {
        EstudianteDTO estudianteDTO = new EstudianteTestDataBuilder()
                .conIdEstado(5L)
                .build();
        Estudiante estudiante = estudianteMapper.toEntity(estudianteDTO);
        Mockito.when(estudianteDAO.existsById(estudiante.getIdEstudiante())).thenReturn(true);
        Mockito.when(avatarDAO.existsById(estudiante.getAvatar().getIdAvatar())).thenReturn(true);
        Mockito.when(generoDAO.existsById(estudiante.getGenero().getIdGenero())).thenReturn(true);
        Mockito.when(semestreDAO.existsById(estudiante.getSemestre().getIdSemestre())).thenReturn(true);
        Mockito.when(estudianteDAO.existsById(estudiante.getEstado().getIdEstado())).thenReturn(false);
        Mockito.when(estudianteDAO.save(Mockito.any())).thenReturn(estudiante);
        Exception exception = assertThrows(Exception.class, ()->{
            estudianteService.actualizar(estudiante);
        });
        assertEquals(exception.getMessage(), NO_SE_ENCONTRO_EL_ESTADO_CON_ESE_ID);

    }

    @Test
    @DisplayName("Deberia fallar Por idPrograma no existe")
    void deberiaFallarPorIdProgramaNoExiste() throws Exception {
        EstudianteDTO estudianteDTO = new EstudianteTestDataBuilder()
                .conIdPrograma(5L)
                .build();
        Estudiante estudiante = estudianteMapper.toEntity(estudianteDTO);
        Mockito.when(estudianteDAO.existsById(estudiante.getIdEstudiante())).thenReturn(true);
        Mockito.when(avatarDAO.existsById(estudiante.getAvatar().getIdAvatar())).thenReturn(true);
        Mockito.when(generoDAO.existsById(estudiante.getGenero().getIdGenero())).thenReturn(true);
        Mockito.when(semestreDAO.existsById(estudiante.getSemestre().getIdSemestre())).thenReturn(true);
        Mockito.when(estudianteDAO.existsById(estudiante.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(programaDAO.existsById(estudiante.getPrograma().getIdPrograma())).thenReturn(false);
        Mockito.when(estadoDAO.existsById(estudiante.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(estudianteDAO.save(Mockito.any())).thenReturn(estudiante);
        Exception exception = assertThrows(Exception.class, ()->{
            estudianteService.actualizar(estudiante);
        });
        assertEquals(exception.getMessage(), NO_SE_ENCONTRO_EL_PROGRAMA_CON_ESE_ID);

    }
    @Test
    @DisplayName("Deberia fallar Por nombre null")
    void deberiaFallarPorNombreNull() throws Exception {
        EstudianteDTO estudianteDTO = new EstudianteTestDataBuilder()
                .conNombre(null)
                .build();
        Estudiante estudiante = estudianteMapper.toEntity(estudianteDTO);
        Mockito.when(estudianteDAO.existsById(estudiante.getIdEstudiante())).thenReturn(true);
        Mockito.when(avatarDAO.existsById(estudiante.getAvatar().getIdAvatar())).thenReturn(true);
        Mockito.when(generoDAO.existsById(estudiante.getGenero().getIdGenero())).thenReturn(true);
        Mockito.when(semestreDAO.existsById(estudiante.getSemestre().getIdSemestre())).thenReturn(true);
        Mockito.when(estudianteDAO.existsById(estudiante.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(programaDAO.existsById(estudiante.getPrograma().getIdPrograma())).thenReturn(true);
        Mockito.when(estudianteDAO.save(Mockito.any())).thenReturn(estudiante);
        Mockito.when(estadoDAO.existsById(estudiante.getEstado().getIdEstado())).thenReturn(true);
        Exception exception = assertThrows(Exception.class, ()->{
            estudianteService.actualizar(estudiante);
        });
        assertEquals(exception.getMessage(), INGRESAR_NOMBRE_ESTUDIANTE);

    }

    @Test
    @DisplayName("Deberia fallar Por nombre vacio")
    void deberiaFallarPorNombreVacio() throws Exception {
        EstudianteDTO estudianteDTO = new EstudianteTestDataBuilder()
                .conNombre("")
                .build();
        Estudiante estudiante = estudianteMapper.toEntity(estudianteDTO);
        Mockito.when(estudianteDAO.existsById(estudiante.getIdEstudiante())).thenReturn(true);
        Mockito.when(avatarDAO.existsById(estudiante.getAvatar().getIdAvatar())).thenReturn(true);
        Mockito.when(generoDAO.existsById(estudiante.getGenero().getIdGenero())).thenReturn(true);
        Mockito.when(semestreDAO.existsById(estudiante.getSemestre().getIdSemestre())).thenReturn(true);
        Mockito.when(estudianteDAO.existsById(estudiante.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(programaDAO.existsById(estudiante.getPrograma().getIdPrograma())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(estudiante.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(estudianteDAO.save(Mockito.any())).thenReturn(estudiante);
        Exception exception = assertThrows(Exception.class, ()->{
            estudianteService.actualizar(estudiante);
        });
        assertEquals(exception.getMessage(), INGRESAR_NOMBRE_ESTUDIANTE);

    }
    @Test
    @DisplayName("Deberia fallar Por nombre largo")
    void deberiaFallarPorNombreLargo() throws Exception {
        EstudianteDTO estudianteDTO = new EstudianteTestDataBuilder()
                .conNombre(NOMBRE_LARGO)
                .build();
        Estudiante estudiante = estudianteMapper.toEntity(estudianteDTO);
        Mockito.when(estudianteDAO.existsById(estudiante.getIdEstudiante())).thenReturn(true);
        Mockito.when(avatarDAO.existsById(estudiante.getAvatar().getIdAvatar())).thenReturn(true);
        Mockito.when(generoDAO.existsById(estudiante.getGenero().getIdGenero())).thenReturn(true);
        Mockito.when(semestreDAO.existsById(estudiante.getSemestre().getIdSemestre())).thenReturn(true);
        Mockito.when(estudianteDAO.existsById(estudiante.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(programaDAO.existsById(estudiante.getPrograma().getIdPrograma())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(estudiante.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(estudianteDAO.save(Mockito.any())).thenReturn(estudiante);
        Exception exception = assertThrows(Exception.class, ()->{
            estudianteService.actualizar(estudiante);
        });
        assertEquals(exception.getMessage(), NOMBRE_ESTUDIANTE_LARGO);

    }

    @Test
    @DisplayName("Deberia fallar Por apellido null")
    void deberiaFallarPorApellidoNull() throws Exception {
        EstudianteDTO estudianteDTO = new EstudianteTestDataBuilder()
                .conApellido(null)
                .build();
        Estudiante estudiante = estudianteMapper.toEntity(estudianteDTO);
        Mockito.when(estudianteDAO.existsById(estudiante.getIdEstudiante())).thenReturn(true);
        Mockito.when(avatarDAO.existsById(estudiante.getAvatar().getIdAvatar())).thenReturn(true);
        Mockito.when(generoDAO.existsById(estudiante.getGenero().getIdGenero())).thenReturn(true);
        Mockito.when(semestreDAO.existsById(estudiante.getSemestre().getIdSemestre())).thenReturn(true);
        Mockito.when(estudianteDAO.existsById(estudiante.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(programaDAO.existsById(estudiante.getPrograma().getIdPrograma())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(estudiante.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(estudianteDAO.save(Mockito.any())).thenReturn(estudiante);
        Exception exception = assertThrows(Exception.class, ()->{
            estudianteService.actualizar(estudiante);
        });
        assertEquals(exception.getMessage(), INGRESAR_APELLIDO_ESTUDIANTE);

    }
    @Test
    @DisplayName("Deberia fallar Por apellido vacio")
    void deberiaFallarPorApellidoVacio() throws Exception {
        EstudianteDTO estudianteDTO = new EstudianteTestDataBuilder()
                .conApellido("")
                .build();
        Estudiante estudiante = estudianteMapper.toEntity(estudianteDTO);
        Mockito.when(estudianteDAO.existsById(estudiante.getIdEstudiante())).thenReturn(true);
        Mockito.when(avatarDAO.existsById(estudiante.getAvatar().getIdAvatar())).thenReturn(true);
        Mockito.when(generoDAO.existsById(estudiante.getGenero().getIdGenero())).thenReturn(true);
        Mockito.when(semestreDAO.existsById(estudiante.getSemestre().getIdSemestre())).thenReturn(true);
        Mockito.when(estudianteDAO.existsById(estudiante.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(programaDAO.existsById(estudiante.getPrograma().getIdPrograma())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(estudiante.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(estudianteDAO.save(Mockito.any())).thenReturn(estudiante);
        Exception exception = assertThrows(Exception.class, ()->{
            estudianteService.actualizar(estudiante);
        });
        assertEquals(exception.getMessage(), INGRESAR_APELLIDO_ESTUDIANTE);

    }
    @Test
    @DisplayName("Deberia fallar Por apellido largo")
    void deberiaFallarPorApellidoLargo() throws Exception {
        EstudianteDTO estudianteDTO = new EstudianteTestDataBuilder()
                .conApellido(APELLIDO_LARGO)
                .build();
        Estudiante estudiante = estudianteMapper.toEntity(estudianteDTO);
        Mockito.when(estudianteDAO.existsById(estudiante.getIdEstudiante())).thenReturn(true);
        Mockito.when(avatarDAO.existsById(estudiante.getAvatar().getIdAvatar())).thenReturn(true);
        Mockito.when(generoDAO.existsById(estudiante.getGenero().getIdGenero())).thenReturn(true);
        Mockito.when(semestreDAO.existsById(estudiante.getSemestre().getIdSemestre())).thenReturn(true);
        Mockito.when(estudianteDAO.existsById(estudiante.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(programaDAO.existsById(estudiante.getPrograma().getIdPrograma())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(estudiante.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(estudianteDAO.save(Mockito.any())).thenReturn(estudiante);
        Exception exception = assertThrows(Exception.class, ()->{
            estudianteService.actualizar(estudiante);
        });
        assertEquals(exception.getMessage(), APELLIDO_ESTUDIANTE_LARGO);

    }

    @Test
    @DisplayName("Deberia fallar Por nickName null")
    void deberiaFallarPorNickNameNull() throws Exception {
        EstudianteDTO estudianteDTO = new EstudianteTestDataBuilder()
                .conNickName(null)
                .build();
        Estudiante estudiante = estudianteMapper.toEntity(estudianteDTO);
        Mockito.when(estudianteDAO.existsById(estudiante.getIdEstudiante())).thenReturn(true);
        Mockito.when(avatarDAO.existsById(estudiante.getAvatar().getIdAvatar())).thenReturn(true);
        Mockito.when(generoDAO.existsById(estudiante.getGenero().getIdGenero())).thenReturn(true);
        Mockito.when(semestreDAO.existsById(estudiante.getSemestre().getIdSemestre())).thenReturn(true);
        Mockito.when(estudianteDAO.existsById(estudiante.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(programaDAO.existsById(estudiante.getPrograma().getIdPrograma())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(estudiante.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(estudianteDAO.save(Mockito.any())).thenReturn(estudiante);
        Exception exception = assertThrows(Exception.class, ()->{
            estudianteService.actualizar(estudiante);
        });
        assertEquals(exception.getMessage(),INGRESAR_NICKNAME_ESTUDIANTE);

    }
    @Test
    @DisplayName("Deberia fallar Por nickName VACIO")
    void deberiaFallarPorNickNameVacio() throws Exception {
        EstudianteDTO estudianteDTO = new EstudianteTestDataBuilder()
                .conNickName("")
                .build();
        Estudiante estudiante = estudianteMapper.toEntity(estudianteDTO);
        Mockito.when(estudianteDAO.existsById(estudiante.getIdEstudiante())).thenReturn(true);
        Mockito.when(avatarDAO.existsById(estudiante.getAvatar().getIdAvatar())).thenReturn(true);
        Mockito.when(generoDAO.existsById(estudiante.getGenero().getIdGenero())).thenReturn(true);
        Mockito.when(semestreDAO.existsById(estudiante.getSemestre().getIdSemestre())).thenReturn(true);
        Mockito.when(estudianteDAO.existsById(estudiante.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(programaDAO.existsById(estudiante.getPrograma().getIdPrograma())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(estudiante.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(estudianteDAO.save(Mockito.any())).thenReturn(estudiante);
        Exception exception = assertThrows(Exception.class, ()->{
            estudianteService.actualizar(estudiante);
        });
        assertEquals(exception.getMessage(),INGRESAR_NICKNAME_ESTUDIANTE);

    }

    @Test
    @DisplayName("Deberia fallar Por nickName largo")
    void deberiaFallarPorNickNameLargo() throws Exception {
        EstudianteDTO estudianteDTO = new EstudianteTestDataBuilder()
                .conNickName(NICKNAME_LARGO)
                .build();
        Estudiante estudiante = estudianteMapper.toEntity(estudianteDTO);
        Mockito.when(estudianteDAO.existsById(estudiante.getIdEstudiante())).thenReturn(true);
        Mockito.when(avatarDAO.existsById(estudiante.getAvatar().getIdAvatar())).thenReturn(true);
        Mockito.when(generoDAO.existsById(estudiante.getGenero().getIdGenero())).thenReturn(true);
        Mockito.when(semestreDAO.existsById(estudiante.getSemestre().getIdSemestre())).thenReturn(true);
        Mockito.when(estudianteDAO.existsById(estudiante.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(programaDAO.existsById(estudiante.getPrograma().getIdPrograma())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(estudiante.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(estudianteDAO.save(Mockito.any())).thenReturn(estudiante);
        Exception exception = assertThrows(Exception.class, ()->{
            estudianteService.actualizar(estudiante);
        });
        assertEquals(exception.getMessage(),NICKNAME_ESTUDIANTE_LARGO);

    }
    @Test
    @DisplayName("Deberia fallar Por puntaje negativo")
    void deberiaFallarPorPuntajeNegativo() throws Exception {
        EstudianteDTO estudianteDTO = new EstudianteTestDataBuilder()
                .conPuntaje(-4)
                .build();
        Estudiante estudiante = estudianteMapper.toEntity(estudianteDTO);
        Mockito.when(estudianteDAO.existsById(estudiante.getIdEstudiante())).thenReturn(true);
        Mockito.when(avatarDAO.existsById(estudiante.getAvatar().getIdAvatar())).thenReturn(true);
        Mockito.when(generoDAO.existsById(estudiante.getGenero().getIdGenero())).thenReturn(true);
        Mockito.when(semestreDAO.existsById(estudiante.getSemestre().getIdSemestre())).thenReturn(true);
        Mockito.when(estudianteDAO.existsById(estudiante.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(programaDAO.existsById(estudiante.getPrograma().getIdPrograma())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(estudiante.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(estudianteDAO.save(Mockito.any())).thenReturn(estudiante);
        Exception exception = assertThrows(Exception.class, ()->{
            estudianteService.actualizar(estudiante);
        });
        assertEquals(exception.getMessage(),PUNTAJE_NO_NEGATIVO);

    }

    @Test
    @DisplayName("Deberia fallar Por MONEDAS DIFERENTES DE 0")
    void deberiaFallarPorMonedasDiferentesCero() throws Exception {
        EstudianteDTO estudianteDTO = new EstudianteTestDataBuilder()
                .conMonedasObtenidas(-5)
                .build();
        Estudiante estudiante = estudianteMapper.toEntity(estudianteDTO);
        Mockito.when(estudianteDAO.existsById(estudiante.getIdEstudiante())).thenReturn(true);
        Mockito.when(avatarDAO.existsById(estudiante.getAvatar().getIdAvatar())).thenReturn(true);
        Mockito.when(generoDAO.existsById(estudiante.getGenero().getIdGenero())).thenReturn(true);
        Mockito.when(semestreDAO.existsById(estudiante.getSemestre().getIdSemestre())).thenReturn(true);
        Mockito.when(estudianteDAO.existsById(estudiante.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(programaDAO.existsById(estudiante.getPrograma().getIdPrograma())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(estudiante.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(estudianteDAO.save(Mockito.any())).thenReturn(estudiante);
        Exception exception = assertThrows(Exception.class, ()->{
            estudianteService.actualizar(estudiante);
        });
        assertEquals(exception.getMessage(),MONEDAS_INICIALES_CERO);

    }

    @Test
    @DisplayName("Deberia fallar Por correo null")
    void deberiaFallarPorCorreoNull() throws Exception {
        EstudianteDTO estudianteDTO = new EstudianteTestDataBuilder()
                .conCorreo(null)
                .build();
        Estudiante estudiante = estudianteMapper.toEntity(estudianteDTO);
        Mockito.when(estudianteDAO.existsById(estudiante.getIdEstudiante())).thenReturn(true);
        Mockito.when(avatarDAO.existsById(estudiante.getAvatar().getIdAvatar())).thenReturn(true);
        Mockito.when(generoDAO.existsById(estudiante.getGenero().getIdGenero())).thenReturn(true);
        Mockito.when(semestreDAO.existsById(estudiante.getSemestre().getIdSemestre())).thenReturn(true);
        Mockito.when(estudianteDAO.existsById(estudiante.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(programaDAO.existsById(estudiante.getPrograma().getIdPrograma())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(estudiante.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(estudianteDAO.save(Mockito.any())).thenReturn(estudiante);
        Exception exception = assertThrows(Exception.class, ()->{
            estudianteService.actualizar(estudiante);
        });
        assertEquals(exception.getMessage(),INGRESAR_CORREO_ESTUDIANTE);

    }

    @Test
    @DisplayName("Deberia fallar Por correo VACIO")
    void deberiaFallarPorCorreoVacio() throws Exception {
        EstudianteDTO estudianteDTO = new EstudianteTestDataBuilder()
                .conCorreo("")
                .build();
        Estudiante estudiante = estudianteMapper.toEntity(estudianteDTO);
        Mockito.when(estudianteDAO.existsById(estudiante.getIdEstudiante())).thenReturn(true);
        Mockito.when(avatarDAO.existsById(estudiante.getAvatar().getIdAvatar())).thenReturn(true);
        Mockito.when(generoDAO.existsById(estudiante.getGenero().getIdGenero())).thenReturn(true);
        Mockito.when(semestreDAO.existsById(estudiante.getSemestre().getIdSemestre())).thenReturn(true);
        Mockito.when(estudianteDAO.existsById(estudiante.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(programaDAO.existsById(estudiante.getPrograma().getIdPrograma())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(estudiante.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(estudianteDAO.save(Mockito.any())).thenReturn(estudiante);
        Exception exception = assertThrows(Exception.class, ()->{
            estudianteService.actualizar(estudiante);
        });
        assertEquals(exception.getMessage(),INGRESAR_CORREO_ESTUDIANTE);

    }

    @Test
    @DisplayName("Deberia fallar Por formato correo malo")
    void deberiaFallarPorFormatoCorreoMalo() throws Exception {
        EstudianteDTO estudianteDTO = new EstudianteTestDataBuilder()
                .conCorreo("pruebita.com")
                .build();
        Estudiante estudiante = estudianteMapper.toEntity(estudianteDTO);
        Mockito.when(estudianteDAO.existsById(estudiante.getIdEstudiante())).thenReturn(true);
        Mockito.when(avatarDAO.existsById(estudiante.getAvatar().getIdAvatar())).thenReturn(true);
        Mockito.when(generoDAO.existsById(estudiante.getGenero().getIdGenero())).thenReturn(true);
        Mockito.when(semestreDAO.existsById(estudiante.getSemestre().getIdSemestre())).thenReturn(true);
        Mockito.when(estudianteDAO.existsById(estudiante.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(programaDAO.existsById(estudiante.getPrograma().getIdPrograma())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(estudiante.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(estudianteDAO.save(Mockito.any())).thenReturn(estudiante);
        Exception exception = assertThrows(Exception.class, ()->{
            estudianteService.actualizar(estudiante);
        });
        assertEquals(exception.getMessage(),CORREO_VALIDO);

    }

    @Test
    @DisplayName("Deberia fallar Por fecha nacimiento null")
    void deberiaFallarPorFechaNacimientoNull() throws Exception {
        EstudianteDTO estudianteDTO = new EstudianteTestDataBuilder()
                .conFechaNacimiento(null)
                .build();
        Estudiante estudiante = estudianteMapper.toEntity(estudianteDTO);
        Mockito.when(estudianteDAO.existsById(estudiante.getIdEstudiante())).thenReturn(true);
        Mockito.when(avatarDAO.existsById(estudiante.getAvatar().getIdAvatar())).thenReturn(true);
        Mockito.when(generoDAO.existsById(estudiante.getGenero().getIdGenero())).thenReturn(true);
        Mockito.when(semestreDAO.existsById(estudiante.getSemestre().getIdSemestre())).thenReturn(true);
        Mockito.when(estudianteDAO.existsById(estudiante.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(programaDAO.existsById(estudiante.getPrograma().getIdPrograma())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(estudiante.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(estudianteDAO.save(Mockito.any())).thenReturn(estudiante);
        Exception exception = assertThrows(Exception.class, ()->{
            estudianteService.actualizar(estudiante);
        });
        assertEquals(exception.getMessage(),INGRESAR_FECHA_NACIMIENTO);

    }

    @Test
    @DisplayName("Deberia fallar Por usuario modificador null")
    void deberiaFallarPorUsuarioModificadorNull() throws Exception {
        EstudianteDTO estudianteDTO = new EstudianteTestDataBuilder()
                .conUsuarioModificador(null)
                .build();
        Estudiante estudiante = estudianteMapper.toEntity(estudianteDTO);
        Mockito.when(estudianteDAO.existsById(estudiante.getIdEstudiante())).thenReturn(true);
        Mockito.when(avatarDAO.existsById(estudiante.getAvatar().getIdAvatar())).thenReturn(true);
        Mockito.when(generoDAO.existsById(estudiante.getGenero().getIdGenero())).thenReturn(true);
        Mockito.when(semestreDAO.existsById(estudiante.getSemestre().getIdSemestre())).thenReturn(true);
        Mockito.when(estudianteDAO.existsById(estudiante.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(programaDAO.existsById(estudiante.getPrograma().getIdPrograma())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(estudiante.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(estudianteDAO.save(Mockito.any())).thenReturn(estudiante);
        Exception exception = assertThrows(Exception.class, ()->{
            estudianteService.actualizar(estudiante);
        });
        assertEquals(exception.getMessage(),USUARIO_MODIFICADOR_FALTANTE);

    }

    @Test
    @DisplayName("Deberia fallar Por usuario modificador VACIO")
    void deberiaFallarPorUsuarioModificadorVacio() throws Exception {
        EstudianteDTO estudianteDTO = new EstudianteTestDataBuilder()
                .conUsuarioModificador("")
                .build();
        Estudiante estudiante = estudianteMapper.toEntity(estudianteDTO);
        Mockito.when(estudianteDAO.existsById(estudiante.getIdEstudiante())).thenReturn(true);
        Mockito.when(avatarDAO.existsById(estudiante.getAvatar().getIdAvatar())).thenReturn(true);
        Mockito.when(generoDAO.existsById(estudiante.getGenero().getIdGenero())).thenReturn(true);
        Mockito.when(semestreDAO.existsById(estudiante.getSemestre().getIdSemestre())).thenReturn(true);
        //Mockito.when(estudianteDAO.existsById(estudiante.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(programaDAO.existsById(estudiante.getPrograma().getIdPrograma())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(estudiante.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(estudianteDAO.save(Mockito.any())).thenReturn(estudiante);
        Exception exception = assertThrows(Exception.class, ()->{
            estudianteService.actualizar(estudiante);
        });
        assertEquals(exception.getMessage(),USUARIO_MODIFICADOR_FALTANTE);

    }

    @Test
    @DisplayName("Deberia fallar Por usuario modificador largo")
    void deberiaFallarPorUsuarioModificadorLargo() throws Exception {
        EstudianteDTO estudianteDTO = new EstudianteTestDataBuilder()
                .conUsuarioModificador(USUARIO_MODIFICADOR)
                .build();
        Estudiante estudiante = estudianteMapper.toEntity(estudianteDTO);
        Mockito.when(estudianteDAO.existsById(estudiante.getIdEstudiante())).thenReturn(true);
        Mockito.when(avatarDAO.existsById(estudiante.getAvatar().getIdAvatar())).thenReturn(true);
        Mockito.when(generoDAO.existsById(estudiante.getGenero().getIdGenero())).thenReturn(true);
        Mockito.when(semestreDAO.existsById(estudiante.getSemestre().getIdSemestre())).thenReturn(true);
       // Mockito.when(estudianteDAO.existsById(estudiante.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(programaDAO.existsById(estudiante.getPrograma().getIdPrograma())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(estudiante.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(estudianteDAO.save(Mockito.any())).thenReturn(estudiante);
        Exception exception = assertThrows(Exception.class, ()->{
            estudianteService.actualizar(estudiante);
        });
        assertEquals(exception.getMessage(),USUARIO_MODIFICADOR_LARGO);

    }

    @Test
    @DisplayName("Deberia fallar Por fecha creación null")
    void deberiaFallarPorFechaModificacionNull() throws Exception {
        EstudianteDTO estudianteDTO = new EstudianteTestDataBuilder()
                .conFechaModificacion(null)
                .build();
        Estudiante estudiante = estudianteMapper.toEntity(estudianteDTO);
        Mockito.when(estudianteDAO.existsById(estudiante.getIdEstudiante())).thenReturn(true);
        Mockito.when(avatarDAO.existsById(estudiante.getAvatar().getIdAvatar())).thenReturn(true);
        Mockito.when(generoDAO.existsById(estudiante.getGenero().getIdGenero())).thenReturn(true);
        Mockito.when(semestreDAO.existsById(estudiante.getSemestre().getIdSemestre())).thenReturn(true);
        Mockito.when(estudianteDAO.existsById(estudiante.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(programaDAO.existsById(estudiante.getPrograma().getIdPrograma())).thenReturn(true);
        Mockito.when(estadoDAO.existsById(estudiante.getEstado().getIdEstado())).thenReturn(true);
        Mockito.when(estudianteDAO.save(Mockito.any())).thenReturn(estudiante);
        Exception exception = assertThrows(Exception.class, ()->{
            estudianteService.actualizar(estudiante);
        });
        assertEquals(exception.getMessage(),FECHA_MODIFICACION_FALTANTE);

    }




}
