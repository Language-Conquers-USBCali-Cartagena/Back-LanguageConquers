package com.example.demo.serviceImpl.profesor.consultas;

import com.example.demo.dao.CursoDAO;
import com.example.demo.dao.EstudianteDAO;
import com.example.demo.dao.GeneroDAO;
import com.example.demo.dao.ProfesorDAO;
import com.example.demo.mapper.ProfesorMapper;
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
public class ManejadorActualizarProfesorTest {
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
    @DisplayName("Deberia actualizar el profesro")
    void deberiaCrearElProfesor() throws Exception{
        ProfesorDTO profesorDTO = new ProfesorTestDataBuilder().build();
        Profesor profesor = profesorMapper.toEntity(profesorDTO);
        Mockito.when(generoDAO.findById(profesorDTO.getIdGenero())).thenReturn(Optional.of(new Genero()));
        Mockito.when(profesorDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(estudianteDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(generoDAO.existsById(profesorDTO.getIdGenero())).thenReturn(true);
        Mockito.when(profesorDAO.existsById(profesorDTO.getIdProfesor())).thenReturn(true);
        Mockito.when(profesorDAO.save(Mockito.any())).thenReturn(profesor);
        String resp = profesorService.actualizar(profesor);
        assertEquals(resp, "Se actualizo el profesor.");
    }

    @Test
    @DisplayName("Deberia fallar por genero nulo")
    void deberiaFallarPorGeneroNulo() throws Exception{
        ProfesorDTO profesorDTO = new ProfesorTestDataBuilder().conIdGenero(null).build();
        Profesor profesor = profesorMapper.toEntity(profesorDTO);
        Mockito.when(generoDAO.findById(profesorDTO.getIdGenero())).thenReturn(Optional.of(new Genero()));
        Mockito.when(profesorDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(estudianteDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(generoDAO.existsById(profesorDTO.getIdGenero())).thenReturn(true);
        Mockito.when(profesorDAO.existsById(profesorDTO.getIdProfesor())).thenReturn(true);
        Mockito.when(profesorDAO.save(Mockito.any())).thenReturn(profesor);
        Exception exception = assertThrows(Exception.class, () -> {
            profesorService.actualizar(profesor);
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
        Mockito.when(generoDAO.existsById(profesorDTO.getIdGenero())).thenReturn(true);
        Mockito.when(profesorDAO.existsById(profesorDTO.getIdProfesor())).thenReturn(true);
        Mockito.when(profesorDAO.save(Mockito.any())).thenReturn(profesor);
        Exception exception = assertThrows(Exception.class, () -> {
            profesorService.actualizar(profesor);
        });
        assertEquals(exception.getMessage(), "Debe ingresar un id genero válido.");
    }
    @Test
    @DisplayName("Deberia fallar nombre profesro nulo")
    void deberiaFallaNombreProfesorNulo() throws Exception{
        ProfesorDTO profesorDTO = new ProfesorTestDataBuilder().conNombre(null).build();
        Profesor profesor = profesorMapper.toEntity(profesorDTO);
        Mockito.when(generoDAO.findById(profesorDTO.getIdGenero())).thenReturn(Optional.of(new Genero()));
        Mockito.when(profesorDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(estudianteDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(generoDAO.existsById(profesorDTO.getIdGenero())).thenReturn(true);
        Mockito.when(profesorDAO.existsById(profesorDTO.getIdProfesor())).thenReturn(true);
        Mockito.when(profesorDAO.save(Mockito.any())).thenReturn(profesor);
        Exception exception = assertThrows(Exception.class, () -> {
            profesorService.actualizar(profesor);
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
        Mockito.when(generoDAO.existsById(profesorDTO.getIdGenero())).thenReturn(true);
        Mockito.when(profesorDAO.existsById(profesorDTO.getIdProfesor())).thenReturn(true);
        Mockito.when(profesorDAO.save(Mockito.any())).thenReturn(profesor);
        Exception exception = assertThrows(Exception.class, () -> {
            profesorService.actualizar(profesor);
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
        Mockito.when(generoDAO.existsById(profesorDTO.getIdGenero())).thenReturn(true);
        Mockito.when(profesorDAO.existsById(profesorDTO.getIdProfesor())).thenReturn(true);
        Mockito.when(profesorDAO.save(Mockito.any())).thenReturn(profesor);
        Exception exception = assertThrows(Exception.class, () -> {
            profesorService.actualizar(profesor);
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
        Mockito.when(generoDAO.existsById(profesorDTO.getIdGenero())).thenReturn(true);
        Mockito.when(profesorDAO.existsById(profesorDTO.getIdProfesor())).thenReturn(true);
        Mockito.when(profesorDAO.save(Mockito.any())).thenReturn(profesor);
        Exception exception = assertThrows(Exception.class, () -> {
            profesorService.actualizar(profesor);
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
        Mockito.when(generoDAO.existsById(profesorDTO.getIdGenero())).thenReturn(true);
        Mockito.when(profesorDAO.existsById(profesorDTO.getIdProfesor())).thenReturn(true);
        Mockito.when(profesorDAO.save(Mockito.any())).thenReturn(profesor);
        Exception exception = assertThrows(Exception.class, () -> {
            profesorService.actualizar(profesor);
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
        Mockito.when(generoDAO.existsById(profesorDTO.getIdGenero())).thenReturn(true);
        Mockito.when(profesorDAO.existsById(profesorDTO.getIdProfesor())).thenReturn(true);
        Mockito.when(profesorDAO.save(Mockito.any())).thenReturn(profesor);
        Exception exception = assertThrows(Exception.class, () -> {
            profesorService.actualizar(profesor);
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
        Mockito.when(generoDAO.existsById(profesorDTO.getIdGenero())).thenReturn(true);
        Mockito.when(profesorDAO.existsById(profesorDTO.getIdProfesor())).thenReturn(true);
        Mockito.when(profesorDAO.save(Mockito.any())).thenReturn(profesor);
        Exception exception = assertThrows(Exception.class, () -> {
            profesorService.actualizar(profesor);
        });
        assertEquals(exception.getMessage(), "Debe ingresar el correo del profesor.");
    }
    @Test
    @DisplayName("Deberia fallar correo vacio")
    void deberiaFallaCorreoVacio() throws Exception{
        ProfesorDTO profesorDTO = new ProfesorTestDataBuilder().conCorreo("").build();
        Profesor profesor = profesorMapper.toEntity(profesorDTO);
        Mockito.when(generoDAO.findById(profesorDTO.getIdGenero())).thenReturn(Optional.of(new Genero()));
        Mockito.when(profesorDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(estudianteDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(generoDAO.existsById(profesorDTO.getIdGenero())).thenReturn(true);
        Mockito.when(profesorDAO.existsById(profesorDTO.getIdProfesor())).thenReturn(true);
        Mockito.when(profesorDAO.save(Mockito.any())).thenReturn(profesor);
        Exception exception = assertThrows(Exception.class, () -> {
            profesorService.actualizar(profesor);
        });
        assertEquals(exception.getMessage(), "Debe ingresar el correo del profesor.");
    }
    @Test
    @DisplayName("Deberia fallar correo caracteres")
    void deberiaFallaCorreoCaracteres() throws Exception{
        ProfesorDTO profesorDTO = new ProfesorTestDataBuilder().conCorreo(TEXTO_MAS_CINCUENTA_CARACTERES).build();
        Profesor profesor = profesorMapper.toEntity(profesorDTO);
        Mockito.when(generoDAO.findById(profesorDTO.getIdGenero())).thenReturn(Optional.of(new Genero()));
        Mockito.when(profesorDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(estudianteDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(generoDAO.existsById(profesorDTO.getIdGenero())).thenReturn(true);
        Mockito.when(profesorDAO.existsById(profesorDTO.getIdProfesor())).thenReturn(true);
        Mockito.when(profesorDAO.save(Mockito.any())).thenReturn(profesor);
        Exception exception = assertThrows(Exception.class, () -> {
            profesorService.actualizar(profesor);
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
        Mockito.when(generoDAO.existsById(profesorDTO.getIdGenero())).thenReturn(true);
        Mockito.when(profesorDAO.existsById(profesorDTO.getIdProfesor())).thenReturn(true);
        Mockito.when(profesorDAO.save(Mockito.any())).thenReturn(profesor);
        Exception exception = assertThrows(Exception.class, () -> {
            profesorService.actualizar(profesor);
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
        Mockito.when(generoDAO.existsById(profesorDTO.getIdGenero())).thenReturn(true);
        Mockito.when(profesorDAO.existsById(profesorDTO.getIdProfesor())).thenReturn(true);
        Mockito.when(profesorDAO.save(Mockito.any())).thenReturn(profesor);
        Exception exception = assertThrows(Exception.class, () -> {
            profesorService.actualizar(profesor);
        });
        assertEquals(exception.getMessage(), "El nombre de la foto es muy largo, se aceptan máximo 80 caracteres.");
    }
    @Test
    @DisplayName("Deberia fallar ususario modificador nulo")
    void deberiaFallaUsuarioModificadorNulo() throws Exception{
        ProfesorDTO profesorDTO = new ProfesorTestDataBuilder().conUsuarioModificador(null).build();
        Profesor profesor = profesorMapper.toEntity(profesorDTO);
        Mockito.when(generoDAO.findById(profesorDTO.getIdGenero())).thenReturn(Optional.of(new Genero()));
        Mockito.when(profesorDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(estudianteDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(generoDAO.existsById(profesorDTO.getIdGenero())).thenReturn(true);
        Mockito.when(profesorDAO.existsById(profesorDTO.getIdProfesor())).thenReturn(true);
        Mockito.when(profesorDAO.save(Mockito.any())).thenReturn(profesor);
        Exception exception = assertThrows(Exception.class, () -> {
            profesorService.actualizar(profesor);
        });
        assertEquals(exception.getMessage(), "Debe ingresar el nombre del usuario modificador.");
    }
    @Test
    @DisplayName("Deberia fallar ususario modificador vacio")
    void deberiaFallaUsuarioModificadorvacio() throws Exception{
        ProfesorDTO profesorDTO = new ProfesorTestDataBuilder().conUsuarioModificador("").build();
        Profesor profesor = profesorMapper.toEntity(profesorDTO);
        Mockito.when(generoDAO.findById(profesorDTO.getIdGenero())).thenReturn(Optional.of(new Genero()));
        Mockito.when(profesorDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(estudianteDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(generoDAO.existsById(profesorDTO.getIdGenero())).thenReturn(true);
        Mockito.when(profesorDAO.existsById(profesorDTO.getIdProfesor())).thenReturn(true);
        Mockito.when(profesorDAO.save(Mockito.any())).thenReturn(profesor);
        Exception exception = assertThrows(Exception.class, () -> {
            profesorService.actualizar(profesor);
        });
        assertEquals(exception.getMessage(), "Debe ingresar el nombre del usuario modificador.");
    }
    @Test
    @DisplayName("Deberia fallar ususario modificador caracteres")
    void deberiaFallaUsuarioModificadorCaracteres() throws Exception{
        ProfesorDTO profesorDTO = new ProfesorTestDataBuilder().conUsuarioModificador(TEXTO_MAS_CINCUENTA_CARACTERES).build();
        Profesor profesor = profesorMapper.toEntity(profesorDTO);
        Mockito.when(generoDAO.findById(profesorDTO.getIdGenero())).thenReturn(Optional.of(new Genero()));
        Mockito.when(profesorDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(estudianteDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(generoDAO.existsById(profesorDTO.getIdGenero())).thenReturn(true);
        Mockito.when(profesorDAO.existsById(profesorDTO.getIdProfesor())).thenReturn(true);
        Mockito.when(profesorDAO.save(Mockito.any())).thenReturn(profesor);
        Exception exception = assertThrows(Exception.class, () -> {
            profesorService.actualizar(profesor);
        });
        assertEquals(exception.getMessage(), "El usuario modificador no debe contener más de 50 caracteres.");
    }
    @Test
    @DisplayName("Deberia fallar fecha modificacion null")
    void deberiaFallaFechaModificacionNull() throws Exception{
        ProfesorDTO profesorDTO = new ProfesorTestDataBuilder().conFechaModificacion(null).build();
        Profesor profesor = profesorMapper.toEntity(profesorDTO);
        Mockito.when(generoDAO.findById(profesorDTO.getIdGenero())).thenReturn(Optional.of(new Genero()));
        Mockito.when(profesorDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(estudianteDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(generoDAO.existsById(profesorDTO.getIdGenero())).thenReturn(true);
        Mockito.when(profesorDAO.existsById(profesorDTO.getIdProfesor())).thenReturn(true);
        Mockito.when(profesorDAO.save(Mockito.any())).thenReturn(profesor);
        Exception exception = assertThrows(Exception.class, () -> {
            profesorService.actualizar(profesor);
        });
        assertEquals(exception.getMessage(), "Debe ingresar la fecha de modificación.");
    }
    @Test
    @DisplayName("Deberia fallar fecha futura")
    void deberiaFallaFechaFutura() throws Exception{
        ProfesorDTO profesorDTO = new ProfesorTestDataBuilder().conFechaModificacion(new Date(2500, 12, 12)).build();
        Profesor profesor = profesorMapper.toEntity(profesorDTO);
        Mockito.when(generoDAO.findById(profesorDTO.getIdGenero())).thenReturn(Optional.of(new Genero()));
        Mockito.when(profesorDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(estudianteDAO.existsByCorreo(profesorDTO.getCorreo())).thenReturn(false);
        Mockito.when(generoDAO.existsById(profesorDTO.getIdGenero())).thenReturn(true);
        Mockito.when(profesorDAO.existsById(profesorDTO.getIdProfesor())).thenReturn(true);
        Mockito.when(profesorDAO.save(Mockito.any())).thenReturn(profesor);
        Exception exception = assertThrows(Exception.class, () -> {
            profesorService.actualizar(profesor);
        });
        assertEquals(exception.getMessage(), "No se puede ingresar una fecha que aun no ha sucedido.");
    }

}
