package com.example.demo.controller;

import com.example.demo.mapper.CursoEstudianteMapper;
import com.example.demo.model.CursoEstudiante;
import com.example.demo.model.RetoEstudiante;
import com.example.demo.model.dto.CursoEstudianteDTO;
import com.example.demo.model.dto.RetoEstudianteDTO;
import com.example.demo.service.CursoEstudianteService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @Operation(summary = "Este metodo permite obtener el curso_estudiante por idEstudiante y por idCurso")
    @GetMapping("/porEstudianteyCurso")
    public ResponseEntity<CursoEstudianteDTO> porCursoYEstudiante(@RequestParam Long idCurso, @RequestParam Long idEstudiante){
        try {
            CursoEstudiante cursoEstudiante = cursoEstudianteService.findByIdEstudianteAndIdCurso(idCurso, idEstudiante);
            CursoEstudianteDTO cursoEstudianteDTO = cursoEstudianteMapper.toDTO(cursoEstudiante);
            return new ResponseEntity<>(cursoEstudianteDTO, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
