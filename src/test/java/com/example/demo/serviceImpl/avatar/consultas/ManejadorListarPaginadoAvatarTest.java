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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ManejadorListarPaginadoAvatarTest {

    @Autowired
    AvatarService avatarService;

    @MockBean
    private AvatarDAO avatarDAO;

    @Autowired
    AvatarMapper avatarMapper;

    @Test
    @DisplayName("Deberia listar los avatares paginados")
    void deberiaListarAvataresPaginados()throws Exception{
        Pageable pageable = Pageable.unpaged();
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
        Page<Avatar> page = new PageImpl<>(avatarList,pageable, avatarList.size());
        Mockito.when((avatarDAO.findAll(pageable))).thenReturn(page);
        Mockito.when(avatarDAO.findAll()).thenReturn(avatarList);
        Page<Avatar> result = avatarService.findAllPage((org.springframework.data.domain.Pageable) pageable);
        Mockito.verify(avatarDAO, Mockito.times(1)).findAll();
        assertEquals(avatarList, result.getContent());


    }
}
