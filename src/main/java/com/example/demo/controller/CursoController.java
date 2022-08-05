package com.example.demo.controller;

import com.example.demo.mapper.CursoMapper;
import com.example.demo.model.Curso;
import com.example.demo.model.dto.CursoDTO;
import com.example.demo.service.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/curso")
public class CursoController {
    @Autowired
    CursoService cursoService;

    @Autowired
    CursoMapper cursoMapper;
    @Operation(summary = "Este metodo permite crear un curso")
    @PostMapping("/registrarCurso")
    public ResponseEntity<String> crearCueros(@RequestBody CursoDTO cursoDTO){
        try {
            String respuesta = cursoService.crearCurso(cursoMapper.toEntity(cursoDTO));
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
