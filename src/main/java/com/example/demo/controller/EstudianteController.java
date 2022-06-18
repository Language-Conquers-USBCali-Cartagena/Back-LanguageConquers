package com.example.demo.controller;

import com.example.demo.mapper.EstudianteMapper;
import com.example.demo.model.dto.EstudianteDTO;
import com.example.demo.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estudiante")
public class EstudianteController {
    @Autowired
    private EstudianteService estudianteService;

    @Autowired
    private EstudianteMapper estudianteMapper;

    @PostMapping("/crearEstudiante")
    public ResponseEntity<String> crear(@RequestBody EstudianteDTO estudianteDTO){
        try {

            return new ResponseEntity<>(estudianteService.crearEstudiante(estudianteDTO), HttpStatus.CREATED);
        }catch (Exception e){
            String mensaje = e.getMessage();
            return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
        }
    }

}
