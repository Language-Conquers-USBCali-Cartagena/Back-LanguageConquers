package com.example.demo.serviceImpl.programa.consultas;

import com.example.demo.dao.ProgramaDAO;
import com.example.demo.mapper.ProgramaMapper;
import com.example.demo.model.Programa;
import com.example.demo.model.dto.ProgramaDTO;
import com.example.demo.service.ProgramaService;
import com.example.demo.serviceImpl.programa.testDataBuilder.ProgramaTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@SpringBootTest
public class ManejadorCrearProgramaTest  {

    private static  final String SE_CREO_EXITOSAMENTE_EL_PROGRAMA = "Se creo exitosamente el programa.";
    private static  final String DEBE_INGRESAR_UN_NOMBRE_DEL_PROGRAMA_NO_SUPERIOR_A_50_CARACTERES = "Debe ingresar un nombre del programa no superior a 50 caracteres.";
    private static  final String SE_DEBE_INGRESAR_UN_NOMBRE_DEL_PROGRAMA = "Se debe ingresar un nombre del programa.";
    private static final String NOMBRE_PROGRAMA = "MNBVCXZLÑKJHGFDSAQWERTYUIOPÚHBGTRESDCVLONGTIFAZBGTQW";
    @Autowired
    ProgramaService programaService;

    @MockBean
    private ProgramaDAO programaDAO;
    @Autowired
    ProgramaMapper programaMapper;

    @Test
    @DisplayName("Deberia crear un programa")
    void deberiaCrearUnPrograma() throws Exception {

        ProgramaDTO programaDTO = new ProgramaTestDataBuilder().build();
        Programa programa = programaMapper.toEntity(programaDTO);
        String crearPrograma = programaService.registrar(programa);
        Mockito.when(programaDAO.save(Mockito.any())).thenReturn(programa);
        assertEquals(SE_CREO_EXITOSAMENTE_EL_PROGRAMA, crearPrograma);

    }

    @Test
    @DisplayName("Deberia lanzar un error nombre null")
    void deberiaFallarPorNombreNull() throws Exception {

        ProgramaDTO programaDTO = new ProgramaTestDataBuilder().conNombre(null).build();
        Programa programa = programaMapper.toEntity(programaDTO);
        Mockito.when(programaDAO.save(Mockito.any())).thenReturn(programa);
        Exception exception = assertThrows(Exception.class, ()->{
            programaService.registrar(programa);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UN_NOMBRE_DEL_PROGRAMA);

    }

    @Test
    @DisplayName("Deberia lanzar un error por longitud del atributo nombre")
    void deberiaFallarPorNombreProgramaLargo() throws Exception {

        ProgramaDTO programaDTO = new ProgramaTestDataBuilder().conNombre(NOMBRE_PROGRAMA).build();
        Programa programa = programaMapper.toEntity(programaDTO);
        Mockito.when(programaDAO.save(Mockito.any())).thenReturn(programa);
        Exception exception = assertThrows(Exception.class, ()->{
            programaService.registrar(programa);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UN_NOMBRE_DEL_PROGRAMA_NO_SUPERIOR_A_50_CARACTERES);

    }

    //todo: no se ha hecho
    @Test
    @DisplayName("Deberia lanzar un error por longitud del usuario creador")
    void deberiaFallarPorUsuarioCreadorLargo() throws Exception {

        ProgramaDTO programaDTO = new ProgramaTestDataBuilder().conNombre(NOMBRE_PROGRAMA).build();
        Programa programa = programaMapper.toEntity(programaDTO);
        Mockito.when(programaDAO.save(Mockito.any())).thenReturn(programa);
        Exception exception = assertThrows(Exception.class, ()->{
            programaService.registrar(programa);
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_UN_NOMBRE_DEL_PROGRAMA_NO_SUPERIOR_A_50_CARACTERES);

    }


}
