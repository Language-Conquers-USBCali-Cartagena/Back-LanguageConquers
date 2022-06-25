package com.example.demo.controller;

import com.example.demo.mapper.AvatarMapper;
import com.example.demo.model.Avatar;
import com.example.demo.model.dto.AvatarDTO;
import com.example.demo.service.AvatarService;
import io.swagger.v3.oas.annotations.Operation;
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


    @Operation(summary = "Este metodo permite listar los avatares")
    @GetMapping
    public ResponseEntity<List<AvatarDTO>> listar() throws Exception {
        try{
            List<Avatar> avatarList = avatarService.listar();
            List<AvatarDTO> avatarDTOS = avatarMapper.ToDTOList(avatarList);
            return new ResponseEntity<>(avatarDTOS, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este metodo permite guardar los avatares," +
            "No se debe de ingresar el usuario modificador y la fecha modificación")
    @PostMapping("/guardarAvatar")
    public ResponseEntity<String> save(@RequestBody AvatarDTO avatarDTO){
        try {
            Avatar avatar = avatarMapper.toEntity(avatarDTO);
            return new ResponseEntity<>(avatarService.registrar(avatar), HttpStatus.CREATED);
        }catch (Exception e){
            String mensaje = e.getMessage();
            return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este metodo permite actualizar los avatares" +
            ", No se debe de ingresar el usuario creador y la fecha creación")
    @PutMapping("/actualizarAvatar")
    public ResponseEntity<String> modificar(@RequestBody AvatarDTO avatarDTO){
        try{
            return new ResponseEntity<>(avatarService.actualizar(avatarDTO), HttpStatus.OK);
        }catch (Exception e){
            String mensaje = e.getMessage();
            System.out.println(mensaje);
            return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este metodo permite eliminar los avatares")
    @DeleteMapping("/eliminarAvatar")
    public ResponseEntity<?> eliminarAvatar(@RequestParam Long id){
        try {
            avatarService.eliminar(id);
            return ResponseEntity.ok("Se eliminó satisfactoriamente");
        } catch (Exception e) {
            String mensaje = e.getMessage();
            return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
        }
    }



}
