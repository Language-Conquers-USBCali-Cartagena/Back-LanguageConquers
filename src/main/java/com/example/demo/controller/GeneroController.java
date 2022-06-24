package com.example.demo.controller;

import com.example.demo.mapper.GeneroMapper;
import com.example.demo.model.Genero;
import com.example.demo.model.dto.GeneroDTO;
import com.example.demo.service.GeneroService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genero")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @Autowired
    private GeneroMapper generoMapper;

    @Operation(summary = "Este metodo permite listar los generos")
    @GetMapping
    public ResponseEntity<List<GeneroDTO>> listar(){
        List<Genero> generoList = generoService.listar();
        List<GeneroDTO> generoDTOS = generoMapper.ToDTOList(generoList);
        return ResponseEntity.ok().body(generoDTOS);
    }

    @Operation(summary = "Este metodo permite crear un genero")
    @PostMapping("/guardarGenero")
    public ResponseEntity<String> save(@RequestBody Genero genero){
        try {
            return new ResponseEntity(generoService.registrar(genero), HttpStatus.CREATED);
        }catch (Exception e){
            String mensaje = e.getMessage();
            return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este metodo permite actualizar un genero")
    @PutMapping("/actualizarGenero")
    public ResponseEntity<Genero> modificar(@RequestBody GeneroDTO generoDTO){
        try{
            return new ResponseEntity<>(generoService.actualizar(generoDTO), HttpStatus.OK);
        }catch (Exception e){
            String mensaje = e.getMessage();
            return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este metodo permite eliminar un genero")
    @DeleteMapping("/eliminarGenero")
    public ResponseEntity<?> eliminarGenero(@RequestParam Long idGenero){
        try {
            generoService.eliminar(idGenero);
            return ResponseEntity.ok("Se eliminó satisfactoriamente");
        } catch (Exception e) {
            String mensaje = e.getMessage();
            return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
        }
    }

}
