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

    @Operation(summary = "Este método permite listar los programas registrados.")
    @GetMapping
    public ResponseEntity<List<ProgramaDTO>> listar(){
        try{
            List<Programa> programaList = programaService.listar();
            List<ProgramaDTO> programaDTOS = programaMapper.toDTOList(programaList);
            return ResponseEntity.ok().body(programaDTOS);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este método permite crear un programa" +
            ", No se debe de ingresar el usuario modificador y la fecha modificación.")
    @PostMapping("/guardarPrograma")
    public ResponseEntity<String> save(@RequestBody ProgramaDTO programaDTO){
        try {
            Programa programa = programaMapper.toEntity(programaDTO);
            return new ResponseEntity<>(programaService.registrar(programa), HttpStatus.CREATED);
        }catch (Exception e){
            String mensaje = e.getMessage();
            return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este método permite actualizar un programa" +
            ", No se debe de ingresar el usuario creador y la fecha creación.")
    @PutMapping("/actualizarPrograma")
    public ResponseEntity<String> modificar(@RequestBody ProgramaDTO programaDTO){
        try{
            return new ResponseEntity<>(programaService.actualizar(programaDTO), HttpStatus.OK);
        }catch (Exception e){
            String mensaje = e.getMessage();
            return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este método permite eliminar un programa.")
    @DeleteMapping("/eliminarPrograma/{id}")
    public ResponseEntity<String> eliminarPrograma(@PathVariable("id") Long idPrograma){
        try {
            return new ResponseEntity<>(programaService.eliminar(idPrograma), HttpStatus.OK);
        } catch (Exception e) {
            String mensaje = e.getMessage();
            return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este método permite buscar por id un programa.")
    @GetMapping("/porId/{id}")
    public ResponseEntity<ProgramaDTO> programaPorId (@PathVariable("id") Long idPrograma){
        try{
            ProgramaDTO programaDTO = programaMapper.toDTO(programaService.findById(idPrograma));
            return new ResponseEntity<>(programaDTO, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
