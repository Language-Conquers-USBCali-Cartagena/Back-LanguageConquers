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

    @Operation(summary = "Este método permite listar los géneros.")
    @GetMapping
    public ResponseEntity<List<GeneroDTO>> listar(){
        try{

            List<Genero> generoList = generoService.listar();
            List<GeneroDTO> generoDTOS = generoMapper.ToDTOList(generoList);
            return new ResponseEntity<>(generoDTOS, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este método permite crear un genero" +
            ", No se debe de ingresar el usuario modificador y la fecha modificación.")
    @PostMapping("/guardarGenero")
    public ResponseEntity<String> save(@RequestBody GeneroDTO generoDTO){
        try {
            Genero genero = generoMapper.toEntity(generoDTO);
            return new ResponseEntity<>(generoService.registrar(genero), HttpStatus.CREATED);
        }catch (Exception e){
            String mensaje = e.getMessage();
            return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este método permite actualizar un genero" +
            ", No se debe de ingresar el usuario creador y la fecha creación.")
    @PutMapping("/actualizarGenero")
    public ResponseEntity<String> modificar(@RequestBody GeneroDTO generoDTO){
        try{
            return new ResponseEntity<>(generoService.actualizar(generoDTO), HttpStatus.OK);
        }catch (Exception e){
            String mensaje = e.getMessage();
            return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este método permite eliminar un genero.")
    @DeleteMapping("/eliminarGenero")
    public ResponseEntity<String> eliminarGenero(@RequestParam Long idGenero){
        try {
            return new ResponseEntity<>(generoService.eliminar(idGenero), HttpStatus.OK);
        } catch (Exception e) {
            String mensaje = e.getMessage();
            return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
        }
    }

}
