package com.example.demo.controller;

import com.example.demo.mapper.LogroEstudianteMapper;
import com.example.demo.model.LogroEstudiante;
import com.example.demo.model.dto.LogroEstudianteDTO;
import com.example.demo.service.LogroEstudianteService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logroEstudiante")
public class LogroEstudianteController {

    @Autowired
    LogroEstudianteService logroEstudianteService;

    @Autowired
    LogroEstudianteMapper logroEstudianteMapper;

    @Operation(summary = "Este método permite obtener logros por estudiante.")
    @GetMapping
    public ResponseEntity<List<LogroEstudianteDTO>> logrosEstudiantePorEstudiante(@RequestParam Long idestudiante){
        try{
            List<LogroEstudiante> logroEstudiantes = logroEstudianteService.findAllByIdEstudiante(idestudiante);
            List<LogroEstudianteDTO> logroEstudianteDTOList = logroEstudianteMapper.toDTOList(logroEstudiantes);
            return new ResponseEntity<>(logroEstudianteDTOList, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este método permite guardar un logro estudiante.")
    @PostMapping
    public ResponseEntity<String> guardarLogroEstudiante(@RequestBody LogroEstudianteDTO logroEstudianteDTO){
        try{
            LogroEstudiante logroEstudiante= logroEstudianteMapper.toEntity(logroEstudianteDTO);
            String mensaje = logroEstudianteService.save(logroEstudiante);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }
    @Operation(summary = "Este método permite actualizar un logro estudiante.")
    @PutMapping
    public ResponseEntity<String> actualizarLogroEstudiante(@RequestBody LogroEstudianteDTO logroEstudianteDTO){
        try{
            LogroEstudiante logroEstudiante = logroEstudianteMapper.toEntity(logroEstudianteDTO);
            String mensaje = logroEstudianteService.actualizar(logroEstudiante);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @Operation(summary = "Este método permite eliminar un logro estudiante.")
    @DeleteMapping
    public ResponseEntity<String> eliminarLogroEstudiante(@RequestParam Long idLogroEstudiante){
        try{
            String mensaje = logroEstudianteService.delete(idLogroEstudiante);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este método permite obtener el logro ahorrador.")
    @GetMapping("/ahorrador")
    public ResponseEntity<String> obtenerLogroAhorrador(@RequestParam Long idEstudiante){
        try{
            String mensaje = logroEstudianteService.logroAhorrador(idEstudiante);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @Operation(summary = "Este método permite obtener el logro comprador.")
    @GetMapping("/comprador")
    public ResponseEntity<String> obtenerLogroComprador(@RequestParam Long idEstudiante){
        try{
            String mensaje = logroEstudianteService.logroArticulos(idEstudiante);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este método permite obtener el logro perfeccionista.")
    @GetMapping("/perfeccionista")
    public ResponseEntity<String> obtenerLogroPerfeccionista(@RequestParam Long idEstudiante, @RequestParam Long idReto){
        try{
            String mensaje = logroEstudianteService.logroPerfeccionista(idEstudiante, idReto);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
