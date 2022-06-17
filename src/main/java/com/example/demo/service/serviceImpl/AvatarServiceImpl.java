package com.example.demo.service.serviceImpl;

import com.example.demo.dao.AvatarDAO;
import com.example.demo.model.Avatar;
import com.example.demo.model.dto.AvatarDTO;
import com.example.demo.service.AvatarService;
import com.example.demo.util.Validaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Scope("singleton")
@Service
public class AvatarServiceImpl implements AvatarService {

    @Autowired
    private AvatarDAO avatarDAO;


    @Override
    public Avatar registrar(Avatar avatar) throws Exception {

        if(avatar.getImgAvatar() == null ||
                avatar.getImgAvatar().trim().equals("") ||
                Validaciones.isStringLenght(avatar.getImgAvatar(), 80)){
            throw new Exception("Se debe ingresar una direccion de la imagen valida");
        }
        if(avatar.getNombreAvatar() == null ||
                avatar.getNombreAvatar().trim().equals("") ||
                Validaciones.isStringLenght(avatar.getNombreAvatar(), 50)){
            throw new Exception("Se debe ingresar un nombre del avatar valido");
        }
        if(avatar.getUsuarioCreador() == null
                || avatar.getUsuarioCreador().trim().equals("")
                || Validaciones.isStringLenght(avatar.getUsuarioCreador(),50)){
            throw new Exception("Se debe ingresar un usuario creador del avatar valido");
        }

        //TODO: Falta hacer validacion para la fecha

        if(avatar.getFechaCreacion() == null
                || avatar.getFechaCreacion().equals("")
                || Validaciones.isStringLenght(avatar.getUsuarioCreador(),50)){
            throw new Exception("Se debe ingresar una fecha valida");
        }

        avatar.setNombreAvatar(avatar.getNombreAvatar());
        avatar.setImgAvatar(avatar.getImgAvatar());
        avatar.setUsuarioCreador(avatar.getUsuarioCreador());
        avatar.setUsuarioModificador(avatar.getUsuarioModificador());
        avatar.setFechaModificacion(avatar.getFechaModificacion());
        return null;
    }

    @Override
    public Avatar actualizar(Avatar avatarDTO) throws Exception {
        Avatar avatar = null;
        if(avatarDTO.getIdAvatar() == null ){
            throw new Exception("Debe ingresar el id del avatar que desea actualizar");
        }
        if(!avatarDAO.existsById(avatarDTO.getIdAvatar())){
            throw new Exception("No existe el avatar con ese Id");
        }
        if(!Validaciones.validExtensionImg(avatar.getImgAvatar())){
            throw new Exception("Debe ingresar una extension valida para la imagen");
        }
        if(avatarDTO.getNombreAvatar() == null ||
                avatarDTO.getNombreAvatar().trim().equals("") ||
                Validaciones.isStringLenght(avatarDTO.getNombreAvatar(), 50)){
            throw new Exception("Se debe ingresar un nombre del avatar valido");
        }
        if(avatarDTO.getUsuarioCreador() == null
                || avatarDTO.getUsuarioCreador().trim().equals("")
                || Validaciones.isStringLenght(avatarDTO.getUsuarioCreador(),50)){
            throw new Exception("Se debe ingresar un usuario creador del avatar valido");
        }

        //TODO: Falta hacer validacion para la fecha

        if(avatarDTO.getFechaCreacion() == null
                || avatarDTO.getFechaCreacion().equals("")
                || Validaciones.isStringLenght(avatarDTO.getUsuarioCreador(),50)){
            throw new Exception("Se debe ingresar una fecha valida");
        }

        avatar.setImgAvatar(avatarDTO.getImgAvatar());
        avatar.setNombreAvatar(avatarDTO.getNombreAvatar());
        avatar.setUsuarioModificador(avatarDTO.getUsuarioModificador());
        avatar.setFechaModificacion(avatarDTO.getFechaModificacion());
        return avatarDAO.save(avatar);
    }

    @Override
    public void eliminar(Long id) {

    }

    @Override
    public Avatar listarPorId(Long id) {
        return null;
    }

    @Override
    public List<Avatar> listar() {
        return avatarDAO.findAll();
    }
}
