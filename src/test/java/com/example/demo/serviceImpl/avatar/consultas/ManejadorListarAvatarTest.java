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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ManejadorListarAvatarTest {

    @Autowired
    AvatarService avatarService;

    @MockBean
    private AvatarDAO avatarDAO;

    @Autowired
    AvatarMapper avatarMapper;

    @Test
    @DisplayName("Deberia listar avatares existentes")
    void deberiaListarAvataresExistentes()throws Exception{
        List<Avatar> avatarList = new ArrayList<>();
        AvatarDTO avatarDTO1 = new AvatarTestDataBuilder()
                .conIdAvatar(5L)
                .conNombreAvatar("Granjero")
                .conImgAvatar("granejro.png")
                .conUsuarioCreador("Angela")
                .conFechaCreacion(new Date())
                .build();
        Avatar avatar1 = avatarMapper.toEntity(avatarDTO1);
        avatarList.add(avatar1);
        AvatarDTO avatarDTO2 = new AvatarTestDataBuilder()
                .conIdAvatar(15L)
                .conNombreAvatar("Soldado")
                .conImgAvatar("soldado.png")
                .conUsuarioCreador("Angela")
                .conFechaCreacion(new Date())
                .build();
        Avatar avatar2 = avatarMapper.toEntity(avatarDTO2);
        avatarList.add(avatar2);
        Mockito.when(avatarDAO.findAll()).thenReturn(avatarList);
        List<Avatar> avatars = avatarService.findAll();
        assertEquals(avatarList, avatars);

    }

    @Test
    @DisplayName("Debería devolver una lista vacía cuando no hay avatares")
    void deberiaDevolverListaVaciaCuandoNoHayAvatares()throws Exception{
        Mockito.when(avatarDAO.findAll()).thenReturn(new ArrayList<>());
        List<Avatar> avatars =avatarService.findAll();
        assertTrue(avatars.isEmpty());
    }
}
