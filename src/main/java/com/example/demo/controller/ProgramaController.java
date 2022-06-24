package com.example.demo.controller;

import com.example.demo.mapper.ProgramaMapper;
import com.example.demo.model.Programa;
import com.example.demo.model.dto.ProgramaDTO;
import com.example.demo.service.ProgramaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/programa")
public class ProgramaController {

    @Autowired
    private ProgramaService programaService;

    @Autowired
    private ProgramaMapper programaMapper;

    @Operation(summary = "Este metodo permite listar los programas registrados")
    @GetMapping
    public ResponseEntity<List<ProgramaDTO>> listar(){
        List<Programa> programaList = programaService.listar();
        List<ProgramaDTO> programaDTOS = programaMapper.toDTOList(programaList);
        return ResponseEntity.ok().body(programaDTOS);
    }

    @Operation(summary = "Este metodo permite crear un programa")
    @PostMapping("/guardarPrograma")
    public ResponseEntity<String> save(@RequestBody Programa programa){
        try {
            return new ResponseEntity(programaService.registrar(programa), HttpStatus.CREATED);
        }catch (Exception e){
            String mensaje = e.getMessage();
            return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este metodo permite actualizar un programa")
    @PutMapping("/actualizarPrograma")
    public ResponseEntity<Programa> modificar(@RequestBody ProgramaDTO programaDTO){
        try{
            return new ResponseEntity<>(programaService.actualizar(programaDTO), HttpStatus.OK);
        }catch (Exception e){
            String mensaje = e.getMessage();
            return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este metodo permite eliminar un programa")
    @DeleteMapping("/eliminarPrograma")
    public ResponseEntity<?> eliminarPrograma(@RequestParam Long idPrograma){
        try {
            programaService.eliminar(idPrograma);
            return ResponseEntity.ok("Se eliminó satisfactoriamente");
        } catch (Exception e) {
            String mensaje = e.getMessage();
            return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
        }
    }


}
