package com.example.demo.serviceImpl.avatar.consultas;

import com.example.demo.dao.AvatarDAO;
import com.example.demo.mapper.AvatarMapper;
import com.example.demo.model.Avatar;
import com.example.demo.model.dto.AvatarDTO;
import com.example.demo.service.AvatarService;
import com.example.demo.serviceImpl.avatar.testDataBuilder.AvatarTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
public class ManejadorListarPorIdAvatarTest {

    private static final String DEBE_INGRESAR_EL_ID_DE_UN_AVATAR = "Debe ingresar el id de un avatar.";
    private static final String EL_AVATAR_CON_ESE_ID_NO_EXISTE = "El avatar con ESE id no existe.";
    @Autowired
    AvatarService avatarService;

    @MockBean
    private AvatarDAO avatarDAO;

    @Autowired
    AvatarMapper avatarMapper;

    @Test
    @DisplayName("Deberia validar la existencia de un avatar")
    void deberiaValidarLaExistenciaDeUnAvatar()throws Exception{

        AvatarDTO avatarDTO = new AvatarTestDataBuilder().conIdAvatar(4343858L).build();

        Avatar avatar = avatarMapper.toEntity(avatarDTO);
        Mockito.when(avatarDAO.existsById(4343858L)).thenReturn(true);
        Mockito.when(avatarDAO.findById(avatarDTO.getIdAvatar())).thenReturn(Optional.of(avatar));
        Avatar avatarGenerado = avatarService.findById(avatar.getIdAvatar());
        assertNotNull(avatarGenerado);
        assertEquals(avatar.getIdAvatar(), avatarGenerado.getIdAvatar());

    }

    @Test
    @DisplayName("Debería lanzar una excepción si el avatar es nulo")
    void deberiaFallarSiIdAvatarEsNull()throws Exception{

        AvatarDTO avatarDTO  = new AvatarTestDataBuilder().conIdAvatar(null).build();
        Exception exception = assertThrows(Exception.class, () ->{
            avatarService.findById(avatarDTO.getIdAvatar());
        });
        assertEquals(exception.getMessage(), DEBE_INGRESAR_EL_ID_DE_UN_AVATAR);

    }

    @Test
    @DisplayName("Deberia lanzar una excepcion si el avatar no existe")
    void deberiaFallarSiIdAvatarNoExiste()throws Exception{

        AvatarDTO avatarDTO = new AvatarTestDataBuilder().conIdAvatar(45L).build();
        Exception exception = assertThrows(Exception.class, () ->{
            avatarService.findById(avatarDTO.getIdAvatar());
        });
        assertEquals(exception.getMessage(), EL_AVATAR_CON_ESE_ID_NO_EXISTE);

    }
}
