package com.example.demo.controller;

import com.example.demo.mapper.CursoEstudianteMapper;
import com.example.demo.model.dto.CursoEstudianteDTO;
import com.example.demo.service.CursoEstudianteService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cursoEstudiante")
public class CursoEstudianteController {
    @Autowired
    CursoEstudianteService cursoEstudianteService;
    @Autowired
    CursoEstudianteMapper cursoEstudianteMapper;
    @Operation(summary = "Este método permite crear un curso estudiante.")
    @PostMapping("/crearCursoEstudiante")
    public ResponseEntity<String> crearCursoEstudiane(@RequestBody CursoEstudianteDTO cursoEstudianteDTO){
        try{
            String respuesta = cursoEstudianteService.crearCursoEstudiante(cursoEstudianteMapper.toEntity(cursoEstudianteDTO));
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
