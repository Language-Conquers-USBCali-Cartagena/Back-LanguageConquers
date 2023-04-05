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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@SpringBootTest
public class ManejadorCrearAvatarTest {

    private static final String SE_REGISTRO_EL_AVATAR = "Se registro el avatar.";
    private static final String SE_DEBE_INGRESAR_UNA_DIRECCION_DE_IMAGEN_VALIDA = "Se debe ingresar una dirección de la imagen válida.";
    private static final String SE_DEBE_INGRESAR_UN_NOMBRE_DEL_AVATAR_VALIDO = "Se debe ingresar un nombre del avatar válido.";
    private static final String SE_DEBE_INGRESAR_UN_USUARIO_CREADOR_DEL_AVATAR_VALIDO = "Se debe ingresar un usuario creador del avatar válido.";
    private static final String SE_DEBE_INGRESAR_UNA_FECHA_VALIDA = "Se debe ingresar una fecha válida.";
    private static final String NO_SE_PUEDE_INGRESAR_UNA_FECHA_QUE_AUN_NO_HA_SUCEDIDO = "No puede ingresar una fecha que aun no ha sucedido.";
    private static final String IMG_Mala = "https://firebasestorage.googleapis.com/v0/b/languageconquers-740dc.appspot.com/o/articulos%2F59876.png?alt=media&token=6b647e03-62c5-4086-a2a2-6a8f291d5db6https://firebasestorage.googleapis.com/v0/b/languageconquers-740dc.appspot.com/o/articulos%2F59876.png?alt=media&token=6b647e03-62c5-4086-a2a2-6a8f291d5db6";
    private static final String NOMBRE_AVATAR_MALO = "SDHCKSDCKDSK CSKJKSDJCKL SCJKCSD DKS JAS SCAJ KSLKSDCJ";
    private static final String USUARIO_CREADOR_MALO = "KJEFD DFJKDJS SDJLFKJ SJDS CLJSD CJ DJSC LJ DSJF KJDS D";

    @Autowired
    AvatarService avatarService;

    @MockBean
    private AvatarDAO avatarDAO;

    @Autowired
    AvatarMapper avatarMapper;

    @Test
    @DisplayName("Deberia crear exitosamente el avatar")
    void deberiaCrearExitosamenteElavatar()throws Exception{

        AvatarDTO avatarDTO = new AvatarTestDataBuilder().build();
        Avatar avatar = avatarMapper.toEntity(avatarDTO);
        String creacionAvatar = avatarService.registrar(avatar);
        Mockito.when(avatarDAO.save(Mockito.any())).thenReturn(avatar);
        assertEquals(SE_REGISTRO_EL_AVATAR, creacionAvatar);
    }

    @Test
    @DisplayName("Deberia fallar por nombre avatar null")
    void deberiaFallarPorNombreAvatarNull()throws Exception{

        AvatarDTO avatarDTO = new AvatarTestDataBuilder().conImgAvatar(null).build();
        Avatar avatar = avatarMapper.toEntity(avatarDTO);
        Mockito.when(avatarDAO.save(Mockito.any())).thenReturn(avatar);
        Exception exception = assertThrows(Exception.class, ()->{
            avatarService.registrar(avatar);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UNA_DIRECCION_DE_IMAGEN_VALIDA);
    }

    @Test
    @DisplayName("Deberia fallar por nombre avatar vacio")
    void deberiaFallarPorNombreAvatarVacio()throws Exception{

        AvatarDTO avatarDTO = new AvatarTestDataBuilder().conImgAvatar("").build();
        Avatar avatar = avatarMapper.toEntity(avatarDTO);
        Mockito.when(avatarDAO.save(Mockito.any())).thenReturn(avatar);
        Exception exception = assertThrows(Exception.class, ()->{
            avatarService.registrar(avatar);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UNA_DIRECCION_DE_IMAGEN_VALIDA);
    }
    @Test
    @DisplayName("Deberia fallar por nombre avatar largo")
    void deberiaFallarPorNombreAvatarLargo()throws Exception{

        AvatarDTO avatarDTO = new AvatarTestDataBuilder().conImgAvatar(IMG_Mala).build();
        Avatar avatar = avatarMapper.toEntity(avatarDTO);
        Mockito.when(avatarDAO.save(Mockito.any())).thenReturn(avatar);
        Exception exception = assertThrows(Exception.class, ()->{
            avatarService.registrar(avatar);
        });
        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UNA_DIRECCION_DE_IMAGEN_VALIDA);
    }
}
