package com.example.demo.controller;

import com.example.demo.dao.ProfesorDAO;
import com.example.demo.mapper.ProfesorMapper;
import com.example.demo.model.Profesor;
import com.example.demo.model.dto.ProfesorDTO;
import com.example.demo.service.ProfesorService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profesor")
public class ProfesorController {
    @Autowired
    private ProfesorService profesorService;

    @Autowired
    private ProfesorMapper profesorMapper;

    @Operation(summary = "Este metodo permite listar los profesores")
    @GetMapping
    public ResponseEntity<List<ProfesorDTO>> listar(){
        try{

            List<Profesor> profesorList = profesorService.listar();
            List<ProfesorDTO> profesorDTOS = profesorMapper.toDTOList(profesorList);
            return new ResponseEntity<>(profesorDTOS, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @Operation(summary = "Este metodo permite crear un profesor" +
            ", No se debe de ingresar el usuario modificador y la fecha modificación")
    @PostMapping("/registrarProfesor")
    public ResponseEntity<String> registrar(@RequestBody ProfesorDTO profesorDTO){
        try {
            Profesor profesor = profesorMapper.toEntity(profesorDTO);
            return new ResponseEntity<>(profesorService.registarProfesor(profesor), HttpStatus.CREATED);
        }catch (Exception e){
            String mensaje = e.getMessage();
            return new ResponseEntity<>(mensaje,HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este metodo permite actualizar el profesor" +
            ", No se debe de ingresar el usuario creador y la fecha creación")
    @PutMapping("/actualizarProfesor")
    public ResponseEntity<String> modificar(@RequestBody ProfesorDTO profesorDTO){
        try{
            return new ResponseEntity<>(profesorService.actualizar(profesorDTO), HttpStatus.OK);
        }catch (Exception e){
            String mensaje = e.getMessage();
            System.out.println(mensaje);
            return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este metodo permite eliminar un profesor")
    @DeleteMapping("/eliminarProfesor")
    public ResponseEntity<?> eliminarProfesor(@RequestParam Long idProfesor){
        try {
            profesorService.eliminar(idProfesor);
            return ResponseEntity.ok("Se eliminó satisfactoriamente");
        } catch (Exception e) {
            String mensaje = e.getMessage();
            return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este metodo permite saber si el correo del profesor existe en la bd")
    @GetMapping("/existePorCorreo")
    public ResponseEntity<Boolean> existePorCorreo(@RequestParam String correo){
        try {

            return new ResponseEntity<>(profesorService.existePorCorreo(correo), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este metodo permite obtener un profesor por su correo")
    @GetMapping("/porCorreo")
    public ResponseEntity<ProfesorDTO> encontrarPorCorreo(@RequestParam String correo){
        try {
            ProfesorDTO profesorDTO = profesorMapper.toDTO(profesorService.findByCorreo(correo));
            return new ResponseEntity<>(profesorDTO, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
