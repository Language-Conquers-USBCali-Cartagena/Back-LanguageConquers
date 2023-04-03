package com.example.demo.controller;

import com.example.demo.mapper.AdministradorMapper;
import com.example.demo.model.Administrador;
import com.example.demo.model.dto.AdministradorDTO;
import com.example.demo.service.AdministradorService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/administrador")
public class AdministradorController {

    @Autowired
    AdministradorService administradorService;

    @Autowired
    AdministradorMapper administradorMapper;

    @Operation(summary = "Este metodo permite obtener un administrador por correo")
    @GetMapping
    public ResponseEntity<AdministradorDTO> obtenerAdministradorPorCorreo(@RequestParam String correo){
        try {
            Administrador administrador = administradorService.getAdministradorPorCorreo(correo);
            AdministradorDTO administradorDTO = administradorMapper.toDTO(administrador);
            return new ResponseEntity<>(administradorDTO, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
