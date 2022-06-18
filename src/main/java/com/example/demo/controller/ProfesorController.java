package com.example.demo.controller;

import com.example.demo.mapper.ProfesorMapper;
import com.example.demo.model.dto.ProfesorDTO;
import com.example.demo.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profesor")
public class ProfesorController {
    @Autowired
    private ProfesorService profesorService;

    @Autowired
    private ProfesorMapper profesorMapper;

    @PostMapping("/registrarProfesor")
    public ResponseEntity<String> registrar(@RequestBody ProfesorDTO profesorDTO){
        try {
            return new ResponseEntity<>(profesorService.registarProfesor(profesorDTO), HttpStatus.CREATED);
        }catch (Exception e){
            String mensaje = e.getMessage();
            return new ResponseEntity<>(mensaje,HttpStatus.BAD_REQUEST);
        }
    }
}
