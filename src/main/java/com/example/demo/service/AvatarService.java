package com.example.demo.service;

import com.example.demo.model.Avatar;
import com.example.demo.model.dto.AvatarDTO;


import java.util.List;

public interface AvatarService{


    String registrar(Avatar avatar) throws Exception;
    String actualizar(AvatarDTO avatarDTO) throws Exception;
     void eliminar(Long idAvatar) throws Exception;
    List<Avatar> listar() throws Exception;
}