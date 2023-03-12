package com.example.demo.controller;

import com.example.demo.mapper.PalabraRetoMapper;
import com.example.demo.model.PalabraReto;
import com.example.demo.model.dto.PalabraRetoDTO;
import com.example.demo.service.PalabraRetoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/palabra-reto")
public class PalabraRetoController {
    @Autowired
    PalabraRetoMapper palabraRetoMapper;

    @Autowired
    PalabraRetoService palabraRetoService;

    @Operation(summary = "Este metodo permite listar todas las palabras reto")
    @GetMapping
    public ResponseEntity<List<PalabraRetoDTO>> listarPalabrasReto(){
        try {

            List<PalabraRetoDTO> palabraRetoDTOS = palabraRetoMapper.toDTOList(palabraRetoService.findAll());
            return new ResponseEntity<>(palabraRetoDTOS, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @Operation(summary = "Este metodo permite crear un reto palabra")
    @PostMapping
    public ResponseEntity<String> crearRetoPalabra(@RequestBody PalabraRetoDTO palabraRetoDTO){
        try {
            PalabraReto palabraReto = palabraRetoMapper.toEntity(palabraRetoDTO);
            return new ResponseEntity<>(palabraRetoService.crearPalabraReto(palabraReto), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @Operation(summary = "Este metodo permite editar un reto palabra")
    @PutMapping
    public ResponseEntity<String> editarRetoPalabra(@RequestBody PalabraRetoDTO palabraRetoDTO){
        try {
            PalabraReto palabraReto = palabraRetoMapper.toEntity(palabraRetoDTO);
            return new ResponseEntity<>(palabraRetoService.editarPalabraReto(palabraReto), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @Operation(summary = "Este metodo permite eliminar un reto palabra")
    @DeleteMapping
    public ResponseEntity<String> eliminarPalabraReto(@RequestParam Long idPalabraReto){
        try {
            return new ResponseEntity<>(palabraRetoService.eliminarPalabraReto(idPalabraReto), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
