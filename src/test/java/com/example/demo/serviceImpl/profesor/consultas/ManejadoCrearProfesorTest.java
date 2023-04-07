package com.example.demo.serviceImpl.profesor.consultas;

import com.example.demo.dao.CursoDAO;
import com.example.demo.dao.EstudianteDAO;
import com.example.demo.dao.GeneroDAO;
import com.example.demo.dao.ProfesorDAO;
import com.example.demo.mapper.ProfesorMapper;
import com.example.demo.model.Estudiante;
import com.example.demo.model.Genero;
import com.example.demo.model.Profesor;
import com.example.demo.model.dto.ProfesorDTO;
import com.example.demo.service.ProfesorService;
import com.example.demo.serviceImpl.profesor.testDataBuilder.ProfesorTestDataBuilder;
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
public class ManejadoCrearProfesorTest {
    private static final String TEXTO_MAS_CINCUENTA_CARACTERES = "DGSDGSDSDAGSFDGDVFEBSBRGNBERNRNBRGMNRGNNGNGHSDFBNTYMDRHEHBSDFGSAGADFGAFGFDSHDGFHGFDHFGHGFHGJFGJFDGJDGFJFDGJDFGJDFGJFGJDFGJDFGJFDGJFGJGFJFGJFDGJFDGJFDNFBDNSNDNFFNFGNNGNFNGMGNMGNMGNMHGNMHFGNMNGMNGMNGMFGNMNGNGFFGSDFFGDFSGDFGDFGDFGDFGDFGDFGDSGDFDVSDDSBDSBSDBSVDVSDVSDVSDVSDV";
    @Autowired
    ProfesorService profesorService;
    @Autowired
    ProfesorMapper profesorMapper;
    @MockBean
    ProfesorDAO profesorDAO;
    @MockBean
    GeneroDAO generoDAO;
    @MockBean
    CursoDAO cursoDAO;
    @MockBean
    EstudianteDAO estudianteDAO;

