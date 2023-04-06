package com.example.demo.service.serviceImpl;

import com.example.demo.dao.AvatarDAO;
import com.example.demo.dao.EstudianteDAO;
import com.example.demo.model.Avatar;
import com.example.demo.model.dto.AvatarDTO;
import com.example.demo.service.AvatarService;
import com.example.demo.util.Validaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Scope("singleton")
@Service
public class AvatarServiceImpl implements AvatarService {

    @Autowired
    private AvatarDAO avatarDAO;

    @Autowired
    private EstudianteDAO estudianteDAO;


    @Override
    public String registrar(Avatar avatar) throws Exception {
        validacionesCrear(avatar);
        avatarDAO.save(avatar);
        return "Se registro el avatar.";
    }

    @Override
    public String actualizar(AvatarDTO avatarDTO) throws Exception {
        Avatar avatar =  null;
        validacionesActualizar(avatarDTO);
        avatar = avatarDAO.findById(avatarDTO.getIdAvatar()).orElse(null);
        avatar.setImgAvatar(avatarDTO.getImgAvatar());
        avatar.setNombreAvatar(avatarDTO.getNombreAvatar());
        avatar.setUsuarioModificador(avatarDTO.getUsuarioModificador());
        avatar.setFechaModificacion(avatarDTO.getFechaModificacion());
        avatarDAO.save(avatar);
        return "Se actualizo el avatar.";
    }

    @Override
    public String eliminar(Long idAvatar) throws Exception {
        if(idAvatar == null){
            throw new Exception("El id del avatar es obligatorio.");
        }
        if(!avatarDAO.existsById(idAvatar)){
            throw new Exception("No se encontró el avatar con ese id.");
        }
        if(!estudianteDAO.findByIdAvatar(idAvatar).isEmpty()){
            throw new Exception("No se puede eliminar el avatar porque esta siendo utilizado por un estudiante.");
        }
        avatarDAO.deleteById(idAvatar);
        return "El avatar fue eliminado exitosamente.";
    }

    @Override
    public List<Avatar> findAll() {
        return avatarDAO.findAll();
    }

    @Override
    public Avatar findById(Long idAvatar) throws Exception {

        if(idAvatar == null) {
             throw new Exception("Debe ingresar el id de un avatar.");
        }
        if(!avatarDAO.existsById(idAvatar)) {
          throw new Exception ("El avatar con ESE id no existe.");
        }
        return avatarDAO.findById(idAvatar).get();

    }

    @Override
    public Page<Avatar> findAllPage(Pageable pageable) throws Exception {
        Page<Avatar> avatarList = avatarDAO.findAll(pageable);
        if(avatarDAO.findAll().isEmpty()){
            throw new Exception("No hay avatares para mostrar.");
        }

        return avatarList;
    }

    @Override
    public int avataresRegistrados() throws Exception {
        int avataresRegistrados = avatarDAO.avataresRegistrados();
        return avataresRegistrados;
    }

    private void validacionesCrear(Avatar avatar) throws Exception {
        if(avatar.getImgAvatar() == null ||
                avatar.getImgAvatar().trim().equals("") ||
                Validaciones.isStringLenght(avatar.getImgAvatar(), 250)){
            throw new Exception("Se debe ingresar una dirección de la imagen válida.");
        }
        if(avatar.getNombreAvatar() == null ||
                avatar.getNombreAvatar().trim().equals("") ||
                Validaciones.isStringLenght(avatar.getNombreAvatar(), 50)){
            throw new Exception("Se debe ingresar un nombre del avatar válido.");
        }
        if(avatar.getUsuarioCreador() == null
                || avatar.getUsuarioCreador().trim().equals("")
                || Validaciones.isStringLenght(avatar.getUsuarioCreador(),50)){
            throw new Exception("Se debe ingresar un usuario creador del avatar válido.");
        }
        if(avatar.getFechaCreacion() == null
                || avatar.getFechaCreacion().toString().equals("")){
            throw new Exception("Se debe ingresar una fecha válida.");
        }
        /*Date fechaActual = new Date();
        if(avatar.getFechaCreacion().compareTo(fechaActual)>0){
            throw new Exception("No puede ingresar una fecha que aun no ha sucedido.");
        }*/
    }
    private void validacionesActualizar(AvatarDTO avatarDTO) throws Exception{
        if(avatarDTO.getIdAvatar() == null){
            throw new Exception("Debe ingresar el id del avatar que desea actualizar.");
        }
        if(!avatarDAO.existsById(avatarDTO.getIdAvatar())){
            throw new Exception("No existe el avatar con ese id.");
        }
        if(avatarDTO.getImgAvatar() == null ||
                avatarDTO.getImgAvatar().trim().equals("") ||
                Validaciones.isStringLenght(avatarDTO.getImgAvatar(), 250)){
            throw new Exception("Se debe ingresar una dirección de la imagen válida.");
        }
        if(avatarDTO.getNombreAvatar() == null ||
                avatarDTO.getNombreAvatar().trim().equals("") ||
                Validaciones.isStringLenght(avatarDTO.getNombreAvatar(), 50)){
            throw new Exception("Se debe ingresar un nombre del avatar válido.");
        }
        if(avatarDTO.getUsuarioModificador() == null
                || avatarDTO.getUsuarioModificador().trim().equals("")
                || Validaciones.isStringLenght(avatarDTO.getUsuarioModificador(),50)){
            throw new Exception("Se debe ingresar un usuario modificador del avatar válido.");
        }
        if(avatarDTO.getFechaModificacion() == null
                || avatarDTO.getFechaModificacion().toString().equals("")){
            throw new Exception("Se debe ingresar una fecha válida.");
        }
        /*
        Date fechaActual = new Date();
        if(avatarDTO.getFechaModificacion().compareTo(fechaActual)>0){
            throw new Exception("No puede ingresar una fecha que aun no ha sucedido.");
        }*/
    }
}
