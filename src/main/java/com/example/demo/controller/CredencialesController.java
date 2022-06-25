package com.example.demo.controller;

import com.example.demo.mapper.CredencialesMapper;
import com.example.demo.model.Credenciales;
import com.example.demo.model.Estudiante;
import com.example.demo.model.dto.CredencialesDTO;
import com.example.demo.model.dto.EstudianteDTO;
import com.example.demo.service.CredencialesService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/credenciales")
public class CredencialesController {

    @Autowired
    private CredencialesService credencialesService;

    @Autowired
    private CredencialesMapper credencialesMapper;


    @Operation(summary = "Este metodo permite listar las credenciales")
    @GetMapping
    public ResponseEntity<List<CredencialesDTO>> listar(){
        try{

            List<Credenciales> credencialesList = credencialesService.listar();
            List<CredencialesDTO> credencialesDTOS = credencialesMapper.toDTOList(credencialesList);
            return new ResponseEntity<>(credencialesDTOS, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este metodo permite crear una credencial" +
            ", No se debe de ingresar el usuario modificador y la fecha modificación")
    @PostMapping("/crearCredencial")
    public ResponseEntity<String> crear(@RequestBody CredencialesDTO credencialesDTO){
        try {
            Credenciales credenciales =  credencialesMapper.toEntity(credencialesDTO);
            return new ResponseEntity<>(credencialesService.crear(credenciales), HttpStatus.CREATED);
        }catch (Exception e){
            String mensaje = e.getMessage();
            return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este metodo permite actualizar la credencial" +
            ", No se debe de ingresar el usuario creador y la fecha creación")
    @PutMapping("/actualizarCredencial")
    public ResponseEntity<String> modificar(@RequestBody CredencialesDTO credencialesDTO){
        try{
            return new ResponseEntity<>(credencialesService.actualizar(credencialesDTO), HttpStatus.OK);
        }catch (Exception e){
            String mensaje = e.getMessage();
            System.out.println(mensaje);
            return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Este metodo permite eliminar una credencial")
    @DeleteMapping("/eliminarCredencial")
    public ResponseEntity<?> eliminarCredencial(@RequestParam Long idCredencial){
        try {
            credencialesService.eliminar(idCredencial);
            return ResponseEntity.ok("Se eliminó satisfactoriamente");
        } catch (Exception e) {
            String mensaje = e.getMessage();
            return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
        }
    }

}