    @Test
    @DisplayName("Deberia crear el profesro")
    void deberiaCrearElProfesor() throws Exception{
        ProfesorDTO profesorDTO = new ProfesorTestDataBuilder().build();
        Profesor profesor = profesorMapper.toEntity(profesorDTO);
        Mockito.when(generoDAO.findById(profesorDTO.getIdGenero())).thenReturn(Optional.of(new Genero()));
        Mockito.when(profesorDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(estudianteDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(profesorDAO.save(Mockito.any())).thenReturn(profesor);
        String resp = profesorService.registarProfesor(profesor);
        assertEquals(resp, "Se creo exitosamente el profesor.");
    }

    @Test
    @DisplayName("Deberia fallar por genero nulo")
    void deberiaFallarPorGeneroNulo() throws Exception{
        ProfesorDTO profesorDTO = new ProfesorTestDataBuilder().conIdGenero(null).build();
        Profesor profesor = profesorMapper.toEntity(profesorDTO);
        Mockito.when(generoDAO.findById(profesorDTO.getIdGenero())).thenReturn(Optional.of(new Genero()));
        Mockito.when(profesorDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(estudianteDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(profesorDAO.save(Mockito.any())).thenReturn(profesor);
        Exception exception = assertThrows(Exception.class, () -> {
            profesorService.registarProfesor(profesor);
        });
        assertEquals(exception.getMessage(), "Debe ingresar el id un genero.");
    }
    @Test
    @DisplayName("Deberia fallar id genero no valido")
    void deberiaFallarIdGeneroNoValido() throws Exception{
        ProfesorDTO profesorDTO = new ProfesorTestDataBuilder().conIdGenero(-1L).build();
        Profesor profesor = profesorMapper.toEntity(profesorDTO);
        Mockito.when(generoDAO.findById(profesorDTO.getIdGenero())).thenReturn(Optional.of(new Genero()));
        Mockito.when(profesorDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(estudianteDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(profesorDAO.save(Mockito.any())).thenReturn(profesor);
        Exception exception = assertThrows(Exception.class, () -> {
            profesorService.registarProfesor(profesor);
        });
        assertEquals(exception.getMessage(), "Debe ingresar un id genero válido.");
    }
    @Test
    @DisplayName("Deberia fallar id genero no existe")
    void deberiaFallarIdGeneroNoExiste() throws Exception{
        ProfesorDTO profesorDTO = new ProfesorTestDataBuilder().build();
        Profesor profesor = profesorMapper.toEntity(profesorDTO);
        Mockito.when(generoDAO.findById(profesorDTO.getIdGenero())).thenReturn(Optional.empty());
        Mockito.when(profesorDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(estudianteDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(profesorDAO.save(Mockito.any())).thenReturn(profesor);
        Exception exception = assertThrows(Exception.class, () -> {
            profesorService.registarProfesor(profesor);
        });
        assertEquals(exception.getMessage(), "No existe un genero con ese id, ingrese un id válido.");
    }
    @Test
    @DisplayName("Deberia fallar nombre profesro nulo")
    void deberiaFallaNombreProfesorNulo() throws Exception{
        ProfesorDTO profesorDTO = new ProfesorTestDataBuilder().conNombre(null).build();
        Profesor profesor = profesorMapper.toEntity(profesorDTO);
        Mockito.when(generoDAO.findById(profesorDTO.getIdGenero())).thenReturn(Optional.of(new Genero()));
        Mockito.when(profesorDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(estudianteDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(profesorDAO.save(Mockito.any())).thenReturn(profesor);
        Exception exception = assertThrows(Exception.class, () -> {
            profesorService.registarProfesor(profesor);
        });
        assertEquals(exception.getMessage(), "Debe ingresar el nombre del profesor.");
    }
    @Test
    @DisplayName("Deberia fallar nombre profesro vacio")
    void deberiaFallaNombreProfesorVacio() throws Exception{
        ProfesorDTO profesorDTO = new ProfesorTestDataBuilder().conNombre("").build();
        Profesor profesor = profesorMapper.toEntity(profesorDTO);
        Mockito.when(generoDAO.findById(profesorDTO.getIdGenero())).thenReturn(Optional.of(new Genero()));
        Mockito.when(profesorDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(estudianteDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(profesorDAO.save(Mockito.any())).thenReturn(profesor);
        Exception exception = assertThrows(Exception.class, () -> {
            profesorService.registarProfesor(profesor);
        });
        assertEquals(exception.getMessage(), "Debe ingresar el nombre del profesor.");
    }
    @Test
    @DisplayName("Deberia fallar nombre caracteres")
    void deberiaFallaNombreProfesorCaracteres() throws Exception{
        ProfesorDTO profesorDTO = new ProfesorTestDataBuilder().conNombre(TEXTO_MAS_CINCUENTA_CARACTERES).build();
        Profesor profesor = profesorMapper.toEntity(profesorDTO);
        Mockito.when(generoDAO.findById(profesorDTO.getIdGenero())).thenReturn(Optional.of(new Genero()));
        Mockito.when(profesorDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(estudianteDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(profesorDAO.save(Mockito.any())).thenReturn(profesor);
        Exception exception = assertThrows(Exception.class, () -> {
            profesorService.registarProfesor(profesor);
        });
        assertEquals(exception.getMessage(), "El nombre del profesor no debe contener más de 50 caracteres.");
    }
    @Test
    @DisplayName("Deberia fallar apellido nulo")
    void deberiaFallaApellidoNulo() throws Exception{
        ProfesorDTO profesorDTO = new ProfesorTestDataBuilder().conApellido(null).build();
        Profesor profesor = profesorMapper.toEntity(profesorDTO);
        Mockito.when(generoDAO.findById(profesorDTO.getIdGenero())).thenReturn(Optional.of(new Genero()));
        Mockito.when(profesorDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(estudianteDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(profesorDAO.save(Mockito.any())).thenReturn(profesor);
        Exception exception = assertThrows(Exception.class, () -> {
            profesorService.registarProfesor(profesor);
        });
        assertEquals(exception.getMessage(), "Debe ingresar el apellido del profesor.");
    }
    @Test
    @DisplayName("Deberia fallar apellido vacio")
    void deberiaFallaApellidoVacio() throws Exception{
        ProfesorDTO profesorDTO = new ProfesorTestDataBuilder().conApellido("").build();
        Profesor profesor = profesorMapper.toEntity(profesorDTO);
        Mockito.when(generoDAO.findById(profesorDTO.getIdGenero())).thenReturn(Optional.of(new Genero()));
        Mockito.when(profesorDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(estudianteDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(profesorDAO.save(Mockito.any())).thenReturn(profesor);
        Exception exception = assertThrows(Exception.class, () -> {
            profesorService.registarProfesor(profesor);
        });
        assertEquals(exception.getMessage(), "Debe ingresar el apellido del profesor.");
    }
    @Test
    @DisplayName("Deberia fallar apellido caracteres")
    void deberiaFallaApellidoCaracteres() throws Exception{
        ProfesorDTO profesorDTO = new ProfesorTestDataBuilder().conApellido(TEXTO_MAS_CINCUENTA_CARACTERES).build();
        Profesor profesor = profesorMapper.toEntity(profesorDTO);
        Mockito.when(generoDAO.findById(profesorDTO.getIdGenero())).thenReturn(Optional.of(new Genero()));
        Mockito.when(profesorDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(estudianteDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(profesorDAO.save(Mockito.any())).thenReturn(profesor);
        Exception exception = assertThrows(Exception.class, () -> {
            profesorService.registarProfesor(profesor);
        });
        assertEquals(exception.getMessage(), "El apellido no debe contener más de 50 caracteres.");
    }
    @Test
    @DisplayName("Deberia fallar correo nulo")
    void deberiaFallaCorreoNulo() throws Exception{
        ProfesorDTO profesorDTO = new ProfesorTestDataBuilder().conCorreo(null).build();
        Profesor profesor = profesorMapper.toEntity(profesorDTO);
        Mockito.when(generoDAO.findById(profesorDTO.getIdGenero())).thenReturn(Optional.of(new Genero()));
        Mockito.when(profesorDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(estudianteDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(profesorDAO.save(Mockito.any())).thenReturn(profesor);
        Exception exception = assertThrows(Exception.class, () -> {
            profesorService.registarProfesor(profesor);
        });
        assertEquals(exception.getMessage(), "Debe ingresar el correo del profesor");
    }
    @Test
    @DisplayName("Deberia fallar correo vacio")
    void deberiaFallaCorreoVacio() throws Exception{
        ProfesorDTO profesorDTO = new ProfesorTestDataBuilder().conCorreo("").build();
        Profesor profesor = profesorMapper.toEntity(profesorDTO);
        Mockito.when(generoDAO.findById(profesorDTO.getIdGenero())).thenReturn(Optional.of(new Genero()));
        Mockito.when(profesorDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(estudianteDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(profesorDAO.save(Mockito.any())).thenReturn(profesor);
        Exception exception = assertThrows(Exception.class, () -> {
            profesorService.registarProfesor(profesor);
        });
        assertEquals(exception.getMessage(), "Debe ingresar el correo del profesor");
    }
    @Test
    @DisplayName("Deberia fallar correo caracteres")
    void deberiaFallaCorreoCaracteres() throws Exception{
        ProfesorDTO profesorDTO = new ProfesorTestDataBuilder().conCorreo(TEXTO_MAS_CINCUENTA_CARACTERES).build();
        Profesor profesor = profesorMapper.toEntity(profesorDTO);
        Mockito.when(generoDAO.findById(profesorDTO.getIdGenero())).thenReturn(Optional.of(new Genero()));
        Mockito.when(profesorDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(estudianteDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(profesorDAO.save(Mockito.any())).thenReturn(profesor);
        Exception exception = assertThrows(Exception.class, () -> {
            profesorService.registarProfesor(profesor);
        });
        assertEquals(exception.getMessage(), "El correo no debe contener más de 50 caracteres.");
    }
    @Test
    @DisplayName("Deberia fallar correo no valido")
    void deberiaFallaCorreonoValido() throws Exception{
        ProfesorDTO profesorDTO = new ProfesorTestDataBuilder().conCorreo("jhfadkjvsdlvjsdlñvsdvñjvl").build();
        Profesor profesor = profesorMapper.toEntity(profesorDTO);
        Mockito.when(generoDAO.findById(profesorDTO.getIdGenero())).thenReturn(Optional.of(new Genero()));
        Mockito.when(profesorDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(estudianteDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(profesorDAO.save(Mockito.any())).thenReturn(profesor);
        Exception exception = assertThrows(Exception.class, () -> {
            profesorService.registarProfesor(profesor);
        });
        assertEquals(exception.getMessage(), "Debe ingresar un correo válido.");
    }
    @Test
    @DisplayName("Deberia fallar foto caracteres")
    void deberiaFallaFotoCaracteres() throws Exception{
        ProfesorDTO profesorDTO = new ProfesorTestDataBuilder().conFoto(TEXTO_MAS_CINCUENTA_CARACTERES).build();
        Profesor profesor = profesorMapper.toEntity(profesorDTO);
        Mockito.when(generoDAO.findById(profesorDTO.getIdGenero())).thenReturn(Optional.of(new Genero()));
        Mockito.when(profesorDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(estudianteDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(profesorDAO.save(Mockito.any())).thenReturn(profesor);
        Exception exception = assertThrows(Exception.class, () -> {
            profesorService.registarProfesor(profesor);
        });
        assertEquals(exception.getMessage(), "El nombre de la foto es muy largo, se aceptan máximo 80 caracteres.");
    }
    @Test
    @DisplayName("Deberia fallar ususario creador nulo")
    void deberiaFallaUsuarioCreadorNulo() throws Exception{
        ProfesorDTO profesorDTO = new ProfesorTestDataBuilder().conUsuarioCreador(null).build();
        Profesor profesor = profesorMapper.toEntity(profesorDTO);
        Mockito.when(generoDAO.findById(profesorDTO.getIdGenero())).thenReturn(Optional.of(new Genero()));
        Mockito.when(profesorDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(estudianteDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(profesorDAO.save(Mockito.any())).thenReturn(profesor);
        Exception exception = assertThrows(Exception.class, () -> {
            profesorService.registarProfesor(profesor);
        });
        assertEquals(exception.getMessage(), "Debe ingresar el nombre del usuario creador.");
    }
    @Test
    @DisplayName("Deberia fallar ususario creador vacio")
    void deberiaFallaUsuarioCreadorvacio() throws Exception{
        ProfesorDTO profesorDTO = new ProfesorTestDataBuilder().conUsuarioCreador("").build();
        Profesor profesor = profesorMapper.toEntity(profesorDTO);
        Mockito.when(generoDAO.findById(profesorDTO.getIdGenero())).thenReturn(Optional.of(new Genero()));
        Mockito.when(profesorDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(estudianteDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(profesorDAO.save(Mockito.any())).thenReturn(profesor);
        Exception exception = assertThrows(Exception.class, () -> {
            profesorService.registarProfesor(profesor);
        });
        assertEquals(exception.getMessage(), "Debe ingresar el nombre del usuario creador.");
    }
    @Test
    @DisplayName("Deberia fallar ususario creador caracteres")
    void deberiaFallaUsuarioCreadorCaracteres() throws Exception{
        ProfesorDTO profesorDTO = new ProfesorTestDataBuilder().conUsuarioCreador(TEXTO_MAS_CINCUENTA_CARACTERES).build();
        Profesor profesor = profesorMapper.toEntity(profesorDTO);
        Mockito.when(generoDAO.findById(profesorDTO.getIdGenero())).thenReturn(Optional.of(new Genero()));
        Mockito.when(profesorDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(estudianteDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(profesorDAO.save(Mockito.any())).thenReturn(profesor);
        Exception exception = assertThrows(Exception.class, () -> {
            profesorService.registarProfesor(profesor);
        });
        assertEquals(exception.getMessage(), "El usuario creador no debe contener más de 50 caracteres.");
    }
    @Test
    @DisplayName("Deberia fallar fecha creacion null")
    void deberiaFallaFechaCreacionNull() throws Exception{
        ProfesorDTO profesorDTO = new ProfesorTestDataBuilder().conFechaCreacion(null).build();
        Profesor profesor = profesorMapper.toEntity(profesorDTO);
        Mockito.when(generoDAO.findById(profesorDTO.getIdGenero())).thenReturn(Optional.of(new Genero()));
        Mockito.when(profesorDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(estudianteDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(profesorDAO.save(Mockito.any())).thenReturn(profesor);
        Exception exception = assertThrows(Exception.class, () -> {
            profesorService.registarProfesor(profesor);
        });
        assertEquals(exception.getMessage(), "Debe ingresar la fecha de creación.");
    }
    @Test
    @DisplayName("Deberia fallar fecha futura")
    void deberiaFallaFechaFutura() throws Exception{
        ProfesorDTO profesorDTO = new ProfesorTestDataBuilder().conFechaCreacion(new Date(2500, 12, 12)).build();
        Profesor profesor = profesorMapper.toEntity(profesorDTO);
        Mockito.when(generoDAO.findById(profesorDTO.getIdGenero())).thenReturn(Optional.of(new Genero()));
        Mockito.when(profesorDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(estudianteDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(profesorDAO.save(Mockito.any())).thenReturn(profesor);
        Exception exception = assertThrows(Exception.class, () -> {
            profesorService.registarProfesor(profesor);
        });
        assertEquals(exception.getMessage(), "No se puede ingresar una fecha que aun no ha sucedido.");
    }
    @Test
    @DisplayName("Deberia fallar correo exiiste estu")
    void deberiaFallaCorreoExisteEstu() throws Exception{
        ProfesorDTO profesorDTO = new ProfesorTestDataBuilder().build();
        Profesor profesor = profesorMapper.toEntity(profesorDTO);
        Mockito.when(generoDAO.findById(profesorDTO.getIdGenero())).thenReturn(Optional.of(new Genero()));
        Mockito.when(profesorDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(estudianteDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(true);
        Mockito.when(profesorDAO.save(Mockito.any())).thenReturn(profesor);
        Exception exception = assertThrows(Exception.class, () -> {
            profesorService.registarProfesor(profesor);
        });
        assertEquals(exception.getMessage(), "El correo ya existe.");
    }
    @Test
    @DisplayName("Deberia fallar correo exiiste prof")
    void deberiaFallaCorreoExisteProf() throws Exception{
        ProfesorDTO profesorDTO = new ProfesorTestDataBuilder().build();
        Profesor profesor = profesorMapper.toEntity(profesorDTO);
        Mockito.when(generoDAO.findById(profesorDTO.getIdGenero())).thenReturn(Optional.of(new Genero()));
        Mockito.when(profesorDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(true);
        Mockito.when(estudianteDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(profesorDAO.save(Mockito.any())).thenReturn(profesor);
        Exception exception = assertThrows(Exception.class, () -> {
            profesorService.registarProfesor(profesor);
        });
        assertEquals(exception.getMessage(), "El correo ya existe.");
    }
}
