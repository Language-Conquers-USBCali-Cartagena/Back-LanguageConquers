package com.example.demo.service;

import com.example.demo.model.Avatar;
import com.example.demo.model.dto.AvatarDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface AvatarService{


    String registrar(Avatar avatar) throws Exception;
    String actualizar(AvatarDTO avatarDTO) throws Exception;
    String eliminar(Long idAvatar) throws Exception;
    List<Avatar> findAll() throws Exception;
    Avatar findById(Long idAvatar) throws Exception;

    Page<Avatar> findAllPage(Pageable pageable) throws Exception;
    int avataresRegistrados()throws Exception;
}