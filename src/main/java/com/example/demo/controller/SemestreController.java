package com.example.demo.controller;

import com.example.demo.mapper.SemestreMapper;
import com.example.demo.model.Semestre;
import com.example.demo.model.dto.EstadoDTO;
import com.example.demo.model.dto.SemestreDTO;
import com.example.demo.service.SemestreService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/semestre")
public class SemestreController {

    @Autowired
    private SemestreService semestreService;

    @Autowired
    private SemestreMapper semestreMapper;

    @Operation(summary = "Este método permite listar los semestres.")
    @GetMapping
    public ResponseEntity<List<SemestreDTO>> listar(){
        try{
            List<Semestre> semestreList = semestreService.listar();
            List<SemestreDTO> semestreDTOList = semestreMapper.toDTOList(semestreList);
            return new ResponseEntity<>(semestreDTOList, HttpStatus.OK);
        }catch (Exception e){
            String mensaje = e.getMessage();
            return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este método permite crear un semestre" +
            ", No se debe de ingresar el usuario modificador y la fecha modificación.")
    @PostMapping("/guardarSemestre")
    public ResponseEntity<String> save(@RequestBody SemestreDTO semestreDTO){
        try {
            Semestre semestre = semestreMapper.toEntity(semestreDTO);
            return new ResponseEntity<>(semestreService.registrar(semestre), HttpStatus.CREATED);
        }catch (Exception e){
            String mensaje = e.getMessage();
            return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este método permite actualizar un semestre" +"\n"+
            ", No se debe de ingresar el usuario creador y la fecha creación.")
    @PutMapping("/actualizarSemestre")
    public ResponseEntity<String> modificar(@RequestBody SemestreDTO semestreDTO){
        try{
            Semestre semestre = semestreMapper.toEntity(semestreDTO);
            return new ResponseEntity<>(semestreService.actualizar(semestre), HttpStatus.OK);
        }catch (Exception e){
            String mensaje = e.getMessage();
            return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este método permite eliminar un semestre.")
    @DeleteMapping("/eliminarSemestre")
    public ResponseEntity<String> eliminarSemestre(@RequestParam Long idSemestre){
        try {
            return new ResponseEntity<>(semestreService.eliminar(idSemestre), HttpStatus.OK);
        } catch (Exception e) {
            String mensaje = e.getMessage();
            return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este método permite buscar por id un semestre.")
    @GetMapping("/porId/{id}")
    public ResponseEntity<SemestreDTO> semestrePorId (@PathVariable("id") Long idSemestre){
        try{
            SemestreDTO semestreDTO = semestreMapper.toDTO(semestreService.findById(idSemestre));
            return new ResponseEntity<>(semestreDTO, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
