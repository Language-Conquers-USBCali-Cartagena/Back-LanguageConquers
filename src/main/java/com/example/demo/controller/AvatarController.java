package com.example.demo.controller;

import com.example.demo.mapper.AvatarMapper;
import com.example.demo.model.Avatar;
import com.example.demo.model.dto.AvatarDTO;
import com.example.demo.service.AvatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avatar")
public class AvatarController {

    @Autowired
    private AvatarService avatarService;

    @Autowired
    private AvatarMapper avatarMapper;

    @GetMapping
    public ResponseEntity<List<AvatarDTO>> listar(){
        List<Avatar> avatarList = avatarService.listar();
        List<AvatarDTO> avatarDTOS = avatarMapper.ToDTOList(avatarList);
        return ResponseEntity.ok().body(avatarDTOS);
    }

    @PostMapping("/guardarAvatar")
    public ResponseEntity<String> save(@RequestBody Avatar avatar){
        try {
            return new ResponseEntity(avatarService.registrar(avatar), HttpStatus.CREATED);
        }catch (Exception e){
            String mensaje = e.getMessage();
            return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
        }
    }



}
