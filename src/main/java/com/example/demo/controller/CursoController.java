package com.example.demo.controller;

import com.example.demo.mapper.CursoMapper;
import com.example.demo.model.Curso;
import com.example.demo.model.dto.CursoDTO;
import com.example.demo.service.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @Operation(summary = "Este metodo permite listar todos los cursos")
    @GetMapping
    public ResponseEntity<List<CursoDTO>> listaCursos(){
        try{
            List<Curso> cursos = cursoService.findAll();
            List<CursoDTO> cursoDTOS = cursoMapper.toDTOList(cursos);
            return new ResponseEntity<>(cursoDTOS, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @Operation(summary = "Este metodo perimite listar los cursos por correo estudiante")
    @GetMapping("/porCorreoEstudiante")
    public ResponseEntity<List<CursoDTO>> listarCursosPorCorreoEstudiante(@RequestParam String correoEstudiante){
        try{
            List<Curso> cursos = cursoService.findByCorreoEstudiante(correoEstudiante);
            List<CursoDTO> cursoDTOS = cursoMapper.toDTOList(cursos);
            return new ResponseEntity<>(cursoDTOS, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
