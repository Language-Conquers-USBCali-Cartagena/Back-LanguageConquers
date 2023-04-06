package com.example.demo.serviceImpl.avatar.consultas;

import com.example.demo.dao.AvatarDAO;
import com.example.demo.dao.EstudianteDAO;
import com.example.demo.mapper.AvatarMapper;
import com.example.demo.model.Estudiante;
import com.example.demo.model.dto.AvatarDTO;
import com.example.demo.service.AvatarService;
import com.example.demo.serviceImpl.avatar.testDataBuilder.AvatarTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@SpringBootTest
public class ManejadorEliminarAvatarTest {
    private static final String EL_AVATAR_FUE_ELIMINADO_EXITOSAMENTE = "El avatar fue eliminado exitosamente.";
    private static final String EL_ID_DEL_AVATAR_ES_OBLIGATORIO = "El id del avatar es obligatorio.";
    private static final String NO_SE_ENCONTRO_EL_AVATAR_CON_ESE_ID = "No se encontró el avatar con ese id.";
    private static final String NO_SE_PUEDE_ELIMINAR_EL_AVATAR_POR_QUE_ESTA_SIENDO_USADO_POR_UN_ESTUDIANTE = "No se puede eliminar el avatar porque esta siendo utilizado por un estudiante.";

    @Autowired
    AvatarService avatarService;

    @MockBean
    private AvatarDAO avatarDAO;

    @MockBean
    private EstudianteDAO estudianteDAO;

    @Autowired
    AvatarMapper avatarMapper;

    @Test
    @DisplayName("Deberia eliminar exitosamente un avatar")
    void deberiaEliminarExitosamenteUnAvatar()throws Exception{

        AvatarDTO avatarDTO = new AvatarTestDataBuilder().conIdAvatar(3689L).build();
        Mockito.when(avatarDAO.existsById(avatarDTO.getIdAvatar())).thenReturn(true);
        Mockito.when(estudianteDAO.findByIdAvatar(avatarDTO.getIdAvatar())).thenReturn(Collections.emptyList());
        String eliminarAvatar = avatarService.eliminar(avatarDTO.getIdAvatar());
        Mockito.verify(avatarDAO, Mockito.times(1)).deleteById(avatarDTO.getIdAvatar());
        assertEquals(EL_AVATAR_FUE_ELIMINADO_EXITOSAMENTE, eliminarAvatar);
    }

    @Test
    @DisplayName("Deberia fallar por idAvatar null")
    void deberiaFallarPorIdAvatarNull()throws Exception{

        AvatarDTO avatarDTO = new AvatarTestDataBuilder().conIdAvatar(null).build();
        Exception exception = assertThrows(Exception.class, ()->{
            avatarService.eliminar(avatarDTO.getIdAvatar());
        });
        assertEquals(exception.getMessage(), EL_ID_DEL_AVATAR_ES_OBLIGATORIO);
    }

    @Test
    @DisplayName("Deberia fallar por idAvatar no existe")
    void deberiaFallarPorIdAvatarNoExiste()throws Exception{

        AvatarDTO avatarDTO = new AvatarTestDataBuilder().conIdAvatar(387478L).build();
        Mockito.when(avatarDAO.existsById(avatarDTO.getIdAvatar())).thenReturn(false);
        Exception exception = assertThrows(Exception.class, ()->{
            avatarService.eliminar(avatarDTO.getIdAvatar());
        });
        assertEquals(exception.getMessage(), NO_SE_ENCONTRO_EL_AVATAR_CON_ESE_ID);
    }

    @Test
    @DisplayName("Deberia fallar por idAvatar usado por un estudiante")
    void deberiaFallarPorIdAvatarUsadoEnEstudiante()throws Exception{

        AvatarDTO avatarDTO = new AvatarTestDataBuilder().conIdAvatar(387478L).build();
        Mockito.when(avatarDAO.existsById(avatarDTO.getIdAvatar())).thenReturn(true);
        Mockito.when(estudianteDAO.findByIdAvatar(avatarDTO.getIdAvatar())).thenReturn(Collections.singletonList(new Estudiante()));
        Exception exception = assertThrows(Exception.class, ()->{
            avatarService.eliminar(avatarDTO.getIdAvatar());
        });
        assertEquals(exception.getMessage(), NO_SE_PUEDE_ELIMINAR_EL_AVATAR_POR_QUE_ESTA_SIENDO_USADO_POR_UN_ESTUDIANTE);
    }

}
