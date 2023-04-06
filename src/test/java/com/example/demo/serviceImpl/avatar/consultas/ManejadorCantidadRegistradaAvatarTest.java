package com.example.demo.serviceImpl.avatar.consultas;

import com.example.demo.dao.AvatarDAO;
import com.example.demo.mapper.AvatarMapper;
import com.example.demo.service.AvatarService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class ManejadorCantidadRegistradaAvatarTest {

    @Autowired
    AvatarService avatarService;

    @MockBean
    private AvatarDAO avatarDAO;

    @Autowired
    AvatarMapper avatarMapper;

    @Test
    @DisplayName("Deberia retornar la cantidad de avatares registrados")
    void deberiaRetornarCantidadAvataresRegistrados() throws Exception {
        Mockito.when(avatarService.avataresRegistrados()).thenReturn(5);
        int result = avatarService.avataresRegistrados();
        assertEquals(5,result);
    }
}
