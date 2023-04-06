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

import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@SpringBootTest
public class ManejadorActualizarAvatarTest {

    private static final String SE_ACTUALIZO_EL_AVATAR = "Se actualizo el avatar.";
    private static final String SE_DEBE_INGRESAR_EL_ID_DEL_AVATAR_QUE_DESEA_ACTUALIZAR = "Debe ingresar el id del avatar que desea actualizar.";
    private static final String NO_EXISTE_EL_AVATAR_CON_ESE_ID = "No existe el avatar con ese id.";
    private static final String SE_DEBE_INGRESAR_UNA_DIRECCION_DE_IMAGEN_VALIDA = "Se debe ingresar una dirección de la imagen válida.";
    private static final String SE_DEBE_INGRESAR_UN_NOMBRE_DEL_AVATAR_VALIDO = "Se debe ingresar un nombre del avatar válido.";
    private static final String SE_DEBE_INGRESAR_UN_USUARIO_MODIFICADOR_DEL_AVATAR_VALIDO = "Se debe ingresar un usuario modificador del avatar válido.";
    private static final String SE_DEBE_INGRESAR_UNA_FECHA_VALIDA = "Se debe ingresar una fecha válida.";
    private static final String IMG_Mala = "https://firebasestorage.googleapis.com/v0/bfri7tytfiuufu/languageconquers-7402spot.com/o/articulos%2F59876.png?alt=media&token=6b647e03-62c5-4086-a2a2-6a8f291d5db6https://firebasestorage.googleapis.com/v0/b/languageconquers-740dc.appspot.com/o/articulos%";
    private static final String IMG = "https://firebasestorage.googleapis.com/v0/b/languageconquers-740dc.appspot.com/o/articulos%2F59876.png?alt=media&token=6b647e03-62c5-4086-a2a2-6a8f291d5db6https://firebasestorage.googleapis.com/v0/b/languageconquers-740dc.appspot.com/o/articulos%";
    private static final String NOMBRE_AVATAR = "Leñador";
    private static final String NOMBRE_AVATAR_MALO = "SDHCKSDCKDSK CSKJKSDJCKL SCJKCSD DKS JAS SCAJ KSLKSDCJ";
    private static final String USUARIO_MODIFICADOR_MALO = "KJEFD DFJKDJS SDJLFKJ SJDS CLJSD CJ DJSC LJ DSJF KJDS D";
    private static final String USUARIO_MODIFICADOR = "Angela";

    @Autowired
    AvatarService avatarService;

    @MockBean
    private AvatarDAO avatarDAO;

    @Autowired
    AvatarMapper avatarMapper;

    @Test
    @DisplayName("Deberia actualizar exitosamente un avatar")
    void deberiaActualizarAvatarExitosamente()throws Exception{

        AvatarDTO avatarDTO = new AvatarTestDataBuilder()
                .conNombreAvatar(NOMBRE_AVATAR)
                .conImgAvatar(IMG)
                .conUsuarioModificador(USUARIO_MODIFICADOR)
                .conFechaModificacion(new Date())
                .build();
        Avatar avatar = avatarMapper.toEntity(avatarDTO);
        Mockito.when(avatarDAO.existsById(avatar.getIdAvatar())).thenReturn(true);
        Mockito.when(avatarDAO.findById(avatar.getIdAvatar())).thenReturn(Optional.of(avatar));
        Mockito.when(avatarDAO.save(Mockito.any(Avatar.class))).thenReturn(avatar);
        String actualizarAvatar = avatarService.actualizar(avatarDTO);
        assertEquals(SE_ACTUALIZO_EL_AVATAR, actualizarAvatar);
    }

