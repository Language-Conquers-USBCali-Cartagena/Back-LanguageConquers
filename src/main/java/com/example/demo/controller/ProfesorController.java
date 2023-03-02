package com.example.demo.controller;

import com.example.demo.dao.ProfesorDAO;
import com.example.demo.mapper.ProfesorMapper;
import com.example.demo.model.Profesor;
import com.example.demo.model.dto.AvatarDTO;
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

    @Operation(summary = "Este método permite listar los profesores.")
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
    @Operation(summary = "Este método permite crear un profesor" +
            ", No se debe de ingresar el usuario modificador y la fecha modificación.")
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

    @Operation(summary = "Este método permite actualizar el profesor" +
            ", No se debe de ingresar el usuario creador y la fecha creación.")
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

    @Operation(summary = "Este método permite eliminar un profesor.")
    @DeleteMapping("/eliminarProfesor/{id}")
    public ResponseEntity<String> eliminarProfesor(@PathVariable("id") Long idProfesor){
        try {
            return new ResponseEntity<>(profesorService.eliminar(idProfesor), HttpStatus.OK);
        } catch (Exception e) {
            String mensaje = e.getMessage();
            return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este método permite saber si el correo del profesor esta registrado.")
    @GetMapping("/existePorCorreo")
    public ResponseEntity<Boolean> existePorCorreo(@RequestParam String correo){
        try {

            return new ResponseEntity<>(profesorService.existePorCorreo(correo), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este método permite buscar un profesor por su correo.")
    @GetMapping("/porCorreo")
    public ResponseEntity<ProfesorDTO> encontrarPorCorreo(@RequestParam String correo){
        try {
            ProfesorDTO profesorDTO = profesorMapper.toDTO(profesorService.findByCorreo(correo));
            return new ResponseEntity<>(profesorDTO, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este método permite buscar por id un profesor.")
    @GetMapping("/porId/{id}")
    public ResponseEntity<ProfesorDTO> profesorPorId (@PathVariable("id") Long idProfesor){
        try{
            ProfesorDTO profesorDTO = profesorMapper.toDTO(profesorService.findById(idProfesor));
            return new ResponseEntity<>(profesorDTO, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este método permite visualizar la cantidad de profesores que se encuentran registrados")
    @GetMapping("/cantidadProfesores")
    public ResponseEntity<Integer> totalProfesoresRegistrados() throws Exception {
        int cantidad = profesorService.cantidadProfesores();
        return new ResponseEntity<>(cantidad, HttpStatus.OK);
    }
}