    @Test
    @DisplayName("Deberia fallar por idAvatar null")
    void deberiaFallarPorIdAvatarNull()throws Exception{

        AvatarDTO avatarDTO = new AvatarTestDataBuilder()
                .conIdAvatar(null)
                .build();
        Avatar avatar = avatarMapper.toEntity(avatarDTO);
        Mockito.when(avatarDAO.existsById(avatar.getIdAvatar())).thenReturn(false);
        Mockito.when(avatarDAO.save(Mockito.any(Avatar.class))).thenReturn(avatar);
        Exception exception = assertThrows(Exception.class, ()->{
            avatarService.actualizar(avatarDTO);
        });

        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_EL_ID_DEL_AVATAR_QUE_DESEA_ACTUALIZAR);
    }
    @Test
    @DisplayName("Deberia fallar por IdAvatar no existe")
    void deberiaFallarPorIdAvatarNoExiste()throws Exception{

        AvatarDTO avatarDTO = new AvatarTestDataBuilder()
                .conIdAvatar(4876L)
                .build();
        Avatar avatar = avatarMapper.toEntity(avatarDTO);
        Mockito.when(avatarDAO.existsById(avatar.getIdAvatar())).thenReturn(false);
        Mockito.when(avatarDAO.save(Mockito.any(Avatar.class))).thenReturn(avatar);
        Exception exception = assertThrows(Exception.class, ()->{
            avatarService.actualizar(avatarDTO);
        });

        assertEquals(exception.getMessage(), NO_EXISTE_EL_AVATAR_CON_ESE_ID);
    }
    @Test
    @DisplayName("Deberia fallar por Imagen null")
    void deberiaFallarPorImgNull()throws Exception{

        AvatarDTO avatarDTO = new AvatarTestDataBuilder()
                .conImgAvatar(null)
                .build();
        Avatar avatar = avatarMapper.toEntity(avatarDTO);
        Mockito.when(avatarDAO.existsById(avatar.getIdAvatar())).thenReturn(true);
        Mockito.when(avatarDAO.save(Mockito.any(Avatar.class))).thenReturn(avatar);
        Exception exception = assertThrows(Exception.class, ()->{
            avatarService.actualizar(avatarDTO);
        });

        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UNA_DIRECCION_DE_IMAGEN_VALIDA);
    }

    @Test
    @DisplayName("Deberia fallar por Imagen vacio")
    void deberiaFallarPorImgVacio()throws Exception{

        AvatarDTO avatarDTO = new AvatarTestDataBuilder()
                .conImgAvatar("")
                .build();
        Avatar avatar = avatarMapper.toEntity(avatarDTO);
        Mockito.when(avatarDAO.existsById(avatar.getIdAvatar())).thenReturn(true);
        Mockito.when(avatarDAO.save(Mockito.any(Avatar.class))).thenReturn(avatar);
        Exception exception = assertThrows(Exception.class, ()->{
            avatarService.actualizar(avatarDTO);
        });

        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UNA_DIRECCION_DE_IMAGEN_VALIDA);
    }
    @Test
    @DisplayName("Deberia fallar por Imagen largo")
    void deberiaFallarPorImgLargo()throws Exception{

        AvatarDTO avatarDTO = new AvatarTestDataBuilder()
                .conImgAvatar(IMG_Mala)
                .build();
        Avatar avatar = avatarMapper.toEntity(avatarDTO);
        Mockito.when(avatarDAO.existsById(avatar.getIdAvatar())).thenReturn(true);
        Mockito.when(avatarDAO.save(Mockito.any(Avatar.class))).thenReturn(avatar);
        Exception exception = assertThrows(Exception.class, ()->{
            avatarService.actualizar(avatarDTO);
        });

        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UNA_DIRECCION_DE_IMAGEN_VALIDA);
    }

    @Test
    @DisplayName("Deberia fallar por nombre avatar null")
    void deberiaFallarPorNombreAvatarNull()throws Exception{

        AvatarDTO avatarDTO = new AvatarTestDataBuilder()
                .conNombreAvatar(null)
                .build();
        Avatar avatar = avatarMapper.toEntity(avatarDTO);
        Mockito.when(avatarDAO.existsById(avatar.getIdAvatar())).thenReturn(true);
        Mockito.when(avatarDAO.save(Mockito.any(Avatar.class))).thenReturn(avatar);
        Exception exception = assertThrows(Exception.class, ()->{
            avatarService.actualizar(avatarDTO);
        });

        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UN_NOMBRE_DEL_AVATAR_VALIDO);
    }

    @Test
    @DisplayName("Deberia fallar por nombre avatar vacio")
    void deberiaFallarPorNombreAvatarVacio()throws Exception{

        AvatarDTO avatarDTO = new AvatarTestDataBuilder()
                .conNombreAvatar("")
                .build();
        Avatar avatar = avatarMapper.toEntity(avatarDTO);
        Mockito.when(avatarDAO.existsById(avatar.getIdAvatar())).thenReturn(true);
        Mockito.when(avatarDAO.save(Mockito.any(Avatar.class))).thenReturn(avatar);
        Exception exception = assertThrows(Exception.class, ()->{
            avatarService.actualizar(avatarDTO);
        });

        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UN_NOMBRE_DEL_AVATAR_VALIDO);
    }
    @Test
    @DisplayName("Deberia fallar por nombre avatar largo")
    void deberiaFallarPorNombreAvatarLargo()throws Exception{

        AvatarDTO avatarDTO = new AvatarTestDataBuilder()
                .conNombreAvatar(NOMBRE_AVATAR_MALO)
                .build();
        Avatar avatar = avatarMapper.toEntity(avatarDTO);
        Mockito.when(avatarDAO.existsById(avatar.getIdAvatar())).thenReturn(true);
        Mockito.when(avatarDAO.save(Mockito.any(Avatar.class))).thenReturn(avatar);
        Exception exception = assertThrows(Exception.class, ()->{
            avatarService.actualizar(avatarDTO);
        });

        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UN_NOMBRE_DEL_AVATAR_VALIDO);
    }

    @Test
    @DisplayName("Deberia fallar por usuario modificador null")
    void deberiaFallarPorUsuarioModificadorNull()throws Exception{

        AvatarDTO avatarDTO = new AvatarTestDataBuilder()
                .conUsuarioModificador(null)
                .build();
        Avatar avatar = avatarMapper.toEntity(avatarDTO);
        Mockito.when(avatarDAO.existsById(avatar.getIdAvatar())).thenReturn(true);
        Mockito.when(avatarDAO.save(Mockito.any(Avatar.class))).thenReturn(avatar);
        Exception exception = assertThrows(Exception.class, ()->{
            avatarService.actualizar(avatarDTO);
        });

        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UN_USUARIO_MODIFICADOR_DEL_AVATAR_VALIDO);
    }
    @Test
    @DisplayName("Deberia fallar por usuario modificador vacio")
    void deberiaFallarPorUsuarioModificadorVacio()throws Exception{

        AvatarDTO avatarDTO = new AvatarTestDataBuilder()
                .conUsuarioModificador("")
                .build();
        Avatar avatar = avatarMapper.toEntity(avatarDTO);
        Mockito.when(avatarDAO.existsById(avatar.getIdAvatar())).thenReturn(true);
        Mockito.when(avatarDAO.save(Mockito.any(Avatar.class))).thenReturn(avatar);
        Exception exception = assertThrows(Exception.class, ()->{
            avatarService.actualizar(avatarDTO);
        });

        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UN_USUARIO_MODIFICADOR_DEL_AVATAR_VALIDO);
    }

    @Test
    @DisplayName("Deberia fallar por usuario modificador largo")
    void deberiaFallarPorUsuarioModificadorLargo()throws Exception{

        AvatarDTO avatarDTO = new AvatarTestDataBuilder()
                .conUsuarioModificador(USUARIO_MODIFICADOR_MALO)
                .build();
        Avatar avatar = avatarMapper.toEntity(avatarDTO);
        Mockito.when(avatarDAO.existsById(avatar.getIdAvatar())).thenReturn(true);
        Mockito.when(avatarDAO.save(Mockito.any(Avatar.class))).thenReturn(avatar);
        Exception exception = assertThrows(Exception.class, ()->{
            avatarService.actualizar(avatarDTO);
        });

        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UN_USUARIO_MODIFICADOR_DEL_AVATAR_VALIDO);
    }
    @Test
    @DisplayName("Deberia fallar por usuario modificador vacio")
    void deberiaFallarPorFechaModificacionNull()throws Exception{

        AvatarDTO avatarDTO = new AvatarTestDataBuilder()
                .conFechaModificacion(null)
                .build();
        Avatar avatar = avatarMapper.toEntity(avatarDTO);
        Mockito.when(avatarDAO.existsById(avatar.getIdAvatar())).thenReturn(true);
        Mockito.when(avatarDAO.save(Mockito.any(Avatar.class))).thenReturn(avatar);
        Exception exception = assertThrows(Exception.class, ()->{
            avatarService.actualizar(avatarDTO);
        });

        assertEquals(exception.getMessage(), SE_DEBE_INGRESAR_UNA_FECHA_VALIDA);
    }
}
